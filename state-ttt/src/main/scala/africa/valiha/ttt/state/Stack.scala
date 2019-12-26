
package africa.valiha.ttt.state

import scalaz.State

/*
http://eed3si9n.com/herding-cats/State.html
 */
object Stack {

  type Stack = List[Int]

  def pop = State[Stack, Int] {
    case x :: xs => (xs, x)
    case Nil     => sys.error("stack is empty")
  }

  def push(a: Int) = State[Stack, Unit] {
    case xs => (a :: xs, ())
  }

  def stackManip: State[Stack, Int] = for {
    _ <- push(0)  // put dummy value to create the list
    a <- pop // pop the dummy value
    b <- pop // pop the FIFO stack
  } yield b

  def main(args: Array[String]): Unit = {
    assert(stackManip.run(List(5, 8, 2, 1))==(List(8, 2, 1),5))
    assert(stackManip.run(List(5, 8, 2, 1, 4))==(List(8, 2, 1, 4),5))
    assert(stackManip.run(List(4))==(List(),4))
  }

}