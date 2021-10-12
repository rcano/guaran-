package guarana.qt.codegen

import better.files.*
import guarana.{toOption, nnn}
import guarana.codegen.*
import io.qt.core.*
import io.qt.gui.*
import io.qt.widgets.*
import scala.jdk.CollectionConverters.*
import scala.util.Try
import scala.util.chaining._

object run extends Panels {

  lazy val window = genNodeDescsrFromMetaObject(QWindow.staticMetaObject.nn, "Window", None)
    .editProperty("contentOrientation") { case ep: ExternalProp => ep.copy(visibility = Some("private")) }
    .editProperty("visibility") { case ep: ExternalProp => ep.copy(tpe = "io.qt.gui.QWindow.Visibility", getter = "_.visibility().nn") }
    .addOps(Seq("def contentOrientation = ContentOrientation.asObsValIn(v)"))

  lazy val widgetNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.nn, "Widget", None)
    .copy(lowerBounds = Seq("io.qt.widgets.QWidget"))
    .addOps(Seq("def windowHandle: Option[Window] = v.windowHandle.?(Window.wrap(_)).toOption"))

  lazy val buttonBaseNode = genNodeDescsrFromMetaObject(QAbstractButton.staticMetaObject.nn, "ButtonBase", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addInitExtra(Seq(
      "v.pressed.nn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(false)))))",
      "v.released.nn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(true)))))",
      "v.toggled.nn.connect(slot((t: java.lang.Boolean) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.checked(v), Some(t)))))"
    ))
    .addEmitter(EmitterDescr("clicked", "Unit", Seq("v.clicked.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.clicked(v), ()))))")))
  lazy val buttonNode = genNodeDescsrFromMetaObject(QPushButton.staticMetaObject.nn, "Button", Some(buttonBaseNode))
  lazy val checkBoxNode = genNodeDescsrFromMetaObject(QCheckBox.staticMetaObject.nn, "CheckBox", Some(buttonBaseNode))
  lazy val radioButtonNode = genNodeDescsrFromMetaObject(QRadioButton.staticMetaObject.nn, "RadioButton", Some(buttonBaseNode))

  lazy val framedNode = genNodeDescsrFromMetaObject(QFrame.staticMetaObject.nn, "FramedWidget", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .editProperty("frameShadow") { case ep: ExternalProp => ep.copy(tpe = "io.qt.widgets.QFrame.Shadow", getter = "_.frameShadow().nn") }
    .editProperty("frameShape") { case ep: ExternalProp => ep.copy(tpe = "io.qt.widgets.QFrame.Shape", getter = "_.frameShape().nn") }
  lazy val labelNode = genNodeDescsrFromMetaObject(QLabel.staticMetaObject.nn, "Label", Some(widgetNode))
    .copy(upperBounds = Seq(framedNode))
  
  lazy val textFieldNode = genNodeDescsrFromMetaObject(QLineEdit.staticMetaObject.nn, "TextField", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addEmitter(EmitterDescr("textEdited", "String", Seq("v.textEdited.nn.connect(slot((newText: String) => Toolkit.update(summon[Emitter.Context].emit(Ops.textEdited(v), newText))))")))
    .addEmitter(EmitterDescr("events", "Event", Seq(
      "v.editingFinished.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.EditFinished))))",
      "v.inputRejected.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.InputRejected))))",
      "v.returnPressed.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.ReturnPressed))))",
    )))
    .addInitExtra(Seq(
      "v.textChanged.nn.connect(slot((newText: String) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.text(v), None))))",
      "v.selectionChanged.nn.connect(slot(Toolkit.update {",
      "  val vc = summon[VarContext]",
      "  vc.externalPropertyUpdated(Ops.hasSelectedText(v), None)",
      "  vc.externalPropertyUpdated(Ops.selectedText(v), None)",
      "}))",
      "v.cursorPositionChanged.nn.connect(slot((oldp: java.lang.Integer, newp: java.lang.Integer) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.cursorPosition(v), Some(oldp)))))",
    ))
    .addCompanionObjectExtras(Seq(
      "enum Event:",
      "  case EditFinished, InputRejected, ReturnPressed"
    ))

  lazy val scrollAreaBaseNode = genNodeDescsrFromMetaObject(QAbstractScrollArea.staticMetaObject.nn, "ScrollAreaBase", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addProperty(ExternalProp("viewport", "Widget", "c => c.viewport().nn", "(c, v) => c.setViewport(v.unwrap)"))

  lazy val scrollAreaNode = genNodeDescsrFromMetaObject(QScrollArea.staticMetaObject.nn, "ScrollArea", Some(widgetNode))
    .copy(upperBounds = Seq(scrollAreaBaseNode))
    .addProperty(ExternalProp("content", "Widget | Null", "_.widget()", "(c, v) => c.setWidget(v.?(_.unwrap))"))

  lazy val svgNode = genNodeDescsrFromMetaObject(io.qt.svg.QSvgWidget.staticMetaObject.nn, "SvgNode", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))

  def main(args: Array[String]): Unit = {
      
    for node <- Seq[NodeDescr](
        window,
        widgetNode,
        buttonBaseNode,
        buttonNode,
        checkBoxNode,
        radioButtonNode,
        framedNode,
        labelNode,
        textFieldNode,
        scrollAreaBaseNode,
        scrollAreaNode,
        svgNode,
    ) ++ panels do
      val f = File(s"src/main/scala/guarana/qt/${node.name}.scala")
      val src = genScalaSource(node)
      f.writeText(
        s"""
        |package guarana
        |package qt
        
        |import io.qt.gui.*
        |import io.qt.widgets.*
        |import util.*

        |$src
        """.stripMargin
      )
      println(s"$f written")

  }

  extension (nd: NodeDescr) {
    def editProperty(propertyName: String)(f: PartialFunction[Property, Property]): NodeDescr = 
      val (discarded, newProps) = nd.props.partition(_.name == propertyName)
      require(discarded.size == 1, s"could not find property $propertyName")
      nd.copy(props = newProps :+ f.lift(discarded.head).get)
    def addProperty(prop: Property): NodeDescr = nd.copy(props = nd.props :+ prop)
    def addEmitter(emitter: EmitterDescr): NodeDescr = nd.copy(emitters = nd.emitters :+ emitter)
    def addOps(ops: Seq[String]): NodeDescr = nd.copy(opsExtra = nd.opsExtra ++ ops)
    def addUninitExtra(ops: Seq[String]): NodeDescr = nd.copy(uninitExtra = nd.uninitExtra ++ ops)
    def addInitExtra(ops: Seq[String]): NodeDescr = nd.copy(initExtra = nd.initExtra ++ ops)
    def addCompanionObjectExtras(extras: Seq[String]): NodeDescr = nd.copy(companionObjectExtras = nd.companionObjectExtras ++ extras)
  }

  def genNodeDescsrFromMetaObject(mo: QMetaObject, targetName: String, parent: Option[NodeDescr]) = {
    given analysis: MetaObjectAnalysis = MetaObjectAnalysis(mo, parent)
    val actualClass = mo.`type`.nn
    val signalNames = analysis.signals.map(_.getName.nn)

    val initializedProp = VarProp(s"${targetName}Initialized", "Boolean", "false", Some("private"))
    
    NodeDescr(
      targetName,
      actualClass.getName.nn,
      Seq(s"new ${actualClass.getName.nn}()"),
      upperBounds = parent.toSeq,
      props = initializedProp +: genPropertiesFromMetaObject(mo),
      opsExtra = genMethodsFromMetaObject(mo),
      isAbstract = java.lang.reflect.Modifier.isAbstract(actualClass.getModifiers),
      initExtra = signalNames.view
        .filter(_.endsWith("Changed"))
        .map(_.stripSuffix("Changed").nn)
        .filter(n => analysis.localPropertiesForVars.exists(_.name == n))
        .map(signal => s"Toolkit.connectVar(${signal.capitalize}.forInstance[v.type], v.${signal}Changed.nn)")
        .toSeq :+
        s"Toolkit.update(${initializedProp.name}.forInstance[v.type] := true)",
      wrapExtra = Seq(s"if !Toolkit.stateReader(${initializedProp.name}.forInstance[v.type]) then init(res)")
    )
  }

  class MetaObjectAnalysis(mo: QMetaObject, parent: Option[NodeDescr]) {
    val actualClass = mo.`type`.nn
    val parents = Seq.unfold(parent)(_.map(p => p -> p.upperBounds.collectFirst { case n: NodeDescr => n }))
    val parentPropertieNamesSet = parents.flatMap(_.props.map(_.name)).toSet
    val localProperties = mo.properties().nn.asScala
      .filterNot(p => parentPropertieNamesSet(p.name.nn))
      .toSeq
    val localPropertiesForVars = localProperties.filter(prop => prop.isReadable)
    private val getterMethods = actualClass.getMethods.nnn.filter(_.getParameterCount == 0).view.map(m =>
      val n = m.getName.nn
      n -> n
    ).toMap
    val localPropertyGetters = localProperties.filter(_.isReadable).map(prop =>
      val name = prop.name.nn
      prop -> (
        if prop.`type` == classOf[Boolean] || prop.`type` == classOf[java.lang.Boolean] then 
          getterMethods.getOrElse(s"is${name.capitalize}", getterMethods.getOrElse(s"has${name.capitalize}", s"$name"))
        else
          getterMethods.getOrElse(s"get${name.capitalize}", s"$name")
      )
    ).toMap
    val signals: Seq[java.lang.reflect.Field] = actualClass.getFields.nnn.toIndexedSeq
      .filter(f => java.lang.reflect.Modifier.isPublic(f.getModifiers) && classOf[QObject#Signal1[?]].isAssignableFrom(f.getType))

  }

  def typeName(tpe: java.lang.reflect.Type): String = tpe match {
    case java.lang.Byte.TYPE => "Byte"
    case java.lang.Short.TYPE => "Short"
    case java.lang.Integer.TYPE => "Int"
    case java.lang.Long.TYPE => "Long"
    case java.lang.Float.TYPE => "Float"
    case java.lang.Double.TYPE => "Double"
    case java.lang.Character.TYPE => "Char"
    case java.lang.Boolean.TYPE => "Boolean"
    case cl: Class[?] =>
      val res = if cl.getEnclosingClass != null then s"${cl.getName.nn.replace("$", ".")}"
        else cl.getTypeName.nn
      if !cl.isEnum then res + " | Null"
      else res
  }

  def genPropertiesFromMetaObject(mo: QMetaObject)(using analysis: MetaObjectAnalysis): Seq[ExternalProp] = {
    val actualClass = mo.`type`.nn
    analysis.localPropertiesForVars
      .map(prop =>
        val name = prop.name.nn
        val getterMethodName = analysis.localPropertyGetters(prop)
        var getter = GetterNameOverrides.getOrElse((actualClass, name), s"_.$getterMethodName()")
        val setter = if prop.isWritable then SetterNameOverrides.getOrElse((actualClass, name), s"_.set${name.capitalize}(_)")
          else ""
        val propTpe = actualClass.getMethods.nnn.find(_.getName == getterMethodName).get.getReturnType.nn
        val tpeName = typeName(propTpe)
        if !tpeName.endsWith("| Null") && !propTpe.isPrimitive then getter += ".nn"
        ExternalProp(name, tpeName, getter, setter, readOnly = !prop.isWritable)
      )
      .toSeq
  }
  def genMethodsFromMetaObject(mo: QMetaObject)(using analysis: MetaObjectAnalysis): Seq[String] = {
    val parentOps = analysis.parents.flatMap(_.opsExtra).toSet
    val actualClass = mo.`type`.nn
    val generalMethods = mo.methods.nn.asScala
      .toSeq
      .filter(m => m.methodType.nn == QMetaMethod.MethodType.Method || m.methodType.nn == QMetaMethod.MethodType.Slot)
      .flatMap(qmethod =>
        Try {
          val paramsTypes = qmethod.parameterTypes.nn.asScala.toArray
          val method = actualClass.getMethod(qmethod.name, paramsTypes:_*).nn
          val params = method.getParameters.nnn.map(p => ParamNameOverride(actualClass, qmethod.name.nn, paramsTypes.length)(p.getName.nn) -> p.getParameterizedType.nn)
          val paramDecls = params.map(p => s"${p._1}: ${typeName(p._2)}")
          val paramNames = params.map(_._1)
          s"def ${qmethod.name}(${paramDecls.mkString(", ")}) = v.${qmethod.name}(${paramNames.mkString(", ")})"
        }.toOption
      )
      .filterNot(parentOps)

    val readOnlyPropertiesAsMethods = analysis.localProperties
      .filter(p => !p.isReadable)
      .map(prop =>
        if prop.isReadable then 
          val getter = analysis.localPropertyGetters(prop)
          s"def $getter: ${typeName(prop.`type`.nn)} = v.$getter()"
        else s"def set${prop.name.nn.capitalize}(p; ${typeName(prop.`type`.nn)}): Unit = v.set${prop.name.nn.capitalize}(p)"
      )
      .filterNot(parentOps)

    generalMethods ++ readOnlyPropertiesAsMethods
  }

  /** Parameter renaming for methods, this is because qtjambi compiles without -paremeters */
  lazy val ParamNameOverride: Map[(Class[?], String, Int), Map[String, String]] = Map(
    (classOf[QWidget], "grab", 1) -> Map("arg0" -> "rectangle").withDefault(identity),
    (classOf[QWidget], "setDisabled", 1) -> Map("arg0" -> "disabled").withDefault(identity),
    (classOf[QWidget], "setHidden", 1) -> Map("arg0" -> "hidden").withDefault(identity),

    (classOf[QWindow], "alert", 1) -> Map("arg0" -> "msec").withDefault(identity),
    (classOf[QWindow], "startSystemResize", 1) -> Map("arg0" -> "edges").withDefault(identity),
    (classOf[QWindow], "setGeometry", 1) -> Map("arg0" -> "rect").withDefault(identity),
    (classOf[QWindow], "setGeometry", 4) -> Map("arg0" -> "posx", "arg1" -> "posy", "arg2" -> "w", "arg3" -> "h").withDefault(identity),
  ).withDefaultValue(Map.empty.withDefault(identity))

  lazy val GetterNameOverrides: Map[(Class[?], String), String] = Map(
    // (classOf[QWidget], "windowModified") -> "(_: QWidget).windowModified()",
  )
  lazy val SetterNameOverrides: Map[(Class[?], String), String] = Map(
    (classOf[QWidget], "pos") -> "_.move(_)",
    (classOf[QWidget], "size") -> "_.resize(_)",
    (classOf[QWindow], "contentOrientation") -> "(_, _) => ()",
  )
}
