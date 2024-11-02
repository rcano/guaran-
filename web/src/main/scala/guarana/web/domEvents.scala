package guarana
package web

import org.scalajs.dom
import scalajs.js

def eventEmitter[T <: dom.Event](
    eventName: String
)(using owner: util.DeclaringOwner): Emitter[T] { type ForInstance <: Singleton & HtmlElement } = {
  new Emitter[T] {
    type ForInstance <: Singleton & HtmlElement
    override def toString = eventName

    private[guarana] override def onFirstListener(v: Any)(using ev: v.type <:< ForInstance): Unit = {
      val domElem = ev(v)
      val listener: js.Function1[T, ?] = evt => Toolkit.update(summon[VarContext].emit(this, evt)(using ValueOf(domElem)))
      domElem.unwrap.addEventListener[T](eventName, listener, false)
      domElem.unwrap.asInstanceOf[js.Dynamic].__emitter_listener = listener
    }

    override def onNoListener(v: Any)(using ev: v.type <:< ForInstance): Unit = {
      val domElem = ev(v)
      val listenerOpt = domElem.unwrap.asInstanceOf[js.Dynamic].__emitter_listener.asInstanceOf[js.UndefOr[js.Function1[T, ?]]]
      listenerOpt.foreach(l => domElem.unwrap.removeEventListener(eventName, l, false))
    }
  }
}

object DomEvents {

  /** The click event is raised when the user clicks on an element. The click event will occur after the mousedown and mouseup events.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/click_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnClick = eventEmitter[dom.MouseEvent]("click")

  /** The dblclick event is fired when a pointing device button (usually a mouse button) is clicked twice on a single element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/dblclick_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnDblClick = eventEmitter[dom.MouseEvent]("dblclick")

  /** The mousedown event is raised when the user presses the mouse button.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mousedown_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseDown = eventEmitter[dom.MouseEvent]("mousedown")

  /** The mousemove event is raised when the user moves the mouse.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mousemove_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseMove = eventEmitter[dom.MouseEvent]("mousemove")

  /** The mouseout event is raised when the mouse leaves an element (e.g, when the mouse moves off of an image in the web page, the mouseout
    * event is raised for that image element).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseout_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseOut = eventEmitter[dom.MouseEvent]("mouseout")

  /** The mouseover event is raised when the user moves the mouse over a particular element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseover_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseOver = eventEmitter[dom.MouseEvent]("mouseover")

  /** The mouseleave event is fired when the pointer of a pointing device (usually a mouse) is moved out of an element.
    *
    * mouseleave and mouseout are similar but differ in that mouseleave does not bubble and mouseout does.
    *
    * This means that mouseleave is fired when the pointer has exited the element and all of its descendants, whereas mouseout is fired when
    * the pointer leaves the element or leaves one of the element's descendants (even if the pointer is still within the element).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseleave_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseLeave = eventEmitter[dom.MouseEvent]("mouseleave")

  /** The mouseenter event is fired when a pointing device (usually a mouse) is moved over the element that has the listener attached.
    *
    * Similar to mouseover, it differs in that it doesn't bubble and that it isn't sent when the pointer is moved from one of its
    * descendants' physical space to its own physical space.
    *
    * With deep hierarchies, the amount of mouseenter events sent can be quite huge and cause significant performance problems. In such
    * cases, it is better to listen for mouseover events.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseenter_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseEnter = eventEmitter[dom.MouseEvent]("mouseenter")

  /** The mouseup event is raised when the user releases the mouse button.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseup_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnMouseUp = eventEmitter[dom.MouseEvent]("mouseup")

  /** Fires when the mouse wheel rolls up or down over an element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/wheel_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/WheelEvent
    */
  lazy val OnWheel = eventEmitter[dom.WheelEvent]("wheel")

  /** Script to be run when a context menu is triggered
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/contextmenu_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
    */
  lazy val OnContextMenu = eventEmitter[dom.MouseEvent]("contextmenu")

  /** Script to be run when an element is dragged
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/drag_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDrag = eventEmitter[dom.DragEvent]("drag")

  /** Script to be run at the end of a drag operation
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragend_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDragEnd = eventEmitter[dom.DragEvent]("dragend")

  /** Script to be run when an element has been dragged to a valid drop target
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragenter_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDragEnter = eventEmitter[dom.DragEvent]("dragenter")

  /** Script to be run when an element leaves a valid drop target
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragleave_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDragLeave = eventEmitter[dom.DragEvent]("dragleave")

  /** Script to be run when an element is being dragged over a valid drop target
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragover_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDragOver = eventEmitter[dom.DragEvent]("dragover")

  /** Script to be run at the start of a drag operation
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragstart_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDragStart = eventEmitter[dom.DragEvent]("dragstart")

  /** Script to be run when dragged element is being dropped
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/drop_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
    */
  lazy val OnDrop = eventEmitter[dom.DragEvent]("drop")

