import scala.util.{Success, Try}
object TryMonad {
  def f(x: Int): Try[Int] = Try { x + 1 }
  def g(x: Int): Try[Int] = Try { x + 2 }
  //  Associativity: (x flatMap f) flatMap g == x flatMap (y => f(y) flatMap g)
  (Try(2) flatMap f).flatMap( g(_) ) ==  Try(2).flatMap(y => f(y) flatMap g )
  // Left unit: unit(x) flatMap f == f(x)
  ( Try{2} flatMap f    ) == f(2)
  // Right unit: m flatMap unit == m
  (   f(2) flatMap( Try(_) ) ) == Success(3)
  for{x <- Try {2}} yield x == 2
}

