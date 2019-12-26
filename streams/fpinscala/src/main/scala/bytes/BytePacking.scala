package bytes

/**
  * https://coderanch.com/t/478995/java/Byte-Packing-Unpacking
  */
object BytePacking {

  def pack(vals: Array[Byte]): Byte = {
    var result: Byte = 0
    for (bit <- vals) yield {
      result = ((result << 1) | (bit & 1)).asInstanceOf[Byte]
    }
    result
  }

/*
  def unpack(vals: Byte): Array[Byte] = {
    val result = new Array[Byte](8)
    for (bit <- vals) yield {
      result(bit) = ((vals >> (7 - bit)) & 1).toByte
    }
    result
  }
*/

    def main(args: Array[String]): Unit = {
      val _1B = 1.toByte
      val _0B = 0.toByte
      val vals = Array(_1B, _0B, _1B, _0B, _1B, _0B, _1B, _1B)

      val value = pack(vals)
      1 :: List(1,2,3)
      print(value)
    }

}
