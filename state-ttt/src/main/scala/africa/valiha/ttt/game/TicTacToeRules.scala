package africa.valiha.ttt.game

trait Rules {

  type StepOffsetGen = (Position, Int) => Position

  def leftDiagonal: StepOffsetGen =
    (pos, offset) => Position(pos.row + offset, pos.col + offset)

  def rightDiagonal: StepOffsetGen =
    (pos, offset) => Position(pos.row - offset, pos.col + offset)

  def row: StepOffsetGen =
    (pos, offset) => Position(pos.row + offset, pos.col)

  def column: StepOffsetGen =
    (pos, offset) => Position(pos.row, pos.col + offset)

  def winConditionsSatisfied(positions: Set[Position], winLength: Int)(step: StepOffsetGen): Boolean =
    positions exists (position =>
      (0 until winLength) forall (offset =>
        positions contains step(position, offset)))
}


object TicTacToeRules extends Rules {

  val directions = List(leftDiagonal, rightDiagonal, row, column)

  def checkWin(positions: Set[Position])(implicit state: TicTacToeBoard): Boolean =
    directions.exists(winConditionsSatisfied(positions, state.winLength))

}

