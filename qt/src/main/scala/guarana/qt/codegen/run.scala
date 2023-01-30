package guarana.qt.codegen

import better.files.*
import guarana.{nnn, toOption, unn}
import guarana.codegen.*
import io.qt.core.*
import io.qt.gui.*
import io.qt.widgets.*
import scala.jdk.CollectionConverters.*
import scala.util.Try
import scala.util.chaining._

object run extends Panels, ItemViews, Dialogs {

  lazy val window = genNodeDescsrFromMetaObject(QWindow.staticMetaObject.unn, "Window", None)
    .editProperty("contentOrientation") { case ep: ExternalProp => ep.copy(visibility = Some("private")) }
    .editProperty("visibility") { case ep: ExternalProp => ep.copy(tpe = "io.qt.gui.QWindow.Visibility", getter = "_.visibility().unn") }
    .addOps(Seq("def contentOrientation = ContentOrientation.asObsValIn(v)"))

  lazy val widgetNode = genNodeDescsrFromMetaObject(QWidget.staticMetaObject.unn, "Widget", None)
    .addUninitParam(Seq(Parameter("parent", "Widget | Null = null", "---"), Parameter("windowFlags", "Qt.WindowFlags | Null = null", "---")))
    .copy(creator = Seq("new io.qt.widgets.QWidget(parent.?(_.unwrap), windowFlags)"), lowerBounds = Seq("io.qt.widgets.QWidget"))
    .addOps(Seq("def windowHandle: Option[Window] = v.windowHandle.?(Window.wrap(_)).toOption"))

