package apricot
package tiled

import guarana.AbstractToolkit
import guarana.animation.*, ScriptDsl.{given, *}
import guarana.{?, nnn}
import guarana.util.cfor
import java.io.ByteArrayInputStream
import io.github.humbleui.skija.{Canvas, Image, Matrix33, Paint, Rect as SkRect, Surface}
import scala.collection.immutable.TreeMap
import scala.concurrent.duration.*

/** A Tiled Map implementation.
  *
  * The given `mapPath` will be used to parse map information, the referenced tilesets will also be loaded. Finally, the the described
  * layers will be configured in the engine, note that if the layer already exists, then it adds to it instead of recreating.
  */
class Map(val mapPath: Path, engine: ApricotEngine[? <: guarana.AbstractToolkit]) extends Scrollable {
  var pauseTileAnimations = false
  private var loadedTilesets = Predef.Map.empty[Path, Resource#Subscription[LoadedTileSet]]

  val mapResource = engine.resourceManager
    .get(mapPath)
    .head
    .subscribe(mapDefinitionSubscriber)

  private def relPath(p: String) = {
    val relPath = p.split("/").nnn
    Path(mapPath.segments.init ++ relPath)
  }

  class MapData private[Map] (val definition: MapDefinition, cachedTiles: util.CachedValue[TileAtlas]) {
    def tiles = cachedTiles.get
  }

  private object mapDefinitionSubscriber extends Resource.Subscriber[MapData] {
    // keep track of all the loaded renderables so that we can remove them on unload
    val loadedRenderablesPerLayer = collection.mutable.Map.empty[apricot.Layer, collection.mutable.Buffer[Renderable]]

    def onLoad(resource: Resource, content: Resource.Content): MapData = {
      val map = Parser.readTiledMap(new ByteArrayInputStream(content(0).content.asInstanceOf))
      val requiredTilesets = map.referencedTilesets.map(ts => relPath(ts.tileset))

      // we calculate the diff between the currently loaded tilesets and the referenced onces, because we want to avoid
      // unnecessary reloading of tilesets if possible. Most map changes after all are not going to be referenced tilesets.
      val toRemove = loadedTilesets.keySet -- requiredTilesets
      val toLoad = requiredTilesets.toSet -- loadedTilesets.keySet

      toRemove.foreach(p =>
        loadedTilesets.get(p).foreach(_.close())
        loadedTilesets = loadedTilesets - p
      )

      toLoad.foreach(subscribeToTileset)

      // Created a cached flat view of the tiles by flatMapping the tiles of all referenced tilesets, this is how Tiled references work.
      // Throwing a custom exception here is intentional so that the renderer can short circuit early if not everything was loaded. CachedValue
      // wont cache an exception.

      val tileAtlas = util.CachedValue(
        TileAtlas(
          map,
          map.referencedTilesets
            .map(ts =>
              val lts = loadedTilesets(relPath(ts.tileset))
              ts.tileset -> lts.content.getOrElse(throw NotAllTileSetsLoaded)
            )
            .toMap
        )
      )
      val tileLayers = map.layers.collect { case tl: TilesLayer => tl }
      for (tileLayer <- tileLayers) {
        val renderingLayer = engine.layers(tileLayer.name)
        val renderable = TileLayerRenderable(map, tileAtlas, tileLayer)
        renderingLayer.renderables += renderable
        loadedRenderablesPerLayer.getOrElseUpdate(renderingLayer, collection.mutable.Buffer.empty) += renderable
      }

      MapData(map, tileAtlas)
    }
    def onUnload(resource: Resource, mapData: MapData): Unit = {
      loadedRenderablesPerLayer.foreach((l, rs) => l.renderables --= rs)
      loadedRenderablesPerLayer.clear()
    }
  }

  private def subscribeToTileset(p: Path) = {
    val s = TilesetSubscriber()
    val subscription = engine.resourceManager.get(p).head.subscribe(s)
    loadedTilesets = loadedTilesets.updated(p, subscription)
  }
  private class TilesetSubscriber extends Resource.Subscriber[LoadedTileSet] {
    val loadedImages = collection.mutable.Map.empty[String, Resource#Subscription[Image]]
    var tilesetImage: Option[Resource#Subscription[Image]] = None
    def onLoad(resource: Resource, content: Resource.Content): LoadedTileSet = {
      val res = Parser.readTileSet(new ByteArrayInputStream(content(0).content.asInstanceOf))
      val imageResource = res.image.flatMap(i => engine.resourceManager.get(relPath(i.source)).headOption)
      tilesetImage = imageResource.map(_.subscribeAs[Resource.Type.Image]().map(_.head))

      def tileFor(tile: Int, customImage: Option[ImageDescriptor], collisionBoxes: IArray[geom.Rect.Int]): StaticTile = customImage match {
        case Some(customImage) =>
          val img = loadedImages.getOrElseUpdate(
            customImage.source,
            engine.resourceManager
              .get(relPath(customImage.source))
              .headOption
              .getOrElse(throw new IllegalStateException(s"Missing custom image $customImage"))
              .subscribeAs[Resource.Type.Image]()
              .map(_.head)
          )
          StaticTile(img, 0, 0, customImage.width, customImage.height, collisionBoxes)
        case _ =>
          StaticTile(
            tilesetImage.getOrElse(throw new IllegalStateException(s"Tileset $resource not found!")),
            tile % res.columns * res.tileWidth,
            tile / res.columns * res.tileHeight,
            res.tileWidth,
            res.tileHeight,
            collisionBoxes
          )
      }

      val tiles = IArray.tabulate(res.tileCount) { tile =>
        res.tileFeatures.get(tile) match
          case Some(TileFeatures(animation, _, colBoxes)) if animation.nonEmpty =>
            AnimatedTile(
              animation.view
                .map(a => tileFor(a.tile, res.tileFeatures.get(a.tile).flatMap(_.customImage), colBoxes) -> a.durationMs.millis)
                .to(IndexedSeq)
            )
          case Some(TileFeatures(_, customImage, colBoxes)) => tileFor(tile, customImage, colBoxes)
          case _ => tileFor(tile, None, IArray.empty)
      }
      tiles.iterator.collect { case a: AnimatedTile => a }.foreach(a => engine.scriptEngine.run(a.animation))
      LoadedTileSet(res, tiles)
    }

    def onUnload(resource: Resource, t: LoadedTileSet): Unit = {
      tilesetImage foreach (_.close())
      loadedImages.foreach(_._2.close())
      loadedImages.clear()
      t.tiles.iterator.collect { case a: AnimatedTile => a }.foreach(a => engine.scriptEngine.remove(a.animation))
    }
  }

  private class LoadedTileSet(val tileSet: TileSet, val tiles: IArray[Tile])

  sealed trait Tile {
    def width: Double
    def height: Double
    def collisionBoxes: IArray[geom.Rect.Int]
    private[Map] def renderTo(
        surface: Surface,
        canvas: Canvas,
        destX: Double,
        destY: Double,
        mapTileWidth: Double,
        mapTileHeight: Double
    ): Unit
  }
  private final case class StaticTile(
      image: Resource#Subscription[Image],
      sx: Double,
      sy: Double,
      width: Double,
      height: Double,
      collisionBoxes: IArray[geom.Rect.Int]
  ) extends Tile {
    def renderTo(surface: Surface, canvas: Canvas, destX: Double, destY: Double, mapTileWidth: Double, mapTileHeight: Double): Unit = {
      image.content.foreach { image =>
        val srcRect = SkRect.makeXYWH(sx.toFloat, sy.toFloat, width.toFloat, height.toFloat)
        val dstRect = SkRect.makeXYWH(destX.toFloat, (destY - height - mapTileHeight).toFloat, width.toFloat, height.toFloat)
        canvas.drawImageRect(image, srcRect, dstRect, null)
      }
    }
  }
  private final case class AnimatedTile(frames: IndexedSeq[(StaticTile, FiniteDuration)]) extends Tile {
    var currentTile: StaticTile = frames.head._1
    val animation: Script = script {
      var i = -1
      while ({ i = (i + 1) % frames.length; true }) {
        if (!pauseTileAnimations) {
          val f = frames(i)
          currentTile = f._1
          await(f._2)
        } else {
          i -= 1 //must undo the stepping
          `yield`
        }
      }
    }
    def width = currentTile.width
    def height = currentTile.height
    def collisionBoxes = currentTile.collisionBoxes

    def renderTo(surface: Surface, canvas: Canvas, destX: Double, destY: Double, mapTileWidth: Double, mapTileHeight: Double): Unit =
      currentTile.renderTo(surface, canvas, destX, destY, mapTileWidth, mapTileHeight)
  }

  private object NotAllTileSetsLoaded extends Exception

  class TileAtlas private[Map] (map: MapDefinition, tileSets: Predef.Map[String, LoadedTileSet]) {
    private val atlas =
      map.referencedTilesets.map(r => (r.firstGid - 1) -> tileSets(r.tileset)).sortBy(_._1).view.map(_._2.tiles).flatten.toArray
    def apply(index: Int): Tile = atlas(index)
  }

  private class TileLayerRenderable(
      map: MapDefinition,
      flattenedTiles: util.CachedValue[TileAtlas],
      tileLayer: TilesLayer,
  ) extends Renderable {
    def render(surface: Surface, canvas: Canvas): Unit = {
      //make sure we loaded all the tiles, if not, bail out early
      val tiles =
        try flattenedTiles.get
        catch case NotAllTileSetsLoaded => return ()

      val maxYTiles = (surface.getHeight / map.tileHeight + map.tileHeight).toInt
      val maxXTiles = (surface.getWidth / map.tileWidth + map.tileWidth).toInt

      cfor(0, _ < maxYTiles) { yOrd =>
        cfor(0, _ < maxXTiles) { xOrd =>
          val yTile = (offsetY / map.tileHeight - 2).max(0).toInt + yOrd
          val xTile = (offsetX / map.tileWidth - 2).max(0).toInt + xOrd

          val tileRef = tileLayer(xTile, yTile)
          if (!tileRef.isNullTile) {
            val y = yTile * map.tileHeight
            val x = xTile * map.tileWidth

            val tile = tiles(tileRef.tileIndex)
            val flipped = tileRef.isFlipped
            if (flipped) {
              canvas.save()
              val tileCenterX = x - tile.width / 2
              val tileCenterY = y - tile.height / 2 + map.tileHeight
              if (tileRef.isDiagonallyFlipped) canvas.concat(Matrix33.makeRotate(90, tileCenterX.toFloat, tileCenterY.toFloat))

              // doing a mirrored image consist of 3 transformations, first a translate to place the center of the scaling, then scaling, and finally undo the translation.
              canvas.translate((tileCenterX + tile.width - offsetX).toFloat, (tileCenterY + tile.height - offsetY).toFloat)
              canvas.scale(
                if (tileRef.isHorizontallyFlipped) -1.0f else 1.0f,
                if (tileRef.isVerticallyFlipped) -1.0f else 1.0f,
              )
              canvas.translate(-(tileCenterX + tile.width - offsetX).toFloat, -(tileCenterY + tile.height - offsetY).toFloat)
            }
            tile.renderTo(surface, canvas, x - offsetX, y - offsetY, map.tileWidth, map.tileHeight)
            if (flipped) canvas.restore()
          }

          xOrd + 1
        }
        yOrd + 1
      }
    }
  }
}
