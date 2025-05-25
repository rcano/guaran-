package guarana
package swing
package plaf

import language.implicitConversions

// class CssTabbedPaneUi extends MetalTabbedPaneUI, CssSwingControlUi {

//   protected var tabsOverlapBorder: Boolean = false
//   protected var contentOpaque: Boolean = true
//   protected var tabsOpaque: Boolean = true

//   override protected def installDefaults() = {
//     super.installDefaults()
//     tabsOverlapBorder = UIManager.getBoolean("TabbedPane.tabsOverlapBorder")
//     contentOpaque = UIManager.getBoolean("TabbedPane.contentOpaque")
//     tabsOpaque = UIManager.getBoolean("TabbedPane.tabsOpaque")
//   }

//   override protected def createScrollButton(direction: Int): JButton =
//     new javax.swing.plaf.basic.BasicArrowButton(direction) with UIResource

//   private def tabBorder(tabIndex: Int, selected: Boolean): style.Border = {
//     val tp = tabPane
//     val borderFunction = style.CssProperties.TabBorder.forInstance(tp).pipe(scenegraph.stateReader(_))
//     borderFunction(style.TabInfo(tabIndex, selected))
//   }

//   override protected def paintContentBorder(g: Graphics, tabPlacement: Int, selectedIndex: Int) = {
//     val tp = tabPane.unn
//     if (contentOpaque || tp.isOpaque()) {
//       if (tp.isOpaque()) {
//         g.setColor(tp.getBackground())
//         g.fillRect(0, 0, tp.getWidth(), tp.getHeight())
//       }
//       val background = style.CssProperties.Background.forInstance(tp)
//       val tabAreaInsets = getTabAreaInsets(tabPlacement).unn
//       withinRegion(tp) { (x, y, w, h) =>
//         var ty = y
//         var tx = y
//         var tw = w
//         var th = h

//         tabPlacement match {
//           case SwingConstants.TOP => 
//             ty += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight)
//             if (tabsOverlapBorder) ty -= tabAreaInsets.bottom
//           case SwingConstants.RIGHT =>
//             tw -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth)
//             if (tabsOverlapBorder) tw += tabAreaInsets.left
//           case SwingConstants.BOTTOM =>
//             th -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight)
//             if (tabsOverlapBorder) th += tabAreaInsets.top
//           case SwingConstants.LEFT =>
//             tx += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth)
//             if (tabsOverlapBorder) tx -= tabAreaInsets.right
//           case _ =>
//             ty += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight)
//             if (tabsOverlapBorder) ty -= tabAreaInsets.bottom
//         }

//         plaf.RegionPainter.paintRegion(scenegraph.stateReader(background), g.upgrade.withAliasing, tx, ty, tw, th)
//       }
//     }
//   }

//   override protected def getTabInsets(tabPlacement: Int, tabIndex: Int): java.awt.Insets = {
//     CssBorder.getBorderInsets(tabBorder(tabIndex, false)).toAwt
//   }

//   override protected def paintTabBorder(g: Graphics, tabPlacement: Int, tabIndex: Int,
//        x: Int, y: Int, w: Int, h: Int, selected: Boolean) = {
//     if (tabsOpaque || tabPane.unn.isOpaque) {
//       val border = tabBorder(tabIndex, selected)
//       CssBorder.paintBorder(border, CssBorder.getBorderInsets(border), g, x, y, w, h)
//     }
//   }
//   override protected def paintTabBackground(g: Graphics, tabPlacement: Int, tabIndex: Int,
//       x: Int, y: Int, w: Int, h: Int, selected: Boolean) = {
//     val tp = tabPane.unn
//     if (tabsOpaque || contentOpaque || tp.isOpaque) {
//       val backgroundFunction = style.CssProperties.TabBackground.forInstance(tp).pipe(scenegraph.stateReader(_))
//       val background = backgroundFunction(style.TabInfo(tabIndex, selected))
//       plaf.RegionPainter.paintRegion(background, g.upgrade.withAliasing, x, y, w, h)
//     }
//   }

//   override protected def paintFocusIndicator(g: Graphics, tabPlacement: Int,
//                                        rects: Array[Rectangle], tabIndex: Int,
//                                        iconRect: Rectangle, textRect: Rectangle,
//                                        isSelected: Boolean) = {}
// }
// object CssTabbedPaneUi {
//   @static def createUI(c: JComponent): CssTabbedPaneUi = CssTabbedPaneUi()

// }
