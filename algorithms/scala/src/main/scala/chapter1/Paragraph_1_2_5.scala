package chapter1

import Figure_1_6.{extendedEuclid => egcd}

object Paragraph_1_2_5 {

  /**
    * https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-inverses
    *
    * Very quick and dirty implementation
    * using brut force
    * time complexity O(n)
    *
    */
  def inverse(a: Int, modulus: Int): Int = {

    def loop(i: Int, acc: Int): Int = {
      if (i >= modulus || acc > 1) acc
      else loop(i + 1, if (a * i % modulus == 1) i else 1)
    }

    loop(0, 1)
  }


  /**
    *
    * Modular Inverse based on
    * http://cacr.uwaterloo.ca/hac/
    *
    * Taken from Rosetta Code
    *
    * https://rosettacode.org/wiki/Modular_inverse#Scala
    *
    */
  def modInv(a: Int, m: Int): Option[Int] = {
    val (_,y,g) = egcd(a, m)
    if (g == 1) Option(if (y >= 0) y else a + y) else Option.empty
  }

}
