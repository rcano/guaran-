package apricot.tools

import better.files.*
import guarana.nnn
import language.experimental.erasedDefinitions
import scala.annotation.{compileTimeOnly, experimental}
import scala.collection.immutable.SortedSet
import scala.quoted.*
import scala.util.chaining.*
import SpiProvider.*


trait SpiProvider[Service] {
  @compileTimeOnly("this method should only be called from the implementation of spi")
  inline def registerSpi: RegisteredSpi = ${SpiProvider.registerMacro}
  @experimental
  erased def spi: RegisteredSpi
}

object SpiProvider {
  sealed trait RegisteredSpi

  def registerMacro(using q: Quotes): Expr[RegisteredSpi] = {
    import q.reflect.*

    val spiProviderType = TypeRepr.of[SpiProvider[?]]

    def typeStaticName(s: Symbol): String = {
      val ownerName = s.maybeOwner match {
        case s if s == defn.RootPackage || s == defn.RootClass => ""
        case sym if sym.flags.is(Flags.StableRealizable) || sym.flags.is(Flags.Module) || sym.flags.is(Flags.Package) => typeStaticName(sym) + "."
        case other => report.errorAndAbort(s"$other is path dependent and cannot be instantiated. ${other.flags.show}")
      }
      ownerName + s.name.replace("$", "")
    }

    val Array(projectBase, _) = q.reflect.Position.ofMacroExpansion.sourceFile.path.split("src/main|test/scala/", 2).nnn

    // spliceOwner is a macro in the spi impl, we move to owner (spi) and to owner again (class defining spi)
    val providerSym = q.reflect.Symbol.spliceOwner.owner.owner
    providerSym.tree match {
      case cls: ClassDef =>
        cls.parents.map(_.asInstanceOf[TypeTree].tpe <:< spiProviderType) foreach println
        val providesType = cls.parents.collectFirst { case t: TypeTree if t.tpe <:< spiProviderType => t }
          .getOrElse(report.errorAndAbort("Unexpected call to registerMacro. Not being called from a class that extends SpiProvider", Position.ofMacroExpansion))
        
        val spiType = providesType.tpe.baseType(spiProviderType.typeSymbol).asInstanceOf[AppliedType].args.head

        val servicesFile = File(projectBase) / "src/main/resources/META-INF/services" / typeStaticName(spiType.typeSymbol)
        servicesFile.createIfNotExists(asDirectory = false, createParents = true)

        val newContent = (servicesFile.lines.to(SortedSet) + typeStaticName(providerSym)).mkString("\n")
        servicesFile.writeText(newContent)

      case _ => report.errorAndAbort("SPI providers must be top level classes, otherwise they cannot be instantiated.", Position.ofMacroExpansion)
    }
    '{???}
  }
}