  /** fired when a pointing device is moved into an element's hit test boundaries.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerover_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerOver = eventEmitter[dom.PointerEvent]("pointerover")

  /** fired when a pointing device is moved into the hit test boundaries of an element or one of its descendants, including as a result of a
    * pointerdown event from a device that does not support hover (see pointerdown).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerenter_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerEnter = eventEmitter[dom.PointerEvent]("pointerenter")

  /** fired when a pointer becomes active.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerdown_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerDown = eventEmitter[dom.PointerEvent]("pointerdown")

  /** fired when a pointer changes coordinates.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointermove_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerMove = eventEmitter[dom.PointerEvent]("pointermove")

  /** fired when a pointer is no longer active.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerup_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerUp = eventEmitter[dom.PointerEvent]("pointerup")

  /** a browser fires this event if it concludes the pointer will no longer be able to generate events (for example the related device is
    * deactived).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointercancel_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerCancel = eventEmitter[dom.PointerEvent]("pointercancel")

  /** fired for several reasons including: pointing device is moved out of the hit test boundaries of an element; firing the pointerup event
    * for a device that does not support hover (see pointerup); after firing the pointercancel event (see pointercancel); when a pen stylus
    * leaves the hover range detectable by the digitizer.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerout_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerOut = eventEmitter[dom.PointerEvent]("pointerout")

  /** fired when a pointing device is moved out of the hit test boundaries of an element. For pen devices, this event is fired when the
    * stylus leaves the hover range detectable by the digitizer.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerleave_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val OnPointerLeave = eventEmitter[dom.PointerEvent]("pointerleave")

  /** fired when an element receives pointer capture.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/gotpointercapture_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val GotPointerCapture = eventEmitter[dom.PointerEvent]("gotpointercapture")

  /** Fired after pointer capture is released for a pointer.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/lostpointercapture_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
    */
  lazy val LostPointerCapture = eventEmitter[dom.PointerEvent]("lostpointercapture")

  /** The change event is fired for input, select, and textarea elements when a change to the element's value is committed by the user.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/change_event
    */
  lazy val OnChange = eventEmitter[dom.Event]("change")

  /** The select event only fires when text inside a text input or textarea is selected. The event is fired after the text has been
    * selected.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/select_event
    */
  lazy val OnSelect = eventEmitter[dom.Event]("select")

  /** The DOM beforeinput event fires when the value of an `<input>`, or `<textarea>` element is about to be modified. The event also
    * applies to elements with contenteditable enabled, and to any element when designMode is turned on.
    *
    * @note
    *   IE does not support this event.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/beforeinput_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/InputEvent
    */
  lazy val OnBeforeInput = eventEmitter[dom.InputEvent]("beforeinput")

  /** The input event is fired for input, select, textarea, and contentEditable elements when it gets user input.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/input_event
    */
  lazy val OnInput = eventEmitter[dom.Event]("input")

  /** The blur event is raised when an element loses focus.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/blur_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent
    */
  lazy val OnBlur = eventEmitter[dom.FocusEvent]("blur")

  /** The focus event is raised when the user sets focus on the given element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/focus_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent
    */
  lazy val OnFocus = eventEmitter[dom.FocusEvent]("focus")

  /** The submit event is fired when the user clicks a submit button in a form (`<input type="submit"/>`).
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/submit_event
    */
  lazy val OnSubmit = eventEmitter[dom.Event]("submit")

  /** The reset event is fired when a form is reset.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/reset_event
    */
  lazy val OnReset = eventEmitter[dom.Event]("reset")

  /** Script to be run when an element is invalid
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement/invalid_event
    */
  lazy val OnInvalid = eventEmitter[dom.Event]("invalid")

  /** Fires when the user writes something in a search field (for `<input="search">`)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement/search_event
    */
  lazy val OnSearch = eventEmitter[dom.Event]("search")

  /** The keydown event is raised when the user presses a keyboard key.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keydown_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
    */
  lazy val OnKeyDown = eventEmitter[dom.KeyboardEvent]("keydown")

  /** The keyup event is raised when the user releases a key that's been pressed.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keyup_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
    */
  lazy val OnKeyUp = eventEmitter[dom.KeyboardEvent]("keyup")

  /** The keypress event should be raised when the user presses a key on the keyboard. However, not all browsers fire keypress events for
    * certain keys.
    *
    * Webkit-based browsers (Google Chrome and Safari, for example) do not fire keypress events on the arrow keys. Firefox does not fire
    * keypress events on modifier keys like SHIFT.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keypress_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
    */
  lazy val OnKeyPress = eventEmitter[dom.KeyboardEvent]("keypress")

  /** Fires when the user copies the content of an element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/copy_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
    */
  lazy val OnCopy = eventEmitter[dom.ClipboardEvent]("copy")

  /** Fires when the user cuts the content of an element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/cut_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
    */
  lazy val OnCut = eventEmitter[dom.ClipboardEvent]("cut")

  /** Fires when the user pastes some content in an element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/paste_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
    */
  lazy val OnPaste = eventEmitter[dom.ClipboardEvent]("paste")

  /** Script to be run on abort
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/abort_event
    */
  lazy val OnAbort = eventEmitter[dom.Event]("abort")

  /** Script to be run when a file is ready to start playing (when it has buffered enough to begin)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/canplay_event
    */
  lazy val OnCanPlay = eventEmitter[dom.Event]("canplay")

