package guarana
package swing

import scala.annotation.targetName

class Modifiers
object Modifiers
/*     extends NodeModifiers,
      ComponentModifiers,
      WindowBaseModifiers,
      FrameModifiers,
      DialogModifiers,
      PopupMenuModifiers,
      PaneModifiers,
      AbsolutePositioningPaneModifiers,
      FlowPaneModifiers,
      BorderPaneModifiers,
      CanvasPaneModifiers,
      GridPaneModifiers,
      HboxModifiers,
      VboxModifiers,
      TextComponentModifiers,
      TextAreaModifiers,
      TextFieldModifiers,
      PasswordFieldModifiers,
      LabelModifiers,
      ButtonBaseModifiers,
      ButtonModifiers,
      ToggleButtonModifiers,
      CheckBoxModifiers,
      RadioButtonModifiers,
      MenuBarModifiers,
      MenuModifiers,
      MenuItemModifiers,
      CheckBoxMenuItemModifiers,
      RadioButtonMenuItemModifiers,
      SliderModifiers,
      ProgressBarModifiers,
      ListViewModifiers,
      TableViewModifiers,
      ScrollPaneModifiers,
      ScrollBarModifiers,
      SplitPaneModifiers,
      ComboBoxModifiers,
      SeparatorModifiers,
      SpinnerModifiers,
      TabbedPaneModifiers,
      TreeViewModifiers */ {
  /////////////////////////////////////////////////////////////////
  // Node properties
  /////////////////////////////////////////////////////////////////
  val background = ModifierVar(Node.Background)
  val bounds = ModifierVar(Node.Bounds)
  val componentOrientation = ModifierVar(Node.ComponentOrientation)
  val cursor = ModifierVar(Node.Cursor)
  val enabled = ModifierVar(Node.Enabled)
  val focusable = ModifierVar(Node.Focusable)
  val font = ModifierVar(Node.Font)
  val foreground = ModifierVar(Node.Foreground)
  val maxSize = ModifierVar(Node.MaxSize)
  val minSize = ModifierVar(Node.MinSize)
  val name = ModifierVar(Node.Name)
  val prefSize = ModifierVar(Node.PrefSize)
  val visible = ModifierVar(Node.Visible)
  val focusEvents = ModifierEmitter(Node.FocusEvents)
  val keyEvents = ModifierEmitter(Node.KeyEvents)
  val mouseEvents = ModifierEmitter(Node.MouseEvents)
  /////////////////////////////////////////////////////////////////
  // Component properties
  /////////////////////////////////////////////////////////////////
  val actionMap = ModifierVar(Component.ActionMap)
  val alignmentX = ModifierVar(Component.AlignmentX)
  val alignmentY = ModifierVar(Component.AlignmentY)
  val autoscrolls = ModifierVar(Component.Autoscrolls)
  val border = ModifierVar(Component.Border)
  val componentPopupMenu = ModifierVar(Component.ComponentPopupMenu)
  val debugGraphicsOptions = ModifierVar(Component.DebugGraphicsOptions)
  val doubleBuffered = ModifierVar(Component.DoubleBuffered)
  val inheritsPopupMenu = ModifierVar(Component.InheritsPopupMenu)
  val inputVerifier = ModifierVar(Component.InputVerifier)
  val opaque = ModifierVar(Component.Opaque)
  val requestFocusEnabled = ModifierVar(Component.RequestFocusEnabled)
  val toolTipText = ModifierVar(Component.ToolTipText)
  val verifyInputWhenFocusTarget = ModifierVar(Component.VerifyInputWhenFocusTarget)
  /////////////////////////////////////////////////////////////////
  // WindowBase properties
  /////////////////////////////////////////////////////////////////
  val alwaysOnTop = ModifierVar(WindowBase.AlwaysOnTop)
  val autoRequestFocus = ModifierVar(WindowBase.AutoRequestFocus)
  val focusCycleRoot = ModifierVar(WindowBase.FocusCycleRoot)
  val focusableWindowState = ModifierVar(WindowBase.FocusableWindowState)
  val iconImages = ModifierVar(WindowBase.IconImages)
  val locationByPlatform = ModifierVar(WindowBase.LocationByPlatform)
  val modalExclusionType = ModifierVar(WindowBase.ModalExclusionType)
  val opacity = ModifierVar(WindowBase.Opacity)
  val root = ModifierVar(WindowBase.Root)
  val shape = ModifierVar(WindowBase.Shape)
  val tpe = ModifierVar(WindowBase.Tpe)
  /////////////////////////////////////////////////////////////////
  // Window properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Frame properties
  /////////////////////////////////////////////////////////////////
  val extendedState = ModifierVar(Frame.ExtendedState)
  val maximizedBounds = ModifierVar(Frame.MaximizedBounds)
  /////////////////////////////////////////////////////////////////
  // Dialog properties
  /////////////////////////////////////////////////////////////////
  val modal = ModifierVar(Dialog.Modal)
  val modalityType = ModifierVar(Dialog.ModalityType)
  /////////////////////////////////////////////////////////////////
  // PopupMenu properties
  /////////////////////////////////////////////////////////////////
  val invoker = ModifierVar(PopupMenu.Invoker)
  /////////////////////////////////////////////////////////////////
  // Pane properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // ChildrenSeqPane properties
  /////////////////////////////////////////////////////////////////
  val nodes = ModifierVar(ChildrenSeqPane.Nodes)

  /////////////////////////////////////////////////////////////////
  // AbsolutePositioningPane properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // FlowPane properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // BorderPane properties
  /////////////////////////////////////////////////////////////////
  val bottom = ModifierVar(BorderPane.Bottom)
  val center = ModifierVar(BorderPane.Center)
  val left = ModifierVar(BorderPane.Left)
  val right = ModifierVar(BorderPane.Right)
  val top = ModifierVar(BorderPane.Top)
  /////////////////////////////////////////////////////////////////
  // CanvasPane properties
  /////////////////////////////////////////////////////////////////
  val paintFunction = ModifierVar(CanvasPane.PaintFunction)
  /////////////////////////////////////////////////////////////////
  // GridPane properties
  /////////////////////////////////////////////////////////////////
  val autoCreateContainerGaps = ModifierVar(GridPane.AutoCreateContainerGaps)
  /////////////////////////////////////////////////////////////////
  // Hbox properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Vbox properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // TextComponent properties
  /////////////////////////////////////////////////////////////////
  val caret = ModifierVar(TextComponent.Caret)
  val caretColor = ModifierVar(TextComponent.CaretColor)
  val disabledTextColor = ModifierVar(TextComponent.DisabledTextColor)
  val document = ModifierVar(TextComponent.Document)
  val focusAccelerator = ModifierVar(TextComponent.FocusAccelerator)
  val highlighter = ModifierVar(TextComponent.Highlighter)
  val keymap = ModifierVar(TextComponent.Keymap)
  val navigationFilter = ModifierVar(TextComponent.NavigationFilter)
  val selectedTextColor = ModifierVar(TextComponent.SelectedTextColor)
  val selectionColor = ModifierVar(TextComponent.SelectionColor)
  /////////////////////////////////////////////////////////////////
  // TextArea properties
  /////////////////////////////////////////////////////////////////
  val lineWrap = ModifierVar(TextArea.LineWrap)
  val tabSize = ModifierVar(TextArea.TabSize)
  val wrapStyleWord = ModifierVar(TextArea.WrapStyleWord)
  /////////////////////////////////////////////////////////////////
  // TextField properties
  /////////////////////////////////////////////////////////////////
  val scrollOffset = ModifierVar(TextField.ScrollOffset)
  /////////////////////////////////////////////////////////////////
  // PasswordField properties
  /////////////////////////////////////////////////////////////////
  val echoChar = ModifierVar(PasswordField.EchoChar)
  /////////////////////////////////////////////////////////////////
  // Label properties
  /////////////////////////////////////////////////////////////////
  val displayedMnemonic = ModifierVar(Label.DisplayedMnemonic)
  val labelFor = ModifierVar(Label.LabelFor)
  /////////////////////////////////////////////////////////////////
  // ButtonBase properties
  /////////////////////////////////////////////////////////////////
  val armed = ModifierVar(ButtonBase.Armed)
  val contentAreaFilled = ModifierVar(ButtonBase.ContentAreaFilled)
  val disabledSelectedIcon = ModifierVar(ButtonBase.DisabledSelectedIcon)
  val focusPainted = ModifierVar(ButtonBase.FocusPainted)
  val hideActionText = ModifierVar(ButtonBase.HideActionText)
  val mnemonic = ModifierVar(ButtonBase.Mnemonic)
  val modelEnabled = ModifierVar(ButtonBase.ModelEnabled)
  val multiClickThreshhold = ModifierVar(ButtonBase.MultiClickThreshhold)
  val pressed = ModifierVar(ButtonBase.Pressed)
  val pressedIcon = ModifierVar(ButtonBase.PressedIcon)
  val rollover = ModifierVar(ButtonBase.Rollover)
  val rolloverEnabled = ModifierVar(ButtonBase.RolloverEnabled)
  val rolloverIcon = ModifierVar(ButtonBase.RolloverIcon)
  val rolloverSelectedIcon = ModifierVar(ButtonBase.RolloverSelectedIcon)
  val selected = ModifierVar(ButtonBase.Selected)
  val selectedIcon = ModifierVar(ButtonBase.SelectedIcon)
  /////////////////////////////////////////////////////////////////
  // Button properties
  /////////////////////////////////////////////////////////////////
  val defaultCapable = ModifierVar(Button.DefaultCapable)
  /////////////////////////////////////////////////////////////////
  // ToggleButton properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // CheckBox properties
  /////////////////////////////////////////////////////////////////
  val borderPaintedFlat = ModifierVar(CheckBox.BorderPaintedFlat)
  /////////////////////////////////////////////////////////////////
  // RadioButton properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // MenuBar properties
  /////////////////////////////////////////////////////////////////
  val helpMenu = ModifierVar(MenuBar.HelpMenu)
  val menus = ModifierVar(MenuBar.Menus)
  /////////////////////////////////////////////////////////////////
  // Menu properties
  /////////////////////////////////////////////////////////////////
  val delay = ModifierVar(Menu.Delay)
  val popupMenuVisible = ModifierVar(Menu.PopupMenuVisible)
  /////////////////////////////////////////////////////////////////
  // MenuItem properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // CheckBoxMenuItem properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // RadioButtonMenuItem properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Slider properties
  /////////////////////////////////////////////////////////////////
  val extent = ModifierVar(Slider.Extent)
  val inverted = ModifierVar(Slider.Inverted)
  val labelTable = ModifierVar(Slider.LabelTable)
  val majorTickSpacing = ModifierVar(Slider.MajorTickSpacing)
  val minorTickSpacing = ModifierVar(Slider.MinorTickSpacing)
  val paintLabels = ModifierVar(Slider.PaintLabels)
  val paintTicks = ModifierVar(Slider.PaintTicks)
  val paintTrack = ModifierVar(Slider.PaintTrack)
  val snapToTicks = ModifierVar(Slider.SnapToTicks)
  /////////////////////////////////////////////////////////////////
  // ProgressBar properties
  /////////////////////////////////////////////////////////////////
  val indeterminate = ModifierVar(ProgressBar.Indeterminate)
  val string = ModifierVar(ProgressBar.String)
  val stringPainted = ModifierVar(ProgressBar.StringPainted)
  /////////////////////////////////////////////////////////////////
  // ListView properties
  /////////////////////////////////////////////////////////////////
  val fixedCellHeight = ModifierVar(ListView.FixedCellHeight)
  val fixedCellWidth = ModifierVar(ListView.FixedCellWidth)
  val layoutOrientation = ModifierVar(ListView.LayoutOrientation)
  val prototypeCellValue = ModifierVar(ListView.PrototypeCellValue)
  val selectedIndices = ModifierVar(ListView.SelectedIndices)
  val selectionMode = ModifierVar(ListView.SelectionMode)
  /////////////////////////////////////////////////////////////////
  // TableView properties
  /////////////////////////////////////////////////////////////////
  val autoCreateColumnsFromModel = ModifierVar(TableView.AutoCreateColumnsFromModel)
  val autoCreateRowSorter = ModifierVar(TableView.AutoCreateRowSorter)
  val autoResizeMode = ModifierVar(TableView.AutoResizeMode)
  val cellSelectionEnabled = ModifierVar(TableView.CellSelectionEnabled)
  val columnModel = ModifierVar(TableView.ColumnModel)
  val columnSelectionAllowed = ModifierVar(TableView.ColumnSelectionAllowed)
  val editingColumn = ModifierVar(TableView.EditingColumn)
  val editingRow = ModifierVar(TableView.EditingRow)
  val fillsViewportHeight = ModifierVar(TableView.FillsViewportHeight)
  val gridColor = ModifierVar(TableView.GridColor)
  val intercellSpacing = ModifierVar(TableView.IntercellSpacing)
  val preferredScrollableViewportSize = ModifierVar(TableView.PreferredScrollableViewportSize)
  val rowMargin = ModifierVar(TableView.RowMargin)
  val rowSelectionAllowed = ModifierVar(TableView.RowSelectionAllowed)
  val rowSorter = ModifierVar(TableView.RowSorter)
  val showHorizontalLines = ModifierVar(TableView.ShowHorizontalLines)
  val showVerticalLines = ModifierVar(TableView.ShowVerticalLines)
  val surrendersFocusOnKeystroke = ModifierVar(TableView.SurrendersFocusOnKeystroke)
  val tableHeader = ModifierVar(TableView.TableHeader)
  val updateSelectionOnSort = ModifierVar(TableView.UpdateSelectionOnSort)
  /////////////////////////////////////////////////////////////////
  // ScrollPane properties
  /////////////////////////////////////////////////////////////////
  val botLeftCorner = ModifierVar(ScrollPane.BotLeftCorner)
  val botRightCorner = ModifierVar(ScrollPane.BotRightCorner)
  val columnHeader = ModifierVar(ScrollPane.ColumnHeader)
  val content = ModifierVar(ScrollPane.Content)
  val horizontalScrollBar = ModifierVar(ScrollPane.HorizontalScrollBar)
  val horizontalScrollBarPolicy = ModifierVar(ScrollPane.HorizontalScrollBarPolicy)
  val rowHeader = ModifierVar(ScrollPane.RowHeader)
  val topLeftCorner = ModifierVar(ScrollPane.TopLeftCorner)
  val topRightCorner = ModifierVar(ScrollPane.TopRightCorner)
  val verticalScrollBar = ModifierVar(ScrollPane.VerticalScrollBar)
  val verticalScrollBarPolicy = ModifierVar(ScrollPane.VerticalScrollBarPolicy)
  val viewport = ModifierVar(ScrollPane.Viewport)
  val viewportBorder = ModifierVar(ScrollPane.ViewportBorder)
  val wheelScrollingEnabled = ModifierVar(ScrollPane.WheelScrollingEnabled)
  /////////////////////////////////////////////////////////////////
  // ScrollBar properties
  /////////////////////////////////////////////////////////////////
  val blockIncrement = ModifierVar(ScrollBar.BlockIncrement)
  val maximum = ModifierVar(ScrollBar.Maximum)
  val minimum = ModifierVar(ScrollBar.Minimum)
  val unitIncrement = ModifierVar(ScrollBar.UnitIncrement)
  val visibleAmount = ModifierVar(ScrollBar.VisibleAmount)
  /////////////////////////////////////////////////////////////////
  // SplitPane properties
  /////////////////////////////////////////////////////////////////
  val componentA = ModifierVar(SplitPane.ComponentA)
  val componentB = ModifierVar(SplitPane.ComponentB)
  val continuousLayout = ModifierVar(SplitPane.ContinuousLayout)
  val dividerLocation = ModifierVar(SplitPane.DividerLocation)
  val dividerSize = ModifierVar(SplitPane.DividerSize)
  val lastDividerLocation = ModifierVar(SplitPane.LastDividerLocation)
  val oneTouchExpandable = ModifierVar(SplitPane.OneTouchExpandable)
  val resizeWeight = ModifierVar(SplitPane.ResizeWeight)
  /////////////////////////////////////////////////////////////////
  // ComboBox properties
  /////////////////////////////////////////////////////////////////
  val keySelectionManager = ModifierVar(ComboBox.KeySelectionManager)
  val maximumRowCount = ModifierVar(ComboBox.MaximumRowCount)
  val popupVisible = ModifierVar(ComboBox.PopupVisible)
  val prototypeDisplayValue = ModifierVar(ComboBox.PrototypeDisplayValue)
  val renderer = ModifierVar(ComboBox.Renderer)
  val selectedItem = ModifierVar(ComboBox.SelectedItem)
  /////////////////////////////////////////////////////////////////
  // Separator properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Spinner properties
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // TabbedPane properties
  /////////////////////////////////////////////////////////////////
  val selectedComponent = ModifierVar(TabbedPane.SelectedComponent)
  val tabLayoutPolicy = ModifierVar(TabbedPane.TabLayoutPolicy)
  val tabPlacement = ModifierVar(TabbedPane.TabPlacement)
  val tabs = ModifierVar(TabbedPane.Tabs)
  /////////////////////////////////////////////////////////////////
  // TreeView properties
  /////////////////////////////////////////////////////////////////
  val anchorSelectionPath = ModifierVar(TreeView.AnchorSelectionPath)
  val expandsSelectedPaths = ModifierVar(TreeView.ExpandsSelectedPaths)
  val invokesStopCellEditing = ModifierVar(TreeView.InvokesStopCellEditing)
  val largeModel = ModifierVar(TreeView.LargeModel)
  val leadSelectionPath = ModifierVar(TreeView.LeadSelectionPath)
  val rootVisible = ModifierVar(TreeView.RootVisible)
  val scrollsOnExpand = ModifierVar(TreeView.ScrollsOnExpand)
  val selectionPath = ModifierVar(TreeView.SelectionPath)
  val selectionPaths = ModifierVar(TreeView.SelectionPaths)
  val selectionRows = ModifierVar(TreeView.SelectionRows)
  val showsRootHandles = ModifierVar(TreeView.ShowsRootHandles)
  val toggleClickCount = ModifierVar(TreeView.ToggleClickCount)

  /////////////////////////////////////////////////////////////////
  // Multi vars
  /////////////////////////////////////////////////////////////////

  object UI {
    @targetName("assignPopupMenu}") def :=(binding: Binding[javax.swing.plaf.PopupMenuUI | Null]) = Modifier[PopupMenu](_.UI := binding)
    @targetName("assignPane}") def :=(binding: Binding[javax.swing.plaf.PanelUI]) = Modifier[Pane](_.UI := binding)
    @targetName("assignFlowPane}") def :=(binding: Binding[javax.swing.plaf.PanelUI]) = Modifier[FlowPane](_.UI := binding)
    @targetName("assignTextComponent}") def :=(binding: Binding[javax.swing.plaf.TextUI]) = Modifier[TextComponent](_.UI := binding)
    @targetName("assignLabel}") def :=(binding: Binding[javax.swing.plaf.LabelUI]) = Modifier[Label](_.UI := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[javax.swing.plaf.ButtonUI]) = Modifier[ButtonBase](_.UI := binding)
    @targetName("assignMenuBar}") def :=(binding: Binding[javax.swing.plaf.MenuBarUI | Null]) = Modifier[MenuBar](_.UI := binding)
    @targetName("assignMenu}") def :=(binding: Binding[javax.swing.plaf.MenuItemUI | Null]) = Modifier[Menu](_.UI := binding)
    @targetName("assignMenuItem}") def :=(binding: Binding[javax.swing.plaf.MenuItemUI | Null]) = Modifier[MenuItem](_.UI := binding)
    @targetName("assignSlider}") def :=(binding: Binding[javax.swing.plaf.SliderUI]) = Modifier[Slider](_.UI := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[javax.swing.plaf.ProgressBarUI]) = Modifier[ProgressBar](_.UI := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[javax.swing.plaf.ListUI]) = Modifier[ListView[E]](_.UI := binding)
    @targetName("assignTableView}") def :=(binding: Binding[javax.swing.plaf.TableUI]) = Modifier[TableView](_.UI := binding)
    @targetName("assignScrollPane}") def :=(binding: Binding[javax.swing.plaf.ScrollPaneUI]) = Modifier[ScrollPane](_.UI := binding)
    @targetName("assignScrollBar}") def :=(binding: Binding[javax.swing.plaf.ScrollBarUI]) = Modifier[ScrollBar](_.UI := binding)
    @targetName("assignSplitPane}") def :=(binding: Binding[javax.swing.plaf.SplitPaneUI]) = Modifier[SplitPane](_.UI := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[javax.swing.plaf.ComboBoxUI]) = Modifier[ComboBox[E]](_.UI := binding)
    @targetName("assignSeparator}") def :=(binding: Binding[javax.swing.plaf.SeparatorUI]) = Modifier[Separator](_.UI := binding)
    @targetName("assignSpinner}") def :=[E](binding: Binding[javax.swing.plaf.SpinnerUI]) = Modifier[Spinner[E]](_.UI := binding)
    @targetName("assignTabbedPane}") def :=(binding: Binding[javax.swing.plaf.TabbedPaneUI]) = Modifier[TabbedPane](_.UI := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.plaf.TreeUI | Null]) = Modifier[TreeView[E]](_.UI := binding)
  }
  object accelerator {
    @targetName("assignMenu}") def :=(binding: Binding[javax.swing.KeyStroke | Null]) = Modifier[Menu](_.accelerator := binding)
    @targetName("assignMenuItem}") def :=(binding: Binding[javax.swing.KeyStroke | Null]) = Modifier[MenuItem](_.accelerator := binding)
  }
  object action {
    @targetName("assignTextField}") def :=(binding: Binding[javax.swing.Action | Null]) = Modifier[TextField](_.action := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[javax.swing.Action | Null]) = Modifier[ButtonBase](_.action := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[javax.swing.Action | Null]) = Modifier[ComboBox[E]](_.action := binding)
  }
  object actionCommand {
    @targetName("assignButtonBase}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[ButtonBase](_.actionCommand := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[java.lang.String | Null]) = Modifier[ComboBox[E]](_.actionCommand := binding)
  }
  object borderPainted {
    @targetName("assignPopupMenu}") def :=(binding: Binding[Boolean]) = Modifier[PopupMenu](_.borderPainted := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Boolean]) = Modifier[ButtonBase](_.borderPainted := binding)
    @targetName("assignMenuBar}") def :=(binding: Binding[Boolean]) = Modifier[MenuBar](_.borderPainted := binding)
    @targetName("assignMenu}") def :=(binding: Binding[Boolean]) = Modifier[Menu](_.borderPainted := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[Boolean]) = Modifier[ProgressBar](_.borderPainted := binding)
  }
  object cellEditor {
    @targetName("assignTableView}") def :=(binding: Binding[javax.swing.table.TableCellEditor]) = Modifier[TableView](_.cellEditor := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.tree.TreeCellEditor | Null]) = Modifier[TreeView[E]](_.cellEditor := binding)
  }
  object cellRenderer {
    @targetName("assignListView}") def :=[E](binding: Binding[javax.swing.ListCellRenderer[? >: E]]) = Modifier[ListView[E]](_.cellRenderer := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.tree.TreeCellRenderer]) = Modifier[TreeView[E]](_.cellRenderer := binding)
  }
  object columns {
    @targetName("assignTextArea}") def :=(binding: Binding[Int]) = Modifier[TextArea](_.columns := binding)
    @targetName("assignTextField}") def :=(binding: Binding[Int]) = Modifier[TextField](_.columns := binding)
  }
  object contentPane {
    @targetName("assignWindow}") def :=(binding: Binding[java.awt.Container]) = Modifier[Window](_.contentPane := binding)
    @targetName("assignFrame}") def :=(binding: Binding[java.awt.Container]) = Modifier[Frame](_.contentPane := binding)
    @targetName("assignDialog}") def :=(binding: Binding[java.awt.Container | Null]) = Modifier[Dialog](_.contentPane := binding)
  }
  object defaultCloseOperation {
    @targetName("assignFrame}") def :=(binding: Binding[Int]) = Modifier[Frame](_.defaultCloseOperation := binding)
    @targetName("assignDialog}") def :=(binding: Binding[Int]) = Modifier[Dialog](_.defaultCloseOperation := binding)
  }
  object disabledIcon {
    @targetName("assignLabel}") def :=(binding: Binding[javax.swing.Icon | Null]) = Modifier[Label](_.disabledIcon := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[javax.swing.Icon | Null]) = Modifier[ButtonBase](_.disabledIcon := binding)
  }
  object displayedMnemonicIndex {
    @targetName("assignLabel}") def :=(binding: Binding[Int]) = Modifier[Label](_.displayedMnemonicIndex := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Int]) = Modifier[ButtonBase](_.displayedMnemonicIndex := binding)
  }
  object dragEnabled {
    @targetName("assignTextComponent}") def :=(binding: Binding[Boolean]) = Modifier[TextComponent](_.dragEnabled := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[Boolean]) = Modifier[ListView[E]](_.dragEnabled := binding)
    @targetName("assignTableView}") def :=(binding: Binding[Boolean]) = Modifier[TableView](_.dragEnabled := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[Boolean]) = Modifier[TreeView[E]](_.dragEnabled := binding)
  }
  object dropMode {
    @targetName("assignTextComponent}") def :=(binding: Binding[javax.swing.DropMode | Null]) = Modifier[TextComponent](_.dropMode := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[javax.swing.DropMode | Null]) = Modifier[ListView[E]](_.dropMode := binding)
    @targetName("assignTableView}") def :=(binding: Binding[javax.swing.DropMode | Null]) = Modifier[TableView](_.dropMode := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.DropMode | Null]) = Modifier[TreeView[E]](_.dropMode := binding)
  }
  object editable {
    @targetName("assignTextComponent}") def :=(binding: Binding[Boolean]) = Modifier[TextComponent](_.editable := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[Boolean]) = Modifier[ComboBox[E]](_.editable := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[Boolean]) = Modifier[TreeView[E]](_.editable := binding)
  }
  object editor {
    @targetName("assignComboBox}") def :=[E](binding: Binding[javax.swing.ComboBoxEditor | Null]) = Modifier[ComboBox[E]](_.editor := binding)
    @targetName("assignSpinner}") def :=[E](binding: Binding[javax.swing.JComponent | Null]) = Modifier[Spinner[E]](_.editor := binding)
  }
  object glassPane {
    @targetName("assignWindow}") def :=(binding: Binding[java.awt.Component | Null]) = Modifier[Window](_.glassPane := binding)
    @targetName("assignFrame}") def :=(binding: Binding[java.awt.Component | Null]) = Modifier[Frame](_.glassPane := binding)
    @targetName("assignDialog}") def :=(binding: Binding[java.awt.Component | Null]) = Modifier[Dialog](_.glassPane := binding)
  }
  object hgap {
    @targetName("assignBorderPane}") def :=(binding: Binding[Double]) = Modifier[BorderPane](_.hgap := binding)
    @targetName("assignGridPane}") def :=(binding: Binding[Double]) = Modifier[GridPane](_.hgap := binding)
  }
  object horizontal {
    @targetName("assignSplitPane}") def :=(binding: Binding[Boolean]) = Modifier[SplitPane](_.horizontal := binding)
    @targetName("assignSeparator}") def :=(binding: Binding[Boolean]) = Modifier[Separator](_.horizontal := binding)
  }
  object horizontalAlignment {
    @targetName("assignTextField}") def :=(binding: Binding[Int]) = Modifier[TextField](_.horizontalAlignment := binding)
    @targetName("assignLabel}") def :=(binding: Binding[Int]) = Modifier[Label](_.horizontalAlignment := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Int]) = Modifier[ButtonBase](_.horizontalAlignment := binding)
  }
  object horizontalTextPosition {
    @targetName("assignLabel}") def :=(binding: Binding[Int]) = Modifier[Label](_.horizontalTextPosition := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Int]) = Modifier[ButtonBase](_.horizontalTextPosition := binding)
  }
  object icon {
    @targetName("assignLabel}") def :=(binding: Binding[javax.swing.Icon | Null]) = Modifier[Label](_.icon := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[javax.swing.Icon | Null]) = Modifier[ButtonBase](_.icon := binding)
  }
  object iconTextGap {
    @targetName("assignLabel}") def :=(binding: Binding[Double]) = Modifier[Label](_.iconTextGap := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Double]) = Modifier[ButtonBase](_.iconTextGap := binding)
  }
  object items {
    @targetName("assignPopupMenu}") def :=(binding: Binding[Seq[MenuItem]]) = Modifier[PopupMenu](_.items := binding)
    @targetName("assignMenu}") def :=(binding: Binding[Seq[MenuItem | Component]]) = Modifier[Menu](_.items := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[Seq[E]]) = Modifier[ComboBox[E]](_.items := binding)
  }
  object label {
    @targetName("assignPopupMenu}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[PopupMenu](_.label := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[ButtonBase](_.label := binding)
  }
  object layeredPane {
    @targetName("assignWindow}") def :=(binding: Binding[javax.swing.JLayeredPane | Null]) = Modifier[Window](_.layeredPane := binding)
    @targetName("assignFrame}") def :=(binding: Binding[javax.swing.JLayeredPane | Null]) = Modifier[Frame](_.layeredPane := binding)
    @targetName("assignDialog}") def :=(binding: Binding[javax.swing.JLayeredPane | Null]) = Modifier[Dialog](_.layeredPane := binding)
  }
  object lightWeightPopupEnabled {
    @targetName("assignPopupMenu}") def :=(binding: Binding[Boolean]) = Modifier[PopupMenu](_.lightWeightPopupEnabled := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[Boolean]) = Modifier[ComboBox[E]](_.lightWeightPopupEnabled := binding)
  }
  object margin {
    @targetName("assignTextComponent}") def :=(binding: Binding[java.awt.Insets | Null]) = Modifier[TextComponent](_.margin := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[java.awt.Insets | Null]) = Modifier[ButtonBase](_.margin := binding)
    @targetName("assignMenuBar}") def :=(binding: Binding[java.awt.Insets | Null]) = Modifier[MenuBar](_.margin := binding)
    @targetName("assignMenu}") def :=(binding: Binding[java.awt.Insets | Null]) = Modifier[Menu](_.margin := binding)
  }
  object max {
    @targetName("assignSlider}") def :=(binding: Binding[Int]) = Modifier[Slider](_.max := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[Int]) = Modifier[ProgressBar](_.max := binding)
  }
  object menuBar {
    @targetName("assignFrame}") def :=(binding: Binding[guarana.swing.MenuBar | Null]) = Modifier[Frame](_.menuBar := binding)
    @targetName("assignDialog}") def :=(binding: Binding[javax.swing.JMenuBar | Null]) = Modifier[Dialog](_.menuBar := binding)
  }
  object min {
    @targetName("assignSlider}") def :=(binding: Binding[Int]) = Modifier[Slider](_.min := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[Int]) = Modifier[ProgressBar](_.min := binding)
  }
  object model {
    @targetName("assignButtonBase}") def :=(binding: Binding[javax.swing.ButtonModel | Null]) = Modifier[ButtonBase](_.model := binding)
    @targetName("assignSlider}") def :=(binding: Binding[javax.swing.BoundedRangeModel | Null]) = Modifier[Slider](_.model := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[javax.swing.BoundedRangeModel | Null]) = Modifier[ProgressBar](_.model := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[javax.swing.ListModel[E]]) = Modifier[ListView[E]](_.model := binding)
    @targetName("assignTableView}") def :=(binding: Binding[javax.swing.table.TableModel]) = Modifier[TableView](_.model := binding)
    @targetName("assignScrollBar}") def :=(binding: Binding[javax.swing.BoundedRangeModel]) = Modifier[ScrollBar](_.model := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[javax.swing.ComboBoxModel[E]]) = Modifier[ComboBox[E]](_.model := binding)
    @targetName("assignSpinner}") def :=[E](binding: Binding[javax.swing.SpinnerModel]) = Modifier[Spinner[E]](_.model := binding)
    @targetName("assignTabbedPane}") def :=(binding: Binding[javax.swing.SingleSelectionModel]) = Modifier[TabbedPane](_.model := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.tree.TreeModel]) = Modifier[TreeView[E]](_.model := binding)
  }
  object orientation {
    @targetName("assignSlider}") def :=(binding: Binding[Int]) = Modifier[Slider](_.orientation := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[Int]) = Modifier[ProgressBar](_.orientation := binding)
    @targetName("assignScrollBar}") def :=(binding: Binding[Int]) = Modifier[ScrollBar](_.orientation := binding)
  }
  object resizable {
    @targetName("assignFrame}") def :=(binding: Binding[Boolean]) = Modifier[Frame](_.resizable := binding)
    @targetName("assignDialog}") def :=(binding: Binding[Boolean]) = Modifier[Dialog](_.resizable := binding)
  }
  object rowHeight {
    @targetName("assignTableView}") def :=(binding: Binding[Int]) = Modifier[TableView](_.rowHeight := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[Int]) = Modifier[TreeView[E]](_.rowHeight := binding)
  }
  object rows {
    @targetName("assignGridPane}") def :=(binding: Binding[Seq[Seq[Node]]]) = Modifier[GridPane](_.rows := binding)
    @targetName("assignTextArea}") def :=(binding: Binding[Int]) = Modifier[TextArea](_.rows := binding)
  }
  object selectedIndex {
    @targetName("assignListView}") def :=[E](binding: Binding[Int]) = Modifier[ListView[E]](_.selectedIndex := binding)
    @targetName("assignComboBox}") def :=[E](binding: Binding[Int]) = Modifier[ComboBox[E]](_.selectedIndex := binding)
    @targetName("assignTabbedPane}") def :=(binding: Binding[Int]) = Modifier[TabbedPane](_.selectedIndex := binding)
  }
  object selectionBackground {
    @targetName("assignListView}") def :=[E](binding: Binding[java.awt.Color | Null]) = Modifier[ListView[E]](_.selectionBackground := binding)
    @targetName("assignTableView}") def :=(binding: Binding[java.awt.Color | Null]) = Modifier[TableView](_.selectionBackground := binding)
  }
  object selectionForeground {
    @targetName("assignListView}") def :=[E](binding: Binding[java.awt.Color | Null]) = Modifier[ListView[E]](_.selectionForeground := binding)
    @targetName("assignTableView}") def :=(binding: Binding[java.awt.Color | Null]) = Modifier[TableView](_.selectionForeground := binding)
  }
  object selectionModel {
    @targetName("assignPopupMenu}") def :=(binding: Binding[javax.swing.SingleSelectionModel | Null]) = Modifier[PopupMenu](_.selectionModel := binding)
    @targetName("assignMenuBar}") def :=(binding: Binding[javax.swing.SingleSelectionModel | Null]) = Modifier[MenuBar](_.selectionModel := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[javax.swing.ListSelectionModel]) = Modifier[ListView[E]](_.selectionModel := binding)
    @targetName("assignTableView}") def :=(binding: Binding[javax.swing.ListSelectionModel]) = Modifier[TableView](_.selectionModel := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[javax.swing.tree.TreeSelectionModel]) = Modifier[TreeView[E]](_.selectionModel := binding)
  }
  object state {
    @targetName("assignFrame}") def :=(binding: Binding[Int]) = Modifier[Frame](_.state := binding)
    @targetName("assignCheckBoxMenuItem}") def :=(binding: Binding[Boolean]) = Modifier[CheckBoxMenuItem](_.state := binding)
  }
  object text {
    @targetName("assignLabel}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[Label](_.text := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[ButtonBase](_.text := binding)
  }
  object title {
    @targetName("assignFrame}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[Frame](_.title := binding)
    @targetName("assignDialog}") def :=(binding: Binding[java.lang.String | Null]) = Modifier[Dialog](_.title := binding)
  }
  object transferHandler {
    @targetName("assignComponent}") def :=(binding: Binding[javax.swing.TransferHandler | Null]) = Modifier[Component](_.transferHandler := binding)
    @targetName("assignWindow}") def :=(binding: Binding[javax.swing.TransferHandler | Null]) = Modifier[Window](_.transferHandler := binding)
    @targetName("assignFrame}") def :=(binding: Binding[javax.swing.TransferHandler | Null]) = Modifier[Frame](_.transferHandler := binding)
    @targetName("assignDialog}") def :=(binding: Binding[javax.swing.TransferHandler | Null]) = Modifier[Dialog](_.transferHandler := binding)
  }
  object undecorated {
    @targetName("assignFrame}") def :=(binding: Binding[Boolean]) = Modifier[Frame](_.undecorated := binding)
    @targetName("assignDialog}") def :=(binding: Binding[Boolean]) = Modifier[Dialog](_.undecorated := binding)
  }
  object value {
    @targetName("assignSlider}") def :=(binding: Binding[Int]) = Modifier[Slider](_.value := binding)
    @targetName("assignProgressBar}") def :=(binding: Binding[Int]) = Modifier[ProgressBar](_.value := binding)
    @targetName("assignScrollBar}") def :=(binding: Binding[Int]) = Modifier[ScrollBar](_.value := binding)
    @targetName("assignSpinner}") def :=[E](binding: Binding[E]) = Modifier[Spinner[E]](_.value := binding)
  }
  object valueIsAdjusting {
    @targetName("assignSlider}") def :=(binding: Binding[Boolean]) = Modifier[Slider](_.valueIsAdjusting := binding)
    @targetName("assignListView}") def :=[E](binding: Binding[Boolean]) = Modifier[ListView[E]](_.valueIsAdjusting := binding)
    @targetName("assignScrollBar}") def :=(binding: Binding[Boolean]) = Modifier[ScrollBar](_.valueIsAdjusting := binding)
  }
  object verticalAlignment {
    @targetName("assignLabel}") def :=(binding: Binding[Int]) = Modifier[Label](_.verticalAlignment := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Int]) = Modifier[ButtonBase](_.verticalAlignment := binding)
  }
  object verticalTextPosition {
    @targetName("assignLabel}") def :=(binding: Binding[Int]) = Modifier[Label](_.verticalTextPosition := binding)
    @targetName("assignButtonBase}") def :=(binding: Binding[Int]) = Modifier[ButtonBase](_.verticalTextPosition := binding)
  }
  object vgap {
    @targetName("assignBorderPane}") def :=(binding: Binding[Double]) = Modifier[BorderPane](_.vgap := binding)
    @targetName("assignGridPane}") def :=(binding: Binding[Double]) = Modifier[GridPane](_.vgap := binding)
  }
  object visibleRowCount {
    @targetName("assignListView}") def :=[E](binding: Binding[Int]) = Modifier[ListView[E]](_.visibleRowCount := binding)
    @targetName("assignTreeView}") def :=[E](binding: Binding[Int]) = Modifier[TreeView[E]](_.visibleRowCount := binding)
  }


}
