package paragraph546

sealed trait MayBe[A] {
  def fold[B](empty: B)(full: A => B) : B =
    this match {
      case Empty() => empty
      case Full(value) => full(value)
    }
}
final case class Full[A](value: A) extends MayBe[A]
final case class Empty[A]() extends MayBe[A]


