package guarana
package web

import org.scalajs.dom
import scala.annotation.{compileTimeOnly, experimental}

import scalajs.js

// these implicits are here to simplify defining the style vars, inside of these the implicits can apply
object StyleConversions
inline implicit def stoi(using StyleConversions.type)(s: String): Int = collection.StringOps(s).toInt
inline implicit def stod(using StyleConversions.type)(s: String): Double = collection.StringOps(s).toDouble
inline implicit def itos(using StyleConversions.type)(i: Int): String = i.toString()
inline implicit def dtos(using StyleConversions.type)(d: Double): String = d.toString()
inline implicit def stylableSizeToString(using StyleConversions.type)(s: StylableSize): String = s.unwrap

inline def StyleVar[T](
    propName: String,
    inline getter: StyleConversions.type ?=> dom.CSSStyleDeclaration => T,
    inline setter: StyleConversions.type ?=> (dom.CSSStyleDeclaration, T) => Unit
): ExternalVar.Aux[HtmlElement, T] = {
  given StyleConversions.type = StyleConversions
  ExternalVar[HtmlElement, T](
    propName,
    v => getter(v.unwrap.style),
    (v, t) => setter(v.unwrap.style, t),
    eagerEvaluation = true,
  )
}

// definition of Vars for style operations. You don't typically use this vars as they are provided via extension methods and Modifiers
object StyleVars {

  type PossibleDisplays = "inline" | "block" | "contents" | "flex" | "grid" | "inline-block" | "inline-flex" | "inline-grid" |
    "inline-table" | "list-item" | "run-in" | "table" | "table-caption" | "table-column-group" | "table-header-group" | "table-footer-group" | "table-row-group" |
    "table-cell" | "table-column" | "table-row" | "none" | "initial" | "inherit"

  /** The all shorthand CSS property resets all of an element's properties except unicode-bidi, direction, and CSS Custom Properties. It can
    * set properties to their initial or inherited values, or to the values specified in another stylesheet origin.
    *
    * Note: IE does not support this property
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/all
    */
  lazy val All = {
    given StyleConversions.type = StyleConversions
    ExternalVar[HtmlElement, String](
      "all",
      v => v.unwrap.style.toString,
      (v, t) => v.unwrap.style = t,
      eagerEvaluation = true,
    )
  }

  /** The animation CSS property is a shorthand property for animation-name, animation-duration, animation-timing-function, animation-delay,
    * animation-iteration-count and animation-direction.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation
    */
  lazy val Animation = StyleVar[String]("animation", _.animation, _.animation = _)

  /** The animation-delay CSS property specifies when the animation should start. This lets the animation sequence begin some time after
    * it's applied to an element.
    *
    * A value of 0s, which is the default value of the property, indicates that the animation should begin as soon as it's applied.
    * Otherwise, the value specifies an offset from the moment the animation is applied to the element; animation will begin that amount of
    * time after being applied.
    *
    * Specifying a negative value for the animation delay causes the animation to begin executing immediately. However, it will appear to
    * have begun executing partway through its cycle. For example, if you specify -1s as the animation delay time, the animation will begin
    * immediately but will start 1 second into the animation sequence.
    *
    * If you specify a negative value for the animation delay, but the starting value is implicit, the starting value is taken from the
    * moment the animation is applied to the element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-delay
    */
  lazy val AnimationDelay = StyleVar[String]("animation-delay", _.animationDelay, _.animationDelay = _)

  /** The animation-direction CSS property indicates whether the animation should play in reverse on alternate cycles.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-direction
    */
  lazy val AnimationDirection = StyleVar[String]("animation-direction", _.animationDirection, _.animationDirection = _)

  /** The animation-duration CSS property specifies the Length of time that an animation should take to complete one cycle.
    *
    * A value of 0s, which is the default value, indicates that no animation should occur.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-duration
    */
  lazy val AnimationDuration = StyleVar[String]("animation-duration", _.animationDuration, _.animationDuration = _)

  /** The animation-fill-mode CSS property specifies how a CSS animation should apply styles to its target before and after it is executing.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-fill-mode
    */
  lazy val AnimationFillMode = StyleVar[String]("animation-fill-mode", _.animationFillMode, _.animationFillMode = _)

  /** The animation-iteration-count CSS property defines the number of times an animation cycle should be played before stopping.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-iteration-count
    */
  lazy val AnimationIterationCount = StyleVar[Double]("animation-iteration-count", _.animationIterationCount, _.animationIterationCount = _)

  /** The animation-name CSS property specifies a list of animations that should be applied to the selected element. Each name indicates a
    * @keyframes at-rule that defines the property values for the animation sequence.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-name
    */
  lazy val AnimationName = StyleVar[String]("animation-name", _.animationName, _.animationName = _)

  /** The animation-play-state CSS property determines whether an animation is running or paused. You can query this property's value to
    * determine whether or not the animation is currently running; in addition, you can set its value to pause and resume playback of an
    * animation.
    *
    * Resuming a paused animation will start the animation from where it left off at the time it was paused, rather than starting over from
    * the beginning of the animation sequence.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-play-state
    */
  lazy val AnimationPlayState = StyleVar[String]("animation-play-state", _.animationPlayState, _.animationPlayState = _)

  /** The CSS animation-timing-function property specifies how a CSS animation should progress over the duration of each cycle. The possible
    * values are one or several `<timing-function>`.
    *
    * For keyframed animations, the timing function applies between keyframes rather than over the entire animation. In other words, the
    * timing function is applied at the start of the keyframe and at the end of the keyframe.
    *
    * An animation timing function defined within a keyframe block applies to that keyframe; otherwise. If no timing function is specified
    * for the keyframe, the timing function specified for the overall animation is used.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-timing-function
    */
  lazy val AnimationTimingFunction = StyleVar[String]("animation-timing-function", _.animationTimingFunction, _.animationTimingFunction = _)

  /** The CSS align-content property sets the distribution of space between and around content items along a flexbox's cross-axis or a
    * grid's block axis.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-content
    */
  lazy val AlignContent = StyleVar[String]("align-content", _.getPropertyValue("align-content"), _.setProperty("align-content", _))

  /** The CSS align-items property sets the align-self value on all direct children as a group. In Flexbox, it controls the alignment of
    * items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-items
    */
  lazy val AlignItems = StyleVar[String]("align-items", _.getPropertyValue("align-items"), _.setProperty("align-items", _))

  /** The align-self CSS property overrides a grid or flex item's align-items value. In Grid, it aligns the item inside the grid area. In
    * Flexbox, it aligns the item on the cross axis.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-self
    */
  lazy val AlignSelf = StyleVar[String]("align-self", _.getPropertyValue("align-self"), _.setProperty("align-self", _))

  /** The background CSS property is a shorthand for setting the individual background values in a single place in the style sheet.
    * background can be used to set the values for one or more of: background-clip, background-color, background-image, background-origin,
    * background-position, background-repeat, background-size, and background-attachment.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background
    */
  lazy val Background = StyleVar[String]("background", _.background, _.background = _)

  /** If a background-image is specified, the background-attachment CSS property determines whether that image's position is fixed within
    * the viewport, or scrolls along with its containing block.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-attachment
    */
  lazy val BackgroundAttachment = StyleVar[String]("background-attachment", _.backgroundAttachment, _.backgroundAttachment = _)

  /** The background-clip CSS property specifies whether an element's background, either the color or image, extends underneath its border.
    *
    * If there is no background image, this property has only visual effect when the border has transparent regions (because of
    * border-style) or partially opaque regions; otherwise the border covers up the difference.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-clip
    */
  lazy val BackgroundClip = StyleVar[String]("background-clip", _.backgroundClip, _.backgroundClip = _)

  /** The background-color CSS property sets the background color of an element, either through a color value or the keyword transparent.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-color
    */
  lazy val BackgroundColor = StyleVar[String]("background-color", _.backgroundColor, _.backgroundColor = _)

  /** The background-image CSS property sets one or more background images on an element. The background images are drawn on stacking
    * context layers on top of each other. The first layer specified is drawn as if it is closest to the user. The borders of the element
    * are then drawn on top of them, and the background-color is drawn beneath them.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-image
    */
  lazy val BackgroundImage = StyleVar[String]("background-image", _.backgroundImage, _.backgroundImage = _)

  /** The background-origin CSS property determines the background positioning area, that is the position of the origin of an image
    * specified using the background-image CSS property.
    *
    * Note that background-origin is ignored when background-attachment is fixed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-origin
    */
  lazy val BackgroundOrigin = StyleVar[String]("background-origin", _.backgroundOrigin, _.backgroundOrigin = _)

  /** The background-position CSS property sets the initial position, relative to the background position layer defined by background-origin
    * for each defined background image.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-position
    */
  lazy val BackgroundPosition = StyleVar[StylableSize]("background-position", _.backgroundPosition, _.backgroundPosition = _)

  /** The background-repeat CSS property defines how background images are repeated. A background image can be repeated along the horizontal
    * axis, the vertical axis, both, or not repeated at all. When the repetition of the image tiles doesn't let them exactly cover the
    * background, the way adjustments are done can be controlled by the author: by default, the last image is clipped, but the different
    * tiles can instead be re-sized, or space can be inserted between the tiles.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-repeat
    */
  lazy val BackgroundRepeat = StyleVar[String]("background-repeat", _.backgroundRepeat, _.backgroundRepeat = _)

  /** The background-size CSS property specifies the size of the background images. The size of the image can be fully constrained or only
    * partially in order to preserve its intrinsic ratio.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-size
    */
  lazy val BackgroundSize = StyleVar[StylableSize]("background-size", _.backgroundSize, _.backgroundSize = _)

  /** The CSS backface-visibility property determines whether or not the back face of the element is visible when facing the user. The back
    * face of an element always is a transparent background, letting, when visible, a mirror image of the front face be displayed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/backface-visibility
    */
  lazy val BackfaceVisibility = StyleVar[String]("backface-visibility", _.backfaceVisibility, _.backfaceVisibility = _)

  /** The border CSS property is a shorthand property for setting the individual border property values in a single place in the style
    * sheet. border can be used to set the values for one or more of: border-width, border-style, border-color.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border
    */
  lazy val Border = StyleVar[String]("border", _.border, _.border = _)

  /** The border-top CSS property is a shorthand that sets the values of border-top-color, border-top-style, and border-top-width. These
    * properties describe the top border of elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top
    */
  lazy val BorderTop = StyleVar[String]("border-top", _.borderTop, _.borderTop = _)

  /** The border-right CSS property is a shorthand that sets the values of border-right-color, border-right-style, and border-right-width.
    * These properties describe the right border of elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right
    */
  lazy val BorderRight = StyleVar[String]("border-right", _.borderRight, _.borderRight = _)

  /** The border-bottom CSS property is a shorthand that sets the values of border-bottom-color, border-bottom-style, and
    * border-bottom-width. These properties describe the bottom border of elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom
    */
  lazy val BorderBottom = StyleVar[String]("border-bottom", _.borderBottom, _.borderBottom = _)

  /** The border-left CSS property is a shorthand that sets the values of border-left-color, border-left-style, and border-left-width. These
    * properties describe the left border of elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left
    */
  lazy val BorderLeft = StyleVar[String]("border-left", _.borderLeft, _.borderLeft = _)

  /** The border-color CSS property is a shorthand for setting the color of the four sides of an element's border: border-top-color,
    * border-right-color, border-bottom-color, border-left-color.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-color
    */
  lazy val BorderColor = StyleVar[String]("border-color", _.borderColor, _.borderColor = _)

  /** The border-top-color CSS property sets the color of the top border of an element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-color
    */
  lazy val BorderTopColor = StyleVar[String]("border-top-color", _.borderTopColor, _.borderTopColor = _)

  /** The border-right-color CSS property sets the color of the right border of an element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-color
    */
  lazy val BorderRightColor = StyleVar[String]("border-right-color", _.borderRightColor, _.borderRightColor = _)

  /** The border-bottom-color CSS property sets the color of the bottom border of an element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-color
    */
  lazy val BorderBottomColor = StyleVar[String]("border-bottom-color", _.borderBottomColor, _.borderBottomColor = _)

  /** The border-left-color CSS property sets the color of the left border of an element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-color
    */
  lazy val BorderLeftColor = StyleVar[String]("border-left-color", _.borderLeftColor, _.borderLeftColor = _)

  /** The border-image CSS property draws an image around a given element. It replaces the element's regular border.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-image
    */
  lazy val BorderImage = StyleVar[String]("border-image", _.getPropertyValue("border-image"), _.setProperty("border-image", _))

  /** The border-style CSS property is a shorthand property for setting the line style for all four sides of the element's border.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-style
    */
  lazy val BorderStyle = StyleVar[String]("border-style", _.borderStyle, _.borderStyle = _)

  /** The border-top-style CSS property sets the line style of the top border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-style
    */
  lazy val BorderTopStyle = StyleVar[String]("border-top-style", _.borderTopStyle, _.borderTopStyle = _)

  /** The border-right-style CSS property sets the line style of the right border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-style
    */
  lazy val BorderRightStyle = StyleVar[String]("border-right-style", _.borderRightStyle, _.borderRightStyle = _)

  /** The border-bottom-style CSS property sets the line style of the bottom border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-style
    */
  lazy val BorderBottomStyle = StyleVar[String]("border-bottom-style", _.borderBottomStyle, _.borderBottomStyle = _)

  /** The border-left-style CSS property sets the line style of the left border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-style
    */
  lazy val BorderLeftStyle = StyleVar[String]("border-left-style", _.borderLeftStyle, _.borderLeftStyle = _)

  /** The border-width CSS property is a shorthand property for setting the width for all four sides of the element's border.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-width
    */
  lazy val BorderWidth = StyleVar[StylableSize]("border-width", _.borderWidth, _.borderWidth = _)

  /** The border-top-width CSS property sets the line width of the top border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-width
    */
  lazy val BorderTopWidth = StyleVar[StylableSize]("border-top-width", _.borderTopWidth, _.borderTopWidth = _)

  /** The border-right-width CSS property sets the line width of the right border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-width
    */
  lazy val BorderRightWidth = StyleVar[StylableSize]("border-right-width", _.borderRightWidth, _.borderRightWidth = _)

  /** The border-bottom-width CSS property sets the line width of the bottom border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-width
    */
  lazy val BorderBottomWidth = StyleVar[StylableSize]("border-bottom-width", _.borderBottomWidth, _.borderBottomWidth = _)

  /** The border-left-width CSS property sets the line width of the left border of a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-width
    */
  lazy val BorderLeftWidth = StyleVar[StylableSize]("border-left-width", _.borderLeftWidth, _.borderLeftWidth = _)

  /** The border-radius CSS property allows Web authors to define how rounded border corners are. The curve of each corner is defined using
    * one or two radii, defining its shape: circle or ellipse.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-radius
    */
  lazy val BorderRadius = StyleVar[StylableSize]("border-radius", _.borderRadius, _.borderRadius = _)

  /** The border-top-left-radius CSS property sets the rounding of the top-left corner of the element. The rounding can be a circle or an
    * ellipse, or if one of the value is 0 no rounding is done and the corner is square.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-left-radius
    */
  lazy val BorderTopLeftRadius = StyleVar[StylableSize]("border-top-left-radius", _.borderTopLeftRadius, _.borderTopLeftRadius = _)

