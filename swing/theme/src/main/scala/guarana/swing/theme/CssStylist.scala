package guarana
package swing
package theme

import com.helger.css.decl.{CSSDeclaration, CSSSelectorSimpleMember, CascadingStyleSheet}
import com.helger.css.reader.{CSSReader, CSSReaderSettings}
import guarana.Stylist.Metrics
import guarana.animation.TransitionType
import guarana.util.UnsetParam
import scala.collection.mutable.HashMap
import scala.jdk.CollectionConverters.*

import CssStylist.*
import com.helger.css.decl.ICSSSelectorMember
import com.helger.css.decl.ECSSSelectorCombinator
import guarana.util.cfor

class CssStylist(cssFiles: Seq[(name: String, content: String)]) extends Stylist {

  val parsedCss = {
    val settings = CSSReaderSettings().setKeepDeprecatedProperties(true).setUseSourceLocation(false)
    cssFiles.map { cssFile =>
      cssFile ++ (parsed = CSSReader.readFromStringReader(cssFile.content, settings).asInstanceOf[CascadingStyleSheet])
    }
  }

  type ElementName = String

  case class Rule(declarations: Array[CSSDeclaration], predicate: Node => Boolean = noPredicate)
  val index = HashMap[ElementName | UnsetParam.type, ClassSetLookup[Rule]]()

  for {
    css <- parsedCss.view
    rule <- css.parsed.getAllStyleRules().asScala
    selector <- rule.getAllSelectors.asScala
  } {
    val selectorMembers = selector.getAllMembers.asScala

    cfor(selectorMembers.size - 1, _ >= 0) { i =>
      
      i - 1
    }

    val classes = selectorMembers.collect {
      case m: CSSSelectorSimpleMember if m.isClass() => m.getValue().substring(1)
    }
    val elementName = selectorMembers
      .collectFirst {
        case m: CSSSelectorSimpleMember if m.isElementName() => m.getValue()
      }
      .getOrElse(UnsetParam)

    val decls = rule.getAllDeclarations().asScala.toArray
    

    index.getOrElseUpdate(elementName, ClassSetLookup(8, 0.8)).register(classes.toSet, Rule(decls))
  }

  private def splitBySelectorCombinators(selectors: List[ICSSSelectorMember]): List[List[ICSSSelectorMember]] = {
    if (selectors.isEmpty) return Nil
    selectors.span(!_.isInstanceOf[ECSSSelectorCombinator]) match {
      case (l, (combinator :: r)) => l :: List(combinator) :: splitBySelectorCombinators(r)
      case (l, _) => l :: Nil
    }
    // l :: splitBySelectorCombinators(r)
  }

  def declarationsFor(elementName: ElementName | UnsetParam.type, classes: List[String]): Array[Rule] = {
    for {
      lookup <- index.get(elementName).toArray
      rules <- lookup.lookup(classes)
    } yield rules
  }

  override def apply[T](metrics: Metrics, property: ObsVal[T], instance: Any): (AbstractToolkit) ?=> Option[T] = ???

  override def getTransition[T](metrics: Metrics, property: ObsVal[T], instance: Any): (AbstractToolkit) ?=> Option[TransitionType[T]] = ???

  override def invalidateCache(node: Any): (AbstractToolkit) ?=> Unit = ???

  override def installDefaults(node: Any): (AbstractToolkit) ?=> Unit = ???

  override def uninstallDefaults(node: Any): (AbstractToolkit) ?=> Unit = ???

}
object CssStylist {
  val noPredicate: Node => Boolean = _ => true
}

@main def testCssIndex(): Unit = {
  val css = 
    """|pane.red.wide.mobile {
       |  background: blue
       |}
       |
       |button.default {
       |  font-size: 18px
       |}
       |
       |.marging-left {
       |  marging: 0 0 0 2em
       |}
       |
       |div {
       |  foreground: black
       |}
       |
       |div.parent > div.nested {
       |  foreground: black
       |}
       |""".stripMargin

  val stylist = CssStylist(Seq("foo" -> css))
  pprint.pprintln(stylist.index)

  
  var found = stylist.declarationsFor(UnsetParam, List("marging-left").sorted)
  pprint.pprintln(found)
  found = stylist.declarationsFor("pane", List("wide", "red", "mobile").sorted)
  pprint.pprintln(found)
  found = stylist.declarationsFor("div", Nil)
  pprint.pprintln(found)

}
