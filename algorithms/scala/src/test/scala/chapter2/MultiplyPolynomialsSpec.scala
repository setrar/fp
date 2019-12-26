package chapter2

import org.scalatest._

class MultiplyPolynomialsSpec extends FlatSpec with Matchers {
  "The MultiplyPolynomialsSpec" should "not fail" in {

    // https://www.geeksforgeeks.org/multiply-two-polynomials-2/
    // First polynomial is  5 + 0x^1 + 10x^2 + 6x^3
    // Second polynomial is 1 + 2x^1 + 4x^2
    // Product polynomial is 5 + 10x^1 + 30x^2 + 26x^3 + 52x^4 + 24x^5

    val a: Array[Int] = Array(5,  0, 10,  6)
    val b: Array[Int] = Array(1,  2,  4)
    val p: Array[Int] = Array(5, 10, 30, 26, 52, 24)

    Figure_2_5.polynomialMultiplication(a, b, Math.min(a.length,b.length)) shouldBe p
  }

}
