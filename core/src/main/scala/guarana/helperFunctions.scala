package guarana


def Rect(x: Int = 0, y: Int = 0, width: Int = 0, height: Int = 0) = java.awt.Rectangle(x, y, width, height)

inline def using[T, R](t: T)(inline f: T ?=> R): R = f(using t)