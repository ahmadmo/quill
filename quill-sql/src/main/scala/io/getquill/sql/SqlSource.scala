package io.getquill.sql

import scala.reflect.ClassTag
import language.experimental.macros
import io.getquill.impl.Queryable
import io.getquill.impl.Quoted
import io.getquill.impl.Actionable

abstract class SqlSource[R: ClassTag, S: ClassTag] extends io.getquill.source.Source[R, S] {

  def run[T](q: Queryable[T]): Any = macro QueryMacro.run[R, S, T]
  def run[P1, T](q: P1 => Queryable[T])(p1: P1): Any = macro QueryMacro.run1[P1, R, S, T]
  def run[P1, P2, T](q: (P1, P2) => Queryable[T])(p1: P1, p2: P2): Any = macro QueryMacro.run2[P1, P2, R, S, T]

  def run[T](q: Actionable[T]): Any = macro ActionMacro.run[R, S, T]
  def run[P1, T](q: P1 => Actionable[T])(bindings: Iterable[P1]): Any = macro ActionMacro.run1[P1, R, S, T]
  def run[P1, P2, T](q: (P1, P2) => Actionable[T])(bindings: Iterable[(P1, P2)]): Any = macro ActionMacro.run2[P1, P2, R, S, T]

}
