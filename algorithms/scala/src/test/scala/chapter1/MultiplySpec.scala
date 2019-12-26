package chapter1

import org.scalatest._

class MultiplySpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    Figure_1_1.multiply(3,2) shouldEqual 6
  }
}
