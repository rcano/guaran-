package guarana
package qt

/** Sum type modeling Qt's LayoutItem for filler space
  * 
  * There's no need to model Widget as we'll construct the type as Widget | LayoutItem
  */
enum LayoutItem {
  case Space(space: Binding[Double])  
  case Stretch(min: Binding[Double] = 0.0)  
}
