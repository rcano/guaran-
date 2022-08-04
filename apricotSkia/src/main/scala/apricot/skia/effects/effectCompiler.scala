package apricot
package skia
package effects

import apricot.graphics.GraphicsStack
import com.github.blemale.scaffeine.{Cache, Scaffeine}
import guarana.*
import guarana.util.Anchor
import io.github.humbleui.skija
import scala.collection.immutable.SortedMap
import scala.compiletime.uninitialized
import scala.concurrent.duration._

trait Effect:
  def location: (Double, Double)
  def location_=(v: (Double, Double)): Unit
  def start(engine: ApricotEngine[? <: AbstractToolkit], layer: Layer): Unit
  def stop(): Unit

object EffectCompiler {

  def compile(particle: ParticleDescr): ParticleLike = {
    makeParticleLike(particle, collection.mutable.Map.empty)
  }
  private def makeParticleLike(particle: ParticleDescr, cache: collection.mutable.Map[ParticleCacheKey, skija.Image]): ParticleLike = {
    val instance = ParticleInstance(
      particle.image,
      particle.duration().toMillis,
      particle.positionCurve.?(_()),
      particle.blendModeCurve.?(_()),
      particle.colorFilterCurve.?(_()),
      particle.imageFilterCurve.?(_()),
      particle.shaders.?(_()),
      particle.blurCurve.?(_()),
      // particle.glowCurve.?(_()),
      particle.alphaCurve.?(_()),
      particle.transformationCurve.?(_()),
    )
    if (particle.cacheInfo != null) new CachedParticleInstance(instance, particle.cacheInfo.snapshots, particle.cacheInfo.insets, cache)
    else instance
  }
  def compile(emitter: ParticleEmitter, engine: ApricotEngine[? <: AbstractToolkit]): ParticleEmitterRenderer =
    ParticleEmitterRenderer(emitter, engine)
  def createEffect(p: ParticleLike | ParticleEmitterRenderer): Effect = new Effect {
    var location: (Double, Double) = (0, 0)
    var engine: ApricotEngine[? <: AbstractToolkit] | Null = null
    var layer: Layer | Null = null
    var effect: Updateable & Renderable = p
    def start(engine: ApricotEngine[? <: AbstractToolkit], layer: Layer): Unit = {
      stop()
      this.engine = engine
      this.layer = layer

      engine.updateables += effectUpdateable
      layer.renderables += effectUpdateable
    }
    def stop(): Unit = {
      if (engine != null) {
        val e = engine.nn
        e.updateables -= effectUpdateable
        layer.nn.renderables -= effectUpdateable
      }
      engine = null
      layer = null
    }

    object effectUpdateable extends Updateable, Renderable {

      def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit = effect.? { effect => effect.update(currentTimeNanos) }
      def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = effect.? { effect =>
        // canvas.save()
        // canvas.translate(location._1.toFloat, location._2.toFloat)
        // effect.render(surface, canvas)
        // canvas.restore()
      }
    }
  }
  def createEffect(effectDescr: ParticleDescr | ParticleEmitter): Effect = new Effect {
    var underlying: Effect | Null = null

    def start(engine: ApricotEngine[? <: AbstractToolkit], layer: Layer): Unit = {

      def getEffect() = effectDescr match {
        case d: ParticleDescr => createEffect(compile(d))
        case d: ParticleEmitter => createEffect(compile(d, engine))
      }
      val e = getEffect()
      underlying = e
      pendingActions.foreach(_(e))
      pendingActions = Nil
      e.start(engine, layer)
    }

    var pendingActions = List.empty[Effect => Unit]
    def location: (Double, Double) = underlying.nullFold(_.location, (0.0, 0.0))
    def location_=(v: (Double, Double)): Unit = underlying.nullFold(_.location = v, { pendingActions :+= (e => e.location = v) })
    def stop(): Unit = underlying.?(_.stop())
  }

