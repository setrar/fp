package africa.valiha.ttt.game

import africa.valiha.ttt.game.TicTacToeRules.checkWin

import scalaz.State
import scalaz.State.modify

sealed trait Input
case class Position(row: Int, col: Int) extends Input

trait Board[S <: Board[S]] {
  def isGameOver      : Boolean
  def playerOneWin    : Boolean
  def playerTwoWin    : Boolean
  def isPlayerOneTurn : Boolean
  // generates all possible states
  // which can be produced from
  // the current state of the game
  def generateStates  : Set[State[S, Unit]]
}

case class TicTacToeBoard(playerOnePositions : Set[Position],
                          playerTwoPositions : Set[Position],
                          availablePositions : Set[Position],
                          isPlayerOneTurn : Boolean,
                          winLength : Int) extends Board[TicTacToeBoard] { self =>

  import TicTacToeBoard._

  implicit val state: TicTacToeBoard = self

  lazy val isGameOver: Boolean =
    availablePositions.isEmpty || playerOneWin || playerTwoWin

  lazy val playerOneWin: Boolean = checkWin(playerOnePositions)

  lazy val playerTwoWin: Boolean = checkWin(playerTwoPositions)

  def generateStates: Set[State[TicTacToeBoard, Unit]] =
    availablePositions.map(modify[TicTacToeBoard] _ compose makeMove)

  def generateSimpleStates: Seq[TicTacToeBoard] =
    for(pos <- availablePositions.toSeq) yield makeSimpleMove(pos)


}


object TicTacToeBoard {

  // Just additional constructor for convenience
  def apply(dimension: Int, winLength: Int): TicTacToeBoard = new TicTacToeBoard(
    Set(), Set(), // positions of the players are empty initially
    (for{row <- 1 to dimension // initialize available positions
         col <- 1 to dimension} yield Position(row, col)).toSet,
    true, winLength)

  def makeMove(p: Position): TicTacToeBoard => TicTacToeBoard = (s: TicTacToeBoard) => {
    assert(s.availablePositions.contains(p), s"Already used $p")
    if(s.isPlayerOneTurn)
      new TicTacToeBoard(s.playerOnePositions + p, s.playerTwoPositions, s.availablePositions - p, !s.isPlayerOneTurn, s.winLength)
    else
      new TicTacToeBoard(s.playerOnePositions, s.playerTwoPositions + p, s.availablePositions - p, !s.isPlayerOneTurn, s.winLength)
  }

  def makeSimpleMove(p: Position)(implicit state: TicTacToeBoard): TicTacToeBoard = {
    assert(state.availablePositions.contains(p))
    if(state.isPlayerOneTurn)
      new TicTacToeBoard(
        state.playerOnePositions + p,
        state.playerTwoPositions,
        state.availablePositions - p,
        !state.isPlayerOneTurn,
        state.winLength)
    else new TicTacToeBoard(
      state.playerOnePositions,
      state.playerTwoPositions + p,
      state.availablePositions - p,
      !state.isPlayerOneTurn,
      state.winLength)}


}

