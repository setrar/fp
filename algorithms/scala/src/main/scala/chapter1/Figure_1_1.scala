package chapter1

object Figure_1_1 {

  def multiply(x: Int, y: Int): Int = {
    assert(y >= 0)

    if (y != 0) {
      val z = multiply(x, y / 2)
      if (y % 2 == 0)
        2 * z
      else
        x + ( 2 * z )
    } else 0
  }

}
