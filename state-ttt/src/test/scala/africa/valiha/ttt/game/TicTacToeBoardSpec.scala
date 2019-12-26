package africa.valiha.ttt.game

import org.scalatest.FunSpec

import scalaz.State.{get, modify}
import scalaz.{Applicative, State}
import scalaz.std.list._

import MinMax._

class TicTacToeBoardSpec extends FunSpec {

  type BoardState[A] = State[TicTacToeBoard, A]

  def simulate(positions: List[Position]): State[TicTacToeBoard,
    (Boolean, Boolean, Boolean, Set[Position], Set[Position], Set[Position])
    ] = for {
    _ <- Applicative[({type f[A] = State[TicTacToeBoard, A]})#f].sequence(positions.map(modify[TicTacToeBoard] _ compose TicTacToeBoard.makeMove))
    s <- get
  } yield (s.isGameOver, s.playerOneWin, s.playerTwoWin, s.availablePositions, s.playerOnePositions, s.playerTwoPositions)

  val board = TicTacToeBoard(3, 3)

  describe("3x3 Board testing") {
    it("should only return 8 available positions") {
      val ((_, _, _, a, p1, p2)) = simulate(List(Position(1, 3))).eval(board)
      assert(a == Set(Position(1, 1), Position(1, 2), Position(2, 1), Position(2, 2), Position(2, 3), Position(3, 1), Position(3, 2), Position(3, 3)))
      assert(p1 == Set(Position(1, 3)))
      assert(p2 == Set())
    }

    it("should only return 7 available positions") {
      val ((_, _, _, a, p1, p2)) = simulate(List(Position(1, 3), Position(3, 3))).eval(board)
      assert(a == Set(Position(1, 1), Position(1, 2), Position(2, 1), Position(2, 2), Position(2, 3), Position(3, 1), Position(3, 2)))
      assert(p1 == Set(Position(1, 3)))
      assert(p2 == Set(Position(3, 3)))
    }

    it("should game be over and Player on wins") {
      val ((isGameOver, p1Win, _, _, _, _)) = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2), Position(1, 1))
      ).eval(board)
      assert(isGameOver && p1Win)
    }

    it("should fail") {
      val thrown = intercept[AssertionError] {
        simulate(List(Position(1, 3), Position(1, 3))).eval(board)
      }
      assert(thrown.getMessage === "assertion failed: Already used")
    }

  }

  describe("Generate Board testing") {
    it("should use foldLeft") {

      case class Outcome[S](cost: Int, state: S)

      val init = State[TicTacToeBoard, Unit](s => (s, ()))

      val s: TicTacToeBoard = simulate(
        List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2))
      ).exec(board)

      val m_z: BoardState[Unit] =
        s.generateStates.foldLeft(init) { (resultM, currM) =>
          println("resultM => %s\n", resultM.exec(s))
          println("currM => %s\n", currM.exec(s))
          resultM flatMap { _ => currM }

        }
      val dd = m_z.eval(s)
            println(dd)

      assert(true)

    }
  }

/*
    describe("Traverse testing") {

      it("should traverse compute") {
        val s = simulate(
          List(Position(1, 3), Position(3, 3), Position(1, 2), Position(3, 2), Position(1, 1))
        ).exec(board)

        import scalaz.Scalaz._

        def minMax: BoardState[Unit] => BoardState[(Int, Int)] =   state => State {
            case s =>
              println(state.exec(board))
              (s, (0, 0))
        }

        val generatedStates: List[BoardState[Unit]] = s.generateStates.toList

        assert(generatedStates.traverse[BoardState,(Int, Int)](minMax).eval(s) == List((3,4), (3,4), (3,4), (3,4)))

      }

    }
*/

}
