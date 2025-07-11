package guarana.impl

import scala.quoted.Quotes
import scala.quoted.Expr

object Debug {
  inline def debugEnabled = true
  // inline def debugEnabled = ${ debugEnabledMacro }
  private def debugEnabledMacro(using Quotes): Expr[Boolean] = {
    System.getenv("debugEnabled") match {
      case null => '{ false }
      case _ => '{ true }
    }
  }

  inline def elidable[R](f: => R): Unit = if (debugEnabled) f
}
