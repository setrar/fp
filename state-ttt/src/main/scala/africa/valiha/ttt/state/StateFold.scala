package africa.valiha.ttt.state

import scala.language.higherKinds
import scalaz.{State, Foldable}
import scalaz.std.iterable._

/*
https://stackoverflow.com/questions/20756436/monadic-fold-with-state-monad-in-constant-space-heap-and-stack
*/
object StateFold {
  // Folds in a state monad over a foldable
  def stateFold[F[_],E,S,A](z: A)
                           (xs: F[E])
                           (f: (A, E) => State[S,A])(implicit F: Foldable[F]): State[S,A] =
    State[S,A]((s: S) => F.foldLeft[E,(S,A)](xs, (s, z))((p, x) => f(p._2, x)(p._1)))

  // Sample a lazy collection view
  def sampleS[F[_],A](k: Int)(xs: F[A])(implicit F: Foldable[F]):
    State[Int,Vector[A]] =
      stateFold[F,A,Int,Vector[A]](Vector())(xs)(update(k))

  // update using State monad
  def update[S,A](k: Int): (Vector[A], A) => State[Int,Vector[A]] = {
    (acc: Vector[A], x: A) => State[Int, Vector[A]] {
      n => (n + 1, algorithmR(k, n + 1, acc, x)) // algR same as impure solution
    }
  }

  // The sampling algorithm copied from the question.
  val rand = new scala.util.Random()

  def algorithmR[A](k: Int, n: Int, sample: Vector[A], x: A): Vector[A] = {
    if (sample.size < k) {
      sample :+ x // must keep first k elements
    } else {
      val r = rand.nextInt(n) + 1 // for simplicity, rand is global/stateful
      if (r <= k)
        sample.updated(r - 1, x) // sample is 0-index
      else
        sample
    }
  }

  def main(args: Array[String]): Unit = {
    println(sampleS(10)(Short.MinValue to Short.MaxValue).eval(0))
    println(sampleS(10)(List(1,2,3,4,5,6,7,8)).eval(0))
    println(sampleS(10)(Set[Double](1.1,2,3,4,5,6,7,8)).eval(0))
  }
}