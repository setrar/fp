package africa.valiha.ttt.game

import scalaz.State

trait BestMoveFinder {
  // produces the state, which represents
  // the most optimal move for the given
  // state of the game
  def move: State[TicTacToeBoard, Int]
}

