package guarana
package web

import guarana.web.utils.OnOff

import scalajs.js

/** Creates a Var view that uses a Boolean to indicate if these classes should be enabled or not */
def htmlClass(classes: String*): ExternalVar.Aux[HtmlElement, Boolean] = ExternalVar[HtmlElement, Boolean](
  classes.mkString(" "),
  node => {
    val cl = node.unwrap.classList
    classes.forall(cl.contains)
  },
  (node, v) => {
    val cl = node.unwrap.classList
    if (v) classes foreach cl.add
    else classes foreach cl.remove
  },
  eagerEvaluation = true
)

inline def HtmlAttr[T: Codec.To[String]](attrName: String): ExternalVar.Aux[HtmlElement, T] = {
  given StyleConversions.type = StyleConversions
  ExternalVar[HtmlElement, T](
    attrName,
    v => Codec.of[T].decode(v.unwrap.getAttribute(attrName)),
    (v, t) => v.unwrap.setAttribute(attrName, Codec.of[T].encode(t)),
    eagerEvaluation = true,
  )
}

object HtmlAttrs {

  /** Declares the character encoding of the page or script. Used on meta and script elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-charset
    */
  lazy val Charset = HtmlAttr[String]("charset")

  /** Indicates whether the element should be editable by the user. If so, the browser modifies its widget to allow editing.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/contentEditable
    */
  lazy val ContentEditable = HtmlAttr[Boolean]("contenteditable")

  /** Specifies a context menu for an element by its element id. The context menu appears when a user right-clicks on the element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/contextmenu
    */
  lazy val ContextMenuId = HtmlAttr[String]("contextmenu")

  /** Specifies whether the dragged data is copied, moved, or linked, when dropped Acceptable values: `copy` | `move` | `link`
    */
  lazy val DropZone = HtmlAttr[String]("dropzone")

  /** The `formaction` attribute provides the URL that will process the input control when the form is submitted and overrides the default
    * `action` attribute of the `form` element. This should be used only with `input` elements of `type` submit or image.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formaction
    */
  lazy val FormAction = HtmlAttr[String]("formaction")

  /** The form attribute specifies an ID of the form an `<input>` element belongs to. */
  lazy val FormId = HtmlAttr[String]("form")

  /** The `height` attribute specifies the pixel height of the following elements: `<canvas>, <embed>, <iframe>, <img>, <input
    * type="image">, <object>, <video>`
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-height
    */
  lazy val HeightAttr = HtmlAttr[Int]("height")

  /** This is the single required attribute for anchors defining a hypertext source link. It indicates the link target, either a URL or a
    * URL fragment. A URL fragment is a name preceded by a hash mark (#), which specifies an internal target location (an ID) within the
    * current document. URLs are not restricted to Web (HTTP)-based documents. URLs might use any protocol supported by the browser. For
    * example, file, ftp, and mailto work in most user agents.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-href
    */
  lazy val Href = HtmlAttr[String]("href")

  /** Identifies a list of pre-defined options to suggest to the user. The value must be the id of a [[FormTags.dataList]] element in the
    * same document. The browser displays only options that are valid values for this input element. This attribute is ignored when the type
    * attribute's value is hidden, checkbox, radio, file, or a button type.
    */
  lazy val ListId = HtmlAttr[String]("list")

  /** The max attribute specifies the maximum value for an `<input>` element of type number, range, date, datetime, datetime-local, month,
    * time, or week.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/max
    */
  lazy val MaxAttr = HtmlAttr[String]("max")

  /** The min attribute specifies the minimum value for an `<input>` element of type number, range, date, datetime, datetime-local, month,
    * time, or week.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/min
    */
  lazy val MinAttr = HtmlAttr[String]("min")

  /** Specifies the URL of an image for `<img>` tag, for `type="image"` input buttons, or the URL of some other network resources like
    * `<iframe>`.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#attr-src
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#src
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe#attr-src
    */
  lazy val Src = HtmlAttr[String]("src")

  /** The step attribute specifies the numeric intervals for an `<input>` element that should be considered legal for the input. For
    * example, if step is 2 on a number typed `<input>` then the legal numbers could be -2, 0, 2, 4, 6 etc. The step attribute should be
    * used in conjunction with the min and max attributes to specify the full range and interval of the legal values. The step attribute is
    * applicable to `<input>` elements of the following types: number, range, date, datetime, datetime-local, month, time and week.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/step
    */
  lazy val StepAttr = HtmlAttr[String]("step")

  /** This attribute is used to define the type of the content linked to. The value of the attribute should be a MIME type such as
    * text/html, text/css, and so on. The common use of this attribute is to define the type of style sheet linked and the most common
    * current value is text/css, which indicates a Cascading Style Sheet format. You can use tpe as an alias for this attribute so you don't
    * have to backtick-escape this attribute.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-type
    */
  lazy val Type = HtmlAttr[String]("type")

  /** This attribute contains CSS styling declarations to be applied to the element. Note that it is recommended for styles to be defined in
    * a separate file or files. This attribute and the style element have mainly the purpose of allowing for quick styling, for example for
    * testing purposes.
    */
  lazy val Style = HtmlAttr[String]("style")

  /** IE-specific property to prevent user selection */
  lazy val Unselectable = HtmlAttr[OnOff]("unselectable")

  /** The `width` attribute specifies the pixel width of the following elements: `<canvas>, <embed>, <iframe>, <img>, <input type="image">,
    * <object>, <video>`
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-width
    */
  lazy val WidthAttr = HtmlAttr[Int]("width")

}

object HtmlAttrsApi {

  /** @see [[package.HtmlAttrs.Charset]] */
  lazy val charset = ModifierVar[HtmlElement, String](HtmlAttrs.Charset)

  /** @see [[package.HtmlAttrs.ContentEditable]] */
  lazy val contentEditable = ModifierVar[HtmlElement, Boolean](HtmlAttrs.ContentEditable)

  /** @see [[package.HtmlAttrs.ContextMenuId]] */
  lazy val contextMenuId = ModifierVar[HtmlElement, String](HtmlAttrs.ContextMenuId)

  /** @see [[package.HtmlAttrs.DropZone]] */
  lazy val dropZone = ModifierVar[HtmlElement, String](HtmlAttrs.DropZone)

  /** @see [[package.HtmlAttrs.FormAction]] */
  lazy val formAction = ModifierVar[HtmlElement, String](HtmlAttrs.FormAction)

  /** @see [[package.HtmlAttrs.FormId]] */
  lazy val formId = ModifierVar[HtmlElement, String](HtmlAttrs.FormId)

  /** @see [[package.HtmlAttrs.HeightAttr]] */
  lazy val heightAttr = ModifierVar[HtmlElement, Int](HtmlAttrs.HeightAttr)

  /** @see [[package.HtmlAttrs.Href]] */
  lazy val href = ModifierVar[HtmlElement, String](HtmlAttrs.Href)

  /** @see [[package.HtmlAttrs.ListId]] */
  lazy val listId = ModifierVar[HtmlElement, String](HtmlAttrs.ListId)

  /** @see [[package.HtmlAttrs.MaxAttr]] */
  lazy val maxAttr = ModifierVar[HtmlElement, String](HtmlAttrs.MaxAttr)

  /** @see [[package.HtmlAttrs.MinAttr]] */
  lazy val minAttr = ModifierVar[HtmlElement, String](HtmlAttrs.MinAttr)

  /** @see [[package.HtmlAttrs.Src]] */
  lazy val src = ModifierVar[HtmlElement, String](HtmlAttrs.Src)

  /** @see [[package.HtmlAttrs.Style]] */
  lazy val style = ModifierVar[HtmlElement, String](HtmlAttrs.Style)

  /** @see [[package.HtmlAttrs.Type]] */
  lazy val `type` = ModifierVar[HtmlElement, String](HtmlAttrs.Type)
  lazy val tpe = `type`