  /** The border-top-right-radius CSS property sets the rounding of the top-right corner of the element. The rounding can be a circle or an
    * ellipse, or if one of the value is 0 no rounding is done and the corner is square.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-right-radius
    */
  lazy val BorderTopRightRadius = StyleVar[StylableSize]("border-top-right-radius", _.borderTopRightRadius, _.borderTopRightRadius = _)

  /** The border-bottom-right-radius CSS property sets the rounding of the bottom-right corner of the element. The rounding can be a circle
    * or an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-right-radius
    */
  lazy val BorderBottomRightRadius =
    StyleVar[StylableSize]("border-bottom-right-radius", _.borderBottomRightRadius, _.borderBottomRightRadius = _)

  /** The border-bottom-left-radius CSS property sets the rounding of the bottom-left corner of the element. The rounding can be a circle or
    * an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-left-radius
    */
  lazy val BorderBottomLeftRadius =
    StyleVar[StylableSize]("border-bottom-left-radius", _.borderBottomLeftRadius, _.borderBottomLeftRadius = _)

  /** The border-collapse CSS property selects a table's border model. This has a big influence on the look and style of the table cells.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-collapse
    */
  lazy val BorderCollapse = StyleVar[String]("border-collapse", _.borderCollapse, _.borderCollapse = _)

  /** The border-spacing CSS property specifies the distance between the borders of adjacent cells (only for the separated borders model).
    * This is equivalent to the cellspacing attribute in presentational HTML, but an optional second value can be used to set different
    * horizontal and vertical spacing.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-spacing
    */
  lazy val BorderSpacing = StyleVar[StylableSize]("border-spacing", _.borderSpacing, _.borderSpacing = _)

  /** The bottom CSS property participates in specifying the position of positioned elements.
    *
    * For absolutely positioned elements, that is those with position: absolute or position: fixed, it specifies the distance between the
    * bottom margin edge of the element and the bottom edge of its containing block.
    *
    * For relatively positioned elements, that is those with position: relative, it specifies the distance the element is moved above its
    * normal position.
    *
    * However, the top property overrides the bottom property, so if top is not auto, the computed value of bottom is the negative of the
    * computed value of top.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/bottom
    */
  lazy val Bottom = StyleVar[StylableSize]("bottom", _.bottom, _.bottom = _)

  /** The box-shadow CSS property describes one or more shadow effects as a comma-separated list. It allows casting a drop shadow from the
    * frame of almost any element. If a border-radius is specified on the element with a box shadow, the box shadow takes on the same
    * rounded corners. The z-ordering of multiple box shadows is the same as multiple text shadows (the first specified shadow is on top).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/box-shadow
    */
  lazy val BoxShadow = StyleVar[String]("box-shadow", _.boxShadow, _.boxShadow = _)

  /** The box-sizing CSS property is used to alter the default CSS box model used to calculate widths and heights of elements. It is
    * possible to use this property to emulate the behavior of browsers that do not correctly support the CSS box model specification.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/box-sizing
    */
  lazy val BoxSizing = StyleVar[String]("box-sizing", _.boxSizing, _.boxSizing = _)

  /** The caption-side CSS property positions the content of a table's caption on the specified side (top or bottom).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/caption-side
    */
  lazy val CaptionSide = StyleVar[String]("caption-side", _.captionSide, _.captionSide = _)

  /** The clear CSS property specifies whether an element can be next to floating elements that precede it or must be moved down (cleared)
    * below them.
    *
    * The clear property applies to both floating and non-floating elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/clear
    */
  lazy val Clear = StyleVar[String]("clear", _.clear, _.clear = _)

  /** The clip CSS property defines what portion of an element is visible. The clip property applies only to elements with
    * position:absolute.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/clip
    */
  lazy val Clip = StyleVar[String]("clip", _.clip, _.clip = _)

  /** The CSS color property sets the foreground color of an element's text content, and its decorations. It doesn't affect any other
    * characteristic of the element; it should really be called text-color and would have been named so, save for historical reasons.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/color
    */
  lazy val Color = StyleVar[String]("color", _.color, _.color = _)

  /** The columns CSS property is a shorthand property allowing to set both the column-width and the column-count properties at the same
    * time.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/columns
    */
  lazy val Columns = StyleVar[String]("columns", _.columns, _.columns = _)

  /** The column-count CSS property describes the number of columns of the element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-count
    */
  lazy val ColumnCount = StyleVar[Int]("column-count", _.columnCount.toString(), _.columnCount = _)

  /** The column-fill CSS property controls how contents are partitioned into columns. Contents are either balanced, which means that
    * contents in all columns will have the same height or, when using auto, just take up the room the content needs.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-fill
    */
  lazy val ColumnFill = StyleVar[String]("column-fill", _.columnFill, _.columnFill = _)

  /** The column-gap CSS property sets the size of the gap between columns for elements which are specified to display as a multi-column
    * element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-gap
    */
  lazy val ColumnGap = StyleVar[StylableSize]("column-gap", _.columnGap.toString(), (s, v) => s.columnGap = v.asInstanceOf[js.Any])

  /** The column-span CSS property makes it possible for an element to span across all columns when its value is set to `all`. An element
    * that spans more than one column is called a spanning element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-span
    */
  lazy val ColumnSpan = StyleVar[Int]("column-span", _.columnSpan, _.columnSpan = _)

  /** The column-width CSS property suggests an optimal column width. This is not a absolute value but a mere hint. Browser will adjust the
    * width of the column around that suggested value, allowing to achieve scalable designs that fit different screen size. Especially in
    * presence of the column-count CSS property which has precedence, to set an exact column width, all Length values must be specified. In
    * horizontal text these are width, column-width, column-gap, and column-rule-width
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-width
    */
  lazy val ColumnWidth = StyleVar[StylableSize]("column-width", _.columnWidth.toString(), (s, v) => s.columnWidth = v.asInstanceOf[js.Any])

  /** In multi-column layouts, the column-rule CSS property specifies a straight line, or "rule", to be drawn between each column. It is a
    * convenient shorthand to avoid setting each of the individual column-rule-* properties separately : column-rule-width,
    * column-rule-style and column-rule-color.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule
    */
  lazy val ColumnRule = StyleVar[String]("column-rule", _.columnRule, _.columnRule = _)

  /** The column-rule-color CSS property lets you set the color of the rule drawn between columns in multi-column layouts.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-color
    */
  lazy val ColumnRuleColor = StyleVar[String]("column-rule-color", _.columnRuleColor.toString(), _.columnRuleColor = _)

  /** The column-rule-width CSS property lets you set the width of the rule drawn between columns in multi-column layouts.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-width
    */
  lazy val ColumnRuleWidth =
    StyleVar[StylableSize]("column-rule-width", _.columnRuleWidth.toString(), (s, v) => s.columnRuleWidth = v.asInstanceOf[js.Any])

  /** The column-rule-style CSS property lets you set the style of the rule drawn between columns in multi-column layouts.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-style
    */
  lazy val ColumnRuleStyle = StyleVar[String]("column-rule-style", _.columnRuleStyle, _.columnRuleStyle = _)

  /** The `content` CSS property is used with the ::before and ::after pseudo-elements to generate content in an element. Objects inserted
    * using the content property are anonymous replaced elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/content
    */
  lazy val ContentCss = StyleVar[String]("content", _.content, _.content = _)

  /** The counter-increment CSS property is used to increase the value of CSS Counters by a given value. The counter's value can be reset
    * using the counter-reset CSS property.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/counter-increment
    */
  lazy val CounterIncrement = StyleVar[String]("counter-increment", _.counterIncrement, _.counterIncrement = _)

  /** The counter-reset CSS property is used to reset CSS Counters to a given value.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/counter-reset
    */
  lazy val CounterReset = StyleVar[String]("counter-reset", _.counterReset, _.counterReset = _)

  /** The cursor CSS property specifies the mouse cursor displayed when the mouse pointer is over an element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/cursor
    */
  lazy val Cursor = StyleVar[String]("cursor", _.cursor, _.cursor = _)

  /** Set the direction CSS property to match the direction of the text: rtl for Hebrew or Arabic text and ltr for other scripts. This is
    * typically done as part of the document (e.g., using the dir attribute in HTML) rather than through direct use of CSS.
    *
    * The property sets the base text direction of block-level elements and the direction of embeddings created by the unicode-bidi
    * property. It also sets the default alignment of text and block-level elements and the direction that cells flow within a table row.
    *
    * Unlike the dir attribute in HTML, the direction property is not inherited from table columns into table cells, since CSS inheritance
    * follows the document tree, and table cells are inside of the rows but not inside of the columns.
    *
    * The direction and unicode-bidi properties are the two only properties which are not affected by the all shorthand.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/direction
    */
  lazy val Direction = StyleVar[String]("direction", _.direction, _.direction = _)

  /** The display CSS property specifies the type of rendering box used for an element. In HTML, default display property values are taken
    * from behaviors described in the HTML specifications or from the browser/user default stylesheet. The default value in XML is inline.
    *
    * In addition to the many different display box types, the value none lets you turn off the display of an element; when you use none,
    * all descendant elements also have their display turned off. The document is rendered as though the element doesn't exist in the
    * document tree.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/display
    */
  lazy val Display = StyleVar[PossibleDisplays]("display", _.display.asInstanceOf[PossibleDisplays], _.display = _)

  /** The empty-cells CSS property specifies how user agents should render borders and backgrounds around cells that have no visible
    * content.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/empty-cells
    */
  lazy val EmptyCells = StyleVar[String]("empty-cells", _.emptyCells, _.emptyCells = _)

  /** The flex CSS property is a shorthand property specifying the ability of a flex item to alter its dimensions to fill available space.
    * Flex items can be stretched to use available space proportional to their flex grow factor or their flex shrink factor to prevent
    * overflow.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex
    */
  lazy val Flex = StyleVar[String]("flex", _.getPropertyValue("flex"), _.setProperty("flex", _))

  /** The CSS flex-basis property specifies the flex basis which is the initial main size of a flex item. The property determines the size
    * of the content-box unless specified otherwise using box-sizing.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis
    */
  lazy val FlexBasis = StyleVar[String]("flex-basis", _.getPropertyValue("flex-basis"), _.setProperty("flex-basis", _))

  /** The CSS flex-direction property specifies how flex items are placed in the flex container defining the main axis and the direction
    * (normal or reversed).
    *
    * Note that the value row and row-reverse are affected by the directionality of the flex container. If its dir attribute is ltr, row
    * represents the horizontal axis oriented from the left to the right, and row-reverse from the right to the left; if the dir attribute
    * is rtl, row represents the axis oriented from the right to the left, and row-reverse from the left to the right.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-direction
    */
  lazy val FlexDirection = StyleVar[String]("flex-direction", _.getPropertyValue("flex-direction"), _.setProperty("flex-direction", _))

  /** The CSS flex-grow property specifies the flex grow factor of a flex item.
    *
    * Default value is 0.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow
    */
  lazy val FlexGrow = StyleVar[Double]("flex-grow", _.getPropertyValue("flex-grow"), _.setProperty("flex-grow", _))

  /** The CSS flex-shrink property specifies the flex shrink factor of a flex item.
    *
    * Default value is 1.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-shrink
    */
  lazy val FlexShrink = StyleVar[Double]("flex-shrink", _.getPropertyValue("flex-shrink"), _.setProperty("flex-shrink", _))

  /** The CSS flex-wrap property specifies whether the children are forced into a single line or if the items can be flowed on multiple
    * lines.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-wrap
    */
  lazy val FlexWrap = StyleVar[String]("flex-wrap", _.getPropertyValue("flex-wrap"), _.setProperty("flex-wrap", _))

  /** The float CSS property specifies that an element should be taken from the normal flow and placed along the left or right side of its
    * container, where text and inline elements will wrap around it. A floating element is one where the computed value of float is not
    * `none`.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/float
    */
  lazy val Float = StyleVar[String]("float", _.getPropertyValue("float"), _.setProperty("float", _))

  /** The font CSS property is either a shorthand property for setting font-style, font-variant, font-weight, font-size, line-height and
    * font-family, or a way to set the element's font to a system font, using specific keywords.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font
    */
  lazy val Font = StyleVar[String]("font", _.font, _.font = _)

  /** The font-family CSS property allows for a prioritized list of font family names and/or generic family names to be specified for the
    * selected element. Unlike most other CSS properties, values are separated by a comma to indicate that they are alternatives. The
    * browser will select the first font on the list that is installed on the computer, or that can be downloaded using the information
    * provided by a @font-face at-rule.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-family
    */
  lazy val FontFamily = StyleVar[String]("font-family", _.fontFamily, _.fontFamily = _)

  /** The font-feature-settings CSS property allows control over advanced typographic features in OpenType fonts.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-feature-settings
    */
  lazy val FontFeatureSettings = StyleVar[String]("font-feature-settings", _.fontFeatureSettings, _.fontFeatureSettings = _)

  /** The font-size CSS property specifies the size of the font – specifically the desired height of glyphs from the font. Setting the font
    * size may, in turn, change the size of other items, since it is used to compute the value of em and ex Length units.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-size
    */
  lazy val FontSize = StyleVar[StylableSize]("font-size", _.fontSize, _.fontSize = _)

  /** The font-size-adjust CSS property sets the size of lower-case letters relative to the current font size (which defines the size of
    * upper-case letters).
    *
    * This is useful since the legibility of fonts, especially at small sizes, is determined more by the size of lowercase letters than by
    * the size of capital letters. This can cause problems when the first-choice font-family is unavailable and its replacement has a
    * significantly different aspect ratio (the ratio of the size of lowercase letters to the size of the font).
    *
    * Note: As of Dec 2021, only Firefox supports this
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-size-adjust
    */
  lazy val FontSizeAdjust = StyleVar[Double]("font-size-adjust", _.fontSizeAdjust, _.fontSizeAdjust = _)

  /** The font-style CSS property allows italic or oblique faces to be selected within a font-family.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-style
    */
  lazy val FontStyle = StyleVar[String]("font-style", _.fontStyle, _.fontStyle = _)

  /** The font-weight CSS property specifies the weight or boldness of the font. However, some fonts are not available in all weights; some
    * are available only on normal and bold.
    *
    * Numeric font weights for fonts that provide more than just normal and bold. If the exact weight given is unavailable, then 600-900 use
    * the closest available darker weight (or, if there is none, the closest available lighter weight), and 100-500 use the closest
    * available lighter weight (or, if there is none, the closest available darker weight). This means that for fonts that provide only
    * normal and bold, 100-500 are normal, and 600-900 are bold.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-weight
    */
  lazy val FontWeight = StyleVar[String]("font-weight", _.fontWeight, _.fontWeight = _)

  /** The height CSS property specifies the height of the content area of an element. The content area is inside the padding, border, and
    * margin of the element.
    *
    * The min-height and max-height properties override height.
    *
    * @see
    *   @see https://developer.mozilla.org/en-US/docs/Web/CSS/height
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/height
    */
  lazy val Height = StyleVar[StylableSize]("height", _.height, _.height = _)

  /** This property determines whether an element must create a new stacking context. It is especially helpful when used in conjunction with
    * mix-blend-mode and z-index.
    *
    * Allowed values: "isolate", "auto"
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/isolation
    */
  lazy val Isolation = StyleVar[String]("isolation", _.getPropertyValue("isolation"), _.setProperty("isolation", _))

