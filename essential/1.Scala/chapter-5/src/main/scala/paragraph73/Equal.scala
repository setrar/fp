package paragraph73

trait Equal[A] {
  def equal(left: A, right: A): Boolean
}

object PersonEqual extends Equal[Person] {
  override def equal(left: Person, right: Person): Boolean = left.email == right.email
}