  /** @see [[package.HtmlAttrs.Unselectable]] */
  lazy val unselectable = ModifierVar[HtmlElement, OnOff](HtmlAttrs.Unselectable)

  /** @see [[package.HtmlAttrs.WidthAttr]] */
  lazy val widthAttr = ModifierVar[HtmlElement, Int](HtmlAttrs.WidthAttr)

  extension (elem: HtmlElement) {

    /** Declares the character encoding of the page or script. Used on meta and script elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-charset
      */
    def charset = HtmlAttrs.Charset.forInstance(elem)

    /** Indicates whether the element should be editable by the user. If so, the browser modifies its widget to allow editing.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/contentEditable
      */
    def contentEditable = HtmlAttrs.ContentEditable.forInstance(elem)

    /** Specifies a context menu for an element by its element id. The context menu appears when a user right-clicks on the element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/contextmenu
      */
    def contextMenuId = HtmlAttrs.ContextMenuId.forInstance(elem)

    /** Specifies whether the dragged data is copied, moved, or linked, when dropped Acceptable values: `copy` | `move` | `link`
      */
    def dropZone = HtmlAttrs.DropZone.forInstance(elem)

    /** The `formaction` attribute provides the URL that will process the input control when the form is submitted and overrides the default
      * `action` attribute of the `form` element. This should be used only with `input` elements of `type` submit or image.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formaction
      */
    def formAction = HtmlAttrs.FormAction.forInstance(elem)

    /** The form attribute specifies an ID of the form an `<input>` element belongs to. */
    def formId = HtmlAttrs.FormId.forInstance(elem)

    /** The `height` attribute specifies the pixel height of the following elements: `<canvas>, <embed>, <iframe>, <img>, <input
      * type="image">, <object>, <video>`
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-height
      */
    def heightAttr = HtmlAttrs.HeightAttr.forInstance(elem)

    /** This is the single required attribute for anchors defining a hypertext source link. It indicates the link target, either a URL or a
      * URL fragment. A URL fragment is a name preceded by a hash mark (#), which specifies an internal target location (an ID) within the
      * current document. URLs are not restricted to Web (HTTP)-based documents. URLs might use any protocol supported by the browser. For
      * example, file, ftp, and mailto work in most user agents.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-href
      */
    def href = HtmlAttrs.Href.forInstance(elem)

    /** Identifies a list of pre-defined options to suggest to the user. The value must be the id of a [[FormTags.dataList]] element in the
      * same document. The browser displays only options that are valid values for this input element. This attribute is ignored when the
      * type attribute's value is hidden, checkbox, radio, file, or a button type.
      */
    def listId = HtmlAttrs.ListId.forInstance(elem)

    /** The max attribute specifies the maximum value for an `<input>` element of type number, range, date, datetime, datetime-local, month,
      * time, or week.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/max
      */
    def maxAttr = HtmlAttrs.MaxAttr.forInstance(elem)

    /** The min attribute specifies the minimum value for an `<input>` element of type number, range, date, datetime, datetime-local, month,
      * time, or week.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/min
      */
    def minAttr = HtmlAttrs.MinAttr.forInstance(elem)

    /** Specifies the URL of an image for `<img>` tag, for `type="image"` input buttons, or the URL of some other network resources like
      * `<iframe>`.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#attr-src
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#src
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe#attr-src
      */
    def src = HtmlAttrs.Src.forInstance(elem)

    /** The step attribute specifies the numeric intervals for an `<input>` element that should be considered legal for the input. For
      * example, if step is 2 on a number typed `<input>` then the legal numbers could be -2, 0, 2, 4, 6 etc. The step attribute should be
      * used in conjunction with the min and max attributes to specify the full range and interval of the legal values. The step attribute
      * is applicable to `<input>` elements of the following types: number, range, date, datetime, datetime-local, month, time and week.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/step
      */
    def stepAttr = HtmlAttrs.StepAttr.forInstance(elem)

    /** This attribute contains CSS styling declarations to be applied to the element. Note that it is recommended for styles to be defined
      * in a separate file or files. This attribute and the style element have mainly the purpose of allowing for quick styling, for example
      * for testing purposes.
      */
    def style = HtmlAttrs.Style.forInstance(elem)

    /** This attribute is used to define the type of the content linked to. The value of the attribute should be a MIME type such as
      * text/html, text/css, and so on. The common use of this attribute is to define the type of style sheet linked and the most common
      * current value is text/css, which indicates a Cascading Style Sheet format. You can use tpe as an alias for this attribute so you
      * don't have to backtick-escape this attribute.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-type
      */
    def `type` = HtmlAttrs.Type.forInstance(elem)

    /** This attribute is used to define the type of the content linked to. The value of the attribute should be a MIME type such as
      * text/html, text/css, and so on. The common use of this attribute is to define the type of style sheet linked and the most common
      * current value is text/css, which indicates a Cascading Style Sheet format. You can use tpe as an alias for this attribute so you
      * don't have to backtick-escape this attribute.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-type
      */
    def tpe = HtmlAttrs.Type.forInstance(elem)

    /** IE-specific property to prevent user selection */
    def unselectable = HtmlAttrs.Unselectable.forInstance(elem)

    /** The `width` attribute specifies the pixel width of the following elements: `<canvas>, <embed>, <iframe>, <img>, <input
      * type="image">, <object>, <video>`
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/object#attr-width
      */
    def widthAttr = HtmlAttrs.WidthAttr.forInstance(elem)
  }
}

// Props:

inline def HtmlProp[T, U](
    propName: String,
    codec: Codec[T, U],
    onFirstAssociation: HtmlElement => Unit = Var.doNothingOnFirstAssociation
): ExternalVar.Aux[HtmlElement, T] = {
  given StyleConversions.type = StyleConversions
  ExternalVar[HtmlElement, T](
    propName,
    v => {
      val p = v.asInstanceOf[js.Dynamic].selectDynamic(propName)
      if (js.isUndefined(p)) null.asInstanceOf[T]
      else codec.decode(p.asInstanceOf[U])
    },
    (v, t) => v.asInstanceOf[js.Dynamic].updateDynamic(propName)(codec.encode(t).asInstanceOf[js.Any]),
    eagerEvaluation = true,
    onFirstAssociation = onFirstAssociation
  )
}

inline def boolProp(name: String, onFirstAssociation: HtmlElement => Unit = Var.doNothingOnFirstAssociation): ExternalVar.Aux[HtmlElement, Boolean] =
  HtmlProp(name, Codec.BooleanAsIsCodec, onFirstAssociation)
inline def doubleProp(name: String, onFirstAssociation: HtmlElement => Unit = Var.doNothingOnFirstAssociation): ExternalVar.Aux[HtmlElement, Double] =
  HtmlProp(name, Codec.DoubleAsIsCodec, onFirstAssociation)
inline def intProp(name: String, onFirstAssociation: HtmlElement => Unit = Var.doNothingOnFirstAssociation): ExternalVar.Aux[HtmlElement, Int] =
  HtmlProp(name, Codec.IntAsIsCodec, onFirstAssociation)
inline def stringProp(name: String, onFirstAssociation: HtmlElement => Unit = Var.doNothingOnFirstAssociation): ExternalVar.Aux[HtmlElement, String] =
  HtmlProp(name, Codec.StringAsIsCodec, onFirstAssociation)

object HtmlProps {

  /** Impl: internal mutable var matching Resize Observer api for a given instance
    */
  private[web] lazy val MutDimensions = ExternalVar[HtmlElement, (Double, Double)](
    "size",
    v => {
      val r = v.unwrap.getBoundingClientRect()
      (r.width, r.height)
    },
    (v, t) => throw new UnsupportedOperationException("It's not valid to set the dimensions of an element directly"), // this is unreachable anyway, we only expose this as an ObsVal
    eagerEvaluation = true,
    onFirstAssociation = n => Toolkit.resizeObserver.observe(n.unwrap) // The following issue seems to imply that ResizeObserver uses weak references: https://github.com/w3c/csswg-drafts/issues/6397
  )

