package paragraph74

trait Equal[A] {
  def equal(left: A, right: A): Boolean
}

object Equal {
  def apply[A](implicit equalImpl: Equal[A]): Equal[A] = equalImpl
}

object Eq {
  def apply[A](left: A, right: A)(implicit equalImpl: Equal[A]): Boolean = equalImpl.equal(left, right)
}

object NameEqualImplementation {

  implicit object NameEqual extends Equal[Person] {
    def equal(left: Person, right: Person): Boolean = left.name == right.name
  }

}

object EmailEqualImplementation {

  object EmailEqual extends Equal[Person] {
    def equal(left: Person, right: Person): Boolean = left.email == right.email
  }

}

