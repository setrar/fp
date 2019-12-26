package chapter1

object Figure_1_3 {

  /**
    * Modular Arithmetic
    *
    * dealing with sign when 0 < | Dividend | < (mod N)
    *
    */
  def modulo(x: Int, modulus: Int): Int = {
    val eval: Int = x % modulus
    val dividend: Int = Math.abs(x)
    val isDividendPositive: Boolean = x > 0
    if (0 < dividend && dividend < modulus)
      if (isDividendPositive) eval - modulus else eval + modulus
    else
      eval
  }

  /**
    * https://en.wikipedia.org/wiki/Modulo_operation
    *
    * Section: Common pitfalls
    */
  def isOdd(n: Int, y: Int): Boolean = {
    n % y == 1 || n % y == -1
  }

  /**
    * https://en.wikipedia.org/wiki/Two%27s_complement
    *
    * Algorithm is taken from Wikipedia since no example provided in the book
    * (Box: Two's complement p. 17)
    *
    */
  def twosComplement(n: Int, bitRepresentation: Int): String = {
    val ts = (Math.pow(2, bitRepresentation) - n).toInt.toBinaryString
    // shift left when negative
    if (n<0) ts.drop(1) else ts
  }

}