  /** A view into the classlist of an element. Note that this isn't a 1:1 tracking of classes because html doesn't notify of changes to it. */
  lazy val ClassList: ExternalVar.Aux[Element, Seq[String]] = ExternalVar[Element, Seq[String]](
    "class",
    v => v.classList.toSeq,
    (v, t) => {
      v.classList.foreach(v.classList.remove)
      t.foreach(v.classList.add)
    },
    eagerEvaluation = true,
  )

  /** Special signal that tracks an element dimensions using the special Observer WebApi
    */
  lazy val Dimensions: ExternalObsVal.Aux[HtmlElement, (Double, Double)] = MutDimensions

  /** In addition to the checked and unchecked states, there is a third state a checkbox can be in: indeterminate. This is a state in which
    * it's impossible to say whether the item is toggled on or off.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox#Indeterminate_state_checkboxes
    */
  lazy val Indeterminate = boolProp("indeterminate")

  /** When the value of the type attribute is "radio" or "checkbox", this property determines whether it is checked or not. This is
    * different from `checked` _attribute_, which contains the _initial_ checked status of the element. More info:
    * https://stackoverflow.com/a/6004028/2601788 (`checked` behaves similar to `value`)
    *
    * See also: defaultChecked prop / attribute
    */
  lazy val Checked: ExternalVar[Boolean]{type ForInstance <: HtmlElement & Singleton} = boolProp("checked", onFirstAssociation = (elem) => {
    scribe.debug(s"registering input listener on $elem to update Checked")
    Toolkit.update {
      DomEvents.OnChange.forInstance(elem) := EventIterator.foreach(evt =>
        summon[VarContext].externalPropertyUpdated(HtmlProps.Checked.forInstance(elem), None)
      )
    }
  })

  /** Indicates whether an `<option>` element is _currently_ selected. This is different from `selected` _attribute_, which contains the
    * _initial_ selected status of the element. More info: https://stackoverflow.com/a/6004028/2601788 (`selected` behaves similar to
    * `value`)
    *
    * See also: defaultSelected prop / attribute
    */
  lazy val Selected = boolProp("selected")

  /** Current value of the element. This is different from `value` _attribute_, which contains the _initial_ value of the element. More
    * info: https://stackoverflow.com/a/6004028/2601788
    *
    * See also: defaultValue prop / attribute
    */
  lazy val Value: ExternalVar[String]{type ForInstance <: HtmlElement & Singleton} = stringProp("value", onFirstAssociation = (elem) => {
    scribe.debug(s"registering input listener on $elem to update Value")
    Toolkit.update {
      DomEvents.OnInput.forInstance(elem) := EventIterator.foreach(evt =>
        summon[VarContext].externalPropertyUpdated(HtmlProps.Value.forInstance(elem), None)
      )
    }
  })

  /** If the value of the type attribute is file, this attribute indicates the types of files that the server accepts; otherwise it is
    * ignored.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/accept
    */
  lazy val Accept = stringProp("accept")

  /** The URI of a program that processes the information submitted via the form. This value can be overridden by a [[formAction]] attribute
    * on a button or input element.
    *
    * Only applies to [[FormTags.form]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/action
    */
  lazy val Action = stringProp("action")

  /** Specifies a shortcut key to activate/focus an element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/accesskey
    */
  lazy val AccessKey = stringProp("accessKey")

  /** This attribute defines the alternative text describing the image. Users will see this displayed if the image URL is wrong, the image
    * is not in one of the supported formats, or until the image is downloaded.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/alt
    */
  lazy val Alt = stringProp("alt")

  /** This is a nonstandard attribute used by Chrome and iOS Safari Mobile, which controls whether and how the text value should be
    * automatically capitalized as it is entered/edited by the user.
    *
    * Possible values: "none" | "sentences" | "words" | "characters"
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/autocapitalize
    */
  lazy val AutoCapitalize = stringProp("autoCapitalize")

  /** This attribute indicates whether the value of the control can be automatically completed by the browser. This attribute is ignored if
    * the value of the type attribute is hidden, checkbox, radio, file, or a button type (button, submit, reset, image).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete
    */
  lazy val AutoComplete = stringProp("autoComplete")

  /** This Boolean attribute lets you specify that a form control should have input focus when the page loads, unless the user overrides it,
    * for example by typing in a different control. Only one form element in a document can have the autofocus attribute, which is a
    * Boolean. It cannot be applied if the type attribute is set to hidden (that is, you cannot automatically set focus to a hidden
    * control).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/autofocus
    */
  lazy val AutoFocus = boolProp("autoFocus")

  /** The visible width of text input or `<textArea>`, in average character widths. If it is specified, it must be a positive integer. If it
    * is not specified, the default value is 20 (HTML5).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea#attr-cols
    */
  lazy val Cols = intProp("cols")

  /** This attribute contains a non-negative integer value that indicates for how many columns the cell extends. Its default value is 1; if
    * its value is set to 0, it extends until the end of the `<colgroup>`, even if implicitly defined, that the cell belongs to. Values
    * higher than 1000 will be considered as incorrect and will be set to the default value (1).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td#attr-colspan
    */
  lazy val ColSpan = intProp("colSpan")

  /** This attribute gives the value associated with the [[name]] or [[httpEquiv]] attribute, of a `<meta>` element, depending on which of
    * those attributes is defined on that element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-content
    */
  lazy val ContentAttr = stringProp("contentAttr")

  /** When the value of the type attribute is "radio" or "checkbox", the presence of this Boolean attribute indicates that the control is
    * selected **by default**; otherwise it is ignored.
    *
    * See [[Props.checked]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox#attr-checked
    */
  lazy val DefaultChecked = boolProp("defaultChecked")

  /** Indicates whether this `<option>` is initially selected in an option list of a `<select>` element.
    *
    * See [[Props.selected]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/option#attr-selected
    */
  lazy val DefaultSelected = boolProp("defaultSelected")

  /** The initial value of the control. This attribute is optional except when the value of the type attribute is radio or checkbox.
    *
    * See also [[Props.value]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#value
    */
  lazy val DefaultValue = stringProp("defaultValue")

  /** Specifies the text direction for the content in an element. The valid values are:
    *
    *   - `ltr` Default. Left-to-right text direction
    *
    *   - `rtl` Right-to-left text direction
    *
    *   - `auto` Let the browser figure out the text direction, based on the content, (only recommended if the text direction is unknown)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir
    */
  lazy val Dir = stringProp("dir")

  /** This Boolean attribute indicates that the form control is not available for interaction. In particular, the click event will not be
    * dispatched on disabled controls. Also, a disabled control's value isn't submitted with the form.
    *
    * This attribute is ignored if the value of the type attribute is hidden.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/disabled
    */
  lazy val Disabled = boolProp("disabled")

  /** Prompts the user to save the linked URL instead of navigating to it. Can be used with or without a value:
    *
    *   - Without a value, the browser will suggest a filename/extension, generated from various sources:
    *     - The Content-Disposition HTTP header
    *     - The final segment in the URL path
    *     - The media type (from the Content-Type header, the start of a data: URL, or Blob.type for a blob: URL)
    *   - Defining a value suggests it as the filename. / and \ characters are converted to underscores (_). Filesystems may forbid other
    *     characters in filenames, so browsers will adjust the suggested name if necessary.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-download
    */
  lazy val Download = stringProp("download")

  /** Specifies whether an element is draggable or not
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/draggable
    */
  lazy val Draggable = boolProp("draggable")

  /** The `enctype` attribute provides the encoding type of the form when it is submitted (for forms with a method of "POST").
    *
    * Only applies to [[FormTags.form]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-enctype
    */
  lazy val EncType = stringProp("encType")

  /** Describes an element which belongs to this one. Used on labels and output elements.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/for
    */
  lazy val ForId = stringProp("forId")