  lazy val buttonBaseNode = genNodeDescsrFromMetaObject(QAbstractButton.staticMetaObject.unn, "ButtonBase", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addInitExtra(
      Seq(
        "v.pressed.unn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(false)))))",
        "v.released.unn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(true)))))",
        "v.toggled.unn.connect(slot((t: java.lang.Boolean) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.checked(v), Some(t)))))"
      )
    )
    .addEmitter(
      EmitterDescr("clicked", "Unit", Seq("v.clicked.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.clicked(v), ()))))"))
    )
  lazy val buttonNode = genNodeDescsrFromMetaObject(QPushButton.staticMetaObject.unn, "Button", Some(buttonBaseNode))
  lazy val checkBoxNode = genNodeDescsrFromMetaObject(QCheckBox.staticMetaObject.unn, "CheckBox", Some(buttonBaseNode))
  lazy val radioButtonNode = genNodeDescsrFromMetaObject(QRadioButton.staticMetaObject.unn, "RadioButton", Some(buttonBaseNode))

  lazy val framedNode = genNodeDescsrFromMetaObject(QFrame.staticMetaObject.unn, "FramedWidget", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .editProperty("frameShadow") { case ep: ExternalProp => ep.copy(tpe = "io.qt.widgets.QFrame.Shadow", getter = "_.frameShadow().unn") }
    .editProperty("frameShape") { case ep: ExternalProp => ep.copy(tpe = "io.qt.widgets.QFrame.Shape", getter = "_.frameShape().unn") }
  lazy val labelNode = genNodeDescsrFromMetaObject(QLabel.staticMetaObject.unn, "Label", Some(widgetNode))
    .copy(upperBounds = Seq(framedNode))

  lazy val textFieldNode = genNodeDescsrFromMetaObject(QLineEdit.staticMetaObject.unn, "TextField", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addEmitter(
      EmitterDescr(
        "textEdited",
        "String",
        Seq("v.textEdited.unn.connect(slot((newText: String) => Toolkit.update(summon[Emitter.Context].emit(Ops.textEdited(v), newText))))")
      )
    )
    .addEmitter(
      EmitterDescr(
        "events",
        "Event",
        Seq(
          "v.editingFinished.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.EditFinished))))",
          "v.inputRejected.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.InputRejected))))",
          "v.returnPressed.unn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.ReturnPressed))))",
        )
      )
    )
    .addInitExtra(
      Seq(
        "v.textChanged.unn.connect(slot((newText: String) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.text(v), None))))",
        "v.selectionChanged.unn.connect(slot(Toolkit.update {",
        "  val vc = summon[VarContext]",
        "  vc.externalPropertyUpdated(Ops.hasSelectedText(v), None)",
        "  vc.externalPropertyUpdated(Ops.selectedText(v), None)",
        "}))",
        "v.cursorPositionChanged.unn.connect(slot((oldp: java.lang.Integer, newp: java.lang.Integer) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.cursorPosition(v), Some(oldp)))))",
      )
    )
    .addCompanionObjectExtras(
      Seq(
        "enum Event:",
        "  case EditFinished, InputRejected, ReturnPressed"
      )
    )

  lazy val scrollAreaBaseNode = genNodeDescsrFromMetaObject(QAbstractScrollArea.staticMetaObject.unn, "ScrollAreaBase", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))
    .addProperty(ExternalProp("viewport", "Widget", "c => c.viewport().unn", "(c, v) => c.setViewport(v.unwrap)"))

  lazy val scrollAreaNode = genNodeDescsrFromMetaObject(QScrollArea.staticMetaObject.unn, "ScrollArea", Some(scrollAreaBaseNode))
    .copy(upperBounds = Seq(scrollAreaBaseNode))
    .addProperty(ExternalProp("content", "Widget | Null", "_.widget()", "(c, v) => c.setWidget(v.?(_.unwrap))"))

  lazy val svgNode = genNodeDescsrFromMetaObject(io.qt.widgets.svg.QSvgWidget.staticMetaObject.unn, "SvgNode", Some(widgetNode))
    .copy(upperBounds = Seq(widgetNode))

  lazy val spinBoxBaseNode = genNodeDescsrFromMetaObject(QAbstractSpinBox.staticMetaObject.unn, "SpinBoxBase", Some(widgetNode))
  lazy val spinBoxNode = genNodeDescsrFromMetaObject(QSpinBox.staticMetaObject.unn, "SpinBox", Some(spinBoxBaseNode))
  lazy val doubleSpinBoxNode = genNodeDescsrFromMetaObject(QDoubleSpinBox.staticMetaObject.unn, "DoubleSpinBox", Some(spinBoxBaseNode))
  lazy val dateTimeSpinBoxNode = genNodeDescsrFromMetaObject(QDateTimeEdit.staticMetaObject.unn, "DateTimeSpinBox", Some(spinBoxBaseNode))

  lazy val sliderBaseNode = genNodeDescsrFromMetaObject(QAbstractSlider.staticMetaObject.unn, "SliderBase", Some(widgetNode))
  lazy val sliderNode = genNodeDescsrFromMetaObject(QSlider.staticMetaObject.unn, "Slider", Some(sliderBaseNode))
  lazy val dialNode = genNodeDescsrFromMetaObject(QDial.staticMetaObject.unn, "Dial", Some(sliderBaseNode))

  def main(args: Array[String]): Unit = {

    for
      node <- Seq[NodeDescr](
                // window,
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
                spinBoxBaseNode,
                spinBoxNode,
                doubleSpinBoxNode,
                dateTimeSpinBoxNode,
                sliderBaseNode,
                sliderNode,
                dialNode,
                svgNode,
              ) ++ panels ++ itemViews ++ dialogs
    do
      val f = File(s"src/main/scala/guarana/qt/${node.name}.scala")
      val src = genScalaSource(node)
      f.writeText(
        s"""
        |package guarana
        |package qt
        
        |import io.qt.core.Qt
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
    def addUninitParam(params: Seq[Parameter]): NodeDescr = nd.copy(uninitExtraParams = nd.uninitExtraParams ++ params)
    def addInitExtra(ops: Seq[String]): NodeDescr = nd.copy(initExtra = nd.initExtra ++ ops)
    def addCompanionObjectExtras(extras: Seq[String]): NodeDescr = nd.copy(companionObjectExtras = nd.companionObjectExtras ++ extras)
  }

  def genNodeDescsrFromMetaObject(mo: QMetaObject, targetName: String, parent: Option[NodeDescr]) = {
    given analysis: MetaObjectAnalysis = MetaObjectAnalysis(mo, parent)
    val actualClass = mo.`type`.unn
    val signalNames = analysis.signals.map(_.getName.unn)

    val initializedProp = VarProp(s"${targetName}Initialized", "Boolean", "false", Some("private"))

    NodeDescr(
      targetName,
      actualClass.getName.unn,
      Seq(s"new ${actualClass.getName.unn}()"),
      upperBounds = parent.toSeq,
      props = initializedProp +: genPropertiesFromMetaObject(mo),
      opsExtra = genMethodsFromMetaObject(mo),
      isAbstract = java.lang.reflect.Modifier.isAbstract(actualClass.getModifiers),
      initExtra = signalNames.view
        .filter(_.endsWith("Changed"))
        .map(_.stripSuffix("Changed").unn)
        .filter(n => analysis.localPropertiesForVars.exists(_.name == n))
        .map(signal => s"Toolkit.connectVar(${signal.capitalize}.forInstance[v.type], v.${signal}Changed.unn)")
        .toSeq :+
        s"Toolkit.update(${initializedProp.name}.forInstance[v.type] := true)",
      wrapExtra = Seq(s"if !Toolkit.stateReader(${initializedProp.name}.forInstance[v.type]) then init(res)")
    )
  }

  class MetaObjectAnalysis(mo: QMetaObject, parent: Option[NodeDescr]) {
    val actualClass = mo.`type`.unn
    val parents = Seq.unfold(parent)(_.map(p => p -> p.upperBounds.collectFirst { case n: NodeDescr => n }))
    val parentPropertieNamesSet = parents.flatMap(_.props.map(_.name)).toSet
    val localProperties = mo
      .properties()
      .unn
      .asScala
      .filterNot(p => parentPropertieNamesSet(p.name.unn))
      .toSeq
    val localPropertiesForVars = localProperties.filter(prop => prop.isReadable)
    private val getterMethods = actualClass.getMethods.nnn
      .filter(_.getParameterCount == 0)
      .view
      .map(m =>
        val n = m.getName.unn
        n -> n
      )
      .toMap
    val localPropertyGetters = localProperties
      .filter(_.isReadable)
      .map(prop =>
        val name = prop.name.unn
        (prop.name.unn, prop.metaType().unn) -> (
          GetterNameOverrides.getOrElse(
            mo.`type`().unn -> prop.name.unn, {
              if prop.metaType().unn.javaType() == classOf[Boolean] || prop.metaType().unn.javaType() == classOf[java.lang.Boolean] then
                getterMethods.getOrElse(s"is${name.capitalize}", getterMethods.getOrElse(s"has${name.capitalize}", s"$name"))
              else getterMethods.getOrElse(s"get${name.capitalize}", s"$name")
            }
          )
        )
      )
      .toMap
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
      val res =
        if cl.getEnclosingClass != null then s"${cl.getName.unn.replace("$", ".")}"
        else cl.getTypeName.unn
      if !cl.isEnum then res + " | Null"
      else res
  }

  def typeName(tpe: io.qt.core.QMetaType): String = io.qt.core.QMetaType.Type.resolve(tpe.id()) match {
    case io.qt.core.QMetaType.Type.Void => "Unit"
    case io.qt.core.QMetaType.Type.VoidStar => classOf[io.qt.QNativePointer].getName().unn
    case io.qt.core.QMetaType.Type.Bool => "Boolean"
    case io.qt.core.QMetaType.Type.Char => "Byte"
    case io.qt.core.QMetaType.Type.Short | io.qt.core.QMetaType.Type.UChar => "Short"
    case io.qt.core.QMetaType.Type.Int | io.qt.core.QMetaType.Type.UShort => "Int"
    case io.qt.core.QMetaType.Type.UInt | io.qt.core.QMetaType.Type.Char32 | io.qt.core.QMetaType.Type.Long |
        io.qt.core.QMetaType.Type.LongLong | io.qt.core.QMetaType.Type.ULong | io.qt.core.QMetaType.Type.ULongLong =>
      "Long"
    case io.qt.core.QMetaType.Type.Float => "Float"
    case io.qt.core.QMetaType.Type.Double => "Double"
    case io.qt.core.QMetaType.Type.Char16 => "Char"
    // case io.qt.core.QMetaType.Type.QMap => classOf[io.qt.core.QMap[Any, Any]].getName().unn + "[Any, Any]"
    // case io.qt.core.QMetaType.Type.List => classOf[io.qt.core.QList[Any]].getName().unn + "[Any]"
    case io.qt.core.QMetaType.Type.QString => "String"
    case io.qt.core.QMetaType.Type.QStringList => classOf[io.qt.core.QStringList].getName().unn
    case io.qt.core.QMetaType.Type.QByteArray => classOf[io.qt.core.QByteArray].getName().unn
    case io.qt.core.QMetaType.Type.QBitArray => classOf[io.qt.core.QBitArray].getName().unn
    case io.qt.core.QMetaType.Type.QDate => classOf[io.qt.core.QDate].getName().unn
    case io.qt.core.QMetaType.Type.QTime => classOf[io.qt.core.QTime].getName().unn
    case io.qt.core.QMetaType.Type.QDateTime => classOf[io.qt.core.QDateTime].getName().unn
    case io.qt.core.QMetaType.Type.QUrl => classOf[io.qt.core.QUrl].getName().unn
    case io.qt.core.QMetaType.Type.QLocale => classOf[io.qt.core.QLocale].getName().unn
    case io.qt.core.QMetaType.Type.QRect => classOf[io.qt.core.QRect].getName().unn
    case io.qt.core.QMetaType.Type.QRectF => classOf[io.qt.core.QRectF].getName().unn
    case io.qt.core.QMetaType.Type.QSize => classOf[io.qt.core.QSize].getName().unn
    case io.qt.core.QMetaType.Type.QSizeF => classOf[io.qt.core.QSizeF].getName().unn
    case io.qt.core.QMetaType.Type.QLine => classOf[io.qt.core.QLine].getName().unn
    case io.qt.core.QMetaType.Type.QLineF => classOf[io.qt.core.QLineF].getName().unn
    case io.qt.core.QMetaType.Type.QPoint => classOf[io.qt.core.QPoint].getName().unn
    case io.qt.core.QMetaType.Type.QPointF => classOf[io.qt.core.QPointF].getName().unn
    // case io.qt.core.QMetaType.Type.RegExp => classOf[io.qt.core.QRegExp].getName().unn
    case io.qt.core.QMetaType.Type.QRegularExpression => classOf[io.qt.core.QRegularExpression].getName().unn
    case io.qt.core.QMetaType.Type.QVariantHash => classOf[io.qt.core.QHash[Any, Any]].getName().unn
    case io.qt.core.QMetaType.Type.QEasingCurve => classOf[io.qt.core.QEasingCurve].getName().unn
    case io.qt.core.QMetaType.Type.QUuid => classOf[io.qt.core.QUuid].getName().unn
    case io.qt.core.QMetaType.Type.QModelIndex => classOf[io.qt.core.QModelIndex].getName().unn
    case io.qt.core.QMetaType.Type.QPersistentModelIndex => classOf[io.qt.core.QPersistentModelIndex].getName().unn
    case io.qt.core.QMetaType.Type.LastCoreType => "LastCoreType?"
    case io.qt.core.QMetaType.Type.QFont => classOf[io.qt.gui.QFont].getName().unn
    case io.qt.core.QMetaType.Type.QPixmap => classOf[io.qt.gui.QPixmap].getName().unn
    case io.qt.core.QMetaType.Type.QBrush => classOf[io.qt.gui.QBrush].getName().unn
    case io.qt.core.QMetaType.Type.QColor => classOf[io.qt.gui.QColor].getName().unn
    case io.qt.core.QMetaType.Type.QPalette => classOf[io.qt.gui.QPalette].getName().unn
    case io.qt.core.QMetaType.Type.QImage => classOf[io.qt.gui.QImage].getName().unn
    case io.qt.core.QMetaType.Type.QPolygon => classOf[io.qt.gui.QPolygon].getName().unn
    case io.qt.core.QMetaType.Type.QRegion => classOf[io.qt.gui.QRegion].getName().unn
    case io.qt.core.QMetaType.Type.QBitmap => classOf[io.qt.gui.QBitmap].getName().unn
    case io.qt.core.QMetaType.Type.QCursor => classOf[io.qt.gui.QCursor].getName().unn
    case io.qt.core.QMetaType.Type.QKeySequence => classOf[io.qt.gui.QKeySequence].getName().unn
    case io.qt.core.QMetaType.Type.QPen => classOf[io.qt.gui.QPen].getName().unn
    case io.qt.core.QMetaType.Type.QTextLength => classOf[io.qt.gui.QTextLength].getName().unn
    case io.qt.core.QMetaType.Type.QTextFormat => classOf[io.qt.gui.QTextFormat].getName().unn
    // case io.qt.core.QMetaType.Type.Matrix => classOf[io.qt.gui.QMatrix2x2].getName().unn
    case io.qt.core.QMetaType.Type.QTransform => classOf[io.qt.gui.QTransform].getName().unn
    case io.qt.core.QMetaType.Type.QMatrix4x4 => classOf[io.qt.gui.QMatrix4x4].getName().unn
    case io.qt.core.QMetaType.Type.QVector2D => classOf[io.qt.gui.QVector2D].getName().unn
    case io.qt.core.QMetaType.Type.QVector3D => classOf[io.qt.gui.QVector3D].getName().unn
    case io.qt.core.QMetaType.Type.QVector4D => classOf[io.qt.gui.QVector4D].getName().unn
    case io.qt.core.QMetaType.Type.QQuaternion => classOf[io.qt.gui.QQuaternion].getName().unn
    case io.qt.core.QMetaType.Type.QPolygonF => classOf[io.qt.gui.QPolygonF].getName().unn
    case io.qt.core.QMetaType.Type.QIcon => classOf[io.qt.gui.QIcon].getName().unn
    case io.qt.core.QMetaType.Type.LastGuiType => "LastGuiType?"
    case io.qt.core.QMetaType.Type.QSizePolicy => classOf[io.qt.widgets.QSizePolicy].getName().unn
    // case io.qt.core.QMetaType.Type.UserType => "UserType?"
    case _ => throw new IllegalArgumentException(s"Unsupported type $tpe")
  }

  def genPropertiesFromMetaObject(mo: QMetaObject)(using analysis: MetaObjectAnalysis): Seq[ExternalProp] = {
    val actualClass = mo.`type`.unn
    analysis.localPropertiesForVars
      .map(prop =>
        val name = prop.name.unn
        val getterMethodName = analysis.localPropertyGetters(prop.name.unn -> prop.metaType().unn)
        var getter = GetterFunctionOverrides.getOrElse((actualClass, name), s"_.$getterMethodName()")
        val setter =
          if prop.isWritable then SetterFunctionOverrides.getOrElse((actualClass, name), s"_.set${name.capitalize}(_)")
          else ""
        val propTpe = actualClass.getMethods.nnn
          .find(_.getName == getterMethodName)
          .getOrElse(
            throw new IllegalStateException(s"Cound't find getter $getterMethodName for property $prop in class $actualClass")
          )
          .getReturnType
          .unn
        val tpeName = typeName(propTpe)
        // println(s"""|Property $name ~ $prop
        //             |getter = $getterMethodName ~ $getter
        //             |setter = $setter
        //             |propTpe = $propTpe
        //             |tpeName = $tpeName
        //             |=====================""".stripMargin)
        if !tpeName.endsWith("| Null") && !propTpe.isPrimitive then getter += ".unn"
        ExternalProp(name, tpeName, getter, setter, readOnly = !prop.isWritable)
      )
      .toSeq
  }
  def genMethodsFromMetaObject(mo: QMetaObject)(using analysis: MetaObjectAnalysis): Seq[String] = {
    val parentOps = analysis.parents.flatMap(_.opsExtra).toSet
    val actualClass = mo.`type`.unn
    val generalMethods = mo.methods.unn.asScala.toSeq
      .filter(m => m.methodType.unn == QMetaMethod.MethodType.Method || m.methodType.unn == QMetaMethod.MethodType.Slot)
      .flatMap(qmethod =>
        Try {
          val paramsTypes = qmethod.parameterClassTypes().unn.asScala.toIndexedSeq
          val method = actualClass.getMethod(qmethod.name.unn.toString(), paramsTypes*).unn
          val params = method.getParameters.nnn.map(p =>
            ParamNameOverride(actualClass, method.getName().unn, paramsTypes.length)(p.getName.unn) -> p.getParameterizedType.unn
          )
          val paramDecls = params.map(p => s"${p._1}: ${typeName(p._2)}")
          val paramNames = params.map(_._1)
          s"def ${qmethod.name}(${paramDecls.mkString(", ")}) = v.${qmethod.name}(${paramNames.mkString(", ")})"
        }.toEither match {
          case Left(_: NoSuchMethodException) => None
          case Left(ex) =>
            println("  err")
            ex.printStackTrace()
            None
          case Right(v) => Some(v)
        }
      )
      .filterNot(parentOps)

    val readOnlyPropertiesAsMethods = analysis.localProperties
      .filter(p => !p.isReadable)
      .map(prop =>
        if prop.isReadable then
          val getter = analysis.localPropertyGetters(prop.name.unn -> prop.metaType().unn)
          s"def $getter: ${typeName(prop.metaType().unn)} = v.$getter()"
        else s"def set${prop.name.unn.capitalize}(p; ${typeName(prop.metaType().unn)}): Unit = v.set${prop.name.unn.capitalize}(p)"
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
    (classOf[QHeaderView], "showSortIndicator") -> "isSortIndicatorShown",
    (classOf[QAbstractSpinBox], "showGroupSeparator") -> "isGroupSeparatorShown",
  )

  lazy val GetterFunctionOverrides: Map[(Class[?], String), String] = Map(
    // (classOf[QWidget], "windowModified") -> "(_: QWidget).windowModified()",
  )
  lazy val SetterFunctionOverrides: Map[(Class[?], String), String] = Map(
    (classOf[QWidget], "pos") -> "_.move(_)",
    (classOf[QWidget], "size") -> "_.resize(_)",
    (classOf[QWindow], "contentOrientation") -> "(_, _) => ()",
    (classOf[QAbstractItemView], "showDropIndicator") -> "_.setDropIndicatorShown(_)",
    (classOf[QListView], "isWrapping") -> "_.setWrapping(_)",
    (classOf[QHeaderView], "showSortIndicator") -> "_.setSortIndicatorShown(_)",
    (classOf[QAbstractSpinBox], "showGroupSeparator") -> "_.setGroupSeparatorShown(_)",
  )
}