  class ParticleEmitterRenderer private[EffectCompiler] (
      val emitterDescr: ParticleEmitter,
      val engine: ApricotEngine[? <: AbstractToolkit]
  ) extends Updateable,
        Renderable {

    private val particlesCache = collection.mutable.HashMap.empty[ParticleCacheKey, skija.Image]
    private val particleSpawners = emitterDescr.particles.map(Spawner(_))
    private val liveParticles = new collection.mutable.HashSet[ParticleHandler]()

    def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit = {
      liveParticles foreach (_.updateElapsed(elapsed))
      particleSpawners foreach (_.update(currentTimeNanos))
    }
    def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
      liveParticles foreach (_.render(graphicsStack, gContext))
    }

    private class ParticleHandler(val particleInstance: ParticleLike, onDead: (ParticleHandler) => Unit) {
      private var totalElapsedNanos = 0L
      private var startX = 0.0
      private var startY = 0.0
      def resetTo(startX: Double, startY: Double): Unit = {
        totalElapsedNanos = 0
        this.startX = startX
        this.startY = startY

      }
      def updateElapsed(elapsed: Long): Unit = {
        totalElapsedNanos += elapsed
        if (isDead) onDead(this)
      }
      def isDead: Boolean = particleInstance.durationInNanos < totalElapsedNanos
      def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
        particleInstance.setTimeAt(totalElapsedNanos)
        particleInstance.startX = startX
        particleInstance.startY = startY
        particleInstance.render(graphicsStack, gContext)
      }
      override val toString = s"Particle@${Integer.toHexString(hashCode)}[$particleInstance]"
    }
    private class Spawner(particle: ParticleDescr) extends Updateable {
      var currDeadline = 0L
      val handlersPool = new collection.mutable.Stack[ParticleHandler](32)

      val disposeParticle = (handler: ParticleHandler) => {
        handlersPool push handler
        val prevSize = liveParticles.size
        liveParticles -= handler
        // scribe.info(s"died, live particles = ${liveParticles.size}")
        ()
      }

      def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit = {
        while (currDeadline < currentTimeNanos) {
          currDeadline = nextDeadline(currDeadline)

          val handler = if (handlersPool.isEmpty) {
            ParticleHandler(makeParticleLike(particle, particlesCache), disposeParticle)
          } else {
            val handler = handlersPool.pop()
            handler.particleInstance.resetTo(
              durationInMillis = particle.duration().toMillis,
              positionCurve = particle.positionCurve.?(_()),
              blendMode = particle.blendModeCurve.?(_()),
              colorFilter = particle.colorFilterCurve.?(_()),
              imageFilter = particle.imageFilterCurve.?(_()),
              shaders = particle.shaders.?(_()),
              blur = particle.blurCurve.?(_()),
              alpha = particle.alphaCurve.?(_()),
              transformation = particle.transformationCurve.?(_()),
            )
            handler
          }
          val (x, y) = particle.startPositionOffset()
          handler.resetTo(x, y)
          liveParticles += handler
          // scribe.info(s"spawned!, live particles = ${liveParticles.size}")
        }
      }

      def nextDeadline(currentTimeNanos: Long): Long = {
        val delay = particle.delay()
        currentTimeNanos + delay.toNanos
      }
    }
  }

  trait ParticleLike extends Updateable, Renderable {

    /** The starting x position of the particle. If the positionCurve where to be empty, then this is the x position of the particle */
    var startX = 0.0

    /** The starting y position of the particle. If the positionCurve where to be empty, then this is the y position of the particle */
    var startY = 0.0
    def durationInMillis: Long
    def durationInNanos = durationInMillis * 1_000_000
    protected var totalElapsedNanos = 0L
    def isDead = totalElapsedNanos > durationInNanos
    def updateImpl(currentTimeNanos: Long, elapsed: Long): Unit = {
      if (isDead) // restart
        totalElapsedNanos = (totalElapsedNanos % totalElapsedNanos) + elapsed
      else
        totalElapsedNanos += elapsed
    }
    def setTimeAt(timeNanos: Long): Unit = totalElapsedNanos = timeNanos

    private[EffectCompiler] def resetTo(
        durationInMillis: Long,
        positionCurve: Curve[(Double, Double)] | Null = null,
        blendMode: Curve[skija.BlendMode] | Null,
        colorFilter: Curve[skija.ColorFilter] | Null,
        imageFilter: Curve[skija.ImageFilter] | Null,
        shaders: Curve[skija.Shader] | Null,
        blur: Curve[Double] | Null,
        alpha: Curve[Double] | Null,
        transformation: Curve[skija.Matrix33] | Null,
    ): Unit

    override val toString = s"ParticleLike@${Integer.toHexString(hashCode)}"

  }

  private class ParticleInstance(
      val particle: skija.Image,
      var durationInMillis: Long,
      var positionCurve: Curve[(Double, Double)] | Null = null,
      var blendMode: Curve[skija.BlendMode] | Null,
      var colorFilter: Curve[skija.ColorFilter] | Null,
      var imageFilter: Curve[skija.ImageFilter] | Null,
      var shaders: Curve[skija.Shader] | Null,
      var blur: Curve[Double] | Null,
      // var glow: Curve[Double] | Null,
      var alpha: Curve[Double] | Null,
      var transformation: Curve[skija.Matrix33] | Null,
  ) extends Updateable,
        Renderable,
        ParticleLike {
    val paint = new skija.Paint()
    def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
      val at = (totalElapsedNanos.toDouble / durationInNanos).min(1.0) // can't go beyond 1.0
      paint.reset()
      blendMode.?(c => paint.setBlendMode(c(at)))
      colorFilter.?(c => paint.setColorFilter(c(at)))
      imageFilter.?(c => paint.setImageFilter(c(at)))
      shaders.?(c => paint.setShader(c(at)))
      alpha.?(c => paint.setAlphaf(c(at).toFloat))

      //positioning must happen _before_ transformation is applied, otherwise it will be wonky
      // canvas.save()
      // val (x, y) = positionCurve.nullFold(_(at), (0.0, 0.0))
      // canvas.translate(startX.toFloat + x.toFloat, startY.toFloat + y.toFloat)

      // transformation.?(c =>
      //   val tr = skija.Matrix33
      //     .makeTranslate(particle.getWidth / 2.0f, particle.getHeight / 2.0f)
      //     .makeConcat(c(at))
      //     .makeConcat(skija.Matrix33.makeTranslate(-particle.getWidth / 2.0f, -particle.getHeight / 2.0f))
      //   canvas.concat(tr)
      // )
      // blur.?(c =>
      //   val blurStrenght = c(at)
      //   val maskFilter = skija.MaskFilter.makeBlur(skija.FilterBlurMode.NORMAL, blurStrenght.toFloat)
      //   paint.setMaskFilter(maskFilter)
      // )
      // canvas.drawImage(particle, 0, 0, paint)
      // canvas.restore()
    }

    private[EffectCompiler] def resetTo(
        durationInMillis: Long,
        positionCurve: Curve[(Double, Double)] | Null = null,
        blendMode: Curve[skija.BlendMode] | Null,
        colorFilter: Curve[skija.ColorFilter] | Null,
        imageFilter: Curve[skija.ImageFilter] | Null,
        shaders: Curve[skija.Shader] | Null,
        blur: Curve[Double] | Null,
        alpha: Curve[Double] | Null,
        transformation: Curve[skija.Matrix33] | Null,
    ): Unit = {
      this.durationInMillis = durationInMillis
      this.positionCurve = positionCurve
      this.blendMode = blendMode
      this.colorFilter = colorFilter
      this.imageFilter = imageFilter
      this.shaders = shaders
      this.blur = blur
      this.alpha = alpha
      this.transformation = transformation
    }
  }

  private type ParticleCacheKey = (
      skija.Image,
      Curve[skija.ColorFilter] | Null,
      Curve[skija.ImageFilter] | Null,
      Curve[skija.Shader] | Null,
      Curve[Double] | Null, // blur
      Curve[Double] | Null, // alpha
      Int // snapshot index
  )

  private class CachedParticleInstance(
      val underlying: ParticleInstance,
      val snapshots: Int,
      val insets: Insets,
      val particlesCache: collection.mutable.Map[ParticleCacheKey, skija.Image]
  ) extends Updateable,
        Renderable,
        ParticleLike {
    def durationInMillis = underlying.durationInMillis
    def snapshotTimeNanos = durationInNanos / snapshots
    // in order to render, the underlying particle must be cached untransformed, and we'll transform when rendering the cached image
    private var positionCurve = underlying.positionCurve
    private var particleTransformation = underlying.transformation
    private var blendModeCurve = underlying.blendMode
    underlying.positionCurve = null
    underlying.transformation = null
    underlying.blendMode = null

    val offscreenCanvas = skija.Surface.makeRasterN32Premul(
      (underlying.particle.getWidth + insets.left + insets.right).ceil.toInt,
      (underlying.particle.getHeight + insets.top + insets.bot).ceil.toInt,
    )

    val canvas = offscreenCanvas.getCanvas
    canvas.translate(insets.left.toFloat, insets.top.toFloat)

    def takeSnapshot(ord: Int): skija.Image = {
      canvas.clear(0x00000000)
      underlying.setTimeAt(snapshotTimeNanos * ord)
      // underlying.render(offscreenCanvas, canvas)
      offscreenCanvas.makeImageSnapshot().nn
    }

    val paint = skija.Paint()
    def render(graphicsStack: GraphicsStack, gContext: graphicsStack.GraphicsContext): Unit = {
      val at = (totalElapsedNanos.toDouble / durationInNanos).min(1.0)
      val ord = (totalElapsedNanos.toDouble / snapshotTimeNanos).toInt.min(1)

      val image = particlesCache.getOrElseUpdate(
        (underlying.particle, underlying.colorFilter, underlying.imageFilter, underlying.shaders, underlying.blur, underlying.alpha, ord),
        takeSnapshot(ord)
      )

      canvas.save()

      //positioning must happen _before_ transformation is applied, otherwise it will be wonky
      val (x, y) = positionCurve.nullFold(_(at), (0.0, 0.0))
      canvas.translate(startX.toFloat + x.toFloat - insets.left.toFloat, startY.toFloat + y.toFloat - insets.top.toFloat)

      particleTransformation.?(c =>
        val tr = skija.Matrix33
          .makeTranslate(image.getWidth / 2.0f, image.getHeight / 2.0f)
          .makeConcat(c(at))
          .makeConcat(skija.Matrix33.makeTranslate(-image.getWidth / 2.0f, -image.getHeight / 2.0f))
        canvas.concat(tr)
      )
      blendModeCurve.?(c => paint.setBlendMode(c(at)))
      canvas.drawImage(image, 0, 0, paint)
      canvas.restore()
      // canvas.drawRect(skija.Rect.makeWH(image.getWidth, image.getHeight), p)
      // canvas.drawRect(skija.Rect.makeXYWH(image.getWidth / 2.0f - 2.5f, image.getHeight / 2.0f - 2.5f, 5, 5), p)
    }

    private[EffectCompiler] def resetTo(
        durationInMillis: Long,
        positionCurve: Curve[(Double, Double)] | Null = null,
        blendMode: Curve[skija.BlendMode] | Null,
        colorFilter: Curve[skija.ColorFilter] | Null,
        imageFilter: Curve[skija.ImageFilter] | Null,
        shaders: Curve[skija.Shader] | Null,
        blur: Curve[Double] | Null,
        alpha: Curve[Double] | Null,
        transformation: Curve[skija.Matrix33] | Null,
    ): Unit = {
      underlying.resetTo(durationInMillis, null, null, colorFilter, imageFilter, shaders, blur, alpha, null)
      this.positionCurve = positionCurve
      this.blendModeCurve = blendMode
      this.particleTransformation = transformation
    }
  }
}