  /** The `formenctype` attribute provides the encoding type of the form when it is submitted (for forms with a method of "POST") and
    * overrides the default `enctype` attribute of the `form` element. This should be used only with the `input` elements of `type` "submit"
    * or "image"
    *
    * Enumerated: "multipart/form-data" | "text/plain" | "application/x-www-form-urlencoded" (default)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formenctype
    */
  lazy val FormEncType = stringProp("formEncType")

  /** The `formmethod` attribute specifies the HTTP Method the form should use when it is submitted and overrides the default `method`
    * attribute of the `form` element. This should be used only with the `input` elements of `type` "submit" or "image".
    *
    * Enumerated: "post" | "get"
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formmethod
    */
  lazy val FormMethod = stringProp("formMethod")

  /** The `formnovalidate` Boolean attribute specifies that the input of the form should not be validated upon submit and overrides the
    * default `novalidate` attribute of the `form`. This should only be used with `input` elements of of `type` "submit".
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formnovalidate
    */
  lazy val FormNoValidate = boolProp("formNoValidate")

  /** The `formtarget` provides a name or keyword that indicates where to display the response that is received after submitting the form
    * and overrides the `target` attribute of them `form` element. This should only be used with the `input` elements of `type` "submit" or
    * "image"
    *
    * Enumerated: "_blank" | "_parent" | "_top" | "_self" (default)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formtarget
    */
  lazy val FormTarget = stringProp("formTarget")

  /** Specifies that an element is not yet, or is no longer, relevant and consequently hidden from view of the user.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/hidden
    */
  lazy val Hidden = boolProp("hidden")

  /** For use in &lt;meter&gt; tags.
    *
    * @see
    *   https://css-tricks.com/html5-meter-element/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-high
    */
  lazy val High = doubleProp("high")

  /** This enumerated attribute defines the pragma that can alter servers and user-agents behavior. The value of the pragma is defined using
    * the content attribute and can be one of the following:
    *
    *   - content-language
    *   - content-type
    *   - default-style
    *   - refresh
    *   - set-cookie
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv
    */
  lazy val HttpEquiv = stringProp("httpEquiv")

  /** This attribute defines a unique identifier (ID) which must be unique in the whole document. Its purpose is to identify the element
    * when linking (using a fragment identifier), scripting, or styling (with CSS).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/id
    */
  lazy val IdAttr = stringProp("idAttr")

  /** The inputmode attribute hints at the type of data that might be entered by the user while editing the element or its contents. This
    * allows a browser to display an appropriate virtual keyboard.
    *
    * Acceptable values: `none` | `text` (default value) | `decimal` | `numeric` | `tel` | `search` | `email` | `url`
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/inputmode
    */
  lazy val InputMode = stringProp("inputMode")

  /** For `optgroup` elements, specifies the name of the group of options, which the browser can use when labeling the options in the user
    * interface.
    */
  lazy val LabelAttr = stringProp("labelAttr")

  /** This attribute participates in defining the language of the element, the language that non-editable elements are written in or the
    * language that editable elements should be written in. The tag contains one single entry value in the format defines in the Tags for
    * Identifying Languages (BCP47) IETF document. If the tag content is the empty string the language is set to unknown; if the tag content
    * is not valid, regarding to BCP47, it is set to invalid.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang
    */
  lazy val Lang = stringProp("lang")

  /** Indicates how the browser should load the image:
    *
    * "eager": Loads the image immediately, regardless of whether or not the image is currently within the visible viewport (this is the
    * default value).
    *
    * "lazy": Defers loading the image until it reaches a calculated distance from the viewport, as defined by the browser. The intent is to
    * avoid the network and storage bandwidth needed to handle the image until it's reasonably certain that it will be needed. This
    * generally improves the performance of the content in most typical use cases.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#attr-loading
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe#attr-loading
    */
  lazy val LoadingAttr = stringProp("loadingAttr")

  /** For use in &lt;meter&gt; tags.
    *
    * @see
    *   https://css-tricks.com/html5-meter-element/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-low
    */
  lazy val Low = doubleProp("low")

  /** If the type of the input element is text, email, search, password, tel, or url, this attribute specifies the minimum number of
    * characters (in Unicode code points) that the user can enter. For other control types, it is ignored.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/minlength
    */
  lazy val MinLength = intProp("minLength")

  /** The maximum allowed length for the input field. This attribute forces the input control to accept no more than the allowed number of
    * characters. It does not produce any feedback to the user; you must write Javascript to make that happen.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/maxlength
    */
  lazy val MaxLength = intProp("maxLength")

  /** This attribute specifies the media which the linked resource applies to. Its value must be a media query. This attribute is mainly
    * useful when linking to external stylesheets by allowing the user agent to pick the best adapted one for the device it runs on.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link#attr-media
    */
  lazy val Media = stringProp("media")

  /** The HTTP method that the browser uses to submit the form. Possible values are:
    *
    *   - post: Corresponds to the HTTP POST method ; form data are included in the body of the form and sent to the server.
    *
    *   - get: Corresponds to the HTTP GET method; form data are appended to the action attribute URI with a '?' as a separator, and the
    *     resulting URI is sent to the server. Use this method when the form has no side-effects and contains only ASCII characters.
    *
    * This value can be overridden by a formmethod attribute on a button or input element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-method
    */
  lazy val Method = stringProp("method")

  /** This Boolean attribute specifies, when present/true, that the user is allowed to enter more than one value for the `<input>` element
    * for types "email" or "file". It can also be provided to the `<select>` element to allow selecting more than one option.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#multiple
    */
  lazy val Multiple = boolProp("multiple")

  /** On form elements (input etc.): Name of the element. For example used by the server to identify the fields in form submits.
    *
    * On the meta tag: This attribute defines the name of a document-level metadata. This document-level metadata name is associated with a
    * value, contained by the content attribute.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#name
    */
  lazy val NameAttr = stringProp("nameAttr")

  /** This Boolean attribute indicates that the form is not to be validated when submitted. If this attribute is not specified (and
    * therefore the form is validated), this default setting can be overridden by a formnovalidate attribute on a `<button>` or `<input>`
    * element belonging to the form.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate
    */
  lazy val NoValidate = boolProp("noValidate")

  /** For use in &lt;meter&gt; tags.
    *
    * @see
    *   https://css-tricks.com/html5-meter-element/
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-optimum
    */
  lazy val Optimum = doubleProp("optimum")

  /** Specifies a regular expression to validate the input. The pattern attribute works with the following input types: text, search, url,
    * tel, email, and password. Use the `title` attribute to describe the pattern to the user.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/pattern
    */
  lazy val Pattern = stringProp("pattern")

  /** A hint to the user of what can be entered in the control. The placeholder text must not contain carriage returns or line-feeds. This
    * attribute applies when the value of the type attribute is text, search, tel, url or email; otherwise it is ignored.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#placeholder
    */
  lazy val Placeholder = stringProp("placeholder")

  /** This Boolean attribute indicates that the user cannot modify the value of the control. This attribute is ignored if the value of the
    * type attribute is hidden, range, color, checkbox, radio, file, or a button type.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/readonly
    */
  lazy val ReadOnly = boolProp("readOnly")

  /** This attribute specifies that the user must fill in a value before submitting a form. It cannot be used when the type attribute is
    * hidden, image, or a button type (submit, reset, or button). The :optional and :required CSS pseudo-classes will be applied to the
    * field as appropriate.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/required
    */
  lazy val Required = boolProp("required")

  /** The number of visible text lines for a text control.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea#attr-rows
    */
  lazy val Rows = intProp("rows")

  /** This attribute contains a non-negative integer value that indicates for how many rows the cell extends. Its default value is 1; if its
    * value is set to 0, it extends until the end of the table section (`<thead>`, `<tbody>`, `<tfoot>`, even if implicitly defined, that
    * the cell belongs to. Values higher than 65534 are clipped down to 65534.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td#attr-rowspan
    */
  lazy val RowSpan = intProp("rowSpan")

