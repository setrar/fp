package africa.valiha.ttt.game

class TicTacToeGame(playerOne: BestMoveFinder,
                    playerTwo: BestMoveFinder) {

  def play = {
    @annotation.tailrec
    def go(players: List[BestMoveFinder], state: TicTacToeBoard, moveNumber: Int): Unit = {
      if (state.isGameOver) {
        if(state.playerOneWin) println("Player One win!")
        else if(state.playerTwoWin) println("Player Two win!")
        else println("Draw!")
      } else {
        println(s"Player ${moveNumber % 2 + 1} makes move:")
        val (nextState, _) =    players.head.move(state)
        println(display(nextState))
        go(players.tail :+  players.head, nextState, moveNumber + 1)
      }
    }
    go(List(playerOne, playerTwo), TicTacToeBoard(DIMENSION, DIMENSION), 0)
  }

  def display(game: TicTacToeBoard) =
    (1 to DIMENSION).map(row =>
      (1 to DIMENSION).map(col => {
        val p = Position(row, col)
        if(game.playerOnePositions contains p) X_PLAYER
        else if(game.playerTwoPositions contains p) O_PLAYER
        else EMPTY_CELL
      }).mkString).mkString("\n") + "\n"

  final val DIMENSION  = 3
  final val X_PLAYER   = "X"
  final val O_PLAYER   = "O"
  final val EMPTY_CELL = "."
}