  /** The CSS justify-content property defines how a browser distributes available space between and around elements when aligning flex
    * items in the main-axis of the current line. The alignment is done after the lengths and auto margins are applied, meaning that, if
    * there is at least one flexible element, with flex-grow different than 0, it will have no effect as there won't be any available space.
    *
    * @see
    *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/justify-content
    */
  lazy val JustifyContent = StyleVar[String]("justify-content", _.getPropertyValue("justify-content"), _.setProperty("justify-content", _))

  /** The left CSS property specifies part of the position of positioned elements.
    *
    * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the left
    * margin edge of the element and the left edge of its containing block.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/left
    */
  lazy val Left = StyleVar[StylableSize]("left", _.left, _.left = _)

  /** The letter-spacing CSS property specifies spacing behavior between text characters.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/letter-spacing
    */
  lazy val LetterSpacing = StyleVar[String]("letter-spacing", _.letterSpacing, _.letterSpacing = _)

  /** On block level elements, the line-height CSS property specifies the minimal height of line boxes within the element.
    *
    * On non-replaced inline elements, line-height specifies the height that is used in the calculation of the line box height.
    *
    * On replaced inline elements, like buttons or other input element, line-height has no effect.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/line-height
    */
  lazy val LineHeight = StyleVar[StylableSize]("line-height", _.lineHeight, _.lineHeight = _)

  /** The list-style CSS property is a shorthand property for setting list-style-type, list-style-image and list-style-position.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style
    */
  lazy val ListStyle = StyleVar[String]("list-style", _.listStyle, _.listStyle = _)

  /** The list-style-image CSS property sets the image that will be used as the list item marker.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-image
    */
  lazy val ListStyleImage = StyleVar[String]("list-style-image", _.listStyleImage, _.listStyleImage = _)

  /** The list-style-position CSS property specifies the position of the marker box in the principal block box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-position
    */
  lazy val ListStylePosition = StyleVar[String]("list-style-position", _.listStylePosition, _.listStylePosition = _)

  /** The list-style-type CSS property sets the marker (such as a disc, character, or custom counter style) of a list item element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type
    */
  lazy val ListStyleType = StyleVar[String]("list-style-type", _.listStyleType, _.listStyleType = _)

  /** The margin CSS property sets the margin for all four sides. It is a shorthand to avoid setting each side separately with the other
    * margin properties: margin-top, margin-right, margin-bottom and margin-left.
    *
    * Negative values are also allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin
    */
  lazy val Margin = StyleVar[StylableSize]("margin", _.margin, _.margin = _)

  /** The margin-top CSS property of an element sets the margin space required on the top of an element. A negative value is also allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-top
    */
  lazy val MarginTop = StyleVar[StylableSize]("margin-top", _.marginTop, _.marginTop = _)

  /** The margin-right CSS property of an element sets the margin space required on the right of an element. A negative value is also
    * allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-right
    */
  lazy val MarginRight = StyleVar[StylableSize]("margin-right", _.marginRight, _.marginRight = _)

  /** The margin-bottom CSS property of an element sets the margin space required on the bottom of an element. A negative value is also
    * allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-bottom
    */
  lazy val MarginBottom = StyleVar[StylableSize]("margin-bottom", _.marginBottom, _.marginBottom = _)

  /** The margin-left CSS property of an element sets the margin space required on the left of an element. A negative value is also allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-left
    */
  lazy val MarginLeft = StyleVar[StylableSize]("margin-left", _.marginLeft, _.marginLeft = _)

  /** If the value is a URI value, the element pointed to by the URI is used as an SVG mask.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/mask
    */
  lazy val Mask = StyleVar[String]("mask", _.mask, _.mask = _)

  /** The max-height CSS property is used to set the maximum height of a given element. It prevents the used value of the height property
    * from becoming larger than the value specified for max-height.
    *
    * max-height overrides height, but min-height overrides max-height.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/max-height
    */
  lazy val MaxHeight = StyleVar[StylableSize]("max-height", _.maxHeight, _.maxHeight = _)

  /** The max-width CSS property is used to set the maximum width of a given element. It prevents the used value of the width property from
    * becoming larger than the value specified for max-width.
    *
    * max-width overrides width, but min-width overrides max-width.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/max-width
    */
  lazy val MaxWidth = StyleVar[StylableSize]("max-width", _.maxWidth, _.maxWidth = _)

  /** The min-height CSS property is used to set the minimum height of a given element. It prevents the used value of the height property
    * from becoming smaller than the value specified for min-height.
    *
    * The value of min-height overrides both max-height and height.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/min-height
    */
  lazy val MinHeight = StyleVar[StylableSize]("min-height", _.minHeight, _.minHeight = _)

  /** The min-width CSS property is used to set the minimum width of a given element. It prevents the used value of the width property from
    * becoming smaller than the value specified for min-width.
    *
    * The value of min-width overrides both max-width and width.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/min-width
    */
  lazy val MinWidth = StyleVar[StylableSize]("min-width", _.minWidth, _.minWidth = _)

  /** This property sets how an element's content should blend with the content of the element's parent and the element's background.
    *
    * Note: not supported by Chrome on Android and Safari
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/mix-blend-mode
    */
  lazy val MixBlendMode = StyleVar[String]("mix-blend-mode", _.getPropertyValue("mix-blend-mode"), _.setProperty("mix-blend-mode", _))

  /** The opacity CSS property specifies the transparency of an element, that is, the degree to which the background behind the element is
    * overlaid.
    *
    * The value applies to the element as a whole, including its contents, even though the value is not inherited by child elements. Thus,
    * an element and its contained children all have the same opacity relative to the element's background, even if the element and its
    * children have different opacities relative to one another.
    *
    * Using this property with a value different than 1 places the element in a new stacking context.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/opacity
    */
  lazy val Opacity = StyleVar[Double]("opacity", _.opacity, _.opacity = _)

  /** The orphans CSS property refers to the minimum number of lines in a block container that must be left at the bottom of the page. This
    * property is normally used to control how page breaks occur.
    *
    * Note: Firefox does not support this property
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/orphans
    */
  lazy val Orphans = StyleVar[Int]("orphans", _.orphans, _.orphans = _)

  /** The CSS outline property is a shorthand property for setting one or more of the individual outline properties outline-style,
    * outline-width and outline-color in a single rule. In most cases the use of this shortcut is preferable and more convenient.
    *
    * Outlines do not take up space, they are drawn above the content.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline
    */
  lazy val Outline = StyleVar[String]("outline", _.outline, _.outline = _)

  /** The outline-style CSS property is used to set the style of the outline of an element. An outline is a line that is drawn around
    * elements, outside the border edge, to make the element stand out.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-style
    */
  lazy val OutlineStyle = StyleVar[String]("outline-style", _.outlineStyle, _.outline = _)

  /** The outline-width CSS property is used to set the width of the outline of an element. An outline is a line that is drawn around
    * elements, outside the border edge, to make the element stand out.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-width
    */
  lazy val OutlineWidth = StyleVar[StylableSize]("outline-width", _.outlineWidth, _.outlineWidth = _)

  /** The outline-color CSS property sets the color of the outline of an element. An outline is a line that is drawn around elements,
    * outside the border edge, to make the element stand out.
    *
    * Note: "invert" is a special outline color you can use for high contrast.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-color
    */
  lazy val OutlineColor = StyleVar[String]("outline-color", _.outlineColor, _.outlineColor = _)

  /** The overflow CSS property specifies whether to clip content, render scroll bars or display overflow content of a block-level element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow
    */
  lazy val Overflow = StyleVar[String]("overflow", _.overflow, _.overflow = _)

  /** The overflow-x CSS property specifies whether to clip content, render a scroll bar or display overflow content of a block-level
    * element, when it overflows at the left and right edges.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-x
    */
  lazy val OverflowX = StyleVar[String]("overflow-x", _.overflowX, _.overflowX = _)

  /** The overflow-y CSS property specifies whether to clip content, render a scroll bar, or display overflow content of a block-level
    * element, when it overflows at the top and bottom edges.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-y
    */
  lazy val OverflowY = StyleVar[String]("overflow-y", _.overflowY, _.overflowY = _)

  /** The overflow-wrap CSS property specifies whether or not the browser should insert line breaks within words to prevent text from
    * overflowing its content box.
    *
    * Alias for: [[wordWrap]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-wrap
    */
  lazy val OverflowWrap = StyleVar[String]("overflow-wrap", _.getPropertyValue("overflow-wrap"), _.setProperty("overflow-wrap", _))

  lazy val WordWrap = OverflowWrap

  /** The padding CSS property sets the required padding space on all sides of an element. The padding area is the space between the content
    * of the element and its border. Negative values are not allowed.
    *
    * The padding property is a shorthand to avoid setting each side separately (padding-top, padding-right, padding-bottom, padding-left).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding
    */
  lazy val Padding = StyleVar[StylableSize]("padding", _.padding, _.padding = _)

  /** The padding-top CSS property of an element sets the padding space required on the top of an element. The padding area is the space
    * between the content of the element and its border. Contrary to margin-top values, negative values of padding-top are invalid.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-top
    */
  lazy val PaddingTop = StyleVar[StylableSize]("padding-top", _.paddingTop, _.paddingTop = _)

  /** The padding-right CSS property of an element sets the padding space required on the right side of an element. The padding area is the
    * space between the content of the element and its border. Negative values are not allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-right
    */
  lazy val PaddingRight = StyleVar[StylableSize]("padding-right", _.paddingRight, _.paddingRight = _)

  /** The padding-bottom CSS property of an element sets the height of the padding area at the bottom of an element. The padding area is the
    * space between the content of the element and it's border. Contrary to margin-bottom values, negative values of padding-bottom are
    * invalid.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-bottom
    */
  lazy val PaddingBottom = StyleVar[StylableSize]("padding-bottom", _.paddingBottom, _.paddingBottom = _)

  /** The padding-left CSS property of an element sets the padding space required on the left side of an element. The padding area is the
    * space between the content of the element and it's border. A negative value is not allowed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-left
    */
  lazy val PaddingLeft = StyleVar[StylableSize]("padding-left", _.paddingLeft, _.paddingLeft = _)

  /** The page-break-after CSS property adjusts page breaks after the current element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-after
    */
  lazy val PageBreakAfter = StyleVar[String]("page-break-after", _.pageBreakAfter, _.pageBreakAfter = _)

  /** The page-break-before CSS property adjusts page breaks before the current element.
    *
    * This properties applies to block elements that generate a box. It won't apply on an empty div that won't generate a box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-before
    */
  lazy val PageBreakBefore = StyleVar[String]("page-break-before", _.pageBreakBefore, _.pageBreakBefore = _)

  /** The page-break-inside CSS property adjusts page breaks inside the current element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-inside
    */
  lazy val PageBreakInside = StyleVar[String]("page-break-inside", _.pageBreakInside, _.pageBreakInside = _)

  /** The perspective CSS property determines the distance between the z=0 plane and the user in order to give to the 3D-positioned element
    * some perspective. Each 3D element with z>0 becomes larger; each 3D-element with z<0 becomes smaller. The strength of the effect is
    * determined by the value of this property.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/perspective
    */
  lazy val Perspective = StyleVar[String]("perspective", _.perspective, _.perspective = _)

  /** The perspective-origin CSS property determines the position the viewer is looking at. It is used as the vanishing point by the
    * perspective property.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/perspective-origin
    */
  lazy val PerspectiveOrigin = StyleVar[String]("perspective-origin", _.perspectiveOrigin, _.perspectiveOrigin = _)

  /** The CSS property pointer-events allows authors to control under what circumstances (if any) a particular graphic element can become
    * the target of mouse events. When this property is unspecified, the same characteristics of the visiblePainted value apply to SVG
    * content.
    *
    * In addition to indicating that the element is not the target of mouse events, the value none instructs the mouse event to go "through"
    * the element and target whatever is "underneath" that element instead.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/pointer-events
    */
  lazy val PointerEvents = StyleVar[String]("pointer-events", _.pointerEvents, _.pointerEvents = _)

  /** The position CSS property chooses alternative rules for positioning elements, designed to be useful for scripted animation effects.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/position
    */
  lazy val Position = StyleVar[String]("position", _.position, _.position = _)

  /** The quotes CSS property sets how the browser should render quotation marks that are added using the open-quotes or close-quotes values
    * of the CSS content property.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/quotes
    */
  lazy val Quotes = StyleVar[String]("quotes", _.quotes, _.quotes = _)

  /** The resize CSS property sets whether an element is resizable, and if so, in which direction(s).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/resize
    */
  lazy val Resize = StyleVar[String]("resize", _.getPropertyValue("resize"), _.setProperty("resize", _))

  /** The right CSS property specifies part of the position of positioned elements.
    *
    * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the right
    * margin edge of the element and the right edge of its containing block.
    *
    * The right property has no effect on non-positioned elements.
    *
    * When both the right CSS property and the left CSS property are defined, the position of the element is overspecified. In that case,
    * the left value has precedence when the container is left-to-right (that is that the right computed value is set to -left), and the
    * right value has precedence when the container is right-to-left (that is that the left computed value is set to -right).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/right
    */
  lazy val Right = StyleVar[StylableSize]("right", _.right, _.right = _)

  /** The table-layout CSS property sets the algorithm used to lay out `<table>` cells, rows, and columns.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/table-layout
    */
  lazy val TableLayout = StyleVar[String]("table-layout", _.tableLayout, _.tableLayout = _)

  /** The text-align CSS property describes how inline content like text is aligned in its parent block element. text-align does not control
    * the alignment of block elements itself, only their inline content.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-align
    */
  lazy val TextAlign = StyleVar[String]("text-align", _.textAlign, _.textAlign = _)

  /** The text-align-last CSS property describes how the last line of a block or a line, right before a forced line break, is aligned.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-align-last
    */
  lazy val TextAlignLast = StyleVar[String]("text-align-last", _.textAlignLast, _.textAlignLast = _)

  /** The text-decoration CSS property is used to set the text formatting to underline, overline, line-through or blink.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-decoration
    */
  lazy val TextDecoration = StyleVar[String]("text-decoration", _.textDecoration, _.textDecoration = _)

  /** The text-indent CSS property specifies how much horizontal space should be left before the beginning of the first line of the text
    * content of an element. Horizontal spacing is with respect to the left (or right, for right-to-left layout) edge of the containing
    * block element's box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-indent
    */
  lazy val TextIndent = StyleVar[String]("text-indent", _.textIndent, _.textIndent = _)

  /** The text-overflow CSS property determines how overflowed content that is not displayed is signaled to the users. It can be clipped, or
    * display an ellipsis ('…', U+2026 HORIZONTAL ELLIPSIS) or a Web author-defined string.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-overflow
    */
  lazy val TextOverflow = StyleVar[String]("text-overflow", _.textOverflow, _.textOverflow = _)

  /** The text-shadow CSS property adds shadows to text. It accepts a comma-separated list of shadows to be applied to the text and
    * text-decorations of the element.
    *
    * Each shadow is specified as an offset from the text, along with optional color and blur radius values.
    *
    * Multiple shadows are applied front-to-back, with the first-specified shadow on top.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-shadow
    */
  lazy val TextShadow = StyleVar[String]("text-shadow", _.textShadow, _.textShadow = _)

  /** The text-transform CSS property specifies how to capitalize an element's text. It can be used to make text appear in all-uppercase or
    * all-lowercase, or with each word capitalized.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-transform
    */
  lazy val TextTransform = StyleVar[String]("text-transform", _.textTransform, _.textTransform = _)

