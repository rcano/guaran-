package guarana.web.utils

opaque type IsPresent >: Boolean = Boolean
opaque type YesNo >: Boolean = Boolean
opaque type OnOff >: Boolean = Boolean

object IsPresent:
  given Conversion[IsPresent, Boolean] = v => v

object YesNo:
  given Conversion[YesNo, Boolean] = v => v

object OnOff:
  given Conversion[OnOff, Boolean] = v => v
