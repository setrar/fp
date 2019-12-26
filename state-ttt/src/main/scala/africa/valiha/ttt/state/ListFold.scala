package africa.valiha.ttt.state

import scalaz.State
import scalaz._
import Scalaz._

sealed trait InputMsg {
  def id: Int
}
case class Started(id: Int) extends InputMsg
case class Stopped(id: Int) extends InputMsg

sealed trait OutputMsg
case class InProgress(id: Int) extends OutputMsg
case class Finished(id: Int) extends OutputMsg

object ListFold {

  type Stack = List[InputMsg]

  def pop(pivot: InputMsg) = State[Stack, InputMsg] { state: Stack =>
    val (pair, rest) = state.partition(_.id == pivot.id)
    (rest, pair.head)
  }

  def push(pivot: InputMsg) = State[Stack, Unit] {
    case xs => (pivot :: xs, ())
  }

  def handler(input: InputMsg): State[Stack, Option[OutputMsg]] =
    input match {
      case e@Started(_) => for {
        _ <- push(e)
      } yield None
      case e@Stopped(i) => for {
        _ <- pop(e)
      } yield Some(Finished(i))
    }

  def smash(input: List[InputMsg]): List[OutputMsg] = {
    val initial = List[InputMsg]()
    val (started, finished) = input.runTraverseS(initial)(handler)

    finished.flatMap {
      case Some(x) => List(x)
      case None => Nil
    } ++
      started.map {
        case Started(i) => InProgress(i)
      }
  }

  def main(args: Array[String]): Unit = {
    assert(smash(List(Started(1), Started(2), Stopped(1)))==List(Finished(1), InProgress(2)))
  }
}