  /** For use in &lt;style&gt; tags.
    *
    * If this attribute is present, then the style applies only to its parent element. If absent, the style applies to the whole document.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLStyleElement/scoped
    */
  lazy val Scoped = boolProp("scoped")

  /** The initial size of the control. This value is in pixels unless the value of the type attribute is text or password, in which case, it
    * is an integer number of characters. Starting in HTML5, this attribute applies only when the type attribute is set to text, search,
    * tel, url, email, or password; otherwise it is ignored. In addition, the size must be greater than zero. If you don't specify a size, a
    * default value of 20 is used.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/size
    */
  lazy val Size = intProp("size")

  /** The slot global attribute assigns a slot in a shadow DOM shadow tree to an element.
    *
    * MDN – https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/slot
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/slot
    */
  lazy val Slot = stringProp("slot")

  /** Defines whether the element may be checked for spelling errors.
    *
    * MDN – https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/spellcheck
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/spellcheck
    */
  lazy val SpellCheck = boolProp("spellCheck")

  /** This integer attribute indicates if the element can take input focus (is focusable), if it should participate to sequential keyboard
    * navigation, and if so, at what position. It can takes several values:
    *
    *   - a negative value means that the element should be focusable, but should not be reachable via sequential keyboard navigation;
    *   - 0 means that the element should be focusable and reachable via sequential keyboard navigation, but its relative order is defined
    *     by the platform convention;
    *   - a positive value which means should be focusable and reachable via sequential keyboard navigation; its relative order is defined
    *     by the value of the attribute: the sequential follow the increasing number of the tabindex. If several elements share the same
    *     tabindex, their relative order follows their relative position in the document).
    *
    * An element with a 0 value, an invalid value, or no tabindex value should be placed after elements with a positive tabindex in the
    * sequential keyboard navigation order.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/tabindex
    */
  lazy val TabIndex = intProp("tabIndex")

  /** A name or keyword indicating where to display the response that is received after submitting the form. In HTML 4, this is the name of,
    * or a keyword for, a frame. In HTML5, it is a name of, or keyword for, a browsing context (for example, tab, window, or inline frame).
    * The following keywords have special meanings:
    *
    *   - _self: Load the response into the same HTML 4 frame (or HTML5 browsing context) as the current one. This value is the default if
    *     the attribute is not specified.
    *   - _blank: Load the response into a new unnamed HTML 4 window or HTML5 browsing context.
    *   - _parent: Load the response into the HTML 4 frameset parent of the current frame or HTML5 parent browsing context of the current
    *     one. If there is no parent, this option behaves the same way as _self.
    *   - _top: HTML 4: Load the response into the full, original window, canceling all other frames. HTML5: Load the response into the
    *     top-level browsing context (that is, the browsing context that is an ancestor of the current one, and has no parent). If there is
    *     no parent, this option behaves the same way as _self.
    *   - iframename: The response is displayed in a named iframe.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-target
    */
  lazy val Target = stringProp("target")

  /** This attribute contains a text representing advisory information related to the element it belongs too. Such information can
    * typically, but not necessarily, be presented to the user as a tooltip.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/title
    */
  lazy val Title = stringProp("title")

  /** Specifies whether the content of an element should be translated or not
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/translate
    */
  lazy val Translate = boolProp("translate")

  /** Specifies XML namespace for the document */
  lazy val Xmlns = stringProp("xmlns")
}

object HtmlPropsApi {

  /** @see [[package.HtmlProps.Indeterminate]] */
  lazy val indeterminate = ModifierVar[HtmlElement, Boolean](HtmlProps.Indeterminate)

  /** @see [[package.HtmlProps.Checked]] */
  lazy val checked = ModifierVar[HtmlElement, Boolean](HtmlProps.Checked)

  /** @see [[package.HtmlProps.ClassList]] */
  lazy val classList = ModifierVar[Element, Seq[String]](HtmlProps.ClassList)

  /** @see [[package.HtmlProps.Selected]] */
  lazy val selected = ModifierVar[HtmlElement, Boolean](HtmlProps.Selected)

  /** @see [[package.HtmlProps.Value]] */
  lazy val value = ModifierVar[HtmlElement, String](HtmlProps.Value)

  /** @see [[package.HtmlProps.Accept]] */
  lazy val accept = ModifierVar[HtmlElement, String](HtmlProps.Accept)

  /** @see [[package.HtmlProps.Action]] */
  lazy val action = ModifierVar[HtmlElement, String](HtmlProps.Action)

  /** @see [[package.HtmlProps.AccessKey]] */
  lazy val accessKey = ModifierVar[HtmlElement, String](HtmlProps.AccessKey)

  /** @see [[package.HtmlProps.Alt]] */
  lazy val alt = ModifierVar[HtmlElement, String](HtmlProps.Alt)

  /** @see [[package.HtmlProps.AutoCapitalize]] */
  lazy val autoCapitalize = ModifierVar[HtmlElement, String](HtmlProps.AutoCapitalize)

  /** @see [[package.HtmlProps.AutoComplete]] */
  lazy val autoComplete = ModifierVar[HtmlElement, String](HtmlProps.AutoComplete)

  /** @see [[package.HtmlProps.AutoFocus]] */
  lazy val autoFocus = ModifierVar[HtmlElement, Boolean](HtmlProps.AutoFocus)

  /** @see [[package.HtmlProps.Cols]] */
  lazy val cols = ModifierVar[HtmlElement, Int](HtmlProps.Cols)

  /** @see [[package.HtmlProps.ColSpan]] */
  lazy val colSpan = ModifierVar[HtmlElement, Int](HtmlProps.ColSpan)

  /** @see [[package.HtmlProps.ContentAttr]] */
  lazy val contentAttr = ModifierVar[HtmlElement, String](HtmlProps.ContentAttr)

  /** @see [[package.HtmlProps.DefaultChecked]] */
  lazy val defaultChecked = ModifierVar[HtmlElement, Boolean](HtmlProps.DefaultChecked)

  /** @see [[package.HtmlProps.DefaultSelected]] */
  lazy val defaultSelected = ModifierVar[HtmlElement, Boolean](HtmlProps.DefaultSelected)

  /** @see [[package.HtmlProps.DefaultValue]] */
  lazy val defaultValue = ModifierVar[HtmlElement, String](HtmlProps.DefaultValue)

  /** @see [[package.HtmlProps.Dir]] */
  lazy val dir = ModifierVar[HtmlElement, String](HtmlProps.Dir)

  /** @see [[package.HtmlProps.Disabled]] */
  lazy val disabled = ModifierVar[HtmlElement, Boolean](HtmlProps.Disabled)

  /** @see [[package.HtmlProps.Download]] */
  lazy val download = ModifierVar[HtmlElement, String](HtmlProps.Download)

  /** @see [[package.HtmlProps.Draggable]] */
  lazy val draggable = ModifierVar[HtmlElement, Boolean](HtmlProps.Draggable)

  /** @see [[package.HtmlProps.EncType]] */
  lazy val encType = ModifierVar[HtmlElement, String](HtmlProps.EncType)

  /** @see [[package.HtmlProps.ForId]] */
  lazy val forId = ModifierVar[HtmlElement, String](HtmlProps.ForId)

  /** @see [[package.HtmlProps.FormEncType]] */
  lazy val formEncType = ModifierVar[HtmlElement, String](HtmlProps.FormEncType)

  /** @see [[package.HtmlProps.FormMethod]] */
  lazy val formMethod = ModifierVar[HtmlElement, String](HtmlProps.FormMethod)

  /** @see [[package.HtmlProps.FormNoValidate]] */
  lazy val formNoValidate = ModifierVar[HtmlElement, Boolean](HtmlProps.FormNoValidate)

  /** @see [[package.HtmlProps.FormTarget]] */
  lazy val formTarget = ModifierVar[HtmlElement, String](HtmlProps.FormTarget)

  /** @see [[package.HtmlProps.Hidden]] */
  lazy val hidden = ModifierVar[HtmlElement, Boolean](HtmlProps.Hidden)

