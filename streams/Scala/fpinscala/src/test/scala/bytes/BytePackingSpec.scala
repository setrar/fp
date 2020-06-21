package bytes

import org.scalatest.{FlatSpec, Matchers}

class BytePackingSpec extends FlatSpec with Matchers {
  "The BytePacking object" should "return something but not sure what" in {
    val _1B = 1.toByte
    val _0B = 0.toByte
    val vals: Array[Byte] = Array(_1B, _0B, _1B, _0B, _1B, _0B, _1B, _1B)

    val value = BytePacking.pack(vals)
    value shouldEqual -85
  }
}