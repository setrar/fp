
package africa.valiha.ttt.state

import scalaz.State
import scalaz.State.{get, init, modify}

import scalaz.StateT


/*
Grok Haskell Monad Transformers

http://blog.sigfpe.com/2006/05/grok-haskell-monad-transformers.html

In Scala

https://stackoverflow.com/questions/7734756/scalaz-state-monad-examples

 */
object MonadTransformation {

  type StateString[A] = State[String, A]

  def a2b: State[Int,(Int,Int)] = for {
    a <- init[Int]
    _ <- modify[Int]( _ + 1)
    b <- init[Int]
  } yield (a, b)

  def b2c: StateString[(String,String)] = for {
    b <- get[String]
    _ <- modify[String](_ + "1")
    c <- init[String]
  } yield (b, c)

  def a2c: StateT[StateString,Int,(Int,String)] = StateT[StateString, Int, (Int, String)]{ i =>
    val (_, a) = a2b.eval(i)
    for (temp <- b2c; (_,c) = temp) yield (a, (a, c))
  }

  def main(args: Array[String]): Unit = {

    assert(a2b.eval(0)==(0,1))
    assert(b2c.eval("0")==("0","01"))
    assert(a2c.eval(0).eval("0")==(1,"01"))

  }

}

