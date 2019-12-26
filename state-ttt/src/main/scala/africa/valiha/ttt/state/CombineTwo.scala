package africa.valiha.ttt.state

import scalaz.State

/*
https://stackoverflow.com/questions/32410919/how-to-compose-two-different-state-monad
*/
object CombineTwo {

  type AppendBang[A] = State[Int, A]

  type AddOne[A] = State[String, A]

  def addOne(n: Int): AddOne[Int] = State(s => (s + ".", n + 1))

  def appendBang(str: String): AppendBang[String] = State(s => (s + 1, str + " !!!"))

  def onFirst[A, B, X](s: State[A, X]): State[(A, B), X] = State {
    {
      (a: A, b: B) => {
        val (nextA, x) = s.run(a)
        ((nextA, b), x)
      }
    }.tupled
  }

  def onSecond[A, B, X](s: State[B, X]): State[(A, B), X] = State {
    {
      (a: A, b: B) => {
      val (nextB, x) = s.run(b)
      ((a, nextB), x)
      }
    }.tupled
  }

  def myAction(i: Int) = for {
    x <- onFirst(addOne(i))
    y <- onSecond(appendBang(x.toString))
  } yield y

  def main(args: Array[String]): Unit = {
    assert(myAction(1).run(("", 1))==((".",2),"2 !!!"))
  }

}