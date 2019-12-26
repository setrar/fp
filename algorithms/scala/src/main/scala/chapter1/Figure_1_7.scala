package chapter1

object Figure_1_7 {

  def primality(N: Int): Boolean = {
    assert(N >= 0)

    // Random in [1, N[ since 0 is neither a prime nor a composite
    val a = util.Random.between(1, N)
    if (Math.pow(a,N-1) % N == 1) true else false
  }

}
