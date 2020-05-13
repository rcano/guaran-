package guarana.swing
package plaf

import java.awt.{Graphics, Graphics2D, RenderingHints}

type Graphics = java.awt.Graphics
type Graphics2D = java.awt.Graphics2D

inline def (g: Graphics | Null) upgrade: Graphics2D = g.asInstanceOf
def (g2: Graphics2D | UncheckedNull) withAliasing: Graphics2D = {
  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
  g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
  g2.asInstanceOf[Graphics2D]
}