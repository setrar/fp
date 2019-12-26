package chapter1

import Figure_1_6.{extendedEuclid => egcd}
import Figure_1_8.{primality2 => isPrime}
import Figure_1_4.modexp
import Paragraph_1_2_5._


object Figure_1_9 {


  def generate(k: Int, R1: Int, R2: Int): (Int, Int) = {

    val a = Array.fill(k)(util.Random.between(R1, R2))

    case class Primes(q: Int = 1, p: Int = 1)

    val acc = a.indices.foldLeft(Primes()){ (acc,x) =>

      val current = a(x)
      if (isPrime(current) )
        acc match {
          case Primes(p, q) if p == 1 || p != current => Primes(current, q)
          case Primes(p, q) if q == 1 || q != current => Primes(p, current)
          case _ => acc
        }
      else
        acc

    }
    (acc.p, acc.q)
  }

  def privateKey(pqe: ((Int,Int), Int)): Int = pqe match { case ((p, q),e) =>
    // the totient:  n ϕ (n) =(p−1)x(q−1)
    val totient = (p - 1) * (q - 1)

    /**
      * can use brut force using modulo inverse
      */
    // val d = inverse(e, totient)

    /**
      * d = e^-1 mod totient using extended euclidean algorithm
      */
    val d = modInv(totient,e) match {
      case Some(pk) => pk
      case _ => 1
    }
    d
  }

  def encrypt(xen: (Int, (Int,Int))): Double = xen match { case (x, (n, e)) => modexp(x, e, n) }

  def decrypt(y: Int, d: Int, n: Int): Int = {
    val m = modexp(y.toInt, d, n).toInt
    m
  }

}
