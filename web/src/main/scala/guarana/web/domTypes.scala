package guarana
package web

import org.scalajs.dom

opaque type Element >: dom.Element <: scalajs.js.Object = dom.Element
object Element {
  extension [Tag <: Element](tag: Tag) {
    def amend(modifiers: Modifier[Tag]*): ToolkitAction[Tag] = {
      modifiers.foreach(_.apply(tag))
      tag
    }
    inline def unwrap: dom.Element = tag
  }
}

opaque type HtmlElement <: Element = dom.HTMLElement

object HtmlElement {
  
  extension [Tag <: HtmlElement](tag: Tag) {
    inline def unwrap: dom.HTMLElement = tag
    // hoist dom manipulation methods here so that they support our HtmlElement


    /** Removes the element from the tree it belongs to. */
    inline def remove(): Unit = tag.unwrap.remove()

    /** Inserts a set of Node or DOMString objects in the children list of this Element's parent, just before this
      * Element. DOMString objects are inserted as equivalent Text nodes.
      */
    inline def before(nodes: (Element | String)*): Unit = tag.unwrap.before(nodes*)

    /** Inserts a set of Node or DOMString objects in the children list of the Element's parent, just after the Element.
      * DOMString objects are inserted as equivalent Text nodes.
      */
    inline def after(nodes: (Element | String)*): Unit = tag.unwrap.after(nodes*)

    /** Inserts a set of Node objects or DOMString objects after the last child of the Element. DOMString objects are
      * inserted as equivalent Text nodes.
      *
      * Differences from Node.appendChild():
      *
      *   - Element.append() allows you to also append DOMString objects, whereas Node.appendChild() only accepts Node
      *     objects.
      *   - Element.append() has no return value, whereas Node.appendChild() returns the appended Node object.
      *   - Element.append() can append several nodes and strings, whereas Node.appendChild() can only append one node.
      */
    inline def append(nodes: (Element | String)*): Unit = tag.unwrap.append(nodes*)

    /** Inserts a set of Node objects or DOMString objects before the first child of the Element. DOMString objects are
      * inserted as equivalent Text nodes.
      */
    inline def prepend(nodes: (Element | String)*): Unit = tag.unwrap.prepend(nodes*)

    /** hasChildNodes returns a Boolean value indicating whether the current Node has child nodes or not. */
    inline def hasChildNodes(): Boolean = tag.unwrap.hasChildNodes()

    /** Replaces one child Node of the current one with the second one given in parameter. */
    inline def replaceChild(newChild: Element, oldChild: Element): Element = tag.unwrap.replaceChild(newChild, oldChild).asInstanceOf[Element]

    /** Inserts the first Node given in a parameter immediately before the second, child of this element, Node. */
    inline def insertBefore(newChild: Element, refChild: Element): Element = tag.unwrap.insertBefore(newChild, refChild).asInstanceOf[Element]

    /** Removes a child node from the current element, which must be a child of the current node. */
    inline def removeChild(oldChild: Element): Element = tag.unwrap.removeChild(oldChild).asInstanceOf[Element]

    /** Adds a node to the end of the list of children of a specified parent node. If the node already exists it is
      * removed from current parent node, then added to new parent node.
      */
    inline def appendChild(newChild: Element): Element = tag.unwrap.appendChild(newChild).asInstanceOf[Element]

    /** Replaces this Element in the children list of its parent with a set of Node or string objects. String objects are
      * inserted as equivalent Text nodes.
      */
    inline def replaceWith(nodes: (Element | String)*): Unit = tag.unwrap.replaceWith(nodes*)

    /** Returns the node immediately preceding the specified one in its parent's childNodes list, None if the specified
      * node is the first in that list.
      */
    def previousSibling: Option[Element] = tag.previousSibling match
      case e: dom.Element if e != null => Some(e.asInstanceOf[Element])
      case _ => None

    /** Returns the node immediately following the specified one in its parent's childNodes list, or None if the specified
      * node is the last node in that list.
      */
    def nextSibling: Option[Element] = tag.nextSibling match
      case e: dom.Element if e != null => Some(e.asInstanceOf[Element])
      case _ => None

    /** Returns a Node that is the parent of this node. If there is no such node, like if this node is the top of the tree
      * or if doesn't participate in a tree, this property returns None.
      */
    def parentNode: Option[Element] = Option(tag.parentElement)

