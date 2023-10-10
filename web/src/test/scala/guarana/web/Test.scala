package guarana
package web


object Test {
  import StylesApi.*

  Toolkit.update { 
    // modifiers style
    val s = span(
      display := "block",
      width := "fit-content",
      height := 10.em,
      fontSize := 5.em,
    )
    // direct properties style
    s.fontSize := 45.em
    s.amend(
      span("hey!")
    )

  }
}