  /** The CSS text-underline-position property specifies the position of the underline which is set using the text-decoration property
    * underline value.
    *
    * This property inherits and is not reset by the text-decoration shorthand, allowing to easily set it globally for a given document.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-underline-position
    */
  lazy val TextUnderlinePosition = StyleVar[String]("text-underline-position", _.textUnderlinePosition, _.textUnderlinePosition = _)

  /** The top CSS property specifies part of the position of positioned elements. It has no effect on non-positioned elements.
    *
    * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the top
    * margin edge of the element and the top edge of its containing block.
    *
    * For relatively positioned elements (those with position: relative), it specifies the amount the element is moved below its normal
    * position.
    *
    * When both top and bottom are specified, the element position is over-constrained and the top property has precedence: the computed
    * value of bottom is set to -top, while its specified value is ignored.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/top
    */
  lazy val Top = StyleVar[StylableSize]("top", _.top, _.top = _)

  /** The CSS transform property lets you modify the coordinate space of the CSS visual formatting model. Using it, elements can be
    * translated, rotated, scaled, and skewed according to the values set.
    *
    * If the property has a value different than none, a stacking context will be created. In that case the object will act as a containing
    * block for position: fixed elements that it contains.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform
    */
  lazy val Transform = StyleVar[String]("transform", _.transform, _.transform = _)

  /** The transform-origin CSS property lets you modify the origin for transformations of an element. For example, the transform-origin of
    * the rotate() function is the centre of rotation. (This property is applied by first translating the element by the negated value of
    * the property, then applying the element's transform, then translating by the property value.)
    *
    * Not explicitly set values are reset to their corresponding values.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform-origin
    */
  lazy val TransformOrigin = StyleVar[String]("transform-origin", _.transformOrigin, _.transformOrigin = _)

  /** The transform-style CSS property determines if the children of the element are positioned in the 3D-space or are flattened in the
    * plane of the element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform-style
    */
  lazy val TransformStyle = StyleVar[String]("transform-style", _.transformStyle, _.transformStyle = _)

  /** The CSS transition property is a shorthand property for transition-property, transition-duration, transition-timing-function, and
    * transition-delay. It allows to define the transition between two states of an element. Different states may be defined using
    * pseudo-classes like :hover or :active or dynamically set using JavaScript.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition
    */
  lazy val Transition = StyleVar[String]("transition", _.transition, _.transition = _)

  /** The transition-delay CSS property specifies the amount of time to wait between a change being requested to a property that is to be
    * transitioned and the start of the transition effect.
    *
    * A value of 0s, or 0ms, indicates that the property will begin to animate its transition immediately when the value changes; positive
    * values will delay the start of the transition effect for the corresponding number of seconds. Negative values cause the transition to
    * begin immediately, but to cause the transition to seem to begin partway through the animation effect.
    *
    * You may specify multiple delays; each delay will be applied to the corresponding property as specified by the transition-property
    * property, which acts as a master list. If there are fewer delays specified than in the master list, missing values are set to the
    * initial value (0s). If there are more delays, the list is simply truncated to the right size. In both case the CSS declaration stays
    * valid.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-delay
    */
  lazy val TransitionDelay = StyleVar[String]("transition-delay", _.transitionDelay, _.transitionDelay = _)

  /** The transition-duration CSS property specifies the number of seconds or milliseconds a transition animation should take to complete.
    * By default, the value is 0s, meaning that no animation will occur.
    *
    * You may specify multiple durations; each duration will be applied to the corresponding property as specified by the
    * transition-property property, which acts as a master list. If there are fewer durations specified than in the master list, the user
    * agent repeat the list of durations. If there are more durations, the list is simply truncated to the right size. In both case the CSS
    * declaration stays valid.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-duration
    */
  lazy val TransitionDuration = StyleVar[String]("transition-duration", _.transitionDuration, _.transitionDuration = _)

  /** The CSS transition-timing-function property is used to describe how the intermediate values of the CSS properties being affected by a
    * transition effect are calculated. This in essence lets you establish an acceleration curve, so that the speed of the transition can
    * vary over its duration.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-timing-function
    */
  lazy val TransitionTimingFunction =
    StyleVar[String]("transition-timing-function", _.transitionTimingFunction, _.transitionTimingFunction = _)

  /** The transition-property CSS property is used to specify the names of CSS properties to which a transition effect should be applied.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-property
    */
  lazy val TransitionProperty = StyleVar[String]("transition-property", _.transitionProperty, _.transitionProperty = _)

  /** The unicode-bidi CSS property together with the `direction` property relates to the handling of bidirectional text in a document. For
    * example, if a block of text contains both left-to-right and right-to-left text then the user-agent uses a complex Unicode algorithm to
    * decide how to display the text. This property overrides this algorithm and allows the developer to control the text embedding.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/unicode-bidi
    */
  lazy val UnicodeBidi = StyleVar[String]("unicode-bidi", _.unicodeBidi, _.unicodeBidi = _)

  /** The vertical-align CSS property specifies the vertical alignment of an inline or table-cell box. It does not apply to block-level
    * elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/vertical-align
    */
  lazy val VerticalAlign = StyleVar[String]("vertical-align", _.verticalAlign, _.verticalAlign = _)

  /** The visibility CSS property shows or hides an element without changing the layout of a document. The property can also hide rows or
    * columns in a `<table>`.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/visibility
    */
  lazy val Visibility = StyleVar[String]("visibility", _.visibility, _.visibility = _)

  /** The width CSS property specifies the width of the content area of an element. The content area is inside the padding, border, and
    * margin of the element.
    *
    * The min-width and max-width properties override width.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/width
    */
  lazy val Width = StyleVar[StylableSize]("width", _.width, _.width = _)

  /** The white-space CSS property is used to to describe how whitespace inside the element is handled.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/white-space
    */
  lazy val WhiteSpace = StyleVar[String]("white-space", _.whiteSpace, _.whiteSpace = _)

  /** The widows CSS property defines how many minimum lines must be left on top of a new page, on a paged media. In typography, a widow is
    * the last line of a paragraph appearing alone at the top of a page. Setting the widows property allows to prevent widows to be left.
    *
    * On a non-paged media, like screen, the widows CSS property has no effect.
    *
    * Note: Firefox does not support this property.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/widows
    */
  lazy val Widows = StyleVar[Int]("widows", _.widows, _.widows = _)

  /** The word-break CSS property specifies whether or not the browser should insert line breaks wherever the text would otherwise overflow
    * its content box.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/word-break
    */
  lazy val WordBreak = StyleVar[String]("word-break", _.wordBreak, _.wordBreak = _)

  /** The word-spacing CSS property specifies spacing behavior between tags and words.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/word-spacing
    */
  lazy val WordSpacing = StyleVar[String]("word-spacing", _.wordSpacing, _.wordSpacing = _)

  /** The z-index CSS property specifies the z-order of an element and its descendants. When elements overlap, z-order determines which one
    * covers the other. An element with a larger z-index generally covers an element with a lower one.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/CSS/z-index
    */
  lazy val ZIndex = StyleVar[Int]("z-index", _.zIndex, _.zIndex = _)
}

// @experimental
// @impl.varsApi[StyleVars.type]
object StylesApi {

  /** @see [[package.StyleVars.All]] */
  lazy val all = ModifierVar[HtmlElement, String](StyleVars.All)

  /** @see [[package.StyleVars.Animation]] */
  lazy val animation = ModifierVar[HtmlElement, String](StyleVars.Animation)

  /** @see [[package.StyleVars.AnimationDelay]] */
  lazy val animationDelay = ModifierVar[HtmlElement, String](StyleVars.AnimationDelay)

  /** @see [[package.StyleVars.AnimationDirection]] */
  lazy val animationDirection = ModifierVar[HtmlElement, String](StyleVars.AnimationDirection)

  /** @see [[package.StyleVars.AnimationDuration]] */
  lazy val animationDuration = ModifierVar[HtmlElement, String](StyleVars.AnimationDuration)

  /** @see [[package.StyleVars.AnimationFillMode]] */
  lazy val animationFillMode = ModifierVar[HtmlElement, String](StyleVars.AnimationFillMode)

  /** @see [[package.StyleVars.AnimationIterationCount]] */
  lazy val animationIterationCount = ModifierVar[HtmlElement, Double](StyleVars.AnimationIterationCount)

  /** @see [[package.StyleVars.AnimationName]] */
  lazy val animationName = ModifierVar[HtmlElement, String](StyleVars.AnimationName)

  /** @see [[package.StyleVars.AnimationPlayState]] */
  lazy val animationPlayState = ModifierVar[HtmlElement, String](StyleVars.AnimationPlayState)

  /** @see [[package.StyleVars.AnimationTimingFunction]] */
  lazy val animationTimingFunction = ModifierVar[HtmlElement, String](StyleVars.AnimationTimingFunction)

  /** @see [[package.StyleVars.AlignContent]] */
  lazy val alignContent = ModifierVar[HtmlElement, String](StyleVars.AlignContent)

  /** @see [[package.StyleVars.AlignItems]] */
  lazy val alignItems = ModifierVar[HtmlElement, String](StyleVars.AlignItems)

  /** @see [[package.StyleVars.AlignSelf]] */
  lazy val alignSelf = ModifierVar[HtmlElement, String](StyleVars.AlignSelf)

  /** @see [[package.StyleVars.Background]] */
  lazy val background = ModifierVar[HtmlElement, String](StyleVars.Background)

  /** @see [[package.StyleVars.BackgroundAttachment]] */
  lazy val backgroundAttachment = ModifierVar[HtmlElement, String](StyleVars.BackgroundAttachment)

  /** @see [[package.StyleVars.BackgroundClip]] */
  lazy val backgroundClip = ModifierVar[HtmlElement, String](StyleVars.BackgroundClip)

  /** @see [[package.StyleVars.BackgroundColor]] */
  lazy val backgroundColor = ModifierVar[HtmlElement, String](StyleVars.BackgroundColor)

  /** @see [[package.StyleVars.BackgroundImage]] */
  lazy val backgroundImage = ModifierVar[HtmlElement, String](StyleVars.BackgroundImage)

  /** @see [[package.StyleVars.BackgroundOrigin]] */
  lazy val backgroundOrigin = ModifierVar[HtmlElement, String](StyleVars.BackgroundOrigin)

  /** @see [[package.StyleVars.BackgroundPosition]] */
  lazy val backgroundPosition = ModifierVar[HtmlElement, StylableSize](StyleVars.BackgroundPosition)

  /** @see [[package.StyleVars.BackgroundRepeat]] */
  lazy val backgroundRepeat = ModifierVar[HtmlElement, String](StyleVars.BackgroundRepeat)

  /** @see [[package.StyleVars.BackgroundSize]] */
  lazy val backgroundSize = ModifierVar[HtmlElement, StylableSize](StyleVars.BackgroundSize)

  /** @see [[package.StyleVars.BackfaceVisibility]] */
  lazy val backfaceVisibility = ModifierVar[HtmlElement, String](StyleVars.BackfaceVisibility)

  /** @see [[package.StyleVars.Border]] */
  lazy val border = ModifierVar[HtmlElement, String](StyleVars.Border)

  /** @see [[package.StyleVars.BorderTop]] */
  lazy val borderTop = ModifierVar[HtmlElement, String](StyleVars.BorderTop)

  /** @see [[package.StyleVars.BorderRight]] */
  lazy val borderRight = ModifierVar[HtmlElement, String](StyleVars.BorderRight)

  /** @see [[package.StyleVars.BorderBottom]] */
  lazy val borderBottom = ModifierVar[HtmlElement, String](StyleVars.BorderBottom)

  /** @see [[package.StyleVars.BorderLeft]] */
  lazy val borderLeft = ModifierVar[HtmlElement, String](StyleVars.BorderLeft)

  /** @see [[package.StyleVars.BorderColor]] */
  lazy val borderColor = ModifierVar[HtmlElement, String](StyleVars.BorderColor)

  /** @see [[package.StyleVars.BorderTopColor]] */
  lazy val borderTopColor = ModifierVar[HtmlElement, String](StyleVars.BorderTopColor)

  /** @see [[package.StyleVars.BorderRightColor]] */
  lazy val borderRightColor = ModifierVar[HtmlElement, String](StyleVars.BorderRightColor)

  /** @see [[package.StyleVars.BorderBottomColor]] */
  lazy val borderBottomColor = ModifierVar[HtmlElement, String](StyleVars.BorderBottomColor)

  /** @see [[package.StyleVars.BorderLeftColor]] */
  lazy val borderLeftColor = ModifierVar[HtmlElement, String](StyleVars.BorderLeftColor)

  /** @see [[package.StyleVars.BorderImage]] */
  lazy val borderImage = ModifierVar[HtmlElement, String](StyleVars.BorderImage)

  /** @see [[package.StyleVars.BorderStyle]] */
  lazy val borderStyle = ModifierVar[HtmlElement, String](StyleVars.BorderStyle)

  /** @see [[package.StyleVars.BorderTopStyle]] */
  lazy val borderTopStyle = ModifierVar[HtmlElement, String](StyleVars.BorderTopStyle)

  /** @see [[package.StyleVars.BorderRightStyle]] */
  lazy val borderRightStyle = ModifierVar[HtmlElement, String](StyleVars.BorderRightStyle)

  /** @see [[package.StyleVars.BorderBottomStyle]] */
  lazy val borderBottomStyle = ModifierVar[HtmlElement, String](StyleVars.BorderBottomStyle)

  /** @see [[package.StyleVars.BorderLeftStyle]] */
  lazy val borderLeftStyle = ModifierVar[HtmlElement, String](StyleVars.BorderLeftStyle)

