package guarana.swing.style

/** Definition of some standard control states.
  * 
  * Note that for Stylist, the state is Any, using these is a convention, but a controller
  * is free to have arbitrary states.
  */
enum ControlStates:
  case Hovered,
       Pressed,
       Focused,
       EvenEntry,
       OddEntry