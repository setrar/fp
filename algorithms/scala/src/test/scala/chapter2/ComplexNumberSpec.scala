package chapter2

import org.scalatest._

import ComplexNumber._

class ComplexNumberSpec extends FlatSpec with Matchers {
  "The ComplexNumberSpec" should "not fail" in {
    Complex(-23, -5).toString shouldBe "-23.0 - 5.0i"
    Complex(23, 1).toString shouldBe "23.0 + 1.0i"
    Complex(24, 1.34).toString shouldBe "24.0 + 1.34i"
  }

}
