package chapter1

object Figure_1_5 {

  def euclid(a: Int, b: Int): Int = {
//    assert(a >= b && b >= 0)
    if (b == 0) a else euclid(b, a % b)
  }

}
