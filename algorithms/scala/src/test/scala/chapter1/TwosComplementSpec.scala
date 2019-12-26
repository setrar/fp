package chapter1

import org.scalatest._

class TwosComplementSpec extends FlatSpec with Matchers {
  "The twosComplementSpec" should "not fail" in {
    Figure_1_3.twosComplement(1, bitRepresentation = 3) shouldBe "111"
    Figure_1_3.twosComplement(-3, bitRepresentation = 3) shouldBe "011"
    Figure_1_3.twosComplement(126, bitRepresentation = 8) shouldBe "10000010"
    Figure_1_3.twosComplement(-2, bitRepresentation = 8) shouldBe "00000010"
  }

}
