package africa.valiha.ttt.state.move

import africa.valiha.ttt.state.evaluate.Evaluate
import Math.{min,max}

object FindBestMove {

  case class Move (row: Int, col: Int)

  case class BestMove(move: Move, cost: Int)


  //  trait Player {def value: Char}
//  case class Human {def value: Char = 'X'} extends Player(value)
//  case object Opponent /*= 'o'*/ extends Player


  // This function returns true if there are moves
  // remaining on the board. It returns false if
  // there are no moves left to play.
  def isMovesLeft(board: Array[Array[Char]]): Boolean = {
    for {
      row <- board.indices
      col <- board(row).indices
    } yield
      if (board(row)(col) == '_') true else false
  }.contains(true)

  // This is the minimax function. It considers all
  // the possible ways the game can go and returns
  // the value of the board
  def minimax(board: Array[Array[Char]], depth: Int, isMax: Boolean): Int = {

    val score: Int = Evaluate.evaluate(board)

    score match {

      // If Maximizer or Minimizer has won the game return his/her
      // evaluated score
      case x if x == 10 || x == -10 => x

      // If there are no more moves and no winner then
      // it is a tie
      case x if !(x == 10 || x == -10) && !isMovesLeft(board) => 0

      case _ =>
        // If this maximizer's move
        if (isMax) {
          var best: Int = -1000 // TODO mutable

          // Traverse all cells
          for {
            row <- board.indices
            col <- board(row).indices
          } yield
            // Check if cell is empty
            if (board(row)(col) == '_') {
              // Make the move
              board(row)(col) = 'x' // player

              // Call minimax recursively and choose
              // the maximum value
              best = max(best, minimax(board, depth + 1, !isMax))

              // Undo the move
              board(row)(col) = '_'
            }
          best
        }

        // If this minimizer's move
        else {
          var best: Int = 1000

          // Traverse all cells
          for {
            row <- board.indices
            col <- board(row).indices
          } yield
            // Check if cell is empty
            if (board(row)(col) == '_') {
              // Make the move
              board(row)(col) = 'o' // opponent

              // Call minimax recursively and choose
              // the minimum value
              best = min(best, minimax(board, depth + 1, !isMax))

              // Undo the move
              board(row)(col) = '_'
            }
          best
        }
    }
  }

  // This will return the best possible move for the player
   def findBestMove(board: Array[Array[Char]]): BestMove = {

     val t: Seq[BestMove] = for {
       row <- board.indices
       col <- board(row).indices
     } yield BestMove(Move(row,col),-1000)

      t.foldLeft(BestMove(Move(0,0),-1000)){ (bestMove: BestMove, m: BestMove) =>
       // Check if cell is empty
       if (board(m.move.row)(m.move.col) == '_') {
         // Make the move
         board(m.move.row)(m.move.col) = 'x' // player

         // compute evaluation function for this move.
         val moveVal: Int = minimax(board, 0, isMax = false)

         // Undo the move
         board(m.move.row)(m.move.col) = '_'

         // If the value of the current move is
         // more than the best value, then update
         // best/
         if (moveVal > bestMove.cost) {
           printf("The value of the best Move is : %d\n\n", moveVal)
           BestMove(Move(m.move.row, m.move.col),moveVal)
         } else bestMove
       } else bestMove
     }
   }

  // Driver code
  def main (args: Array[String] ): Unit = {

    assert(findBestMove(
      Array(
        Array('x', 'o', 'x')
        , Array('o', 'o', 'x')
        , Array('_', '_', '_')
        )
      ) == BestMove(Move(2, 2),10)
    )

    assert(findBestMove(
      Array(
        Array('x', 'o', 'o')
        , Array('o', 'x', 'x')
        , Array('_', '_', '_')
        )
      ) == BestMove(Move(2, 2),10)
    )

  }

}

