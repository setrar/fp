package paragraph74

import org.scalatest._

class EqualSpec extends FlatSpec with Matchers {
  "The Eq companion object" should "demonstrate the Interfaces using implicit Parameters" in {
    implicit object NameEmailEqual extends Equal[Person] {
      def equal(left: Person, right: Person): Boolean =
        left.email == right.email &&
          left.name == right.name
    }
    Eq(Person("John","john@example.com"),Person("John","john@example.com")) shouldBe true
  }
  "The Eq companion object" should "demonstrate something wrong" in {
    implicit object NameEmailEqual extends Equal[Person] {
      def equal(left: Person, right: Person): Boolean =
        left.email == right.email &&
          left.name == right.name
    }
    Eq(Person("John","john@example.com"),Person("John","john@doe.com")) shouldBe false
  }
  "The Eq suggested object" should "demonstrate by Name only" in {
    import NameEqualImplementation._
    Eq(Person("John","john@example.com"),Person("John","john@doe.com")) shouldBe true
  }
  "The Eq companion object" should "demonstrate by Name only" in {
    import NameEqualImplementation._
    Equal[Person].equal(Person("John","john@example.com"),Person("John","john@doe.com")) shouldBe true
  }
}
