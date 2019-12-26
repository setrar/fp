package africa.valiha.ttt.game

import org.scalatest.FunSpec

import scalaz.{Applicative, State}
import scalaz.State.{get, modify}
import scalaz.std.list._

import scala.language.higherKinds

import africa.valiha.ttt.game.MinMax._


class MinMaxSpec extends FunSpec {

  val board = TicTacToeBoard(3, 3)

  type BoardState[A] = State[TicTacToeBoard,A]

  def simulate(positions: List[Position]): State[TicTacToeBoard, (Boolean, Boolean, Boolean, Set[Position], Set[Position], Set[Position])] = for {
      _ <- Applicative[BoardState].sequence(positions.map(modify[TicTacToeBoard] _ compose TicTacToeBoard.makeMove))
      s <- get
    } yield (s.isGameOver, s.playerOneWin, s.playerTwoWin, s.availablePositions, s.playerOnePositions, s.playerTwoPositions)

  describe("3x3 Board testing") {

    it("should traverse ") {

      val board = TicTacToeBoard(3, 3)

      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2))
      ).exec(board)


      def see: State[TicTacToeBoard, Unit] => State[TicTacToeBoard, Int] = { s =>
        s.run(board)
        for {
          s <- get
          _ <- modify[TicTacToeBoard](miniMax(outcome = 0, cost = 9, strategy = minimize))
          ts <- get
        } yield {
          println(ts)
          ts.availablePositions.size
        }
      }

      //      val m = Traverse[Set[State[TicTacToeBoard, Unit]]].traverseS(s.generateStates)(see)
      //      val l = s.generateStates.toList.traverseS(see).eval(board)
      //      println(l.sequence)
    }

    it("should use foldLeft") {

      type BoardState[A] = State[TicTacToeBoard, A]

      case class Outcome[S](cost: Int, state: BoardState[Unit])

      val init = State[TicTacToeBoard, Unit](s => (s, ()))

      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2))
      ).exec(board)

      def opp(strategy: Criteria): BoardState[Position] = State { s => (s, Position(3, 4)) }

      val m_z: State[TicTacToeBoard, Unit] =
        s.generateStates
          .foldLeft(State[TicTacToeBoard, Unit](s => (s, 0))) { (resultM, currM) =>
            resultM flatMap { _ => currM }
          }
      val dd = m_z.exec(s)
      println(dd)
//      assert(true)

    }

    it("should use bestFinder when game is not over --") {

      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2))
      ).exec(board)

      assert(!s.isGameOver)
      assert(s.isPlayerOneTurn)

      val t: List[BoardState[Unit]] = s.generateStates.toList
      assert(MinMax.foldS_[List,BoardState[Unit],TicTacToeBoard,Int](0)(t)(update).eval(TicTacToeBoard(3,3))==25)

    }
  }

  describe("more 3x3 Board testing") {

    it("should use bestFinder when game is over (testing strictiness out outcome param)") {
      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2),Position(1, 1))
      ).exec(board)

      assert(s.isGameOver)
      assert(!s.isPlayerOneTurn)
      val (_,cost) = MinMax.move(s)

      assert(cost==MinMax.PLAYER_ONE_WIN)

    }

    it("should use bestFinder when game is not over") {
      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(2, 2), Position(1, 2), Position(1, 1), Position(2,1))
      ).exec(board)

      assert(!s.isGameOver)
      assert(!s.isPlayerOneTurn)
      val m = MinMax.move(s)

      println(m)

    }

    it("should generate states") {
      val p = simulate(
        List(Position(1, 3), Position(2, 2), Position(1, 2), Position(1, 1), Position(2,1))
      ).eval(board)

      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(2, 2), Position(1, 2), Position(1, 1), Position(2,1))
      ).exec(board)

      val t = s.generateStates.toList

      val m_z: State[TicTacToeBoard, Unit] =
        t
          .foldLeft(State[TicTacToeBoard, Unit](s => (s, 0))) { (resultM, currM) =>
            println(currM)
            resultM flatMap { _ =>  currM }
          }
      val dd = m_z.exec(s)
      println(dd)
      println(dd.isGameOver)
      println(dd.playerOneWin)
      println(dd.playerTwoWin)
      println(p)
      assert(true)

    }

  }


}
