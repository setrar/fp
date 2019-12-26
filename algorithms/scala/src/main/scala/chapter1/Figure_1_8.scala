package chapter1

object Figure_1_8 {

  def primality2(N: Int): Boolean = {
    assert(N >= 0)

    val k = 100

    // Random in [2, N[ since 0 is neither a prime nor a composite and 1 is the neutral element
    val a = Array.fill(k)(util.Random.between(2, N))

    def loop(i: Int, acc: Boolean): Boolean =
      if (i >= k || !acc) acc
      else loop(i + 1, Math.pow(a(i),N-1) % N == 1)

    loop(0, acc = true)
  }

}
