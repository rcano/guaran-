package guarana.web.codegen

import better.files.*

val HtmlTagRegex = (
  raw"(?ms)^(?<docstring>/\*\*.*?\*/)\s+" +
  raw"""lazy val (?<fieldName>[^:]+?): HtmlTag\[(?<domElem>[^\]]+?)\] = htmlTag\("(?<tagName>[^"]+?)"[^)]*?\)$$"""
).r

val StylePropRegex = (
  raw"(?ms)^(?<docstring>/\*\*.*?\*/)\s+" +
  raw"""lazy val (?<fieldName>[^:]+?): StyleProp\[(?<varType>[^\]]+?)\] (?<typeDecorators>with [^=]+)?= .+?Style\("(?<propName>[^"]+?)"[^)]*?\)$$"""
).r

val HtmlPropRegex = (
  raw"(?ms)^(?<docstring>/\*\*.*?\*/)\s+" +
  raw"""lazy val (?<fieldName>[^:]+?): HtmlProp\[(?<propType>[^\]]+?)\] = (?<ctor>\w+?Prop)\("(?<propName>[^"]+?)"\)$$"""
).r

val DomEventsRegex = (
  raw"(?ms)^(?<docstring>/\*\*.*?\*/)\s+" +
  raw"""lazy val (?<fieldName>[^:]+?): EventProp\[(?<evtType>[^\]]+?)\] = eventProp\("(?<evtName>[^"]+?)"\)$$"""
).r

@main def parseHtmlTags(): Unit = {
  val source = Resource.asString("html-tags").get

  // println(s"Using regex ${EntryRegex.pattern}")
  HtmlTagRegex.findAllMatchIn(source).foreach { m =>
    println(m.group("docstring"))
    println(m.group("fieldName") + ": " + m.group("domElem") + " = " + m.group("tagName"))
    println("================================")  
  }
}

@main def processHtmlNodes(): Unit = {
  val source = Resource.asString("html-tags").get

  // println(s"Using regex ${EntryRegex.pattern}")
  val domElem = HtmlTagRegex.findAllMatchIn(source)//.map(_.group("domElem")).distinct
  val generatedTypes = collection.mutable.Set("HtmlElement", "Element")
  domElem.foreach(m =>
    val domName = m.group("domElem")
    val tpe = domName.stripPrefix("dom.HTML") match {
      case "Element" => "HtmlElement"
      case other => other
    }
    val fieldName = m.group("fieldName")
    val tagName = m.group("tagName")
    if (!generatedTypes(tpe)) {
      generatedTypes += tpe
      println(s"opaque type $tpe <: HtmlElement = $domName")
      println(s"object $tpe { extension (v: $tpe) inline def unwrap: $domName = v }")
    }
    println(s"lazy val $fieldName: TagCreator[$tpe] = TagCreator(\"$tagName\")")
  )
}

@main def processHtmlVars(): Unit = {
  val source = Resource.asString("style-vars").get

  val matches = StylePropRegex.findAllMatchIn(source)
  case class StyleVar(name: String, prop: String, tpe: String, docstring: String)
  val vars = matches.map(m =>
    val tpe = m.group("varType")
    val typeDecorators = Option(m.group("typeDecorators"))
    val resultType = if (typeDecorators.exists(_.contains("u.Length[DSP"))) "StylableSize" else tpe

    StyleVar(m.group("fieldName"), m.group("propName"), resultType, m.group("docstring"))
  ).toArray
  // first modifiers
  vars foreach (v => println(s"""|/** @see [[package.StyleVars.${v.name.capitalize}]] */
                                 |lazy val ${v.name} = ModifierVar[HtmlElement, ${v.tpe}](StyleVars.${v.name.capitalize})""".stripMargin))
  println()
  // extension methods
  println("extension (elem: HtmlElement) {")
  vars foreach (v => println(s"  ${v.docstring.replace("\n", "\n  ")}\n  def ${v.name} = StyleVars.${v.name.capitalize}.forInstance(elem)"))
  println("}")
}

@main def processHtmlProps(): Unit = {
  val source = Resource.asString("html-props").get

  val matches = HtmlPropRegex.findAllMatchIn(source)
  case class Prop(name: String, prop: String, tpe: String, ctor: String, docstring: String)
  val props = matches.map(m => Prop(m.group("fieldName"), m.group("propName"), m.group("propType"), m.group("ctor"), m.group("docstring")) ).toArray

  props foreach { prop =>
    println(prop.docstring)
    println(s"lazy val ${prop.name.capitalize} = ${prop.ctor}(\"${prop.name}\")")
    println()
  }

  println()
  props foreach { prop => 
    println(s"/** @see [[package.HtmlProps.${prop.name.capitalize}]]*/")  
    println(s"lazy val ${prop.name} = ModifierVar[HtmlElement, ${prop.tpe.split(", ").head}](HtmlProps.${prop.name.capitalize})")
  }

  println()
  println("extension (elem: HtmlElement) {")
  props foreach (p => println(s"  ${p.docstring.replace("\n", "\n  ")}\n  def ${p.name} = HtmlProps.${p.name.capitalize}.forInstance(elem)\n"))
  println("}")
}

@main def processDomEvents(): Unit = {
  val source = Resource.asString("html-events").get

  val matches = DomEventsRegex.findAllMatchIn(source)
  case class Evt(name: String, evtName: String, tpe: String, docstring: String)
  val evts = matches.map(m => Evt(m.group("fieldName"), m.group("evtName"), m.group("evtType"), m.group("docstring")) ).toArray

  evts foreach { evt =>
    println(evt.docstring)
    println(s"lazy val ${evt.name.capitalize} = eventEmitter[${evt.tpe}](\"${evt.evtName}\")")
    println()
  }
  println()
  evts foreach { evt => 
    println(s"/** @see [[package.DomEvents.${evt.name.capitalize}]]*/")  
    println(s"lazy val ${evt.name} = ModifierEmitter[HtmlElement, ${evt.tpe}](DomEvents.${evt.name.capitalize})")
  }

  println()
  println("extension (elem: HtmlElement) {")
  evts foreach (p => println(s"  ${p.docstring.replace("\n", "\n  ")}\n  def ${p.name} = DomEvents.${p.name.capitalize}.forInstance(elem)\n"))
  println("}")
}