  /** @see [[package.HtmlProps.High]] */
  lazy val high = ModifierVar[HtmlElement, Double](HtmlProps.High)

  /** @see [[package.HtmlProps.HttpEquiv]] */
  lazy val httpEquiv = ModifierVar[HtmlElement, String](HtmlProps.HttpEquiv)

  /** @see [[package.HtmlProps.IdAttr]] */
  lazy val idAttr = ModifierVar[HtmlElement, String](HtmlProps.IdAttr)

  /** @see [[package.HtmlProps.InputMode]] */
  lazy val inputMode = ModifierVar[HtmlElement, String](HtmlProps.InputMode)

  /** @see [[package.HtmlProps.LabelAttr]] */
  lazy val labelAttr = ModifierVar[HtmlElement, String](HtmlProps.LabelAttr)

  /** @see [[package.HtmlProps.Lang]] */
  lazy val lang = ModifierVar[HtmlElement, String](HtmlProps.Lang)

  /** @see [[package.HtmlProps.LoadingAttr]] */
  lazy val loadingAttr = ModifierVar[HtmlElement, String](HtmlProps.LoadingAttr)

  /** @see [[package.HtmlProps.Low]] */
  lazy val low = ModifierVar[HtmlElement, Double](HtmlProps.Low)

  /** @see [[package.HtmlProps.MinLength]] */
  lazy val minLength = ModifierVar[HtmlElement, Int](HtmlProps.MinLength)

  /** @see [[package.HtmlProps.MaxLength]] */
  lazy val maxLength = ModifierVar[HtmlElement, Int](HtmlProps.MaxLength)

  /** @see [[package.HtmlProps.Media]] */
  lazy val media = ModifierVar[HtmlElement, String](HtmlProps.Media)

  /** @see [[package.HtmlProps.Method]] */
  lazy val method = ModifierVar[HtmlElement, String](HtmlProps.Method)

  /** @see [[package.HtmlProps.Multiple]] */
  lazy val multiple = ModifierVar[HtmlElement, Boolean](HtmlProps.Multiple)

  /** @see [[package.HtmlProps.NameAttr]] */
  lazy val nameAttr = ModifierVar[HtmlElement, String](HtmlProps.NameAttr)

  /** @see [[package.HtmlProps.NoValidate]] */
  lazy val noValidate = ModifierVar[HtmlElement, Boolean](HtmlProps.NoValidate)

  /** @see [[package.HtmlProps.Optimum]] */
  lazy val optimum = ModifierVar[HtmlElement, Double](HtmlProps.Optimum)

  /** @see [[package.HtmlProps.Pattern]] */
  lazy val pattern = ModifierVar[HtmlElement, String](HtmlProps.Pattern)

  /** @see [[package.HtmlProps.Placeholder]] */
  lazy val placeholder = ModifierVar[HtmlElement, String](HtmlProps.Placeholder)

  /** @see [[package.HtmlProps.ReadOnly]] */
  lazy val readOnly = ModifierVar[HtmlElement, Boolean](HtmlProps.ReadOnly)

  /** @see [[package.HtmlProps.Required]] */
  lazy val required = ModifierVar[HtmlElement, Boolean](HtmlProps.Required)

  /** @see [[package.HtmlProps.Rows]] */
  lazy val rows = ModifierVar[HtmlElement, Int](HtmlProps.Rows)

  /** @see [[package.HtmlProps.RowSpan]] */
  lazy val rowSpan = ModifierVar[HtmlElement, Int](HtmlProps.RowSpan)

  /** @see [[package.HtmlProps.Scoped]] */
  lazy val scoped = ModifierVar[HtmlElement, Boolean](HtmlProps.Scoped)

  /** @see [[package.HtmlProps.Size]] */
  lazy val size = ModifierVar[HtmlElement, Int](HtmlProps.Size)

  /** @see [[package.HtmlProps.Slot]] */
  lazy val slot = ModifierVar[HtmlElement, String](HtmlProps.Slot)

  /** @see [[package.HtmlProps.SpellCheck]] */
  lazy val spellCheck = ModifierVar[HtmlElement, Boolean](HtmlProps.SpellCheck)

  /** @see [[package.HtmlProps.TabIndex]] */
  lazy val tabIndex = ModifierVar[HtmlElement, Int](HtmlProps.TabIndex)

  /** @see [[package.HtmlProps.Target]] */
  lazy val target = ModifierVar[HtmlElement, String](HtmlProps.Target)

  /** @see [[package.HtmlProps.Title]] */
  lazy val title = ModifierVar[HtmlElement, String](HtmlProps.Title)

  /** @see [[package.HtmlProps.Translate]] */
  lazy val translate = ModifierVar[HtmlElement, Boolean](HtmlProps.Translate)

  /** @see [[package.HtmlProps.Xmlns]] */
  lazy val xmlns = ModifierVar[HtmlElement, String](HtmlProps.Xmlns)

  extension (elem: HtmlElement) {

    /** Special signal that tracks an element dimensions using the special Observer WebApi
      */
    def dimensions = HtmlProps.Dimensions.forInstance(elem)

    /** In addition to the checked and unchecked states, there is a third state a checkbox can be in: indeterminate. This is a state in
      * which it's impossible to say whether the item is toggled on or off.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox#Indeterminate_state_checkboxes
      */
    def indeterminate = HtmlProps.Indeterminate.forInstance(elem)

    /** When the value of the type attribute is "radio" or "checkbox", this property determines whether it is checked or not. This is
      * different from `checked` _attribute_, which contains the _initial_ checked status of the element. More info:
      * https://stackoverflow.com/a/6004028/2601788 (`checked` behaves similar to `value`)
      *
      * See also: defaultChecked prop / attribute
      */
    def checked = HtmlProps.Checked.forInstance(elem)

    def classList = HtmlProps.ClassList.forInstance(elem)

    /** Indicates whether an `<option>` element is _currently_ selected. This is different from `selected` _attribute_, which contains the
      * _initial_ selected status of the element. More info: https://stackoverflow.com/a/6004028/2601788 (`selected` behaves similar to
      * `value`)
      *
      * See also: defaultSelected prop / attribute
      */
    def selected = HtmlProps.Selected.forInstance(elem)

    /** Current value of the element. This is different from `value` _attribute_, which contains the _initial_ value of the element. More
      * info: https://stackoverflow.com/a/6004028/2601788
      *
      * See also: defaultValue prop / attribute
      */
    def value = HtmlProps.Value.forInstance(elem)

    /** If the value of the type attribute is file, this attribute indicates the types of files that the server accepts; otherwise it is
      * ignored.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/accept
      */
    def accept = HtmlProps.Accept.forInstance(elem)

    /** The URI of a program that processes the information submitted via the form. This value can be overridden by a [[formAction]]
      * attribute on a button or input element.
      *
      * Only applies to [[FormTags.form]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/action
      */
    def action = HtmlProps.Action.forInstance(elem)

    /** Specifies a shortcut key to activate/focus an element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/accesskey
      */
    def accessKey = HtmlProps.AccessKey.forInstance(elem)

    /** This attribute defines the alternative text describing the image. Users will see this displayed if the image URL is wrong, the image
      * is not in one of the supported formats, or until the image is downloaded.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/alt
      */
    def alt = HtmlProps.Alt.forInstance(elem)

    /** This is a nonstandard attribute used by Chrome and iOS Safari Mobile, which controls whether and how the text value should be
      * automatically capitalized as it is entered/edited by the user.
      *
      * Possible values: "none" | "sentences" | "words" | "characters"
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/autocapitalize
      */
    def autoCapitalize = HtmlProps.AutoCapitalize.forInstance(elem)

    /** This attribute indicates whether the value of the control can be automatically completed by the browser. This attribute is ignored
      * if the value of the type attribute is hidden, checkbox, radio, file, or a button type (button, submit, reset, image).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete
      */
    def autoComplete = HtmlProps.AutoComplete.forInstance(elem)