    /** Returns the node's first child in the tree, or None if the node is childless. If the node is a Document, it
      * returns the first node in the list of its direct children.
      */
    def firstChild: Option[Element] = Option(tag.firstElementChild).asInstanceOf[Option[Element]]
      
    /** Returns a Node representing the last direct child node of the node, or null if the node has no child. */
    def lastChild: Option[Element] = Option(tag.lastElementChild).asInstanceOf[Option[Element]]
    

    // def idAttr = IdAttrVar.forInstance(tag)
    // def forAttr = ForAttrVar.forInstance(tag)
    // def labelAttr = LabelAttrVar.forInstance(tag)
    // def readOnlyProp = ReadOnlyVar.forInstance(tag)
  }
}


trait Modifier[-T] {
  def apply(t: T): ToolkitAction[Unit]
}
object Modifier {
  inline def apply[T](inline action: ToolkitAction[T => Unit]): Modifier[T] = new Modifier[T] {
    def apply(t: T): ToolkitAction[Unit] = action(t)
  }
  val empty: Modifier[Any] = apply(_ => ())
  given optModifier[T]: Conversion[Option[Modifier[T]], Modifier[T]] = {
    case None => empty
    case Some(value) => value
  }

  given seqModifier[T]: Conversion[Seq[Modifier[T]], Modifier[T]] = {
    case s if s.isEmpty => empty
    case seq => new Modifier[T] { def apply(t: T) = seq.foreach(_.apply(t)) }
  }

  given appendChild[ToAdd <: HtmlElement, Tag <: HtmlElement]: Conversion[ToAdd, Modifier[Tag]] = toAdd =>
    Modifier(parent => parent.append(toAdd))

  given appendText[T: RenderableText, Tag <: HtmlElement]: Conversion[T, Modifier[Tag]] = text =>
    Modifier(parent => parent.append(text.renderText()))

}

class ModifierVar[Base, T](val v: Var[T] { type ForInstance <: Base & Singleton}) {
  def :=(binding: Binding[T]): Modifier[Base] = Modifier(base => v.forInstance(base) := binding)
}

class ModifierEmitter[Base, T](val v: Emitter[T] { type ForInstance <: Base & Singleton}) {
  def :=(listener: EventIterator[T]): Modifier[Base] = Modifier(base => v.forInstance(base) := listener)
}

class TagCreator[Tag <: Element](tagName: String) {
  def apply(modifiers: Modifier[Tag]*): ToolkitAction[Tag] =
    Element.amend(dom.document.createElement(tagName).asInstanceOf[Tag])(modifiers*)
}

