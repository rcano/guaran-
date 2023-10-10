package guarana
package web

import org.scalajs.dom
import scalajs.js

// trait EncodedVarWriter[From, To](val to: Var[To]) {
//   protected def encode(f: From): To
//   def :=(b: Binding[From])(using instance: ValueOf[to.ForInstance]): VarContextAction[Unit] = to := b.map(encode)
// }

def HtmlAttrVar[Tag <: HtmlElement](attrName: String): ExternalVar.Aux[Tag, String] = ExternalVar[Tag, String](
  attrName,
  _.unwrap.getAttribute(attrName),
  (v, t) => v.unwrap.setAttribute(attrName, t),
  eagerEvaluation = true,
)
def HtmlPropVar[Tag <: HtmlElement, T](propName: String): ExternalVar.Aux[Tag, T] = ExternalVar[Tag, T](
  propName,
  _.asInstanceOf[js.Dynamic].selectDynamic(propName).asInstanceOf[T],
  (v, t) => v.asInstanceOf[js.Dynamic].updateDynamic(propName)(t.asInstanceOf[js.Any]),
  eagerEvaluation = true,
)