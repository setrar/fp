package africa.valiha.ttt.state

import scalaz.State
import scalaz.Scalaz._

case class LeftOver(size: Int)

/*
http://www.smartjava.org/content/scalaz-features-everyday-usage-part-3-state-monad-writer-monad-and-lenses#the-state-monad
 */
object LeftOver {
  /** A state transition, representing a function `S => (S, A)`. */
  type Result[A] = State[LeftOver, A]

  def getFromState(a: Int): Result[Int] =
    // do all kinds of computations
    State[LeftOver, Int] {
      // just return the amount of stuff we got from the state
      // and return the new state
      x => (LeftOver(x.size - a), a)
    }

  def addToState(a: Int): Result[Int] =
    // do all kinds of computations
    State[LeftOver, Int] {
      // just return the amount of stuff we added to the state
      // and return the new state
      x => (LeftOver(x.size + a), a)
    }

  def main(args: Array[String]): Unit = {

    val res: Result[Int] = for {
      _ <- addToState(20)
      _ <- getFromState(5)
      _ <- getFromState(5)
      _ <- getFromState(5)
      currentState <- get[LeftOver] // get the state at this moment
      _ <- put[LeftOver](LeftOver(9000)) // set the state to some new value
      b <- getFromState(10) // and continue with the new state
    } yield {
      println(s"currenState: $currentState")
      b
    }
    // we start with state 10, and after processing we're left with 5
    // without having to pass state around using implicits or something else
    assert(res(LeftOver(10))==(LeftOver(8990),10))
  }
}