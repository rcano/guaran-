package guarana.swing

import javax.swing.Icon
import util._

class Tab {
  def title = Tab.Title.forInstance(this)
  def icon = Tab.Icon.forInstance(this)
  def content = Tab.Content.forInstance(this)
  def tip = Tab.Tip.forInstance(this)
  def enabled = Tab.Enabled.forInstance(this)
  def tabNode = Tab.TabNode.forInstance(this)
}
object Tab {
  val Title = Var[String | Null]("title", null, eagerEvaluation = true)
  val Icon = Var[Icon | Null]("icon", null, eagerEvaluation = true)
  val Content = Var[Node]("content", throw new IllegalStateException("Content not set!"), eagerEvaluation = true)
  val Tip = Var[String | Null]("tip", null, eagerEvaluation = true)
  val Enabled = Var[Boolean]("enabled", true, eagerEvaluation = true)
  val TabNode = Var[Node | Null]("tabNode", null, true)


  def apply(
    title: Opt[Binding[String | Null]] = UnsetParam,
    icon: Opt[Binding[Icon | Null]] = UnsetParam,
    content: Opt[Binding[Node]] = UnsetParam,
    tip: Opt[Binding[String | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    tabNode: Opt[Binding[Node | Null]] = UnsetParam,
  ): Scenegraph ?=> VarContextAction[Tab] = {
    val res = new Tab()
    ifSet(title, res.title := _)
    ifSet(icon, res.icon := _)
    ifSet(content, res.content := _)
    ifSet(tip, res.tip := _)
    ifSet(enabled, res.enabled := _)
    ifSet(tabNode, res.tabNode := _)
    res
  }
}