package guarana
package web

import org.scalajs.dom

import scalajs.js
import Binding.*
import HtmlPropsApi.*
import HtmlAttrsApi.*
import StylesApi.*
import DomEventsApi.*
import guarana.util.cfor

@main def apiTest: Unit = {
  // scribe.Logger.apply("guarana.impl.emitters")
  //   .clearHandlers()
  //   .clearModifiers()
  //   .withHandler(minimumLevel = Some(scribe.Level.Debug))
  //   .replace()

  // scribe.Logger.apply("guarana.impl.signals")
  //   .clearHandlers()
  //   .clearModifiers()
  //   .withHandler(minimumLevel = Some(scribe.Level.Debug))
  //   .replace()

  // scribe.Logger.apply("guarana.AbstractToolkit")
  //   .clearHandlers()
  //   .clearModifiers()
  //   .withHandler(minimumLevel = Some(scribe.Level.Debug))
  //   .replace()

  val customTag = TagCreator[HtmlElement]("custom-guarana-tag")

  Toolkit.update {
    val clickCount = Var.autoName(0)

    def newWatchmeDiv() = Toolkit.update {
      val c = Seq("blue", "magenta", "aqua", "brown", "green").apply(scala.util.Random.nextInt(5))
      val id = scala.util.Random.nextInt(100)
      val res = div(color := c)
      res.amend(child.text := dyn {
        println(s"$id: changing! div size: ${res.dimensions()}")
        s"my own size is ${res.dimensions()}"
      })
      res
    }
    val watchmeDiv = Var.autoName(newWatchmeDiv())

    
    val anInput = input()
    anInput.display
    val checkbox = input(tpe := "checkbox", checked := false)
    val myDiv = div(
      div(
        display := "flex",
        div(
          p("Hello world!")
        ),
        button(
          "Click here",
          onClick := EventIterator.take(10).foreach(_ => clickCount += 1)
        ),
        p(
          marginLeft := dyn { clickCount().em },
          child.text := dyn { s"pressed ${clickCount()} times" }
        ),
        "write something here:",
        anInput,
        child.text := dyn { s"You wrote: ${anInput.value()}" },
        br(),
        checkbox,
        child.text := dyn { if (checkbox.checked()) "it is checked" else "try again" },
        br(),
        button("change the div", onClick := EventIterator.foreach(evt => 
          dom.console.info(s"got event", evt)
          watchmeDiv := newWatchmeDiv())
        ),
        child := dyn { watchmeDiv() },
        br(),
        br(),
      )
    )

    myDiv.amend(
      child.text := dyn { s"top level size is ${myDiv.dimensions()}" }
    )

    val app = dom.document.getElementById("app")
    app.append(myDiv.unwrap)
  }
}

