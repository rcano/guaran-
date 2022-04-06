package apricot.tiled

import language.implicitConversions
import language.unsafeNulls

import apricot.geom.*
import guarana.unn
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.{Element, Node, NodeList}
import scala.collection.immutable.IntMap

object Parser {

  def readTiledMap(in: InputStream): MapDefinition = {
    val document = DocumentBuilderFactory.newInstance().unn.newDocumentBuilder().unn.parse(in)
    val map = document.getFirstChildByTagName("map").get.asElement

    val tilesets = map
      .getChildrenByTagName("tileset")
      .map(_.asElement)
      .map(ref => TilesetReference(ref.getAttribute("source"), ref.getAttribute("firstgid").toInt))

    val tileLayers = map.getChildrenByTagName("layer").map(_.asElement).map { layer =>
      val lines = layer.getFirstChildByTagName("data").get.asElement.getTextContent.unn.split("\n").filterNot(_.isEmpty)
      val tiles = lines.flatMap(l => l.split(",").map(_.toLong.asInstanceOf[TilesLayer.Tile]))
      val properties = layer
        .getElementsByTagName("properties")
        .iterator
        .collect {
          case e: Element if e.getTagName == "property" =>
            e.getAttribute("name") -> propertyValue(e.getAttribute("type"), e.getAttribute("value"))
        }
        .toMap
      TilesLayer(layer.getAttribute("name"), layer.getAttribute("width").toInt, layer.getAttribute("height").toInt, tiles, properties)
    }

    MapDefinition(
      width = map.getAttribute("width").toInt,
      height = map.getAttribute("height").toInt,
      tileWidth = map.getAttribute("tilewidth").toInt,
      tileHeight = map.getAttribute("tileheight").toInt,
      referencedTilesets = tilesets.toArray,
      layers = tileLayers.toArray
    )
  }

  def readTileSet(in: InputStream): TileSet = {
    val document = DocumentBuilderFactory.newInstance().unn.newDocumentBuilder().unn.parse(in)
    val tileset = document.getFirstChildByTagName("tileset").get
    val image = tileset.getFirstChildByTagName("image")

    val tileFeatures: IntMap[TileFeatures] = tileset
      .getChildrenByTagName("tile")
      .toArray
      .view
      .map { element =>
        val tileIdx = element.getAttribute("id").toInt
        val animation = element
          .getElementsByTagName("animation")
          .headOption
          .map(_.asElement.getElementsByTagName("frame").map { n =>
            val f = n.asElement
            TileFeatures.Frame(f.getAttribute("tileid").toInt, f.getAttribute("duration").toInt)
          })

        val customImage = element.getElementsByTagName("image").headOption.map { n =>
          val element = n.asElement
          ImageDescriptor(element.getAttribute("source"), element.getAttribute("width").toInt, element.getAttribute("height").toInt)
        }

        val collisionBoxes = element
          .getFirstChildByTagName("objectgroup")
          .iterator
          .flatMap(
            _.getChildrenByTagName("object").map(obj =>
              Rect.Int(
                obj.getAttribute("x").toFloat.round,
                obj.getAttribute("y").toFloat.round,
                obj.getAttribute("width").toFloat.round,
                obj.getAttribute("height").toFloat.round
              )
            )
          )
          .toArray

        tileIdx -> TileFeatures(animation getOrElse Seq.empty, customImage, collisionBoxes.asInstanceOf)
      }
      .to(IntMap)
      .filter(f => f._2.animation.nonEmpty || f._2.customImage.nonEmpty || f._2.collisionBoxes.nonEmpty)

    TileSet(
      tileset.getAttribute("name"),
      tileset.getAttribute("tilewidth").toInt,
      tileset.getAttribute("tileheight").toInt,
      tileset.getAttribute("tilecount").toInt,
      tileset.getAttribute("columns").toInt,
      image.map(image =>
        ImageDescriptor(image.getAttribute("source"), image.getAttribute("width").toInt, image.getAttribute("height").toInt)
      ),
      tileFeatures
    )
  }

  private def propertyValue(declaredType: String, value: String): Any = declaredType match {
    case "int" => value.toInt
    case "float" => value.toFloat
    case "string" => value
    case "file" => better.files.File(value)
    case "bool" => value.toBoolean
    case "color" => ???
  }

  implicit def nodeSeq2IndexableSeq(nd: NodeList): IndexedSeq[Node] = new IndexedSeq[Node] {
    def apply(i: Int) = nd.item(i).unn
    def length = nd.getLength
  }
  extension (n: Node) {
    def asElement = n.asInstanceOf[Element]
    def getChildrenByTagName(tagName: String) = n.getChildNodes.unn.iterator.collect { case e: Element if e.getTagName == tagName => e }
    def getFirstChildByTagName(tagName: String) = n.getChildNodes.unn.iterator.collectFirst {
      case e: Element if e.getTagName == tagName => e
    }
  }
}

case class MapDefinition(
    width: Int,
    height: Int,
    tileWidth: Int,
    tileHeight: Int,
    referencedTilesets: Array[TilesetReference],
    layers: Array[Layer]
)
case class TilesetReference(tileset: String, firstGid: Int)
sealed trait Layer {
  def name: String
  def width: Int
  def height: Int
}
final case class TilesLayer(name: String, width: Int, height: Int, tiles: Array[TilesLayer.Tile], properties: collection.Map[String, Any])
    extends Layer {
  inline final def apply(x: Int, y: Int): TilesLayer.Tile = tiles(y * width + x)
}

object TilesLayer {
  val FlippedHorizontallyFlag = 0x80000000
  val FlippedVerticallyFlag = 0x40000000
  val FlippedDiagonallyFlag = 0x20000000
  val TileMask = ~(FlippedHorizontallyFlag | FlippedVerticallyFlag | FlippedDiagonallyFlag)

  val NoTile = 0
  type NoTile = NoTile.type

  opaque type Tile = Long
  extension (t: Tile) {
    def isHorizontallyFlipped: Boolean = (t & FlippedHorizontallyFlag) > 0
    def isVerticallyFlipped: Boolean = (t & FlippedVerticallyFlag) > 0
    def isDiagonallyFlipped: Boolean = (t & FlippedDiagonallyFlag) > 0
    def isFlipped: Boolean = t.isHorizontallyFlipped | t.isVerticallyFlipped | t.isDiagonallyFlipped
    def isNullTile: Boolean = (t & TileMask).toInt == NoTile
    def tileIndex: Int = (t & TileMask).toInt - 1
  }
}

final case class TileSet(
    name: String,
    tileWidth: Int,
    tileHeight: Int,
    tileCount: Int,
    columns: Int,
    image: Option[ImageDescriptor],
    tileFeatures: IntMap[TileFeatures]
)
case class ImageDescriptor(source: String, width: Int, height: Int)
case class TileFeatures(animation: Seq[TileFeatures.Frame], customImage: Option[ImageDescriptor], collisionBoxes: IArray[Rect.Int])
object TileFeatures {
  case class Frame(tile: Int, durationMs: Int)
}