lazy val htmlRootTag: TagCreator[HtmlElement] = TagCreator("html")
opaque type HeadElement <: HtmlElement = dom.HTMLHeadElement
object HeadElement { extension (v: HeadElement) inline def unwrap: dom.HTMLHeadElement = v }
lazy val headTag: TagCreator[HeadElement] = TagCreator("head")
opaque type BaseElement <: HtmlElement = dom.HTMLBaseElement
object BaseElement { extension (v: BaseElement) inline def unwrap: dom.HTMLBaseElement = v }
lazy val baseTag: TagCreator[BaseElement] = TagCreator("base")
opaque type LinkElement <: HtmlElement = dom.HTMLLinkElement
object LinkElement { extension (v: LinkElement) inline def unwrap: dom.HTMLLinkElement = v }
lazy val linkTag: TagCreator[LinkElement] = TagCreator("link")
opaque type MetaElement <: HtmlElement = dom.HTMLMetaElement
object MetaElement { extension (v: MetaElement) inline def unwrap: dom.HTMLMetaElement = v }
lazy val metaTag: TagCreator[MetaElement] = TagCreator("meta")
opaque type ScriptElement <: HtmlElement = dom.HTMLScriptElement
object ScriptElement { extension (v: ScriptElement) inline def unwrap: dom.HTMLScriptElement = v }
lazy val scriptTag: TagCreator[ScriptElement] = TagCreator("script")
lazy val noScriptTag: TagCreator[HtmlElement] = TagCreator("noscript")
opaque type ImageElement <: HtmlElement = dom.HTMLImageElement
object ImageElement { extension (v: ImageElement) inline def unwrap: dom.HTMLImageElement = v }
lazy val img: TagCreator[ImageElement] = TagCreator("img")
opaque type IFrameElement <: HtmlElement = dom.HTMLIFrameElement
object IFrameElement { extension (v: IFrameElement) inline def unwrap: dom.HTMLIFrameElement = v }
lazy val iframe: TagCreator[IFrameElement] = TagCreator("iframe")
opaque type EmbedElement <: HtmlElement = dom.HTMLEmbedElement
object EmbedElement { extension (v: EmbedElement) inline def unwrap: dom.HTMLEmbedElement = v }
lazy val embedTag: TagCreator[EmbedElement] = TagCreator("embed")
opaque type ObjectElement <: HtmlElement = dom.HTMLObjectElement
object ObjectElement { extension (v: ObjectElement) inline def unwrap: dom.HTMLObjectElement = v }
lazy val objectTag: TagCreator[ObjectElement] = TagCreator("object")
opaque type ParamElement <: HtmlElement = dom.HTMLParamElement
object ParamElement { extension (v: ParamElement) inline def unwrap: dom.HTMLParamElement = v }
lazy val paramTag: TagCreator[ParamElement] = TagCreator("param")
opaque type VideoElement <: HtmlElement = dom.HTMLVideoElement
object VideoElement { extension (v: VideoElement) inline def unwrap: dom.HTMLVideoElement = v }
lazy val videoTag: TagCreator[VideoElement] = TagCreator("video")
opaque type AudioElement <: HtmlElement = dom.HTMLAudioElement
object AudioElement { extension (v: AudioElement) inline def unwrap: dom.HTMLAudioElement = v }
lazy val audioTag: TagCreator[AudioElement] = TagCreator("audio")
opaque type SourceElement <: HtmlElement = dom.HTMLSourceElement
object SourceElement { extension (v: SourceElement) inline def unwrap: dom.HTMLSourceElement = v }
lazy val sourceTag: TagCreator[SourceElement] = TagCreator("source")
opaque type TrackElement <: HtmlElement = dom.HTMLTrackElement
object TrackElement { extension (v: TrackElement) inline def unwrap: dom.HTMLTrackElement = v }
lazy val trackTag: TagCreator[TrackElement] = TagCreator("track")
opaque type CanvasElement <: HtmlElement = dom.HTMLCanvasElement
object CanvasElement { extension (v: CanvasElement) inline def unwrap: dom.HTMLCanvasElement = v }
lazy val canvasTag: TagCreator[CanvasElement] = TagCreator("canvas")
opaque type MapElement <: HtmlElement = dom.HTMLMapElement
object MapElement { extension (v: MapElement) inline def unwrap: dom.HTMLMapElement = v }
lazy val mapTag: TagCreator[MapElement] = TagCreator("map")
opaque type AreaElement <: HtmlElement = dom.HTMLAreaElement
object AreaElement { extension (v: AreaElement) inline def unwrap: dom.HTMLAreaElement = v }
lazy val areaTag: TagCreator[AreaElement] = TagCreator("area")
opaque type BodyElement <: HtmlElement = dom.HTMLBodyElement
object BodyElement { extension (v: BodyElement) inline def unwrap: dom.HTMLBodyElement = v }
lazy val bodyTag: TagCreator[BodyElement] = TagCreator("body")
lazy val headerTag: TagCreator[HtmlElement] = TagCreator("header")
lazy val footerTag: TagCreator[HtmlElement] = TagCreator("footer")
opaque type HeadingElement <: HtmlElement = dom.HTMLHeadingElement
object HeadingElement { extension (v: HeadingElement) inline def unwrap: dom.HTMLHeadingElement = v }
lazy val h1: TagCreator[HeadingElement] = TagCreator("h1")
lazy val h2: TagCreator[HeadingElement] = TagCreator("h2")
lazy val h3: TagCreator[HeadingElement] = TagCreator("h3")
lazy val h4: TagCreator[HeadingElement] = TagCreator("h4")
lazy val h5: TagCreator[HeadingElement] = TagCreator("h5")
lazy val h6: TagCreator[HeadingElement] = TagCreator("h6")
opaque type AnchorElement <: HtmlElement = dom.HTMLAnchorElement
object AnchorElement { extension (v: AnchorElement) inline def unwrap: dom.HTMLAnchorElement = v }
lazy val a: TagCreator[AnchorElement] = TagCreator("a")
lazy val emTag: TagCreator[HtmlElement] = TagCreator("em")
lazy val strong: TagCreator[HtmlElement] = TagCreator("strong")
lazy val small: TagCreator[HtmlElement] = TagCreator("small")
lazy val s: TagCreator[HtmlElement] = TagCreator("s")
lazy val cite: TagCreator[HtmlElement] = TagCreator("cite")
lazy val code: TagCreator[HtmlElement] = TagCreator("code")
lazy val sub: TagCreator[HtmlElement] = TagCreator("sub")
lazy val sup: TagCreator[HtmlElement] = TagCreator("sup")
lazy val i: TagCreator[HtmlElement] = TagCreator("i")
lazy val b: TagCreator[HtmlElement] = TagCreator("b")
lazy val u: TagCreator[HtmlElement] = TagCreator("u")
opaque type SpanElement <: HtmlElement = dom.HTMLSpanElement
object SpanElement { extension (v: SpanElement) inline def unwrap: dom.HTMLSpanElement = v }
lazy val span: TagCreator[SpanElement] = TagCreator("span")
opaque type BRElement <: HtmlElement = dom.HTMLBRElement
object BRElement { extension (v: BRElement) inline def unwrap: dom.HTMLBRElement = v }
lazy val br: TagCreator[BRElement] = TagCreator("br")
lazy val wbr: TagCreator[HtmlElement] = TagCreator("wbr")
opaque type ModElement <: HtmlElement = dom.HTMLModElement
object ModElement { extension (v: ModElement) inline def unwrap: dom.HTMLModElement = v }
lazy val ins: TagCreator[ModElement] = TagCreator("ins")
lazy val del: TagCreator[ModElement] = TagCreator("del")
opaque type FormElement <: HtmlElement = dom.HTMLFormElement
object FormElement { extension (v: FormElement) inline def unwrap: dom.HTMLFormElement = v }
lazy val form: TagCreator[FormElement] = TagCreator("form")
opaque type FieldSetElement <: HtmlElement = dom.HTMLFieldSetElement
object FieldSetElement { extension (v: FieldSetElement) inline def unwrap: dom.HTMLFieldSetElement = v }
lazy val fieldSet: TagCreator[FieldSetElement] = TagCreator("fieldset")
opaque type LegendElement <: HtmlElement = dom.HTMLLegendElement
object LegendElement { extension (v: LegendElement) inline def unwrap: dom.HTMLLegendElement = v }
lazy val legend: TagCreator[LegendElement] = TagCreator("legend")
opaque type LabelElement <: HtmlElement = dom.HTMLLabelElement
object LabelElement { extension (v: LabelElement) inline def unwrap: dom.HTMLLabelElement = v }
lazy val label: TagCreator[LabelElement] = TagCreator("label")
opaque type InputElement <: HtmlElement = dom.HTMLInputElement
object InputElement { extension (v: InputElement) inline def unwrap: dom.HTMLInputElement = v }
lazy val input: TagCreator[InputElement] = TagCreator("input")
opaque type ButtonElement <: HtmlElement = dom.HTMLButtonElement
object ButtonElement { extension (v: ButtonElement) inline def unwrap: dom.HTMLButtonElement = v }
lazy val button: TagCreator[ButtonElement] = TagCreator("button")
opaque type SelectElement <: HtmlElement = dom.HTMLSelectElement
object SelectElement { extension (v: SelectElement) inline def unwrap: dom.HTMLSelectElement = v }
lazy val select: TagCreator[SelectElement] = TagCreator("select")
opaque type DataListElement <: HtmlElement = dom.HTMLDataListElement
object DataListElement { extension (v: DataListElement) inline def unwrap: dom.HTMLDataListElement = v }
lazy val dataList: TagCreator[DataListElement] = TagCreator("datalist")
opaque type OptGroupElement <: HtmlElement = dom.HTMLOptGroupElement
object OptGroupElement { extension (v: OptGroupElement) inline def unwrap: dom.HTMLOptGroupElement = v }
lazy val optGroup: TagCreator[OptGroupElement] = TagCreator("optgroup")
opaque type OptionElement <: HtmlElement = dom.HTMLOptionElement
object OptionElement { extension (v: OptionElement) inline def unwrap: dom.HTMLOptionElement = v }
lazy val option: TagCreator[OptionElement] = TagCreator("option")
opaque type TextAreaElement <: HtmlElement = dom.HTMLTextAreaElement
object TextAreaElement { extension (v: TextAreaElement) inline def unwrap: dom.HTMLTextAreaElement = v }
lazy val textArea: TagCreator[TextAreaElement] = TagCreator("textarea")
opaque type ParagraphElement <: HtmlElement = dom.HTMLParagraphElement
object ParagraphElement { extension (v: ParagraphElement) inline def unwrap: dom.HTMLParagraphElement = v }
lazy val p: TagCreator[ParagraphElement] = TagCreator("p")
opaque type HRElement <: HtmlElement = dom.HTMLHRElement
object HRElement { extension (v: HRElement) inline def unwrap: dom.HTMLHRElement = v }
lazy val hr: TagCreator[HRElement] = TagCreator("hr")
opaque type PreElement <: HtmlElement = dom.HTMLPreElement
object PreElement { extension (v: PreElement) inline def unwrap: dom.HTMLPreElement = v }
lazy val pre: TagCreator[PreElement] = TagCreator("pre")
opaque type QuoteElement <: HtmlElement = dom.HTMLQuoteElement
object QuoteElement { extension (v: QuoteElement) inline def unwrap: dom.HTMLQuoteElement = v }
lazy val blockQuote: TagCreator[QuoteElement] = TagCreator("blockquote")
opaque type OListElement <: HtmlElement = dom.HTMLOListElement
object OListElement { extension (v: OListElement) inline def unwrap: dom.HTMLOListElement = v }
lazy val ol: TagCreator[OListElement] = TagCreator("ol")
opaque type UListElement <: HtmlElement = dom.HTMLUListElement
object UListElement { extension (v: UListElement) inline def unwrap: dom.HTMLUListElement = v }
lazy val ul: TagCreator[UListElement] = TagCreator("ul")
opaque type LIElement <: HtmlElement = dom.HTMLLIElement
object LIElement { extension (v: LIElement) inline def unwrap: dom.HTMLLIElement = v }
lazy val li: TagCreator[LIElement] = TagCreator("li")
opaque type DListElement <: HtmlElement = dom.HTMLDListElement
object DListElement { extension (v: DListElement) inline def unwrap: dom.HTMLDListElement = v }
lazy val dl: TagCreator[DListElement] = TagCreator("dl")
lazy val dt: TagCreator[HtmlElement] = TagCreator("dt")
lazy val dd: TagCreator[HtmlElement] = TagCreator("dd")
lazy val figure: TagCreator[HtmlElement] = TagCreator("figure")
lazy val figCaption: TagCreator[HtmlElement] = TagCreator("figcaption")
opaque type DivElement <: HtmlElement = dom.HTMLDivElement
object DivElement { extension (v: DivElement) inline def unwrap: dom.HTMLDivElement = v }
lazy val div: TagCreator[DivElement] = TagCreator("div")
opaque type TableElement <: HtmlElement = dom.HTMLTableElement
object TableElement { extension (v: TableElement) inline def unwrap: dom.HTMLTableElement = v }
lazy val table: TagCreator[TableElement] = TagCreator("table")
opaque type TableCaptionElement <: HtmlElement = dom.HTMLTableCaptionElement
object TableCaptionElement { extension (v: TableCaptionElement) inline def unwrap: dom.HTMLTableCaptionElement = v }
lazy val caption: TagCreator[TableCaptionElement] = TagCreator("caption")
opaque type TableColElement <: HtmlElement = dom.HTMLTableColElement
object TableColElement { extension (v: TableColElement) inline def unwrap: dom.HTMLTableColElement = v }
lazy val colGroup: TagCreator[TableColElement] = TagCreator("colgroup")
lazy val col: TagCreator[TableColElement] = TagCreator("col")
opaque type TableSectionElement <: HtmlElement = dom.HTMLTableSectionElement
object TableSectionElement { extension (v: TableSectionElement) inline def unwrap: dom.HTMLTableSectionElement = v }
lazy val tbody: TagCreator[TableSectionElement] = TagCreator("tbody")
lazy val thead: TagCreator[TableSectionElement] = TagCreator("thead")
lazy val tfoot: TagCreator[TableSectionElement] = TagCreator("tfoot")
opaque type TableRowElement <: HtmlElement = dom.HTMLTableRowElement
object TableRowElement { extension (v: TableRowElement) inline def unwrap: dom.HTMLTableRowElement = v }
lazy val tr: TagCreator[TableRowElement] = TagCreator("tr")
opaque type TableCellElement <: HtmlElement = dom.HTMLTableCellElement
object TableCellElement { extension (v: TableCellElement) inline def unwrap: dom.HTMLTableCellElement = v }
lazy val td: TagCreator[TableCellElement] = TagCreator("td")
lazy val th: TagCreator[TableCellElement] = TagCreator("th")
opaque type TitleElement <: HtmlElement = dom.HTMLTitleElement
object TitleElement { extension (v: TitleElement) inline def unwrap: dom.HTMLTitleElement = v }
lazy val titleTag: TagCreator[TitleElement] = TagCreator("title")
opaque type StyleElement <: HtmlElement = dom.HTMLStyleElement
object StyleElement { extension (v: StyleElement) inline def unwrap: dom.HTMLStyleElement = v }
lazy val styleTag: TagCreator[StyleElement] = TagCreator("style")
lazy val sectionTag: TagCreator[HtmlElement] = TagCreator("section")
lazy val navTag: TagCreator[HtmlElement] = TagCreator("nav")
lazy val articleTag: TagCreator[HtmlElement] = TagCreator("article")
lazy val asideTag: TagCreator[HtmlElement] = TagCreator("aside")
lazy val addressTag: TagCreator[HtmlElement] = TagCreator("address")
lazy val mainTag: TagCreator[HtmlElement] = TagCreator("main")
lazy val q: TagCreator[QuoteElement] = TagCreator("q")
lazy val dfn: TagCreator[HtmlElement] = TagCreator("dfn")
lazy val abbr: TagCreator[HtmlElement] = TagCreator("abbr")
lazy val dataTag: TagCreator[HtmlElement] = TagCreator("data")
lazy val timeTag: TagCreator[HtmlElement] = TagCreator("time")
lazy val varTag: TagCreator[HtmlElement] = TagCreator("var")
lazy val samp: TagCreator[HtmlElement] = TagCreator("samp")
lazy val kbd: TagCreator[HtmlElement] = TagCreator("kbd")
lazy val mathTag: TagCreator[HtmlElement] = TagCreator("math")
lazy val mark: TagCreator[HtmlElement] = TagCreator("mark")
lazy val ruby: TagCreator[HtmlElement] = TagCreator("ruby")
lazy val rt: TagCreator[HtmlElement] = TagCreator("rt")
lazy val rp: TagCreator[HtmlElement] = TagCreator("rp")
lazy val bdi: TagCreator[HtmlElement] = TagCreator("bdi")
lazy val bdo: TagCreator[HtmlElement] = TagCreator("bdo")
lazy val keyGenTag: TagCreator[HtmlElement] = TagCreator("keygen")
lazy val outputTag: TagCreator[HtmlElement] = TagCreator("output")
opaque type ProgressElement <: HtmlElement = dom.HTMLProgressElement
object ProgressElement { extension (v: ProgressElement) inline def unwrap: dom.HTMLProgressElement = v }
lazy val progressTag: TagCreator[ProgressElement] = TagCreator("progress")
lazy val meterTag: TagCreator[HtmlElement] = TagCreator("meter")
lazy val detailsTag: TagCreator[HtmlElement] = TagCreator("details")
lazy val summaryTag: TagCreator[HtmlElement] = TagCreator("summary")
lazy val commandTag: TagCreator[HtmlElement] = TagCreator("command")
opaque type MenuElement <: HtmlElement = dom.HTMLMenuElement
object MenuElement { extension (v: MenuElement) inline def unwrap: dom.HTMLMenuElement = v }
lazy val menuTag: TagCreator[MenuElement] = TagCreator("menu")
opaque type DialogElement <: HtmlElement = dom.HTMLDialogElement
object DialogElement { extension (v: DialogElement) inline def unwrap: dom.HTMLDialogElement = v }
lazy val dialogTag: TagCreator[DialogElement] = TagCreator("dialog")
