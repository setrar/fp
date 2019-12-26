package paragraph73

import org.scalatest._

class EqualSpec extends FlatSpec with Matchers {
  "The Equal class" should "do some comparisons" in {
    PersonEqual.equal(Person("John","john@example.com"),Person("John","john@example.com")) shouldBe true
  }
}
