
package africa.valiha.ttt.state.minimax

import java.lang.Math.{max, min}

// https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
// A simple scala program to find maximum score that
// maximizing player can get.
object MinimaxOrig {


  // Returns the optimal value a maximizer can obtain.
  // depth is current depth in game tree.
  // nodeIndex is index of current node in scores[].
  // isMax is true if current move is of maximizer, else false
  // scores[] stores leaves of Game tree.
  // h is maximum height of Game tree
  def minimax(depth: Int, nodeIndex: Int, isMax: Boolean, h: Int): Array[Int] => Int = { scores =>

    // Terminating condition. i.e leaf node is reached
    if (depth == h)
      scores(nodeIndex)
    else
      if (isMax)
          // If current move is maximizer, find the maximum attainable value
        max(minimax(depth + 1, nodeIndex * 2, false, h)(scores), minimax(depth + 1, nodeIndex * 2 + 1, false, h)(scores))
      else
          // Else (If current move is Minimizer), find the minimum attainable value
        min(minimax(depth + 1, nodeIndex * 2, true, h)(scores), minimax(depth + 1, nodeIndex * 2 + 1, true, h)(scores))

  }

  // A utility function to find Log n in base 2
  def log2(n: Int): Int = if (n == 1) 0 else 1 + log2(n / 2)

  // Driver code
  def main(args: Array[String]): Unit = { // The number of elements in scores must be
    // a power of 2.
    val scores = Array(3, 5, 2, 9)
    val n = scores.length
    val h = log2(n)
    val res = minimax(0, 0, true, h)(scores)
    println("The optimal value is : " + res)
  }

}

// This article is contributed by vt_m
