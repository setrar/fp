package chapter2

object Figure_2_1 {

  /**
    * This can probably be improved
    * 1) n is calculated using BinaryString, may find another solution
    * 2) mask is used to take rightmost bits like in 32 bits network IP addressing
    */
  def sizeOf(n: Int): Int = n.toBinaryString.length

  def leftmost(n: Int, size: Int): Int = n >>> size

  def rightmost(n: Int, size: Int): Int = {
    val mask: Int = Math.pow(2, size).toInt - 1 // i.e. 0xFF
    n & mask.toByte
  }

  def multiply(x: Int, y: Int): Int = {

    val n = Math.max(sizeOf(x),sizeOf(y)) // Errata missing in book

    if (n == 1) x * y
    else {
      val Xl = leftmost(x,n/2); val Xr = rightmost(x, n/2)
      val Yl = leftmost(y,n/2); val Yr = rightmost(y, n/2)
      val p1 = multiply(Xl, Yl)
      val p2 = multiply(Xr, Yr)
      val p3 = multiply(Xl + Xr, Yl + Yr)
      (p1 * Math.pow(2, 2 * (n/2))).toInt + ((p3 - p1 - p2) * Math.pow(2, n/2)).toInt + p2
    }
  }

}