  /** Script to be run when a file can be played all the way to the end without pausing for buffering
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/canplaythrough_event
    */
  lazy val OnCanPlayThrough = eventEmitter[dom.Event]("canplaythrough")

  /** Script to be run when the cue changes in a `<track>` element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/TextTrack/cuechange_event
    */
  lazy val OnCueChange = eventEmitter[dom.Event]("cuechange")

  /** Script to be run when the length of the media changes
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/durationchange_event
    */
  lazy val OnDurationChange = eventEmitter[dom.Event]("durationchange")

  /** Script to be run when something bad happens and the file is suddenly unavailable (like unexpectedly disconnects)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/emptied_event
    */
  lazy val OnEmptied = eventEmitter[dom.Event]("emptied")

  /** Script to be run when the media has reach the end (a useful event for messages like "thanks for listening")
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/ended_event
    */
  lazy val OnEnded = eventEmitter[dom.Event]("ended")

  /** Script to be run when media data is loaded
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadeddata_event
    */
  lazy val OnLoadedData = eventEmitter[dom.Event]("loadeddata")

  /** Script to be run when meta data (like dimensions and duration) are loaded
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadedmetadata_event
    */
  lazy val OnLoadedMetadata = eventEmitter[dom.Event]("loadedmetadata")

  /** Script to be run just as the file begins to load before anything is actually loaded
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadstart_event
    */
  lazy val OnLoadStart = eventEmitter[dom.Event]("loadstart")

  /** Script to be run when the media is paused either by the user or programmatically
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/pause_event
    */
  lazy val OnPause = eventEmitter[dom.Event]("pause")

  /** Script to be run when the media is ready to start playing
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/play_event
    */
  lazy val OnPlay = eventEmitter[dom.Event]("play")

  /** Script to be run when the media actually has started playing
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/playing_event
    */
  lazy val OnPlaying = eventEmitter[dom.Event]("playing")

  /** Script to be run when the browser is in the process of getting the media data
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/progress_event
    */
  lazy val OnProgress = eventEmitter[dom.Event]("progress")

  /** Script to be run each time the playback rate changes (like when a user switches to a slow motion or fast forward mode)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/ratechange_event
    */
  lazy val OnRateChange = eventEmitter[dom.Event]("ratechange")

  /** Script to be run when the seeking attribute is set to false indicating that seeking has ended
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/seeked_event
    */
  lazy val OnSeeked = eventEmitter[dom.Event]("seeked")

  /** Script to be run when the seeking attribute is set to true indicating that seeking is active
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/seeking_event
    */
  lazy val OnSeeking = eventEmitter[dom.Event]("seeking")

  /** Script to be run when the browser is unable to fetch the media data for whatever reason
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/stalled_event
    */
  lazy val OnStalled = eventEmitter[dom.Event]("stalled")

  /** Script to be run when fetching the media data is stopped before it is completely loaded for whatever reason
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/suspend_event
    */
  lazy val OnSuspend = eventEmitter[dom.Event]("suspend")

  /** Script to be run when the playing position has changed (like when the user fast forwards to a different point in the media)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/timeupdate_event
    */
  lazy val OnTimeUpdate = eventEmitter[dom.Event]("timeupdate")

  /** Script to be run each time the volume is changed which (includes setting the volume to "mute")
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/volumechange_event
    */
  lazy val OnVolumeChange = eventEmitter[dom.Event]("volumechange")

  /** Script to be run when the media has paused but is expected to resume (like when the media pauses to buffer more data)
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/waiting_event
    */
  lazy val OnWaiting = eventEmitter[dom.Event]("waiting")

  /** The animationend event is event fires when a CSS animation reaches the end of its active period.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationend_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
    */
  lazy val OnAnimationEnd = eventEmitter[dom.AnimationEvent]("animationend")

  /** The animationiteration event is sent when a CSS animation reaches the end of an iteration. An iteration ends when a single pass
    * through the sequence of animation instructions is completed by executing the last animation step.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationiteration_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
    */
  lazy val OnAnimationIteration = eventEmitter[dom.AnimationEvent]("animationiteration")

  /** The animationstart event is sent when a CSS Animation starts to play.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationstart_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
    */
  lazy val OnAnimationStart = eventEmitter[dom.AnimationEvent]("animationstart")

  /** The `transitionend` event is sent to when a CSS transition completes.
    *
    * Note: If the transition is removed from its target node before the transition completes execution, the `transitionend` event won't be
    * generated. One way this can happen is by changing the value of the `transition-property` attribute which applies to the target.
    * Another is if the `display` attribute is set to `none`.
    *
    * @see
    *   [[https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/ontransitionend MDN]]
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/transitionend_event
    */
  lazy val OnTransitionEnd = eventEmitter[dom.Event]("transitionend")

  /** The onload property of the GlobalEventHandlers mixin is an event handler for the load event of a Window, XMLHttpRequest, `<img>`
    * element, etc., which fires when the resource has loaded.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
    */
  lazy val OnLoad = eventEmitter[dom.UIEvent]("load")

  /** The GlobalEventHandlers.onresize property contains an EventHandler triggered when a resize event is received.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Window/resize_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
    */
  lazy val OnResize = eventEmitter[dom.UIEvent]("resize")

