package africa.valiha.ttt.state

import scalaz.State

object GenNumber {

  def addOne = State[Int, String] { state =>
    val a = state + 1
    (a, s"Result of addOne is $a")
  }

  def double = State[Int, String] { state =>
    val a = state * 2
    (a, s"Result of double is $a")
  }

  def modTen = State[Int, String] { state =>
    val a = state % 10
    (a, s"Result of modTen is $a")
  }

  def genNumber = for {
    a <- addOne // threads the new state to the next computation
    b <- double // threads the new state to the next computation
    c <- modTen
  } yield c

  def main(args: Array[String]): Unit = {
    val (state, result) = genNumber.run(3)
    assert((state, result)==(8,"Result of modTen is 8"))
  }

}
