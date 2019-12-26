package redbook

import Math._

trait Tree[+A] {

  /**
    * before Fold
    */
  def size_(): Int = this match {
    case Leaf(_) => 1
    case Branch(l, r) => l.size_ + r.size_ + 1
  }

  def maximum_(): Int = this match {
    case Leaf(n: Int) => n
    case Branch(l, r) => l.maximum_ max r.maximum_
  }

  def depth_(): Int = this match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + l.depth_() max r.depth_()
  }

  def map_[C >: A, B](f: A => B): Tree[B] = this match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l, r) => Branch(l.map_(f), r.map_(f))
  }

  /**
    * Implement Fold
    */
  def fold[B](z: A => B)(f: (B, B) => B): B = this match {
    case Leaf(a) => z(a)
    case Branch(l, r) => f(l.fold(z)(f), r.fold(z)(f))
  }

  def size: Int =
    this.fold(_ => 1)(1 + _ + _)

  def depth: Int =
    this.fold(_ => 0)(1 + _ max _)

  def map[B](f: A => B): Tree[B] =
    this.fold(a => Leaf(f(a)): Tree[B])(Branch(_, _))

  def exist(f: A => Boolean): Boolean =
    this.fold(a => f(a))((a,b) => a && b)


  def maximum: Int = this.asInstanceOf[Tree[Int]].fold(a => a)(_ max _)

  /**
    * https://stackoverflow.com/questions/48043093/check-if-a-binary-tree-is-balanced-in-scala
    * https://en.wikipedia.org/wiki/AVL_treek
    * @tparam A
    */
  def balanced: Int = this match {
    case Leaf(_) => 0
    case Branch(l, r) =>
      val left = l.balanced
      val right = r.balanced
      if (left < 0 || right < 0 || abs(left - right) > 1)
        -1
      else
        (left max right) + 1
  }

  def isBalanced: Boolean =
    this.balanced== this.depth_

}

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree extends Foldable[Tree] {
  override def foldMap[A, B](as: Tree[A])(f: A => B)(mb: Monoid[B]): B =
    as match {
      case Leaf(a) => f(a)
      case Branch(l, r) => mb.op(foldMap(l)(f)(mb), foldMap(r)(f)(mb))
    }
  override def foldLeft[A, B](as: Tree[A])(z: B)(f: (B, A) => B) = as match {
    case Leaf(a) => f(z, a)
    case Branch(l, r) => foldLeft(r)(foldLeft(l)(z)(f))(f)
  }
  override def foldRight[A, B](as: Tree[A])(z: B)(f: (A, B) => B) = as match {
    case Leaf(a) => f(a, z)
    case Branch(l, r) => foldRight(l)(foldRight(r)(z)(f))(f)
  }

}