    /** This Boolean attribute lets you specify that a form control should have input focus when the page loads, unless the user overrides
      * it, for example by typing in a different control. Only one form element in a document can have the autofocus attribute, which is a
      * Boolean. It cannot be applied if the type attribute is set to hidden (that is, you cannot automatically set focus to a hidden
      * control).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/autofocus
      */
    def autoFocus = HtmlProps.AutoFocus.forInstance(elem)

    /** The visible width of text input or `<textArea>`, in average character widths. If it is specified, it must be a positive integer. If
      * it is not specified, the default value is 20 (HTML5).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea#attr-cols
      */
    def cols = HtmlProps.Cols.forInstance(elem)

    /** This attribute contains a non-negative integer value that indicates for how many columns the cell extends. Its default value is 1;
      * if its value is set to 0, it extends until the end of the `<colgroup>`, even if implicitly defined, that the cell belongs to. Values
      * higher than 1000 will be considered as incorrect and will be set to the default value (1).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td#attr-colspan
      */
    def colSpan = HtmlProps.ColSpan.forInstance(elem)

    /** This attribute gives the value associated with the [[name]] or [[httpEquiv]] attribute, of a `<meta>` element, depending on which of
      * those attributes is defined on that element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-content
      */
    def contentAttr = HtmlProps.ContentAttr.forInstance(elem)

    /** When the value of the type attribute is "radio" or "checkbox", the presence of this Boolean attribute indicates that the control is
      * selected **by default**; otherwise it is ignored.
      *
      * See [[Props.checked]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox#attr-checked
      */
    def defaultChecked = HtmlProps.DefaultChecked.forInstance(elem)

    /** Indicates whether this `<option>` is initially selected in an option list of a `<select>` element.
      *
      * See [[Props.selected]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/option#attr-selected
      */
    def defaultSelected = HtmlProps.DefaultSelected.forInstance(elem)

    /** The initial value of the control. This attribute is optional except when the value of the type attribute is radio or checkbox.
      *
      * See also [[Props.value]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#value
      */
    def defaultValue = HtmlProps.DefaultValue.forInstance(elem)

    /** Specifies the text direction for the content in an element. The valid values are:
      *
      *   - `ltr` Default. Left-to-right text direction
      *
      *   - `rtl` Right-to-left text direction
      *
      *   - `auto` Let the browser figure out the text direction, based on the content, (only recommended if the text direction is unknown)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir
      */
    def dir = HtmlProps.Dir.forInstance(elem)

    /** This Boolean attribute indicates that the form control is not available for interaction. In particular, the click event will not be
      * dispatched on disabled controls. Also, a disabled control's value isn't submitted with the form.
      *
      * This attribute is ignored if the value of the type attribute is hidden.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/disabled
      */
    def disabled = HtmlProps.Disabled.forInstance(elem)

    /** Prompts the user to save the linked URL instead of navigating to it. Can be used with or without a value:
      *
      *   - Without a value, the browser will suggest a filename/extension, generated from various sources:
      *     - The Content-Disposition HTTP header
      *     - The final segment in the URL path
      *     - The media type (from the Content-Type header, the start of a data: URL, or Blob.type for a blob: URL)
      *   - Defining a value suggests it as the filename. / and \ characters are converted to underscores (_). Filesystems may forbid other
      *     characters in filenames, so browsers will adjust the suggested name if necessary.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-download
      */
    def download = HtmlProps.Download.forInstance(elem)

    /** Specifies whether an element is draggable or not
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/draggable
      */
    def draggable = HtmlProps.Draggable.forInstance(elem)

    /** The `enctype` attribute provides the encoding type of the form when it is submitted (for forms with a method of "POST").
      *
      * Only applies to [[FormTags.form]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-enctype
      */
    def encType = HtmlProps.EncType.forInstance(elem)

    /** Describes an element which belongs to this one. Used on labels and output elements.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/for
      */
    def forId = HtmlProps.ForId.forInstance(elem)

    /** The `formenctype` attribute provides the encoding type of the form when it is submitted (for forms with a method of "POST") and
      * overrides the default `enctype` attribute of the `form` element. This should be used only with the `input` elements of `type`
      * "submit" or "image"
      *
      * Enumerated: "multipart/form-data" | "text/plain" | "application/x-www-form-urlencoded" (default)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formenctype
      */
    def formEncType = HtmlProps.FormEncType.forInstance(elem)

    /** The `formmethod` attribute specifies the HTTP Method the form should use when it is submitted and overrides the default `method`
      * attribute of the `form` element. This should be used only with the `input` elements of `type` "submit" or "image".
      *
      * Enumerated: "post" | "get"
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formmethod
      */
    def formMethod = HtmlProps.FormMethod.forInstance(elem)

    /** The `formnovalidate` Boolean attribute specifies that the input of the form should not be validated upon submit and overrides the
      * default `novalidate` attribute of the `form`. This should only be used with `input` elements of of `type` "submit".
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formnovalidate
      */
    def formNoValidate = HtmlProps.FormNoValidate.forInstance(elem)

    /** The `formtarget` provides a name or keyword that indicates where to display the response that is received after submitting the form
      * and overrides the `target` attribute of them `form` element. This should only be used with the `input` elements of `type` "submit"
      * or "image"
      *
      * Enumerated: "_blank" | "_parent" | "_top" | "_self" (default)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#formtarget
      */
    def formTarget = HtmlProps.FormTarget.forInstance(elem)

    /** Specifies that an element is not yet, or is no longer, relevant and consequently hidden from view of the user.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/hidden
      */
    def hidden = HtmlProps.Hidden.forInstance(elem)

    /** For use in &lt;meter&gt; tags.
      *
      * @see
      *   https://css-tricks.com/html5-meter-element/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-high
      */
    def high = HtmlProps.High.forInstance(elem)

    /** This enumerated attribute defines the pragma that can alter servers and user-agents behavior. The value of the pragma is defined
      * using the content attribute and can be one of the following:
      *
      *   - content-language
      *   - content-type
      *   - default-style
      *   - refresh
      *   - set-cookie
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv
      */
    def httpEquiv = HtmlProps.HttpEquiv.forInstance(elem)

    /** This attribute defines a unique identifier (ID) which must be unique in the whole document. Its purpose is to identify the element
      * when linking (using a fragment identifier), scripting, or styling (with CSS).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/id
      */
    def idAttr = HtmlProps.IdAttr.forInstance(elem)

    /** The inputmode attribute hints at the type of data that might be entered by the user while editing the element or its contents. This
      * allows a browser to display an appropriate virtual keyboard.
      *
      * Acceptable values: `none` | `text` (default value) | `decimal` | `numeric` | `tel` | `search` | `email` | `url`
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/inputmode
      */
    def inputMode = HtmlProps.InputMode.forInstance(elem)

    /** For `optgroup` elements, specifies the name of the group of options, which the browser can use when labeling the options in the user
      * interface.
      */
    def labelAttr = HtmlProps.LabelAttr.forInstance(elem)

    /** This attribute participates in defining the language of the element, the language that non-editable elements are written in or the
      * language that editable elements should be written in. The tag contains one single entry value in the format defines in the Tags for
      * Identifying Languages (BCP47) IETF document. If the tag content is the empty string the language is set to unknown; if the tag
      * content is not valid, regarding to BCP47, it is set to invalid.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/lang
      */
    def lang = HtmlProps.Lang.forInstance(elem)

    /** Indicates how the browser should load the image:
      *
      * "eager": Loads the image immediately, regardless of whether or not the image is currently within the visible viewport (this is the
      * default value).
      *
      * "lazy": Defers loading the image until it reaches a calculated distance from the viewport, as defined by the browser. The intent is
      * to avoid the network and storage bandwidth needed to handle the image until it's reasonably certain that it will be needed. This
      * generally improves the performance of the content in most typical use cases.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#attr-loading
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe#attr-loading
      */
    def loadingAttr = HtmlProps.LoadingAttr.forInstance(elem)

