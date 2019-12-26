package chapter1

object Figure_1_6 {

  /**
    * Output (x, y, d)
    */
  def extendedEuclid(a: Int, b: Int): (Int, Int, Int) = {
    assert(a >= b && b >= 0)

    if (b == 0) (1, 0, a)
    else {
      val (x: Int, y: Int, d: Int) = extendedEuclid(b, a % b)
      (y, x - (a / b) * y, d)
    }

  }

  def xgcd(x: Long, y: Long): Array[Long] = {
    var a = x
    var b = y
    val retvals = Array(0L, 0L, 0L)
    val aa = Array(1L, 0L)
    val bb = Array(0L, 1L)
    var q = 0L
    while ( {
      true
    }) {
      q = a / b
      a = a % b
      aa(0) = aa(0) - q * aa(1)
      bb(0) = bb(0) - q * bb(1)
      if (a == 0) {
        retvals(0) = b
        retvals(1) = aa(1)
        retvals(2) = bb(1)
        return retvals
      }

      q = b / a
      b = b % a
      aa(1) = aa(1) - q * aa(0)
      bb(1) = bb(1) - q * bb(0)
      if (b == 0) {
        retvals(0) = a
        retvals(1) = aa(0)
        retvals(2) = bb(0)
        return retvals
      }

    }
    retvals
  }

}
