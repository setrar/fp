package africa.valiha.ttt.game

import sun.util.resources.ar.CurrencyNames_ar_MA

import scalaz.{Foldable, State}
import scalaz.State.{get, gets, init, modify, put}
import scala.language.higherKinds

case class Outcome[S](cost: Int, state: S)

class MinMaxStrategyFinder[S <: TicTacToeBoard] extends BestMoveFinder {

  final val DIMENSION  = 3
  final val X_PLAYER   = "X"
  final val O_PLAYER   = "O"
  final val EMPTY_CELL = "."


  def display(game: TicTacToeBoard) =
    (1 to DIMENSION).map(row =>
      (1 to DIMENSION).map(col => {
        val p = Position(row, col)
        if(game.playerOnePositions contains p) X_PLAYER
        else if(game.playerTwoPositions contains p) O_PLAYER
        else EMPTY_CELL
      }).mkString).mkString("\n") + "\n"


  type BoardState[A] = State[TicTacToeBoard, A]

  def move: State[TicTacToeBoard, Int] = State {
      case s if s.isPlayerOneTurn => firstTurn(s)
      case s => secondTurn(s)
    }

  def secondTurn: BoardState[Int] =
      bestMoveFinder(minimize, firstTurn, PLAYER_ONE_WIN)

    def firstTurn: BoardState[Int] =
      bestMoveFinder(maximize, secondTurn, PLAYER_ONE_LOOSE)

  def bestMoveFinder(strategy: Criteria
                     , opponentMove: => BoardState[Int]
                     , worstOutcome: Int
                    ): State[TicTacToeBoard,Int] = State { t =>
//    println(worstOutcome match {
//      case PLAYER_ONE_WIN => s"isPlayerOneTurn?${t.isPlayerOneTurn} secondTurn for minimize"
//      case PLAYER_ONE_LOOSE => s"isPlayerOneTurn?${t.isPlayerOneTurn} firstTurn for maximize"
//    })

    t match {
      case s if s.isGameOver => outcomeOfGame(s)
      case s =>
//        println(s" size: ${s.generateStates.size}")
//        val take = s.generateStates.size
        val take = 1
        val t: Set[BoardState[Unit]] = s.generateStates.take(take)
//        val t: Seq[TicTacToeBoard] = s.generateSimpleStates
        val g: BoardState[Int] =
          t.foldLeft(State[TicTacToeBoard, Int](s => (s, worstOutcome))) { (acc, currM) =>
            for {
              accOutcome <- acc // retrieve result
//              _ = println(s"acc: $accOutcome")
//              _ <- State((s: TicTacToeBoard) => (currM, accOutcome)) // set current state
              _ <- currM // retrieve result
              currentBoard <- get[TicTacToeBoard] // debugging
//              _ = println(s"curr: $currentBoard")
            } yield {
//                val z = display(currentBoard)
                val (_,opponentOutcome) = opponentMove(currentBoard)
//                println(s"opp: $opponentBoard")
                if (strategy(opponentOutcome, accOutcome)) {
//                  println(s" <<<<< $opponentOutcome, <<<<<\n$z")
                  opponentOutcome
                } else {
//                  println(s" >>>>> $accOutcome, >>>>>\n$z")
                  accOutcome
                }

            }
          }
        (g.exec(s), g.eval(s))
    }
  }

/*
  s.isPlayerOneTurn
  println(worstOutcome match {
    case PLAYER_ONE_WIN => s"isPlayerOneTurn?${s.isPlayerOneTurn} secondTurn for minimize"
    case PLAYER_ONE_LOOSE => s"isPlayerOneTurn?${s.isPlayerOneTurn} firstTurn for maximize"
  })

  if (s.isGameOver) Outcome(outcomeOfGame(s), s)
  else {
    val g: Seq[S] = s.generateStates
    val o: Outcome = g.foldLeft(Outcome(worstOutcome, s)) { (acc, state) =>
      val outcome = opponentMove(state).cost
      if (strategy(outcome, acc.cost)) {
        val z = display(state.asInstanceOf[TicTacToeState])
        println(s" >>>>> $outcome, >>>>>\n$z")
        Outcome(outcome, state)
      }
      else {
        val z = display(acc.state.asInstanceOf[TicTacToeState])
        println(s" <<<<< ${acc.cost}, <<<<<\n$z")
        acc
      }
    }
    val z = display(o.state.asInstanceOf[TicTacToeState])
    println(s" ===== ${o.cost}, =====\n$z")
    o
  }
*/

  val PLAYER_ONE_WIN   =  1 // the outcome, when first player wins
  val PLAYER_ONE_LOOSE = -1 // the outcome, when second player wins
  val DRAW             =  0 // the outcome, when nobody wins

  def outcomeOfGame: BoardState[Int] = State{
    case x if x.playerOneWin => (x,PLAYER_ONE_WIN)
    case x if x.playerTwoWin => (x,PLAYER_ONE_LOOSE)
    case x => (x,DRAW)
  }

  type Criteria = (Int, Int) => Boolean
  def maximize: Criteria = (a, b) => a >= b
  def minimize: Criteria = (a, b) => a <= b

}

object MinMax extends MinMaxStrategyFinder {

  // Folds in a state monad over a foldable
  def foldS_[F[_],E,S,A](z: A)(xs: F[E])(f: (A, E) => State[S,A])
                        (implicit F: Foldable[F]): State[S,A] = State[S,A]{ (s: S) =>
    F.foldLeft[E,(S,A)](xs, (s, z))((p, x) => f(p._2, x)(p._1))
  }

  def miniMax(outcome: Int, cost: Int, strategy: Criteria): TicTacToeBoard => TicTacToeBoard = (s: TicTacToeBoard) => {
    //    val cost = opponentMove(strategy: Criteria)(s).cost
    if (strategy(outcome, cost))
      TicTacToeBoard(s.playerOnePositions, s.playerTwoPositions, s.availablePositions, !s.isPlayerOneTurn, s.winLength)
    else
      TicTacToeBoard(s.playerOnePositions, s.playerTwoPositions, s.availablePositions, !s.isPlayerOneTurn, s.winLength)
  }

  // update using State monad
  def update[S,A]: (Int, A) => BoardState[Int] = (acc: Int, x: A) => State[TicTacToeBoard, Int] { s =>
    for {
      t <- get[TicTacToeBoard]
    } yield
      println(t)

    val cost = 5
    (s, acc + cost)
  }

}