  /** @see [[package.StyleVars.BorderWidth]] */
  lazy val borderWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderWidth)

  /** @see [[package.StyleVars.BorderTopWidth]] */
  lazy val borderTopWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderTopWidth)

  /** @see [[package.StyleVars.BorderRightWidth]] */
  lazy val borderRightWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderRightWidth)

  /** @see [[package.StyleVars.BorderBottomWidth]] */
  lazy val borderBottomWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderBottomWidth)

  /** @see [[package.StyleVars.BorderLeftWidth]] */
  lazy val borderLeftWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderLeftWidth)

  /** @see [[package.StyleVars.BorderRadius]] */
  lazy val borderRadius = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderRadius)

  /** @see [[package.StyleVars.BorderTopLeftRadius]] */
  lazy val borderTopLeftRadius = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderTopLeftRadius)

  /** @see [[package.StyleVars.BorderTopRightRadius]] */
  lazy val borderTopRightRadius = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderTopRightRadius)

  /** @see [[package.StyleVars.BorderBottomRightRadius]] */
  lazy val borderBottomRightRadius = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderBottomRightRadius)

  /** @see [[package.StyleVars.BorderBottomLeftRadius]] */
  lazy val borderBottomLeftRadius = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderBottomLeftRadius)

  /** @see [[package.StyleVars.BorderCollapse]] */
  lazy val borderCollapse = ModifierVar[HtmlElement, String](StyleVars.BorderCollapse)

  /** @see [[package.StyleVars.BorderSpacing]] */
  lazy val borderSpacing = ModifierVar[HtmlElement, StylableSize](StyleVars.BorderSpacing)

  /** @see [[package.StyleVars.Bottom]] */
  lazy val bottom = ModifierVar[HtmlElement, StylableSize](StyleVars.Bottom)

  /** @see [[package.StyleVars.BoxShadow]] */
  lazy val boxShadow = ModifierVar[HtmlElement, String](StyleVars.BoxShadow)

  /** @see [[package.StyleVars.BoxSizing]] */
  lazy val boxSizing = ModifierVar[HtmlElement, String](StyleVars.BoxSizing)

  /** @see [[package.StyleVars.CaptionSide]] */
  lazy val captionSide = ModifierVar[HtmlElement, String](StyleVars.CaptionSide)

  /** @see [[package.StyleVars.Clear]] */
  lazy val clear = ModifierVar[HtmlElement, String](StyleVars.Clear)

  /** @see [[package.StyleVars.Clip]] */
  lazy val clip = ModifierVar[HtmlElement, String](StyleVars.Clip)

  /** @see [[package.StyleVars.Color]] */
  lazy val color = ModifierVar[HtmlElement, String](StyleVars.Color)

  /** @see [[package.StyleVars.Columns]] */
  lazy val columns = ModifierVar[HtmlElement, String](StyleVars.Columns)

  /** @see [[package.StyleVars.ColumnCount]] */
  lazy val columnCount = ModifierVar[HtmlElement, Int](StyleVars.ColumnCount)

  /** @see [[package.StyleVars.ColumnFill]] */
  lazy val columnFill = ModifierVar[HtmlElement, String](StyleVars.ColumnFill)

  /** @see [[package.StyleVars.ColumnGap]] */
  lazy val columnGap = ModifierVar[HtmlElement, StylableSize](StyleVars.ColumnGap)

  /** @see [[package.StyleVars.ColumnSpan]] */
  lazy val columnSpan = ModifierVar[HtmlElement, Int](StyleVars.ColumnSpan)

  /** @see [[package.StyleVars.ColumnWidth]] */
  lazy val columnWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.ColumnWidth)

  /** @see [[package.StyleVars.ColumnRule]] */
  lazy val columnRule = ModifierVar[HtmlElement, String](StyleVars.ColumnRule)

  /** @see [[package.StyleVars.ColumnRuleColor]] */
  lazy val columnRuleColor = ModifierVar[HtmlElement, String](StyleVars.ColumnRuleColor)

  /** @see [[package.StyleVars.ColumnRuleWidth]] */
  lazy val columnRuleWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.ColumnRuleWidth)

  /** @see [[package.StyleVars.ColumnRuleStyle]] */
  lazy val columnRuleStyle = ModifierVar[HtmlElement, String](StyleVars.ColumnRuleStyle)

  /** @see [[package.StyleVars.ContentCss]] */
  lazy val contentCss = ModifierVar[HtmlElement, String](StyleVars.ContentCss)

  /** @see [[package.StyleVars.CounterIncrement]] */
  lazy val counterIncrement = ModifierVar[HtmlElement, String](StyleVars.CounterIncrement)

  /** @see [[package.StyleVars.CounterReset]] */
  lazy val counterReset = ModifierVar[HtmlElement, String](StyleVars.CounterReset)

  /** @see [[package.StyleVars.Cursor]] */
  lazy val cursor = ModifierVar[HtmlElement, String](StyleVars.Cursor)

  /** @see [[package.StyleVars.Direction]] */
  lazy val direction = ModifierVar[HtmlElement, String](StyleVars.Direction)

  /** @see [[package.StyleVars.Display]] */
  lazy val display = ModifierVar[HtmlElement, StyleVars.PossibleDisplays](StyleVars.Display)

  /** @see [[package.StyleVars.EmptyCells]] */
  lazy val emptyCells = ModifierVar[HtmlElement, String](StyleVars.EmptyCells)

  /** @see [[package.StyleVars.Flex]] */
  lazy val flex = ModifierVar[HtmlElement, String](StyleVars.Flex)

  /** @see [[package.StyleVars.FlexBasis]] */
  lazy val flexBasis = ModifierVar[HtmlElement, String](StyleVars.FlexBasis)

  /** @see [[package.StyleVars.FlexDirection]] */
  lazy val flexDirection = ModifierVar[HtmlElement, String](StyleVars.FlexDirection)

  /** @see [[package.StyleVars.FlexGrow]] */
  lazy val flexGrow = ModifierVar[HtmlElement, Double](StyleVars.FlexGrow)

  /** @see [[package.StyleVars.FlexShrink]] */
  lazy val flexShrink = ModifierVar[HtmlElement, Double](StyleVars.FlexShrink)

  /** @see [[package.StyleVars.FlexWrap]] */
  lazy val flexWrap = ModifierVar[HtmlElement, String](StyleVars.FlexWrap)

  /** @see [[package.StyleVars.Float]] */
  lazy val float = ModifierVar[HtmlElement, String](StyleVars.Float)

  /** @see [[package.StyleVars.Font]] */
  lazy val font = ModifierVar[HtmlElement, String](StyleVars.Font)

  /** @see [[package.StyleVars.FontFamily]] */
  lazy val fontFamily = ModifierVar[HtmlElement, String](StyleVars.FontFamily)

  /** @see [[package.StyleVars.FontFeatureSettings]] */
  lazy val fontFeatureSettings = ModifierVar[HtmlElement, String](StyleVars.FontFeatureSettings)

  /** @see [[package.StyleVars.FontSize]] */
  lazy val fontSize = ModifierVar[HtmlElement, StylableSize](StyleVars.FontSize)

  /** @see [[package.StyleVars.FontSizeAdjust]] */
  lazy val fontSizeAdjust = ModifierVar[HtmlElement, Double](StyleVars.FontSizeAdjust)

  /** @see [[package.StyleVars.FontStyle]] */
  lazy val fontStyle = ModifierVar[HtmlElement, String](StyleVars.FontStyle)

  /** @see [[package.StyleVars.FontWeight]] */
  lazy val fontWeight = ModifierVar[HtmlElement, String](StyleVars.FontWeight)

  /** @see [[package.StyleVars.Height]] */
  lazy val height = ModifierVar[HtmlElement, StylableSize](StyleVars.Height)

  /** @see [[package.StyleVars.Isolation]] */
  lazy val isolation = ModifierVar[HtmlElement, String](StyleVars.Isolation)

  /** @see [[package.StyleVars.JustifyContent]] */
  lazy val justifyContent = ModifierVar[HtmlElement, String](StyleVars.JustifyContent)

  /** @see [[package.StyleVars.Left]] */
  lazy val left = ModifierVar[HtmlElement, StylableSize](StyleVars.Left)

  /** @see [[package.StyleVars.LetterSpacing]] */
  lazy val letterSpacing = ModifierVar[HtmlElement, String](StyleVars.LetterSpacing)

  /** @see [[package.StyleVars.LineHeight]] */
  lazy val lineHeight = ModifierVar[HtmlElement, StylableSize](StyleVars.LineHeight)

  /** @see [[package.StyleVars.ListStyle]] */
  lazy val listStyle = ModifierVar[HtmlElement, String](StyleVars.ListStyle)

  /** @see [[package.StyleVars.ListStyleImage]] */
  lazy val listStyleImage = ModifierVar[HtmlElement, String](StyleVars.ListStyleImage)

  /** @see [[package.StyleVars.ListStylePosition]] */
  lazy val listStylePosition = ModifierVar[HtmlElement, String](StyleVars.ListStylePosition)

  /** @see [[package.StyleVars.ListStyleType]] */
  lazy val listStyleType = ModifierVar[HtmlElement, String](StyleVars.ListStyleType)

  /** @see [[package.StyleVars.Margin]] */
  lazy val margin = ModifierVar[HtmlElement, StylableSize](StyleVars.Margin)

  /** @see [[package.StyleVars.MarginTop]] */
  lazy val marginTop = ModifierVar[HtmlElement, StylableSize](StyleVars.MarginTop)

  /** @see [[package.StyleVars.MarginRight]] */
  lazy val marginRight = ModifierVar[HtmlElement, StylableSize](StyleVars.MarginRight)

  /** @see [[package.StyleVars.MarginBottom]] */
  lazy val marginBottom = ModifierVar[HtmlElement, StylableSize](StyleVars.MarginBottom)

  /** @see [[package.StyleVars.MarginLeft]] */
  lazy val marginLeft = ModifierVar[HtmlElement, StylableSize](StyleVars.MarginLeft)

  /** @see [[package.StyleVars.Mask]] */
  lazy val mask = ModifierVar[HtmlElement, String](StyleVars.Mask)

  /** @see [[package.StyleVars.MaxHeight]] */
  lazy val maxHeight = ModifierVar[HtmlElement, StylableSize](StyleVars.MaxHeight)

  /** @see [[package.StyleVars.MaxWidth]] */
  lazy val maxWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.MaxWidth)

  /** @see [[package.StyleVars.MinHeight]] */
  lazy val minHeight = ModifierVar[HtmlElement, StylableSize](StyleVars.MinHeight)

  /** @see [[package.StyleVars.MinWidth]] */
  lazy val minWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.MinWidth)

  /** @see [[package.StyleVars.MixBlendMode]] */
  lazy val mixBlendMode = ModifierVar[HtmlElement, String](StyleVars.MixBlendMode)

  /** @see [[package.StyleVars.Opacity]] */
  lazy val opacity = ModifierVar[HtmlElement, Double](StyleVars.Opacity)

  /** @see [[package.StyleVars.Orphans]] */
  lazy val orphans = ModifierVar[HtmlElement, Int](StyleVars.Orphans)

  /** @see [[package.StyleVars.Outline]] */
  lazy val outline = ModifierVar[HtmlElement, String](StyleVars.Outline)

  /** @see [[package.StyleVars.OutlineStyle]] */
  lazy val outlineStyle = ModifierVar[HtmlElement, String](StyleVars.OutlineStyle)

  /** @see [[package.StyleVars.OutlineWidth]] */
  lazy val outlineWidth = ModifierVar[HtmlElement, StylableSize](StyleVars.OutlineWidth)

  /** @see [[package.StyleVars.OutlineColor]] */
  lazy val outlineColor = ModifierVar[HtmlElement, String](StyleVars.OutlineColor)

  /** @see [[package.StyleVars.Overflow]] */
  lazy val overflow = ModifierVar[HtmlElement, String](StyleVars.Overflow)

  /** @see [[package.StyleVars.OverflowX]] */
  lazy val overflowX = ModifierVar[HtmlElement, String](StyleVars.OverflowX)

  /** @see [[package.StyleVars.OverflowY]] */
  lazy val overflowY = ModifierVar[HtmlElement, String](StyleVars.OverflowY)

  /** @see [[package.StyleVars.OverflowWrap]] */
  lazy val overflowWrap = ModifierVar[HtmlElement, String](StyleVars.OverflowWrap)

  /** @see [[package.StyleVars.Padding]] */
  lazy val padding = ModifierVar[HtmlElement, StylableSize](StyleVars.Padding)

  /** @see [[package.StyleVars.PaddingTop]] */
  lazy val paddingTop = ModifierVar[HtmlElement, StylableSize](StyleVars.PaddingTop)

  /** @see [[package.StyleVars.PaddingRight]] */
  lazy val paddingRight = ModifierVar[HtmlElement, StylableSize](StyleVars.PaddingRight)

  /** @see [[package.StyleVars.PaddingBottom]] */
  lazy val paddingBottom = ModifierVar[HtmlElement, StylableSize](StyleVars.PaddingBottom)

  /** @see [[package.StyleVars.PaddingLeft]] */
  lazy val paddingLeft = ModifierVar[HtmlElement, StylableSize](StyleVars.PaddingLeft)

  /** @see [[package.StyleVars.PageBreakAfter]] */
  lazy val pageBreakAfter = ModifierVar[HtmlElement, String](StyleVars.PageBreakAfter)

  /** @see [[package.StyleVars.PageBreakBefore]] */
  lazy val pageBreakBefore = ModifierVar[HtmlElement, String](StyleVars.PageBreakBefore)

  /** @see [[package.StyleVars.PageBreakInside]] */
  lazy val pageBreakInside = ModifierVar[HtmlElement, String](StyleVars.PageBreakInside)

  /** @see [[package.StyleVars.Perspective]] */
  lazy val perspective = ModifierVar[HtmlElement, String](StyleVars.Perspective)

  /** @see [[package.StyleVars.PerspectiveOrigin]] */
  lazy val perspectiveOrigin = ModifierVar[HtmlElement, String](StyleVars.PerspectiveOrigin)

  /** @see [[package.StyleVars.PointerEvents]] */
  lazy val pointerEvents = ModifierVar[HtmlElement, String](StyleVars.PointerEvents)

  /** @see [[package.StyleVars.Position]] */
  lazy val position = ModifierVar[HtmlElement, String](StyleVars.Position)

  /** @see [[package.StyleVars.Quotes]] */
  lazy val quotes = ModifierVar[HtmlElement, String](StyleVars.Quotes)

  /** @see [[package.StyleVars.Resize]] */
  lazy val resize = ModifierVar[HtmlElement, String](StyleVars.Resize)

  /** @see [[package.StyleVars.Right]] */
  lazy val right = ModifierVar[HtmlElement, StylableSize](StyleVars.Right)

  /** @see [[package.StyleVars.TableLayout]] */
  lazy val tableLayout = ModifierVar[HtmlElement, String](StyleVars.TableLayout)

  /** @see [[package.StyleVars.TextAlign]] */
  lazy val textAlign = ModifierVar[HtmlElement, String](StyleVars.TextAlign)

  /** @see [[package.StyleVars.TextAlignLast]] */
  lazy val textAlignLast = ModifierVar[HtmlElement, String](StyleVars.TextAlignLast)

  /** @see [[package.StyleVars.TextDecoration]] */
  lazy val textDecoration = ModifierVar[HtmlElement, String](StyleVars.TextDecoration)

  /** @see [[package.StyleVars.TextIndent]] */
  lazy val textIndent = ModifierVar[HtmlElement, String](StyleVars.TextIndent)

  /** @see [[package.StyleVars.TextOverflow]] */
  lazy val textOverflow = ModifierVar[HtmlElement, String](StyleVars.TextOverflow)

  /** @see [[package.StyleVars.TextShadow]] */
  lazy val textShadow = ModifierVar[HtmlElement, String](StyleVars.TextShadow)

  /** @see [[package.StyleVars.TextTransform]] */
  lazy val textTransform = ModifierVar[HtmlElement, String](StyleVars.TextTransform)

  /** @see [[package.StyleVars.TextUnderlinePosition]] */
  lazy val textUnderlinePosition = ModifierVar[HtmlElement, String](StyleVars.TextUnderlinePosition)

  /** @see [[package.StyleVars.Top]] */
  lazy val top = ModifierVar[HtmlElement, StylableSize](StyleVars.Top)

  /** @see [[package.StyleVars.Transform]] */
  lazy val transform = ModifierVar[HtmlElement, String](StyleVars.Transform)

  /** @see [[package.StyleVars.TransformOrigin]] */
  lazy val transformOrigin = ModifierVar[HtmlElement, String](StyleVars.TransformOrigin)

  /** @see [[package.StyleVars.TransformStyle]] */
  lazy val transformStyle = ModifierVar[HtmlElement, String](StyleVars.TransformStyle)

  /** @see [[package.StyleVars.Transition]] */
  lazy val transition = ModifierVar[HtmlElement, String](StyleVars.Transition)

  /** @see [[package.StyleVars.TransitionDelay]] */
  lazy val transitionDelay = ModifierVar[HtmlElement, String](StyleVars.TransitionDelay)

  /** @see [[package.StyleVars.TransitionDuration]] */
  lazy val transitionDuration = ModifierVar[HtmlElement, String](StyleVars.TransitionDuration)

  /** @see [[package.StyleVars.TransitionTimingFunction]] */
  lazy val transitionTimingFunction = ModifierVar[HtmlElement, String](StyleVars.TransitionTimingFunction)

  /** @see [[package.StyleVars.TransitionProperty]] */
  lazy val transitionProperty = ModifierVar[HtmlElement, String](StyleVars.TransitionProperty)

  /** @see [[package.StyleVars.UnicodeBidi]] */
  lazy val unicodeBidi = ModifierVar[HtmlElement, String](StyleVars.UnicodeBidi)

  /** @see [[package.StyleVars.VerticalAlign]] */
  lazy val verticalAlign = ModifierVar[HtmlElement, String](StyleVars.VerticalAlign)

  /** @see [[package.StyleVars.Visibility]] */
  lazy val visibility = ModifierVar[HtmlElement, String](StyleVars.Visibility)

  /** @see [[package.StyleVars.Width]] */
  lazy val width = ModifierVar[HtmlElement, StylableSize](StyleVars.Width)

  /** @see [[package.StyleVars.WhiteSpace]] */
  lazy val whiteSpace = ModifierVar[HtmlElement, String](StyleVars.WhiteSpace)

  /** @see [[package.StyleVars.Widows]] */
  lazy val widows = ModifierVar[HtmlElement, Int](StyleVars.Widows)

  /** @see [[package.StyleVars.WordBreak]] */
  lazy val wordBreak = ModifierVar[HtmlElement, String](StyleVars.WordBreak)

  /** @see [[package.StyleVars.WordSpacing]] */
  lazy val wordSpacing = ModifierVar[HtmlElement, String](StyleVars.WordSpacing)

  /** @see [[package.StyleVars.ZIndex]] */
  lazy val zIndex = ModifierVar[HtmlElement, Int](StyleVars.ZIndex)

  extension (elem: HtmlElement) {

    /** The all shorthand CSS property resets all of an element's properties except unicode-bidi, direction, and CSS Custom Properties. It
      * can set properties to their initial or inherited values, or to the values specified in another stylesheet origin.
      *
      * Note: IE does not support this property
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/all
      */
    def all = StyleVars.All.forInstance(elem)

    /** The animation CSS property is a shorthand property for animation-name, animation-duration, animation-timing-function,
      * animation-delay, animation-iteration-count and animation-direction.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation
      */
    def animation = StyleVars.Animation.forInstance(elem)

    /** The animation-delay CSS property specifies when the animation should start. This lets the animation sequence begin some time after
      * it's applied to an element.
      *
      * A value of 0s, which is the default value of the property, indicates that the animation should begin as soon as it's applied.
      * Otherwise, the value specifies an offset from the moment the animation is applied to the element; animation will begin that amount
      * of time after being applied.
      *
      * Specifying a negative value for the animation delay causes the animation to begin executing immediately. However, it will appear to
      * have begun executing partway through its cycle. For example, if you specify -1s as the animation delay time, the animation will
      * begin immediately but will start 1 second into the animation sequence.
      *
      * If you specify a negative value for the animation delay, but the starting value is implicit, the starting value is taken from the
      * moment the animation is applied to the element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-delay
      */
    def animationDelay = StyleVars.AnimationDelay.forInstance(elem)

    /** The animation-direction CSS property indicates whether the animation should play in reverse on alternate cycles.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-direction
      */
    def animationDirection = StyleVars.AnimationDirection.forInstance(elem)

    /** The animation-duration CSS property specifies the Length of time that an animation should take to complete one cycle.
      *
      * A value of 0s, which is the default value, indicates that no animation should occur.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-duration
      */
    def animationDuration = StyleVars.AnimationDuration.forInstance(elem)

    /** The animation-fill-mode CSS property specifies how a CSS animation should apply styles to its target before and after it is
      * executing.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-fill-mode
      */
    def animationFillMode = StyleVars.AnimationFillMode.forInstance(elem)

    /** The animation-iteration-count CSS property defines the number of times an animation cycle should be played before stopping.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-iteration-count
      */
    def animationIterationCount = StyleVars.AnimationIterationCount.forInstance(elem)

    /** The animation-name CSS property specifies a list of animations that should be applied to the selected element. Each name indicates a
      * @keyframes at-rule that defines the property values for the animation sequence.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-name
      */
    def animationName = StyleVars.AnimationName.forInstance(elem)

    /** The animation-play-state CSS property determines whether an animation is running or paused. You can query this property's value to
      * determine whether or not the animation is currently running; in addition, you can set its value to pause and resume playback of an
      * animation.
      *
      * Resuming a paused animation will start the animation from where it left off at the time it was paused, rather than starting over
      * from the beginning of the animation sequence.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-play-state
      */
    def animationPlayState = StyleVars.AnimationPlayState.forInstance(elem)

    /** The CSS animation-timing-function property specifies how a CSS animation should progress over the duration of each cycle. The
      * possible values are one or several `<timing-function>`.
      *
      * For keyframed animations, the timing function applies between keyframes rather than over the entire animation. In other words, the
      * timing function is applied at the start of the keyframe and at the end of the keyframe.
      *
      * An animation timing function defined within a keyframe block applies to that keyframe; otherwise. If no timing function is specified
      * for the keyframe, the timing function specified for the overall animation is used.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/animation-timing-function
      */
    def animationTimingFunction = StyleVars.AnimationTimingFunction.forInstance(elem)

    /** The CSS align-content property sets the distribution of space between and around content items along a flexbox's cross-axis or a
      * grid's block axis.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-content
      */
    def alignContent = StyleVars.AlignContent.forInstance(elem)

    /** The CSS align-items property sets the align-self value on all direct children as a group. In Flexbox, it controls the alignment of
      * items on the Cross Axis. In Grid Layout, it controls the alignment of items on the Block Axis within their grid area.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-items
      */
    def alignItems = StyleVars.AlignItems.forInstance(elem)

    /** The align-self CSS property overrides a grid or flex item's align-items value. In Grid, it aligns the item inside the grid area. In
      * Flexbox, it aligns the item on the cross axis.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/align-self
      */
    def alignSelf = StyleVars.AlignSelf.forInstance(elem)

    /** The background CSS property is a shorthand for setting the individual background values in a single place in the style sheet.
      * background can be used to set the values for one or more of: background-clip, background-color, background-image, background-origin,
      * background-position, background-repeat, background-size, and background-attachment.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background
      */
    def background = StyleVars.Background.forInstance(elem)

    /** If a background-image is specified, the background-attachment CSS property determines whether that image's position is fixed within
      * the viewport, or scrolls along with its containing block.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-attachment
      */
    def backgroundAttachment = StyleVars.BackgroundAttachment.forInstance(elem)

    /** The background-clip CSS property specifies whether an element's background, either the color or image, extends underneath its
      * border.
      *
      * If there is no background image, this property has only visual effect when the border has transparent regions (because of
      * border-style) or partially opaque regions; otherwise the border covers up the difference.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-clip
      */
    def backgroundClip = StyleVars.BackgroundClip.forInstance(elem)

    /** The background-color CSS property sets the background color of an element, either through a color value or the keyword transparent.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-color
      */
    def backgroundColor = StyleVars.BackgroundColor.forInstance(elem)

    /** The background-image CSS property sets one or more background images on an element. The background images are drawn on stacking
      * context layers on top of each other. The first layer specified is drawn as if it is closest to the user. The borders of the element
      * are then drawn on top of them, and the background-color is drawn beneath them.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-image
      */
    def backgroundImage = StyleVars.BackgroundImage.forInstance(elem)

    /** The background-origin CSS property determines the background positioning area, that is the position of the origin of an image
      * specified using the background-image CSS property.
      *
      * Note that background-origin is ignored when background-attachment is fixed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-origin
      */
    def backgroundOrigin = StyleVars.BackgroundOrigin.forInstance(elem)

    /** The background-position CSS property sets the initial position, relative to the background position layer defined by
      * background-origin for each defined background image.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-position
      */
    def backgroundPosition = StyleVars.BackgroundPosition.forInstance(elem)

    /** The background-repeat CSS property defines how background images are repeated. A background image can be repeated along the
      * horizontal axis, the vertical axis, both, or not repeated at all. When the repetition of the image tiles doesn't let them exactly
      * cover the background, the way adjustments are done can be controlled by the author: by default, the last image is clipped, but the
      * different tiles can instead be re-sized, or space can be inserted between the tiles.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-repeat
      */
    def backgroundRepeat = StyleVars.BackgroundRepeat.forInstance(elem)

    /** The background-size CSS property specifies the size of the background images. The size of the image can be fully constrained or only
      * partially in order to preserve its intrinsic ratio.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/background-size
      */
    def backgroundSize = StyleVars.BackgroundSize.forInstance(elem)

    /** The CSS backface-visibility property determines whether or not the back face of the element is visible when facing the user. The
      * back face of an element always is a transparent background, letting, when visible, a mirror image of the front face be displayed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/backface-visibility
      */
    def backfaceVisibility = StyleVars.BackfaceVisibility.forInstance(elem)

    /** The border CSS property is a shorthand property for setting the individual border property values in a single place in the style
      * sheet. border can be used to set the values for one or more of: border-width, border-style, border-color.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border
      */
    def border = StyleVars.Border.forInstance(elem)

    /** The border-top CSS property is a shorthand that sets the values of border-top-color, border-top-style, and border-top-width. These
      * properties describe the top border of elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top
      */
    def borderTop = StyleVars.BorderTop.forInstance(elem)

    /** The border-right CSS property is a shorthand that sets the values of border-right-color, border-right-style, and border-right-width.
      * These properties describe the right border of elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right
      */
    def borderRight = StyleVars.BorderRight.forInstance(elem)

    /** The border-bottom CSS property is a shorthand that sets the values of border-bottom-color, border-bottom-style, and
      * border-bottom-width. These properties describe the bottom border of elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom
      */
    def borderBottom = StyleVars.BorderBottom.forInstance(elem)

    /** The border-left CSS property is a shorthand that sets the values of border-left-color, border-left-style, and border-left-width.
      * These properties describe the left border of elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left
      */
    def borderLeft = StyleVars.BorderLeft.forInstance(elem)

    /** The border-color CSS property is a shorthand for setting the color of the four sides of an element's border: border-top-color,
      * border-right-color, border-bottom-color, border-left-color.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-color
      */
    def borderColor = StyleVars.BorderColor.forInstance(elem)

    /** The border-top-color CSS property sets the color of the top border of an element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-color
      */
    def borderTopColor = StyleVars.BorderTopColor.forInstance(elem)

    /** The border-right-color CSS property sets the color of the right border of an element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-color
      */
    def borderRightColor = StyleVars.BorderRightColor.forInstance(elem)

    /** The border-bottom-color CSS property sets the color of the bottom border of an element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-color
      */
    def borderBottomColor = StyleVars.BorderBottomColor.forInstance(elem)

    /** The border-left-color CSS property sets the color of the left border of an element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-color
      */
    def borderLeftColor = StyleVars.BorderLeftColor.forInstance(elem)

    /** The border-image CSS property draws an image around a given element. It replaces the element's regular border.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-image
      */
    def borderImage = StyleVars.BorderImage.forInstance(elem)

    /** The border-style CSS property is a shorthand property for setting the line style for all four sides of the element's border.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-style
      */
    def borderStyle = StyleVars.BorderStyle.forInstance(elem)

    /** The border-top-style CSS property sets the line style of the top border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-style
      */
    def borderTopStyle = StyleVars.BorderTopStyle.forInstance(elem)

    /** The border-right-style CSS property sets the line style of the right border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-style
      */
    def borderRightStyle = StyleVars.BorderRightStyle.forInstance(elem)

    /** The border-bottom-style CSS property sets the line style of the bottom border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-style
      */
    def borderBottomStyle = StyleVars.BorderBottomStyle.forInstance(elem)

    /** The border-left-style CSS property sets the line style of the left border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-style
      */
    def borderLeftStyle = StyleVars.BorderLeftStyle.forInstance(elem)

    /** The border-width CSS property is a shorthand property for setting the width for all four sides of the element's border.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-width
      */
    def borderWidth = StyleVars.BorderWidth.forInstance(elem)

    /** The border-top-width CSS property sets the line width of the top border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-width
      */
    def borderTopWidth = StyleVars.BorderTopWidth.forInstance(elem)

    /** The border-right-width CSS property sets the line width of the right border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-right-width
      */
    def borderRightWidth = StyleVars.BorderRightWidth.forInstance(elem)

    /** The border-bottom-width CSS property sets the line width of the bottom border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-width
      */
    def borderBottomWidth = StyleVars.BorderBottomWidth.forInstance(elem)

    /** The border-left-width CSS property sets the line width of the left border of a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-left-width
      */
    def borderLeftWidth = StyleVars.BorderLeftWidth.forInstance(elem)

    /** The border-radius CSS property allows Web authors to define how rounded border corners are. The curve of each corner is defined
      * using one or two radii, defining its shape: circle or ellipse.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-radius
      */
    def borderRadius = StyleVars.BorderRadius.forInstance(elem)

    /** The border-top-left-radius CSS property sets the rounding of the top-left corner of the element. The rounding can be a circle or an
      * ellipse, or if one of the value is 0 no rounding is done and the corner is square.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-left-radius
      */
    def borderTopLeftRadius = StyleVars.BorderTopLeftRadius.forInstance(elem)

    /** The border-top-right-radius CSS property sets the rounding of the top-right corner of the element. The rounding can be a circle or
      * an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-right-radius
      */
    def borderTopRightRadius = StyleVars.BorderTopRightRadius.forInstance(elem)

    /** The border-bottom-right-radius CSS property sets the rounding of the bottom-right corner of the element. The rounding can be a
      * circle or an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-right-radius
      */
    def borderBottomRightRadius = StyleVars.BorderBottomRightRadius.forInstance(elem)

    /** The border-bottom-left-radius CSS property sets the rounding of the bottom-left corner of the element. The rounding can be a circle
      * or an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom-left-radius
      */
    def borderBottomLeftRadius = StyleVars.BorderBottomLeftRadius.forInstance(elem)

    /** The border-collapse CSS property selects a table's border model. This has a big influence on the look and style of the table cells.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-collapse
      */
    def borderCollapse = StyleVars.BorderCollapse.forInstance(elem)

    /** The border-spacing CSS property specifies the distance between the borders of adjacent cells (only for the separated borders model).
      * This is equivalent to the cellspacing attribute in presentational HTML, but an optional second value can be used to set different
      * horizontal and vertical spacing.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/border-spacing
      */
    def borderSpacing = StyleVars.BorderSpacing.forInstance(elem)

    /** The bottom CSS property participates in specifying the position of positioned elements.
      *
      * For absolutely positioned elements, that is those with position: absolute or position: fixed, it specifies the distance between the
      * bottom margin edge of the element and the bottom edge of its containing block.
      *
      * For relatively positioned elements, that is those with position: relative, it specifies the distance the element is moved above its
      * normal position.
      *
      * However, the top property overrides the bottom property, so if top is not auto, the computed value of bottom is the negative of the
      * computed value of top.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/bottom
      */
    def bottom = StyleVars.Bottom.forInstance(elem)

    /** The box-shadow CSS property describes one or more shadow effects as a comma-separated list. It allows casting a drop shadow from the
      * frame of almost any element. If a border-radius is specified on the element with a box shadow, the box shadow takes on the same
      * rounded corners. The z-ordering of multiple box shadows is the same as multiple text shadows (the first specified shadow is on top).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/box-shadow
      */
    def boxShadow = StyleVars.BoxShadow.forInstance(elem)

    /** The box-sizing CSS property is used to alter the default CSS box model used to calculate widths and heights of elements. It is
      * possible to use this property to emulate the behavior of browsers that do not correctly support the CSS box model specification.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/box-sizing
      */
    def boxSizing = StyleVars.BoxSizing.forInstance(elem)

    /** The caption-side CSS property positions the content of a table's caption on the specified side (top or bottom).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/caption-side
      */
    def captionSide = StyleVars.CaptionSide.forInstance(elem)

    /** The clear CSS property specifies whether an element can be next to floating elements that precede it or must be moved down (cleared)
      * below them.
      *
      * The clear property applies to both floating and non-floating elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/clear
      */
    def clear = StyleVars.Clear.forInstance(elem)

    /** The clip CSS property defines what portion of an element is visible. The clip property applies only to elements with
      * position:absolute.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/clip
      */
    def clip = StyleVars.Clip.forInstance(elem)

    /** The CSS color property sets the foreground color of an element's text content, and its decorations. It doesn't affect any other
      * characteristic of the element; it should really be called text-color and would have been named so, save for historical reasons.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/color
      */
    def color = StyleVars.Color.forInstance(elem)

    /** The columns CSS property is a shorthand property allowing to set both the column-width and the column-count properties at the same
      * time.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/columns
      */
    def columns = StyleVars.Columns.forInstance(elem)

    /** The column-count CSS property describes the number of columns of the element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-count
      */
    def columnCount = StyleVars.ColumnCount.forInstance(elem)

    /** The column-fill CSS property controls how contents are partitioned into columns. Contents are either balanced, which means that
      * contents in all columns will have the same height or, when using auto, just take up the room the content needs.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-fill
      */
    def columnFill = StyleVars.ColumnFill.forInstance(elem)

    /** The column-gap CSS property sets the size of the gap between columns for elements which are specified to display as a multi-column
      * element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-gap
      */
    def columnGap = StyleVars.ColumnGap.forInstance(elem)

    /** The column-span CSS property makes it possible for an element to span across all columns when its value is set to `all`. An element
      * that spans more than one column is called a spanning element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-span
      */
    def columnSpan = StyleVars.ColumnSpan.forInstance(elem)

    /** The column-width CSS property suggests an optimal column width. This is not a absolute value but a mere hint. Browser will adjust
      * the width of the column around that suggested value, allowing to achieve scalable designs that fit different screen size. Especially
      * in presence of the column-count CSS property which has precedence, to set an exact column width, all Length values must be
      * specified. In horizontal text these are width, column-width, column-gap, and column-rule-width
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-width
      */
    def columnWidth = StyleVars.ColumnWidth.forInstance(elem)

    /** In multi-column layouts, the column-rule CSS property specifies a straight line, or "rule", to be drawn between each column. It is a
      * convenient shorthand to avoid setting each of the individual column-rule-* properties separately : column-rule-width,
      * column-rule-style and column-rule-color.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule
      */
    def columnRule = StyleVars.ColumnRule.forInstance(elem)

    /** The column-rule-color CSS property lets you set the color of the rule drawn between columns in multi-column layouts.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-color
      */
    def columnRuleColor = StyleVars.ColumnRuleColor.forInstance(elem)

    /** The column-rule-width CSS property lets you set the width of the rule drawn between columns in multi-column layouts.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-width
      */
    def columnRuleWidth = StyleVars.ColumnRuleWidth.forInstance(elem)

    /** The column-rule-style CSS property lets you set the style of the rule drawn between columns in multi-column layouts.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/column-rule-style
      */
    def columnRuleStyle = StyleVars.ColumnRuleStyle.forInstance(elem)

    /** The `content` CSS property is used with the ::before and ::after pseudo-elements to generate content in an element. Objects inserted
      * using the content property are anonymous replaced elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/content
      */
    def contentCss = StyleVars.ContentCss.forInstance(elem)

    /** The counter-increment CSS property is used to increase the value of CSS Counters by a given value. The counter's value can be reset
      * using the counter-reset CSS property.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/counter-increment
      */
    def counterIncrement = StyleVars.CounterIncrement.forInstance(elem)

    /** The counter-reset CSS property is used to reset CSS Counters to a given value.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/counter-reset
      */
    def counterReset = StyleVars.CounterReset.forInstance(elem)

    /** The cursor CSS property specifies the mouse cursor displayed when the mouse pointer is over an element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/cursor
      */
    def cursor = StyleVars.Cursor.forInstance(elem)

    /** Set the direction CSS property to match the direction of the text: rtl for Hebrew or Arabic text and ltr for other scripts. This is
      * typically done as part of the document (e.g., using the dir attribute in HTML) rather than through direct use of CSS.
      *
      * The property sets the base text direction of block-level elements and the direction of embeddings created by the unicode-bidi
      * property. It also sets the default alignment of text and block-level elements and the direction that cells flow within a table row.
      *
      * Unlike the dir attribute in HTML, the direction property is not inherited from table columns into table cells, since CSS inheritance
      * follows the document tree, and table cells are inside of the rows but not inside of the columns.
      *
      * The direction and unicode-bidi properties are the two only properties which are not affected by the all shorthand.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/direction
      */
    def direction = StyleVars.Direction.forInstance(elem)

    /** The display CSS property specifies the type of rendering box used for an element. In HTML, default display property values are taken
      * from behaviors described in the HTML specifications or from the browser/user default stylesheet. The default value in XML is inline.
      *
      * In addition to the many different display box types, the value none lets you turn off the display of an element; when you use none,
      * all descendant elements also have their display turned off. The document is rendered as though the element doesn't exist in the
      * document tree.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/display
      */
    def display = StyleVars.Display.forInstance(elem)

    /** The empty-cells CSS property specifies how user agents should render borders and backgrounds around cells that have no visible
      * content.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/empty-cells
      */
    def emptyCells = StyleVars.EmptyCells.forInstance(elem)

    /** The flex CSS property is a shorthand property specifying the ability of a flex item to alter its dimensions to fill available space.
      * Flex items can be stretched to use available space proportional to their flex grow factor or their flex shrink factor to prevent
      * overflow.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex
      */
    def flex = StyleVars.Flex.forInstance(elem)

    /** The CSS flex-basis property specifies the flex basis which is the initial main size of a flex item. The property determines the size
      * of the content-box unless specified otherwise using box-sizing.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis
      */
    def flexBasis = StyleVars.FlexBasis.forInstance(elem)

    /** The CSS flex-direction property specifies how flex items are placed in the flex container defining the main axis and the direction
      * (normal or reversed).
      *
      * Note that the value row and row-reverse are affected by the directionality of the flex container. If its dir attribute is ltr, row
      * represents the horizontal axis oriented from the left to the right, and row-reverse from the right to the left; if the dir attribute
      * is rtl, row represents the axis oriented from the right to the left, and row-reverse from the left to the right.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-direction
      */
    def flexDirection = StyleVars.FlexDirection.forInstance(elem)

    /** The CSS flex-grow property specifies the flex grow factor of a flex item.
      *
      * Default value is 0.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow
      */
    def flexGrow = StyleVars.FlexGrow.forInstance(elem)

    /** The CSS flex-shrink property specifies the flex shrink factor of a flex item.
      *
      * Default value is 1.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-shrink
      */
    def flexShrink = StyleVars.FlexShrink.forInstance(elem)

    /** The CSS flex-wrap property specifies whether the children are forced into a single line or if the items can be flowed on multiple
      * lines.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/flex-wrap
      */
    def flexWrap = StyleVars.FlexWrap.forInstance(elem)

    /** The float CSS property specifies that an element should be taken from the normal flow and placed along the left or right side of its
      * container, where text and inline elements will wrap around it. A floating element is one where the computed value of float is not
      * `none`.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/float
      */
    def float = StyleVars.Float.forInstance(elem)

    /** The font CSS property is either a shorthand property for setting font-style, font-variant, font-weight, font-size, line-height and
      * font-family, or a way to set the element's font to a system font, using specific keywords.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font
      */
    def font = StyleVars.Font.forInstance(elem)

    /** The font-family CSS property allows for a prioritized list of font family names and/or generic family names to be specified for the
      * selected element. Unlike most other CSS properties, values are separated by a comma to indicate that they are alternatives. The
      * browser will select the first font on the list that is installed on the computer, or that can be downloaded using the information
      * provided by a @font-face at-rule.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-family
      */
    def fontFamily = StyleVars.FontFamily.forInstance(elem)

    /** The font-feature-settings CSS property allows control over advanced typographic features in OpenType fonts.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-feature-settings
      */
    def fontFeatureSettings = StyleVars.FontFeatureSettings.forInstance(elem)

    /** The font-size CSS property specifies the size of the font – specifically the desired height of glyphs from the font. Setting the
      * font size may, in turn, change the size of other items, since it is used to compute the value of em and ex Length units.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-size
      */
    def fontSize = StyleVars.FontSize.forInstance(elem)

    /** The font-size-adjust CSS property sets the size of lower-case letters relative to the current font size (which defines the size of
      * upper-case letters).
      *
      * This is useful since the legibility of fonts, especially at small sizes, is determined more by the size of lowercase letters than by
      * the size of capital letters. This can cause problems when the first-choice font-family is unavailable and its replacement has a
      * significantly different aspect ratio (the ratio of the size of lowercase letters to the size of the font).
      *
      * Note: As of Dec 2021, only Firefox supports this
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-size-adjust
      */
    def fontSizeAdjust = StyleVars.FontSizeAdjust.forInstance(elem)

    /** The font-style CSS property allows italic or oblique faces to be selected within a font-family.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-style
      */
    def fontStyle = StyleVars.FontStyle.forInstance(elem)

    /** The font-weight CSS property specifies the weight or boldness of the font. However, some fonts are not available in all weights;
      * some are available only on normal and bold.
      *
      * Numeric font weights for fonts that provide more than just normal and bold. If the exact weight given is unavailable, then 600-900
      * use the closest available darker weight (or, if there is none, the closest available lighter weight), and 100-500 use the closest
      * available lighter weight (or, if there is none, the closest available darker weight). This means that for fonts that provide only
      * normal and bold, 100-500 are normal, and 600-900 are bold.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/font-weight
      */
    def fontWeight = StyleVars.FontWeight.forInstance(elem)

    /** The height CSS property specifies the height of the content area of an element. The content area is inside the padding, border, and
      * margin of the element.
      *
      * The min-height and max-height properties override height.
      *
      * @see
      *   @see https://developer.mozilla.org/en-US/docs/Web/CSS/height
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/height
      */
    def height = StyleVars.Height.forInstance(elem)

    /** This property determines whether an element must create a new stacking context. It is especially helpful when used in conjunction
      * with mix-blend-mode and z-index.
      *
      * Allowed values: "isolate", "auto"
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/isolation
      */
    def isolation = StyleVars.Isolation.forInstance(elem)

    /** The CSS justify-content property defines how a browser distributes available space between and around elements when aligning flex
      * items in the main-axis of the current line. The alignment is done after the lengths and auto margins are applied, meaning that, if
      * there is at least one flexible element, with flex-grow different than 0, it will have no effect as there won't be any available
      * space.
      *
      * @see
      *   https://css-tricks.com/snippets/css/a-guide-to-flexbox/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/justify-content
      */
    def justifyContent = StyleVars.JustifyContent.forInstance(elem)

    /** The left CSS property specifies part of the position of positioned elements.
      *
      * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the left
      * margin edge of the element and the left edge of its containing block.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/left
      */
    def left = StyleVars.Left.forInstance(elem)

    /** The letter-spacing CSS property specifies spacing behavior between text characters.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/letter-spacing
      */
    def letterSpacing = StyleVars.LetterSpacing.forInstance(elem)

    /** On block level elements, the line-height CSS property specifies the minimal height of line boxes within the element.
      *
      * On non-replaced inline elements, line-height specifies the height that is used in the calculation of the line box height.
      *
      * On replaced inline elements, like buttons or other input element, line-height has no effect.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/line-height
      */
    def lineHeight = StyleVars.LineHeight.forInstance(elem)

    /** The list-style CSS property is a shorthand property for setting list-style-type, list-style-image and list-style-position.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style
      */
    def listStyle = StyleVars.ListStyle.forInstance(elem)

    /** The list-style-image CSS property sets the image that will be used as the list item marker.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-image
      */
    def listStyleImage = StyleVars.ListStyleImage.forInstance(elem)

    /** The list-style-position CSS property specifies the position of the marker box in the principal block box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-position
      */
    def listStylePosition = StyleVars.ListStylePosition.forInstance(elem)

    /** The list-style-type CSS property sets the marker (such as a disc, character, or custom counter style) of a list item element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type
      */
    def listStyleType = StyleVars.ListStyleType.forInstance(elem)

    /** The margin CSS property sets the margin for all four sides. It is a shorthand to avoid setting each side separately with the other
      * margin properties: margin-top, margin-right, margin-bottom and margin-left.
      *
      * Negative values are also allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin
      */
    def margin = StyleVars.Margin.forInstance(elem)

    /** The margin-top CSS property of an element sets the margin space required on the top of an element. A negative value is also allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-top
      */
    def marginTop = StyleVars.MarginTop.forInstance(elem)

    /** The margin-right CSS property of an element sets the margin space required on the right of an element. A negative value is also
      * allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-right
      */
    def marginRight = StyleVars.MarginRight.forInstance(elem)

    /** The margin-bottom CSS property of an element sets the margin space required on the bottom of an element. A negative value is also
      * allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-bottom
      */
    def marginBottom = StyleVars.MarginBottom.forInstance(elem)

    /** The margin-left CSS property of an element sets the margin space required on the left of an element. A negative value is also
      * allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/margin-left
      */
    def marginLeft = StyleVars.MarginLeft.forInstance(elem)

    /** If the value is a URI value, the element pointed to by the URI is used as an SVG mask.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/mask
      */
    def mask = StyleVars.Mask.forInstance(elem)

    /** The max-height CSS property is used to set the maximum height of a given element. It prevents the used value of the height property
      * from becoming larger than the value specified for max-height.
      *
      * max-height overrides height, but min-height overrides max-height.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/max-height
      */
    def maxHeight = StyleVars.MaxHeight.forInstance(elem)

    /** The max-width CSS property is used to set the maximum width of a given element. It prevents the used value of the width property
      * from becoming larger than the value specified for max-width.
      *
      * max-width overrides width, but min-width overrides max-width.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/max-width
      */
    def maxWidth = StyleVars.MaxWidth.forInstance(elem)

    /** The min-height CSS property is used to set the minimum height of a given element. It prevents the used value of the height property
      * from becoming smaller than the value specified for min-height.
      *
      * The value of min-height overrides both max-height and height.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/min-height
      */
    def minHeight = StyleVars.MinHeight.forInstance(elem)

    /** The min-width CSS property is used to set the minimum width of a given element. It prevents the used value of the width property
      * from becoming smaller than the value specified for min-width.
      *
      * The value of min-width overrides both max-width and width.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/min-width
      */
    def minWidth = StyleVars.MinWidth.forInstance(elem)

    /** This property sets how an element's content should blend with the content of the element's parent and the element's background.
      *
      * Note: not supported by Chrome on Android and Safari
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/mix-blend-mode
      */
    def mixBlendMode = StyleVars.MixBlendMode.forInstance(elem)

    /** The opacity CSS property specifies the transparency of an element, that is, the degree to which the background behind the element is
      * overlaid.
      *
      * The value applies to the element as a whole, including its contents, even though the value is not inherited by child elements. Thus,
      * an element and its contained children all have the same opacity relative to the element's background, even if the element and its
      * children have different opacities relative to one another.
      *
      * Using this property with a value different than 1 places the element in a new stacking context.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/opacity
      */
    def opacity = StyleVars.Opacity.forInstance(elem)

    /** The orphans CSS property refers to the minimum number of lines in a block container that must be left at the bottom of the page.
      * This property is normally used to control how page breaks occur.
      *
      * Note: Firefox does not support this property
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/orphans
      */
    def orphans = StyleVars.Orphans.forInstance(elem)

    /** The CSS outline property is a shorthand property for setting one or more of the individual outline properties outline-style,
      * outline-width and outline-color in a single rule. In most cases the use of this shortcut is preferable and more convenient.
      *
      * Outlines do not take up space, they are drawn above the content.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline
      */
    def outline = StyleVars.Outline.forInstance(elem)

    /** The outline-style CSS property is used to set the style of the outline of an element. An outline is a line that is drawn around
      * elements, outside the border edge, to make the element stand out.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-style
      */
    def outlineStyle = StyleVars.OutlineStyle.forInstance(elem)

    /** The outline-width CSS property is used to set the width of the outline of an element. An outline is a line that is drawn around
      * elements, outside the border edge, to make the element stand out.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-width
      */
    def outlineWidth = StyleVars.OutlineWidth.forInstance(elem)

    /** The outline-color CSS property sets the color of the outline of an element. An outline is a line that is drawn around elements,
      * outside the border edge, to make the element stand out.
      *
      * Note: "invert" is a special outline color you can use for high contrast.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/outline-color
      */
    def outlineColor = StyleVars.OutlineColor.forInstance(elem)

    /** The overflow CSS property specifies whether to clip content, render scroll bars or display overflow content of a block-level
      * element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow
      */
    def overflow = StyleVars.Overflow.forInstance(elem)

    /** The overflow-x CSS property specifies whether to clip content, render a scroll bar or display overflow content of a block-level
      * element, when it overflows at the left and right edges.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-x
      */
    def overflowX = StyleVars.OverflowX.forInstance(elem)

    /** The overflow-y CSS property specifies whether to clip content, render a scroll bar, or display overflow content of a block-level
      * element, when it overflows at the top and bottom edges.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-y
      */
    def overflowY = StyleVars.OverflowY.forInstance(elem)

    /** The overflow-wrap CSS property specifies whether or not the browser should insert line breaks within words to prevent text from
      * overflowing its content box.
      *
      * Alias for: [[wordWrap]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/overflow-wrap
      */
    def overflowWrap = StyleVars.OverflowWrap.forInstance(elem)

    /** The padding CSS property sets the required padding space on all sides of an element. The padding area is the space between the
      * content of the element and its border. Negative values are not allowed.
      *
      * The padding property is a shorthand to avoid setting each side separately (padding-top, padding-right, padding-bottom,
      * padding-left).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding
      */
    def padding = StyleVars.Padding.forInstance(elem)

    /** The padding-top CSS property of an element sets the padding space required on the top of an element. The padding area is the space
      * between the content of the element and its border. Contrary to margin-top values, negative values of padding-top are invalid.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-top
      */
    def paddingTop = StyleVars.PaddingTop.forInstance(elem)

    /** The padding-right CSS property of an element sets the padding space required on the right side of an element. The padding area is
      * the space between the content of the element and its border. Negative values are not allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-right
      */
    def paddingRight = StyleVars.PaddingRight.forInstance(elem)

    /** The padding-bottom CSS property of an element sets the height of the padding area at the bottom of an element. The padding area is
      * the space between the content of the element and it's border. Contrary to margin-bottom values, negative values of padding-bottom
      * are invalid.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-bottom
      */
    def paddingBottom = StyleVars.PaddingBottom.forInstance(elem)

    /** The padding-left CSS property of an element sets the padding space required on the left side of an element. The padding area is the
      * space between the content of the element and it's border. A negative value is not allowed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/padding-left
      */
    def paddingLeft = StyleVars.PaddingLeft.forInstance(elem)

    /** The page-break-after CSS property adjusts page breaks after the current element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-after
      */
    def pageBreakAfter = StyleVars.PageBreakAfter.forInstance(elem)

    /** The page-break-before CSS property adjusts page breaks before the current element.
      *
      * This properties applies to block elements that generate a box. It won't apply on an empty div that won't generate a box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-before
      */
    def pageBreakBefore = StyleVars.PageBreakBefore.forInstance(elem)

    /** The page-break-inside CSS property adjusts page breaks inside the current element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/page-break-inside
      */
    def pageBreakInside = StyleVars.PageBreakInside.forInstance(elem)

    /** The perspective CSS property determines the distance between the z=0 plane and the user in order to give to the 3D-positioned
      * element some perspective. Each 3D element with z>0 becomes larger; each 3D-element with z<0 becomes smaller. The strength of the
      * effect is determined by the value of this property.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/perspective
      */
    def perspective = StyleVars.Perspective.forInstance(elem)

    /** The perspective-origin CSS property determines the position the viewer is looking at. It is used as the vanishing point by the
      * perspective property.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/perspective-origin
      */
    def perspectiveOrigin = StyleVars.PerspectiveOrigin.forInstance(elem)

    /** The CSS property pointer-events allows authors to control under what circumstances (if any) a particular graphic element can become
      * the target of mouse events. When this property is unspecified, the same characteristics of the visiblePainted value apply to SVG
      * content.
      *
      * In addition to indicating that the element is not the target of mouse events, the value none instructs the mouse event to go
      * "through" the element and target whatever is "underneath" that element instead.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/pointer-events
      */
    def pointerEvents = StyleVars.PointerEvents.forInstance(elem)

    /** The position CSS property chooses alternative rules for positioning elements, designed to be useful for scripted animation effects.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/position
      */
    def position = StyleVars.Position.forInstance(elem)

    /** The quotes CSS property sets how the browser should render quotation marks that are added using the open-quotes or close-quotes
      * values of the CSS content property.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/quotes
      */
    def quotes = StyleVars.Quotes.forInstance(elem)

    /** The resize CSS property sets whether an element is resizable, and if so, in which direction(s).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/resize
      */
    def resize = StyleVars.Resize.forInstance(elem)

    /** The right CSS property specifies part of the position of positioned elements.
      *
      * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the right
      * margin edge of the element and the right edge of its containing block.
      *
      * The right property has no effect on non-positioned elements.
      *
      * When both the right CSS property and the left CSS property are defined, the position of the element is overspecified. In that case,
      * the left value has precedence when the container is left-to-right (that is that the right computed value is set to -left), and the
      * right value has precedence when the container is right-to-left (that is that the left computed value is set to -right).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/right
      */
    def right = StyleVars.Right.forInstance(elem)

    /** The table-layout CSS property sets the algorithm used to lay out `<table>` cells, rows, and columns.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/table-layout
      */
    def tableLayout = StyleVars.TableLayout.forInstance(elem)

    /** The text-align CSS property describes how inline content like text is aligned in its parent block element. text-align does not
      * control the alignment of block elements itself, only their inline content.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-align
      */
    def textAlign = StyleVars.TextAlign.forInstance(elem)

    /** The text-align-last CSS property describes how the last line of a block or a line, right before a forced line break, is aligned.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-align-last
      */
    def textAlignLast = StyleVars.TextAlignLast.forInstance(elem)

    /** The text-decoration CSS property is used to set the text formatting to underline, overline, line-through or blink.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-decoration
      */
    def textDecoration = StyleVars.TextDecoration.forInstance(elem)

    /** The text-indent CSS property specifies how much horizontal space should be left before the beginning of the first line of the text
      * content of an element. Horizontal spacing is with respect to the left (or right, for right-to-left layout) edge of the containing
      * block element's box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-indent
      */
    def textIndent = StyleVars.TextIndent.forInstance(elem)

    /** The text-overflow CSS property determines how overflowed content that is not displayed is signaled to the users. It can be clipped,
      * or display an ellipsis ('…', U+2026 HORIZONTAL ELLIPSIS) or a Web author-defined string.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-overflow
      */
    def textOverflow = StyleVars.TextOverflow.forInstance(elem)

    /** The text-shadow CSS property adds shadows to text. It accepts a comma-separated list of shadows to be applied to the text and
      * text-decorations of the element.
      *
      * Each shadow is specified as an offset from the text, along with optional color and blur radius values.
      *
      * Multiple shadows are applied front-to-back, with the first-specified shadow on top.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-shadow
      */
    def textShadow = StyleVars.TextShadow.forInstance(elem)

    /** The text-transform CSS property specifies how to capitalize an element's text. It can be used to make text appear in all-uppercase
      * or all-lowercase, or with each word capitalized.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-transform
      */
    def textTransform = StyleVars.TextTransform.forInstance(elem)

    /** The CSS text-underline-position property specifies the position of the underline which is set using the text-decoration property
      * underline value.
      *
      * This property inherits and is not reset by the text-decoration shorthand, allowing to easily set it globally for a given document.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/text-underline-position
      */
    def textUnderlinePosition = StyleVars.TextUnderlinePosition.forInstance(elem)

    /** The top CSS property specifies part of the position of positioned elements. It has no effect on non-positioned elements.
      *
      * For absolutely positioned elements (those with position: absolute or position: fixed), it specifies the distance between the top
      * margin edge of the element and the top edge of its containing block.
      *
      * For relatively positioned elements (those with position: relative), it specifies the amount the element is moved below its normal
      * position.
      *
      * When both top and bottom are specified, the element position is over-constrained and the top property has precedence: the computed
      * value of bottom is set to -top, while its specified value is ignored.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/top
      */
    def top = StyleVars.Top.forInstance(elem)

    /** The CSS transform property lets you modify the coordinate space of the CSS visual formatting model. Using it, elements can be
      * translated, rotated, scaled, and skewed according to the values set.
      *
      * If the property has a value different than none, a stacking context will be created. In that case the object will act as a
      * containing block for position: fixed elements that it contains.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform
      */
    def transform = StyleVars.Transform.forInstance(elem)

    /** The transform-origin CSS property lets you modify the origin for transformations of an element. For example, the transform-origin of
      * the rotate() function is the centre of rotation. (This property is applied by first translating the element by the negated value of
      * the property, then applying the element's transform, then translating by the property value.)
      *
      * Not explicitly set values are reset to their corresponding values.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform-origin
      */
    def transformOrigin = StyleVars.TransformOrigin.forInstance(elem)

    /** The transform-style CSS property determines if the children of the element are positioned in the 3D-space or are flattened in the
      * plane of the element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transform-style
      */
    def transformStyle = StyleVars.TransformStyle.forInstance(elem)

    /** The CSS transition property is a shorthand property for transition-property, transition-duration, transition-timing-function, and
      * transition-delay. It allows to define the transition between two states of an element. Different states may be defined using
      * pseudo-classes like :hover or :active or dynamically set using JavaScript.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition
      */
    def transition = StyleVars.Transition.forInstance(elem)

    /** The transition-delay CSS property specifies the amount of time to wait between a change being requested to a property that is to be
      * transitioned and the start of the transition effect.
      *
      * A value of 0s, or 0ms, indicates that the property will begin to animate its transition immediately when the value changes; positive
      * values will delay the start of the transition effect for the corresponding number of seconds. Negative values cause the transition
      * to begin immediately, but to cause the transition to seem to begin partway through the animation effect.
      *
      * You may specify multiple delays; each delay will be applied to the corresponding property as specified by the transition-property
      * property, which acts as a master list. If there are fewer delays specified than in the master list, missing values are set to the
      * initial value (0s). If there are more delays, the list is simply truncated to the right size. In both case the CSS declaration stays
      * valid.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-delay
      */
    def transitionDelay = StyleVars.TransitionDelay.forInstance(elem)

    /** The transition-duration CSS property specifies the number of seconds or milliseconds a transition animation should take to complete.
      * By default, the value is 0s, meaning that no animation will occur.
      *
      * You may specify multiple durations; each duration will be applied to the corresponding property as specified by the
      * transition-property property, which acts as a master list. If there are fewer durations specified than in the master list, the user
      * agent repeat the list of durations. If there are more durations, the list is simply truncated to the right size. In both case the
      * CSS declaration stays valid.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-duration
      */
    def transitionDuration = StyleVars.TransitionDuration.forInstance(elem)

    /** The CSS transition-timing-function property is used to describe how the intermediate values of the CSS properties being affected by
      * a transition effect are calculated. This in essence lets you establish an acceleration curve, so that the speed of the transition
      * can vary over its duration.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-timing-function
      */
    def transitionTimingFunction = StyleVars.TransitionTimingFunction.forInstance(elem)

    /** The transition-property CSS property is used to specify the names of CSS properties to which a transition effect should be applied.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/transition-property
      */
    def transitionProperty = StyleVars.TransitionProperty.forInstance(elem)

    /** The unicode-bidi CSS property together with the `direction` property relates to the handling of bidirectional text in a document.
      * For example, if a block of text contains both left-to-right and right-to-left text then the user-agent uses a complex Unicode
      * algorithm to decide how to display the text. This property overrides this algorithm and allows the developer to control the text
      * embedding.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/unicode-bidi
      */
    def unicodeBidi = StyleVars.UnicodeBidi.forInstance(elem)

    /** The vertical-align CSS property specifies the vertical alignment of an inline or table-cell box. It does not apply to block-level
      * elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/vertical-align
      */
    def verticalAlign = StyleVars.VerticalAlign.forInstance(elem)

    /** The visibility CSS property shows or hides an element without changing the layout of a document. The property can also hide rows or
      * columns in a `<table>`.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/visibility
      */
    def visibility = StyleVars.Visibility.forInstance(elem)

    /** The width CSS property specifies the width of the content area of an element. The content area is inside the padding, border, and
      * margin of the element.
      *
      * The min-width and max-width properties override width.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/width
      */
    def width = StyleVars.Width.forInstance(elem)

    /** The white-space CSS property is used to to describe how whitespace inside the element is handled.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/white-space
      */
    def whiteSpace = StyleVars.WhiteSpace.forInstance(elem)

    /** The widows CSS property defines how many minimum lines must be left on top of a new page, on a paged media. In typography, a widow
      * is the last line of a paragraph appearing alone at the top of a page. Setting the widows property allows to prevent widows to be
      * left.
      *
      * On a non-paged media, like screen, the widows CSS property has no effect.
      *
      * Note: Firefox does not support this property.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/widows
      */
    def widows = StyleVars.Widows.forInstance(elem)

    /** The word-break CSS property specifies whether or not the browser should insert line breaks wherever the text would otherwise
      * overflow its content box.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/word-break
      */
    def wordBreak = StyleVars.WordBreak.forInstance(elem)

    /** The word-spacing CSS property specifies spacing behavior between tags and words.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/word-spacing
      */
    def wordSpacing = StyleVars.WordSpacing.forInstance(elem)

    /** The z-index CSS property specifies the z-order of an element and its descendants. When elements overlap, z-order determines which
      * one covers the other. An element with a larger z-index generally covers an element with a lower one.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/CSS/z-index
      */
    def zIndex = StyleVars.ZIndex.forInstance(elem)
  }

}
