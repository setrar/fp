package africa.valiha.ttt.game

import scalaz.State
import scalaz.Scalaz._
import scala.io.StdIn.readLine

class HumanTicTacPlayer extends BestMoveFinder {
  def move: State[TicTacToeBoard, Int] = {
    println("Input the row and the column, please:")
    val s = readLine("{0, number} {1,number}\n")
    val r = s.split(" ")
    try {
      val pos = Position(r(0).toInt, r(1).toInt)
      for {
        _ <- List(pos).map(modify[TicTacToeBoard] _ compose TicTacToeBoard.makeMove).head
      } yield 0
    } catch {
      case _: Throwable =>
        println("Such move is impossible!")
        move
    }
  }
}

