package paragraph72

import org.scalatest._

class RationalSpec extends FlatSpec with Matchers {
  "The Rational ordering" should "be equal" in {
    List(Rational(1,2), Rational(3,4), Rational(1,3)).sorted shouldEqual List(Rational(1,3), Rational(1,2), Rational(3,4))
  }

  "The Rational Greater Than ordering" should "be equal" in {
    import paragraph72.RationalGreaterThanOrdering._
    List(Rational(1,2), Rational(3,4), Rational(1,3)).sorted shouldEqual List(Rational(3,4), Rational(1,2), Rational(1, 3))
  }
}
