package chapter1

object Figure_1_4 {

  /**
    * https://en.wikipedia.org/wiki/Modular_exponentiation
    *
    */
  def modexp(x: Int, y: Int, n: Int): Double = {
    if (y == 0 ) 1
    else {
      val z: Double = modexp(x, y / 2, n)
      val zSquare: Double = Math.pow(z,2)
      if (y % 2 == 0 ) zSquare % n else (x * zSquare) % n
    }
  }

}
