package chapter1

object Figure_1_2 {

  /**
    * Local Effect using Mutable State
    * i.e. following the books algorithm
    */
  def mutableDivide(x: Int, y: Int): (Int, Int) = {
    assert(y >= 1)

    if (x == 0) (0,0)
    else {
      var (q, r) = mutableDivide(x / 2, y)
      q = 2 * q; r = 2 * r
      if (x % 2 == 1) r = r + 1
      if (r >= y) { r = r - y; q = q + 1 }
      (q, r)
    }
  }

  /**
    * Not accurate, let's find more
    */
  def divide(numerator: Int, denominator: Int): (Int, Int) = {
    assert(denominator >= 1)

    case class Result(quotient: Int = 0, remainder: Int = 0)

    def odd(x: Int, acc: Result): Result = {
      val result = if (x % 2 == 1) acc.copy(remainder = acc.remainder + 1) else acc
      if (result.remainder >= denominator)
        result.copy(result.quotient + 1, result.remainder - denominator)
      else result
    }

    def loop(n: Int, acc: Result): Result = {
      if (n == 0) acc
      else {
        val reduce = loop(n / 2, acc)
        val result = odd(n, Result(2 * reduce.quotient, 2 * reduce.remainder))
        result.copy(result.quotient, if (numerator % 2 == 1) result.remainder + 1 else result.remainder)
      }
    }

    val result = loop(numerator, Result())

    (result.quotient, result.remainder)
  }


}