    /** For use in &lt;meter&gt; tags.
      *
      * @see
      *   https://css-tricks.com/html5-meter-element/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-low
      */
    def low = HtmlProps.Low.forInstance(elem)

    /** If the type of the input element is text, email, search, password, tel, or url, this attribute specifies the minimum number of
      * characters (in Unicode code points) that the user can enter. For other control types, it is ignored.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/minlength
      */
    def minLength = HtmlProps.MinLength.forInstance(elem)

    /** The maximum allowed length for the input field. This attribute forces the input control to accept no more than the allowed number of
      * characters. It does not produce any feedback to the user; you must write Javascript to make that happen.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/maxlength
      */
    def maxLength = HtmlProps.MaxLength.forInstance(elem)

    /** This attribute specifies the media which the linked resource applies to. Its value must be a media query. This attribute is mainly
      * useful when linking to external stylesheets by allowing the user agent to pick the best adapted one for the device it runs on.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link#attr-media
      */
    def media = HtmlProps.Media.forInstance(elem)

    /** The HTTP method that the browser uses to submit the form. Possible values are:
      *
      *   - post: Corresponds to the HTTP POST method ; form data are included in the body of the form and sent to the server.
      *
      *   - get: Corresponds to the HTTP GET method; form data are appended to the action attribute URI with a '?' as a separator, and the
      *     resulting URI is sent to the server. Use this method when the form has no side-effects and contains only ASCII characters.
      *
      * This value can be overridden by a formmethod attribute on a button or input element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-method
      */
    def method = HtmlProps.Method.forInstance(elem)

    /** This Boolean attribute specifies, when present/true, that the user is allowed to enter more than one value for the `<input>` element
      * for types "email" or "file". It can also be provided to the `<select>` element to allow selecting more than one option.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#multiple
      */
    def multiple = HtmlProps.Multiple.forInstance(elem)

    /** On form elements (input etc.): Name of the element. For example used by the server to identify the fields in form submits.
      *
      * On the meta tag: This attribute defines the name of a document-level metadata. This document-level metadata name is associated with
      * a value, contained by the content attribute.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#name
      */
    def nameAttr = HtmlProps.NameAttr.forInstance(elem)

    /** This Boolean attribute indicates that the form is not to be validated when submitted. If this attribute is not specified (and
      * therefore the form is validated), this default setting can be overridden by a formnovalidate attribute on a `<button>` or `<input>`
      * element belonging to the form.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form#attr-novalidate
      */
    def noValidate = HtmlProps.NoValidate.forInstance(elem)

    /** For use in &lt;meter&gt; tags.
      *
      * @see
      *   https://css-tricks.com/html5-meter-element/
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meter#attr-optimum
      */
    def optimum = HtmlProps.Optimum.forInstance(elem)

    /** Specifies a regular expression to validate the input. The pattern attribute works with the following input types: text, search, url,
      * tel, email, and password. Use the `title` attribute to describe the pattern to the user.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/pattern
      */
    def pattern = HtmlProps.Pattern.forInstance(elem)

    /** A hint to the user of what can be entered in the control. The placeholder text must not contain carriage returns or line-feeds. This
      * attribute applies when the value of the type attribute is text, search, tel, url or email; otherwise it is ignored.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#placeholder
      */
    def placeholder = HtmlProps.Placeholder.forInstance(elem)

    /** This Boolean attribute indicates that the user cannot modify the value of the control. This attribute is ignored if the value of the
      * type attribute is hidden, range, color, checkbox, radio, file, or a button type.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/readonly
      */
    def readOnly = HtmlProps.ReadOnly.forInstance(elem)

    /** This attribute specifies that the user must fill in a value before submitting a form. It cannot be used when the type attribute is
      * hidden, image, or a button type (submit, reset, or button). The :optional and :required CSS pseudo-classes will be applied to the
      * field as appropriate.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/required
      */
    def required = HtmlProps.Required.forInstance(elem)

    /** The number of visible text lines for a text control.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea#attr-rows
      */
    def rows = HtmlProps.Rows.forInstance(elem)

    /** This attribute contains a non-negative integer value that indicates for how many rows the cell extends. Its default value is 1; if
      * its value is set to 0, it extends until the end of the table section (`<thead>`, `<tbody>`, `<tfoot>`, even if implicitly defined,
      * that the cell belongs to. Values higher than 65534 are clipped down to 65534.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td#attr-rowspan
      */
    def rowSpan = HtmlProps.RowSpan.forInstance(elem)

    /** For use in &lt;style&gt; tags.
      *
      * If this attribute is present, then the style applies only to its parent element. If absent, the style applies to the whole document.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLStyleElement/scoped
      */
    def scoped = HtmlProps.Scoped.forInstance(elem)

    /** The initial size of the control. This value is in pixels unless the value of the type attribute is text or password, in which case,
      * it is an integer number of characters. Starting in HTML5, this attribute applies only when the type attribute is set to text,
      * search, tel, url, email, or password; otherwise it is ignored. In addition, the size must be greater than zero. If you don't specify
      * a size, a default value of 20 is used.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/size
      */
    def size = HtmlProps.Size.forInstance(elem)

    /** The slot global attribute assigns a slot in a shadow DOM shadow tree to an element.
      *
      * MDN – https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/slot
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/slot
      */
    def slot = HtmlProps.Slot.forInstance(elem)

    /** Defines whether the element may be checked for spelling errors.
      *
      * MDN – https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/spellcheck
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/spellcheck
      */
    def spellCheck = HtmlProps.SpellCheck.forInstance(elem)

    /** This integer attribute indicates if the element can take input focus (is focusable), if it should participate to sequential keyboard
      * navigation, and if so, at what position. It can takes several values:
      *
      *   - a negative value means that the element should be focusable, but should not be reachable via sequential keyboard navigation;
      *   - 0 means that the element should be focusable and reachable via sequential keyboard navigation, but its relative order is defined
      *     by the platform convention;
      *   - a positive value which means should be focusable and reachable via sequential keyboard navigation; its relative order is defined
      *     by the value of the attribute: the sequential follow the increasing number of the tabindex. If several elements share the same
      *     tabindex, their relative order follows their relative position in the document).
      *
      * An element with a 0 value, an invalid value, or no tabindex value should be placed after elements with a positive tabindex in the
      * sequential keyboard navigation order.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/tabindex
      */
    def tabIndex = HtmlProps.TabIndex.forInstance(elem)

    /** A name or keyword indicating where to display the response that is received after submitting the form. In HTML 4, this is the name
      * of, or a keyword for, a frame. In HTML5, it is a name of, or keyword for, a browsing context (for example, tab, window, or inline
      * frame). The following keywords have special meanings:
      *
      *   - _self: Load the response into the same HTML 4 frame (or HTML5 browsing context) as the current one. This value is the default if
      *     the attribute is not specified.
      *   - _blank: Load the response into a new unnamed HTML 4 window or HTML5 browsing context.
      *   - _parent: Load the response into the HTML 4 frameset parent of the current frame or HTML5 parent browsing context of the current
      *     one. If there is no parent, this option behaves the same way as _self.
      *   - _top: HTML 4: Load the response into the full, original window, canceling all other frames. HTML5: Load the response into the
      *     top-level browsing context (that is, the browsing context that is an ancestor of the current one, and has no parent). If there
      *     is no parent, this option behaves the same way as _self.
      *   - iframename: The response is displayed in a named iframe.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a#attr-target
      */
    def target = HtmlProps.Target.forInstance(elem)

    /** This attribute contains a text representing advisory information related to the element it belongs too. Such information can
      * typically, but not necessarily, be presented to the user as a tooltip.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/title
      */
    def title = HtmlProps.Title.forInstance(elem)

    /** Specifies whether the content of an element should be translated or not
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/translate
      */
    def translate = HtmlProps.Translate.forInstance(elem)

    /** Specifies XML namespace for the document */
    def xmlns = HtmlProps.Xmlns.forInstance(elem)

  }

}
