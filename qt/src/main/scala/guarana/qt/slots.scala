package guarana.qt

import io.qt.core.*

def slot(f: => Any): QMetaObject.Slot0 = () => f
def slot[T1](f: T1 => Any): QMetaObject.Slot1[T1] = v1 => f(v1.asInstanceOf[T1])
def slot[T1, T2](f: (T1, T2) => Any): QMetaObject.Slot2[T1, T2] = (v1, v2) => f(v1.asInstanceOf[T1], v2.asInstanceOf[T2])
def slot[T1, T2, T3](f: (T1, T2, T3) => Any): QMetaObject.Slot3[T1, T2, T3] = (v1, v2, v3) => f(v1.asInstanceOf[T1], v2.asInstanceOf[T2], v3.asInstanceOf[T3])
