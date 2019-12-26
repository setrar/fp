package chapter2

import org.scalatest._

class MultiplySpec extends FlatSpec with Matchers {
  "The MultiplySpec" should "not fail" in {
    Figure_2_1.multiply(1, 3) shouldBe 3
    Figure_2_1.multiply(178, 99) shouldBe 17622
  }

  "The Multiply2Spec" should "not fail" in {
    178.toBinaryString shouldEqual "10110010" // from Int to Binary String
    Integer.parseInt("10110010",2) shouldEqual 178 // from Binary String to Int Fig 2.2
    Integer.parseInt("01100011",2) shouldEqual 99  // from Binary String to Int Fig 2.2
  }

}