  /** An event handler for scroll events on element.
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Window/scroll_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
    */
  lazy val OnScroll = eventEmitter[dom.UIEvent]("scroll")

  /** Fires when a `<menu>` element is shown as a context menu
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/show_event
    */
  lazy val OnShow = eventEmitter[dom.Event]("show")

  /** Fires when the user opens or closes the `<details>` element
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/toggle_event
    */
  lazy val OnToggle = eventEmitter[dom.Event]("toggle")

  /** Script to be run when an error occurs when the file is being loaded
    *
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/Element/error_event
    * @see
    *   https://developer.mozilla.org/en-US/docs/Web/API/ErrorEvent
    */
  lazy val OnError = eventEmitter[dom.ErrorEvent]("error")

}

object DomEventsApi {
  import DomEvents.*

  /** @see [[package.DomEvents.OnClick]] */
  lazy val onClick = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnClick)

  /** @see [[package.DomEvents.OnDblClick]] */
  lazy val onDblClick = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnDblClick)

  /** @see [[package.DomEvents.OnMouseDown]] */
  lazy val onMouseDown = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseDown)

  /** @see [[package.DomEvents.OnMouseMove]] */
  lazy val onMouseMove = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseMove)

  /** @see [[package.DomEvents.OnMouseOut]] */
  lazy val onMouseOut = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseOut)

  /** @see [[package.DomEvents.OnMouseOver]] */
  lazy val onMouseOver = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseOver)

  /** @see [[package.DomEvents.OnMouseLeave]] */
  lazy val onMouseLeave = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseLeave)

  /** @see [[package.DomEvents.OnMouseEnter]] */
  lazy val onMouseEnter = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseEnter)

  /** @see [[package.DomEvents.OnMouseUp]] */
  lazy val onMouseUp = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnMouseUp)

  /** @see [[package.DomEvents.OnWheel]] */
  lazy val onWheel = ModifierEmitter[HtmlElement, dom.WheelEvent](DomEvents.OnWheel)

  /** @see [[package.DomEvents.OnContextMenu]] */
  lazy val onContextMenu = ModifierEmitter[HtmlElement, dom.MouseEvent](DomEvents.OnContextMenu)

  /** @see [[package.DomEvents.OnDrag]] */
  lazy val onDrag = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDrag)

  /** @see [[package.DomEvents.OnDragEnd]] */
  lazy val onDragEnd = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDragEnd)

  /** @see [[package.DomEvents.OnDragEnter]] */
  lazy val onDragEnter = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDragEnter)

  /** @see [[package.DomEvents.OnDragLeave]] */
  lazy val onDragLeave = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDragLeave)

  /** @see [[package.DomEvents.OnDragOver]] */
  lazy val onDragOver = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDragOver)

  /** @see [[package.DomEvents.OnDragStart]] */
  lazy val onDragStart = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDragStart)

  /** @see [[package.DomEvents.OnDrop]] */
  lazy val onDrop = ModifierEmitter[HtmlElement, dom.DragEvent](DomEvents.OnDrop)

  /** @see [[package.DomEvents.OnPointerOver]] */
  lazy val onPointerOver = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerOver)

  /** @see [[package.DomEvents.OnPointerEnter]] */
  lazy val onPointerEnter = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerEnter)

  /** @see [[package.DomEvents.OnPointerDown]] */
  lazy val onPointerDown = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerDown)

  /** @see [[package.DomEvents.OnPointerMove]] */
  lazy val onPointerMove = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerMove)

  /** @see [[package.DomEvents.OnPointerUp]] */
  lazy val onPointerUp = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerUp)

  /** @see [[package.DomEvents.OnPointerCancel]] */
  lazy val onPointerCancel = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerCancel)

  /** @see [[package.DomEvents.OnPointerOut]] */
  lazy val onPointerOut = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerOut)

  /** @see [[package.DomEvents.OnPointerLeave]] */
  lazy val onPointerLeave = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.OnPointerLeave)

  /** @see [[package.DomEvents.GotPointerCapture]] */
  lazy val gotPointerCapture = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.GotPointerCapture)

  /** @see [[package.DomEvents.LostPointerCapture]] */
  lazy val lostPointerCapture = ModifierEmitter[HtmlElement, dom.PointerEvent](DomEvents.LostPointerCapture)

  /** @see [[package.DomEvents.OnChange]] */
  lazy val onChange = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnChange)

  /** @see [[package.DomEvents.OnSelect]] */
  lazy val onSelect = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSelect)

  /** @see [[package.DomEvents.OnBeforeInput]] */
  lazy val onBeforeInput = ModifierEmitter[HtmlElement, dom.InputEvent](DomEvents.OnBeforeInput)

  /** @see [[package.DomEvents.OnInput]] */
  lazy val onInput = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnInput)

  /** @see [[package.DomEvents.OnBlur]] */
  lazy val onBlur = ModifierEmitter[HtmlElement, dom.FocusEvent](DomEvents.OnBlur)

  /** @see [[package.DomEvents.OnFocus]] */
  lazy val onFocus = ModifierEmitter[HtmlElement, dom.FocusEvent](DomEvents.OnFocus)

  /** @see [[package.DomEvents.OnSubmit]] */
  lazy val onSubmit = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSubmit)

  /** @see [[package.DomEvents.OnReset]] */
  lazy val onReset = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnReset)

  /** @see [[package.DomEvents.OnInvalid]] */
  lazy val onInvalid = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnInvalid)

  /** @see [[package.DomEvents.OnSearch]] */
  lazy val onSearch = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSearch)

  /** @see [[package.DomEvents.OnKeyDown]] */
  lazy val onKeyDown = ModifierEmitter[HtmlElement, dom.KeyboardEvent](DomEvents.OnKeyDown)

  /** @see [[package.DomEvents.OnKeyUp]] */
  lazy val onKeyUp = ModifierEmitter[HtmlElement, dom.KeyboardEvent](DomEvents.OnKeyUp)

  /** @see [[package.DomEvents.OnKeyPress]] */
  lazy val onKeyPress = ModifierEmitter[HtmlElement, dom.KeyboardEvent](DomEvents.OnKeyPress)

  /** @see [[package.DomEvents.OnCopy]] */
  lazy val onCopy = ModifierEmitter[HtmlElement, dom.ClipboardEvent](DomEvents.OnCopy)

  /** @see [[package.DomEvents.OnCut]] */
  lazy val onCut = ModifierEmitter[HtmlElement, dom.ClipboardEvent](DomEvents.OnCut)

  /** @see [[package.DomEvents.OnPaste]] */
  lazy val onPaste = ModifierEmitter[HtmlElement, dom.ClipboardEvent](DomEvents.OnPaste)

  /** @see [[package.DomEvents.OnAbort]] */
  lazy val onAbort = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnAbort)

  /** @see [[package.DomEvents.OnCanPlay]] */
  lazy val onCanPlay = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnCanPlay)

  /** @see [[package.DomEvents.OnCanPlayThrough]] */
  lazy val onCanPlayThrough = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnCanPlayThrough)

  /** @see [[package.DomEvents.OnCueChange]] */
  lazy val onCueChange = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnCueChange)

  /** @see [[package.DomEvents.OnDurationChange]] */
  lazy val onDurationChange = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnDurationChange)

  /** @see [[package.DomEvents.OnEmptied]] */
  lazy val onEmptied = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnEmptied)

  /** @see [[package.DomEvents.OnEnded]] */
  lazy val onEnded = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnEnded)

  /** @see [[package.DomEvents.OnLoadedData]] */
  lazy val onLoadedData = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnLoadedData)

  /** @see [[package.DomEvents.OnLoadedMetadata]] */
  lazy val onLoadedMetadata = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnLoadedMetadata)

  /** @see [[package.DomEvents.OnLoadStart]] */
  lazy val onLoadStart = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnLoadStart)

  /** @see [[package.DomEvents.OnPause]] */
  lazy val onPause = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnPause)

  /** @see [[package.DomEvents.OnPlay]] */
  lazy val onPlay = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnPlay)

  /** @see [[package.DomEvents.OnPlaying]] */
  lazy val onPlaying = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnPlaying)

  /** @see [[package.DomEvents.OnProgress]] */
  lazy val onProgress = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnProgress)

  /** @see [[package.DomEvents.OnRateChange]] */
  lazy val onRateChange = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnRateChange)

  /** @see [[package.DomEvents.OnSeeked]] */
  lazy val onSeeked = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSeeked)

  /** @see [[package.DomEvents.OnSeeking]] */
  lazy val onSeeking = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSeeking)

  /** @see [[package.DomEvents.OnStalled]] */
  lazy val onStalled = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnStalled)

  /** @see [[package.DomEvents.OnSuspend]] */
  lazy val onSuspend = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnSuspend)

  /** @see [[package.DomEvents.OnTimeUpdate]] */
  lazy val onTimeUpdate = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnTimeUpdate)

  /** @see [[package.DomEvents.OnVolumeChange]] */
  lazy val onVolumeChange = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnVolumeChange)

  /** @see [[package.DomEvents.OnWaiting]] */
  lazy val onWaiting = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnWaiting)

  /** @see [[package.DomEvents.OnAnimationEnd]] */
  lazy val onAnimationEnd = ModifierEmitter[HtmlElement, dom.AnimationEvent](DomEvents.OnAnimationEnd)

  /** @see [[package.DomEvents.OnAnimationIteration]] */
  lazy val onAnimationIteration = ModifierEmitter[HtmlElement, dom.AnimationEvent](DomEvents.OnAnimationIteration)

  /** @see [[package.DomEvents.OnAnimationStart]] */
  lazy val onAnimationStart = ModifierEmitter[HtmlElement, dom.AnimationEvent](DomEvents.OnAnimationStart)

  /** @see [[package.DomEvents.OnTransitionEnd]] */
  lazy val onTransitionEnd = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnTransitionEnd)

  /** @see [[package.DomEvents.OnLoad]] */
  lazy val onLoad = ModifierEmitter[HtmlElement, dom.UIEvent](DomEvents.OnLoad)

  /** @see [[package.DomEvents.OnResize]] */
  lazy val onResize = ModifierEmitter[HtmlElement, dom.UIEvent](DomEvents.OnResize)

  /** @see [[package.DomEvents.OnScroll]] */
  lazy val onScroll = ModifierEmitter[HtmlElement, dom.UIEvent](DomEvents.OnScroll)

  /** @see [[package.DomEvents.OnShow]] */
  lazy val onShow = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnShow)

  /** @see [[package.DomEvents.OnToggle]] */
  lazy val onToggle = ModifierEmitter[HtmlElement, dom.Event](DomEvents.OnToggle)

  /** @see [[package.DomEvents.OnError]] */
  lazy val onError = ModifierEmitter[HtmlElement, dom.ErrorEvent](DomEvents.OnError)

  extension (elem: HtmlElement) {

    /** The click event is raised when the user clicks on an element. The click event will occur after the mousedown and mouseup events.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/click_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onClick = DomEvents.OnClick.forInstance(elem)

    /** The dblclick event is fired when a pointing device button (usually a mouse button) is clicked twice on a single element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/dblclick_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onDblClick = DomEvents.OnDblClick.forInstance(elem)

    /** The mousedown event is raised when the user presses the mouse button.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mousedown_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseDown = DomEvents.OnMouseDown.forInstance(elem)

    /** The mousemove event is raised when the user moves the mouse.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mousemove_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseMove = DomEvents.OnMouseMove.forInstance(elem)

    /** The mouseout event is raised when the mouse leaves an element (e.g, when the mouse moves off of an image in the web page, the
      * mouseout event is raised for that image element).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseout_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseOut = DomEvents.OnMouseOut.forInstance(elem)

    /** The mouseover event is raised when the user moves the mouse over a particular element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseover_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseOver = DomEvents.OnMouseOver.forInstance(elem)

    /** The mouseleave event is fired when the pointer of a pointing device (usually a mouse) is moved out of an element.
      *
      * mouseleave and mouseout are similar but differ in that mouseleave does not bubble and mouseout does.
      *
      * This means that mouseleave is fired when the pointer has exited the element and all of its descendants, whereas mouseout is fired
      * when the pointer leaves the element or leaves one of the element's descendants (even if the pointer is still within the element).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseleave_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseLeave = DomEvents.OnMouseLeave.forInstance(elem)

    /** The mouseenter event is fired when a pointing device (usually a mouse) is moved over the element that has the listener attached.
      *
      * Similar to mouseover, it differs in that it doesn't bubble and that it isn't sent when the pointer is moved from one of its
      * descendants' physical space to its own physical space.
      *
      * With deep hierarchies, the amount of mouseenter events sent can be quite huge and cause significant performance problems. In such
      * cases, it is better to listen for mouseover events.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseenter_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseEnter = DomEvents.OnMouseEnter.forInstance(elem)

    /** The mouseup event is raised when the user releases the mouse button.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/mouseup_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onMouseUp = DomEvents.OnMouseUp.forInstance(elem)

    /** Fires when the mouse wheel rolls up or down over an element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/wheel_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/WheelEvent
      */
    def onWheel = DomEvents.OnWheel.forInstance(elem)

    /** Script to be run when a context menu is triggered
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/contextmenu_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/MouseEvent
      */
    def onContextMenu = DomEvents.OnContextMenu.forInstance(elem)

    /** Script to be run when an element is dragged
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/drag_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDrag = DomEvents.OnDrag.forInstance(elem)

    /** Script to be run at the end of a drag operation
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragend_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDragEnd = DomEvents.OnDragEnd.forInstance(elem)

    /** Script to be run when an element has been dragged to a valid drop target
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragenter_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDragEnter = DomEvents.OnDragEnter.forInstance(elem)

    /** Script to be run when an element leaves a valid drop target
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragleave_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDragLeave = DomEvents.OnDragLeave.forInstance(elem)

    /** Script to be run when an element is being dragged over a valid drop target
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragover_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDragOver = DomEvents.OnDragOver.forInstance(elem)

    /** Script to be run at the start of a drag operation
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/dragstart_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDragStart = DomEvents.OnDragStart.forInstance(elem)

    /** Script to be run when dragged element is being dropped
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/drop_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/DragEvent
      */
    def onDrop = DomEvents.OnDrop.forInstance(elem)

    /** fired when a pointing device is moved into an element's hit test boundaries.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerover_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerOver = DomEvents.OnPointerOver.forInstance(elem)

    /** fired when a pointing device is moved into the hit test boundaries of an element or one of its descendants, including as a result of
      * a pointerdown event from a device that does not support hover (see pointerdown).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerenter_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerEnter = DomEvents.OnPointerEnter.forInstance(elem)

    /** fired when a pointer becomes active.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerdown_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerDown = DomEvents.OnPointerDown.forInstance(elem)

    /** fired when a pointer changes coordinates.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointermove_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerMove = DomEvents.OnPointerMove.forInstance(elem)

    /** fired when a pointer is no longer active.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerup_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerUp = DomEvents.OnPointerUp.forInstance(elem)

    /** a browser fires this event if it concludes the pointer will no longer be able to generate events (for example the related device is
      * deactived).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointercancel_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerCancel = DomEvents.OnPointerCancel.forInstance(elem)

    /** fired for several reasons including: pointing device is moved out of the hit test boundaries of an element; firing the pointerup
      * event for a device that does not support hover (see pointerup); after firing the pointercancel event (see pointercancel); when a pen
      * stylus leaves the hover range detectable by the digitizer.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerout_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerOut = DomEvents.OnPointerOut.forInstance(elem)

    /** fired when a pointing device is moved out of the hit test boundaries of an element. For pen devices, this event is fired when the
      * stylus leaves the hover range detectable by the digitizer.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/pointerleave_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def onPointerLeave = DomEvents.OnPointerLeave.forInstance(elem)

    /** fired when an element receives pointer capture.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/gotpointercapture_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def gotPointerCapture = DomEvents.GotPointerCapture.forInstance(elem)

    /** Fired after pointer capture is released for a pointer.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/lostpointercapture_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/PointerEvent
      */
    def lostPointerCapture = DomEvents.LostPointerCapture.forInstance(elem)

    /** The change event is fired for input, select, and textarea elements when a change to the element's value is committed by the user.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/change_event
      */
    def onChange = DomEvents.OnChange.forInstance(elem)

    /** The select event only fires when text inside a text input or textarea is selected. The event is fired after the text has been
      * selected.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/select_event
      */
    def onSelect = DomEvents.OnSelect.forInstance(elem)

    /** The DOM beforeinput event fires when the value of an `<input>`, or `<textarea>` element is about to be modified. The event also
      * applies to elements with contenteditable enabled, and to any element when designMode is turned on.
      *
      * @note
      *   IE does not support this event.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/beforeinput_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/InputEvent
      */
    def onBeforeInput = DomEvents.OnBeforeInput.forInstance(elem)

    /** The input event is fired for input, select, textarea, and contentEditable elements when it gets user input.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/input_event
      */
    def onInput = DomEvents.OnInput.forInstance(elem)

    /** The blur event is raised when an element loses focus.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/blur_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent
      */
    def onBlur = DomEvents.OnBlur.forInstance(elem)

    /** The focus event is raised when the user sets focus on the given element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/focus_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/FocusEvent
      */
    def onFocus = DomEvents.OnFocus.forInstance(elem)

    /** The submit event is fired when the user clicks a submit button in a form (`<input type="submit"/>`).
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/submit_event
      */
    def onSubmit = DomEvents.OnSubmit.forInstance(elem)

    /** The reset event is fired when a form is reset.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/reset_event
      */
    def onReset = DomEvents.OnReset.forInstance(elem)

    /** Script to be run when an element is invalid
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement/invalid_event
      */
    def onInvalid = DomEvents.OnInvalid.forInstance(elem)

    /** Fires when the user writes something in a search field (for `<input="search">`)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement/search_event
      */
    def onSearch = DomEvents.OnSearch.forInstance(elem)

    /** The keydown event is raised when the user presses a keyboard key.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keydown_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
      */
    def onKeyDown = DomEvents.OnKeyDown.forInstance(elem)

    /** The keyup event is raised when the user releases a key that's been pressed.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keyup_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
      */
    def onKeyUp = DomEvents.OnKeyUp.forInstance(elem)

    /** The keypress event should be raised when the user presses a key on the keyboard. However, not all browsers fire keypress events for
      * certain keys.
      *
      * Webkit-based browsers (Google Chrome and Safari, for example) do not fire keypress events on the arrow keys. Firefox does not fire
      * keypress events on modifier keys like SHIFT.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/keypress_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent
      */
    def onKeyPress = DomEvents.OnKeyPress.forInstance(elem)

    /** Fires when the user copies the content of an element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/copy_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
      */
    def onCopy = DomEvents.OnCopy.forInstance(elem)

    /** Fires when the user cuts the content of an element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/cut_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
      */
    def onCut = DomEvents.OnCut.forInstance(elem)

    /** Fires when the user pastes some content in an element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/paste_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/ClipboardEvent
      */
    def onPaste = DomEvents.OnPaste.forInstance(elem)

    /** Script to be run on abort
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/abort_event
      */
    def onAbort = DomEvents.OnAbort.forInstance(elem)

    /** Script to be run when a file is ready to start playing (when it has buffered enough to begin)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/canplay_event
      */
    def onCanPlay = DomEvents.OnCanPlay.forInstance(elem)

    /** Script to be run when a file can be played all the way to the end without pausing for buffering
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/canplaythrough_event
      */
    def onCanPlayThrough = DomEvents.OnCanPlayThrough.forInstance(elem)

    /** Script to be run when the cue changes in a `<track>` element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/TextTrack/cuechange_event
      */
    def onCueChange = DomEvents.OnCueChange.forInstance(elem)

    /** Script to be run when the length of the media changes
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/durationchange_event
      */
    def onDurationChange = DomEvents.OnDurationChange.forInstance(elem)

    /** Script to be run when something bad happens and the file is suddenly unavailable (like unexpectedly disconnects)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/emptied_event
      */
    def onEmptied = DomEvents.OnEmptied.forInstance(elem)

    /** Script to be run when the media has reach the end (a useful event for messages like "thanks for listening")
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/ended_event
      */
    def onEnded = DomEvents.OnEnded.forInstance(elem)

    /** Script to be run when media data is loaded
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadeddata_event
      */
    def onLoadedData = DomEvents.OnLoadedData.forInstance(elem)

    /** Script to be run when meta data (like dimensions and duration) are loaded
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadedmetadata_event
      */
    def onLoadedMetadata = DomEvents.OnLoadedMetadata.forInstance(elem)

    /** Script to be run just as the file begins to load before anything is actually loaded
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/loadstart_event
      */
    def onLoadStart = DomEvents.OnLoadStart.forInstance(elem)

    /** Script to be run when the media is paused either by the user or programmatically
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/pause_event
      */
    def onPause = DomEvents.OnPause.forInstance(elem)

    /** Script to be run when the media is ready to start playing
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/play_event
      */
    def onPlay = DomEvents.OnPlay.forInstance(elem)

    /** Script to be run when the media actually has started playing
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/playing_event
      */
    def onPlaying = DomEvents.OnPlaying.forInstance(elem)

    /** Script to be run when the browser is in the process of getting the media data
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/progress_event
      */
    def onProgress = DomEvents.OnProgress.forInstance(elem)

    /** Script to be run each time the playback rate changes (like when a user switches to a slow motion or fast forward mode)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/ratechange_event
      */
    def onRateChange = DomEvents.OnRateChange.forInstance(elem)

    /** Script to be run when the seeking attribute is set to false indicating that seeking has ended
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/seeked_event
      */
    def onSeeked = DomEvents.OnSeeked.forInstance(elem)

    /** Script to be run when the seeking attribute is set to true indicating that seeking is active
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/seeking_event
      */
    def onSeeking = DomEvents.OnSeeking.forInstance(elem)

    /** Script to be run when the browser is unable to fetch the media data for whatever reason
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/stalled_event
      */
    def onStalled = DomEvents.OnStalled.forInstance(elem)

    /** Script to be run when fetching the media data is stopped before it is completely loaded for whatever reason
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/suspend_event
      */
    def onSuspend = DomEvents.OnSuspend.forInstance(elem)

    /** Script to be run when the playing position has changed (like when the user fast forwards to a different point in the media)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/timeupdate_event
      */
    def onTimeUpdate = DomEvents.OnTimeUpdate.forInstance(elem)

    /** Script to be run each time the volume is changed which (includes setting the volume to "mute")
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/volumechange_event
      */
    def onVolumeChange = DomEvents.OnVolumeChange.forInstance(elem)

    /** Script to be run when the media has paused but is expected to resume (like when the media pauses to buffer more data)
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/HTMLMediaElement/waiting_event
      */
    def onWaiting = DomEvents.OnWaiting.forInstance(elem)

    /** The animationend event is event fires when a CSS animation reaches the end of its active period.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationend_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
      */
    def onAnimationEnd = DomEvents.OnAnimationEnd.forInstance(elem)

    /** The animationiteration event is sent when a CSS animation reaches the end of an iteration. An iteration ends when a single pass
      * through the sequence of animation instructions is completed by executing the last animation step.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationiteration_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
      */
    def onAnimationIteration = DomEvents.OnAnimationIteration.forInstance(elem)

    /** The animationstart event is sent when a CSS Animation starts to play.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/animationstart_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/AnimationEvent
      */
    def onAnimationStart = DomEvents.OnAnimationStart.forInstance(elem)

    /** The `transitionend` event is sent to when a CSS transition completes.
      *
      * Note: If the transition is removed from its target node before the transition completes execution, the `transitionend` event won't
      * be generated. One way this can happen is by changing the value of the `transition-property` attribute which applies to the target.
      * Another is if the `display` attribute is set to `none`.
      *
      * @see
      *   [[https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/ontransitionend MDN]]
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/transitionend_event
      */
    def onTransitionEnd = DomEvents.OnTransitionEnd.forInstance(elem)

    /** The onload property of the GlobalEventHandlers mixin is an event handler for the load event of a Window, XMLHttpRequest, `<img>`
      * element, etc., which fires when the resource has loaded.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
      */
    def onLoad = DomEvents.OnLoad.forInstance(elem)

    /** The GlobalEventHandlers.onresize property contains an EventHandler triggered when a resize event is received.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Window/resize_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
      */
    def onResize = DomEvents.OnResize.forInstance(elem)

    /** An event handler for scroll events on element.
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Window/scroll_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/UIEvent
      */
    def onScroll = DomEvents.OnScroll.forInstance(elem)

    /** Fires when a `<menu>` element is shown as a context menu
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/show_event
      */
    def onShow = DomEvents.OnShow.forInstance(elem)

    /** Fires when the user opens or closes the `<details>` element
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/toggle_event
      */
    def onToggle = DomEvents.OnToggle.forInstance(elem)

    /** Script to be run when an error occurs when the file is being loaded
      *
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/Element/error_event
      * @see
      *   https://developer.mozilla.org/en-US/docs/Web/API/ErrorEvent
      */
    def onError = DomEvents.OnError.forInstance(elem)
  }
}
