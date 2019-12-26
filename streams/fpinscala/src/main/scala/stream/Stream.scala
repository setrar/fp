package stream

sealed trait Stream[+A] {

  final val _ONE_ = 1

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > _ONE_ => Stream.cons[A](h(), t().take(n - _ONE_))
    case Cons(h, _) if n == _ONE_ => Stream.cons[A](h(), Stream.empty)
    case _ => Stream.empty // Stream is empty or n == 0
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n >= _ONE_ => t().drop(n - _ONE_)
    case _ => this
  }

  /**
    * TakeWhile with Early Termination see underscore_ (i.e. not using foldRight)
    *
    * @param p : A
    * @return Stream[A]
    */
  def takeWhile_(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => Stream.cons[A](h(), t() takeWhile_ p)
    case _ => Stream.empty
  }

  /**
    * exists with Early Termination see underscore
    *
    * @param p : A
    * @return Boolean
    */
  def exists_(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exists(p)
    case _ => false
  }

  private def foldRight[B](z: => B)(f: (A, => B) => B): B =
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((h, t) => p(h) && t)

  def takeWhile(p: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((h, t) =>
      if (p(h)) Stream.cons[A](h, t) else Stream.empty[A]
    )

  /*
  Folded headOption
   */
  def headOption_(): Option[A] =
    foldRight(Option.empty[A])((h, _) => Some(h))

  def map[B](f: A => B): Stream[B] =
    foldRight(Stream.empty[B])((h, t) => Stream.cons(f(h), t))

  def filter(p: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((h, t) =>
      if (p(h)) Stream.cons[A](h, t) else t
    )

  def append[B >: A](s: Stream[B]): Stream[B] =
    foldRight(s)((h, t) => Stream.cons(h, t))

  def flatMap[B >: A](f: A => Stream[B]): Stream[B] =
    foldRight(Stream.empty[B])((h, t) => f(h) append t)

  def find(p: A => Boolean): Option[A] =
    filter(p).headOption

  def constant[B >: A](b: B): Stream[B] =
    Stream.cons(b, constant(b))

  def from(n: Int): Stream[Int] =
    Stream.cons(n, from(n + 1))

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((h, s)) => Stream.cons[A](h, unfold(s)(f))
    case None => Stream.empty[A]
  }

  def unfoldFibs: Stream[Int] =
    unfold((0, 1)) { case (prev, next) => Some(prev, (next, prev + next)) }

  def unfoldConstant[B >: A](b: B): Stream[B] =
    unfold(b)(_ => Some(b,b))
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)

  val fibs: Stream[Int] = {
    def go(prev: Int, next: Int): Stream[Int] =
      Stream.cons(prev, go(next, prev + next))

    go(0, 1)
  }

}
