package chapter1

import java.math.BigInteger

import org.scalatest._
import Paragraph_1_2_5._

class InverseSpec extends FlatSpec with Matchers {
  "The InverseSpec" should "not fail" in {
    inverse(7, modulus = 120) shouldEqual 103
    //inverse(-9, modulus = 25) shouldEqual 16
    inverse(20, modulus = 79) shouldEqual 4
    inverse(3, modulus = 62) shouldEqual 21
    inverse(21, modulus = 91) shouldEqual 1
    inverse(5, modulus = 23) shouldEqual 14
  }

  /*
  "The modInvSpec" should "not fail" in {
    import scala.math.BigInt
    val rand = 7
    val mod = 120
    val e = new BigInt(new BigInteger(rand.toString))
    val m = new BigInt(new BigInteger(mod.toString))

    val g = new BigInt(new BigInteger(rand.toString))
    println("m = " + m.toString(16))
    println("g = " + g.toString(16))
    println("e = " + e.toString(16))

    val res = g.modPow(e, m)
    println("g^e mod n = " + res.toString(16))

    val inv = g.modInverse(120)
    println("g^-1 mod n = " + inv.toString(16))
    val ingtg = (inv * g).mod(m)
    println("g * g^-1 = " + ingtg.toString(16)) //should be 1
    println(if(ingtg == BigInt(1)) "success" else "failure")
        }
   */

}
