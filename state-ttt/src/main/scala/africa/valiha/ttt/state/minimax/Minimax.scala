
package africa.valiha.ttt.state.minimax

import java.lang.Math.{max, min}

import scalaz.State

// https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
// A simple scala program to find maximum score that
// maximizing player can get.
object Minimax {

  type Board = List[Int]

  def optimal: Board => Int = { scores =>

    // The number of elements in scores must be a power of 2.
    // A utility function to find Log n in base 2
    def log2(n: Int): Int = if (n == 1) 0 else 1 + log2(n / 2)

    // Returns the optimal value a maximizer can obtain.
    // depth is current depth in game tree.
    // nodeIndex is index of current node in scores[].
    // isMax is true if current move is of maximizer, else false
    // scores[] stores leaves of Game tree.
    // h is maximum height of Game tree
    def go(depth: Int, nodeIndex: Int, isMax: Boolean, h: Int): State[Board,Int] = State{ board: Board =>

      // Terminating condition. i.e leaf node is reached
      if (depth == h)
        (board , board(nodeIndex))
      else
        (board,
          if (isMax) {
            // If current move is maximizer, find the maximum attainable value
            val left = go(depth + 1, nodeIndex * 2, isMax = false, h).eval(board)
            val right = go(depth + 1, nodeIndex * 2 + 1, isMax = false, h).eval(board)
            max(left, right)
          } else {
            // Else (If current move is Minimizer), find the minimum attainable value
            val left = go(depth + 1, nodeIndex * 2, isMax = true, h).eval(board)
            val right = go(depth + 1, nodeIndex * 2 + 1, isMax = true, h).eval(board)
            min(left, right)
          }
        )
    }

    val h = log2(scores.length)
    go(0, 0, isMax = true, h).eval(scores)
  }

  // Driver code
  def main(args: Array[String]): Unit = {
    assert(Minimax.optimal(List(3, 5, 2, 9))==3)
    assert(Minimax.optimal(List(3, 5, 2, 9, 12, 5, 23, 23))==12)
  }

}

// This article is contributed by vt_m
