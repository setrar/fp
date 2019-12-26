package africa.valiha.ttt.state.evaluate

// Scala program to compute evaluation function for Tic Tac Toe Game.

// Returns a value based on who is winning
// b[3][3] is the Tic-Tac-Toe board
object Evaluate {

  def value: Char => Int = {
    case 'x' => 10
    case 'o' => -10
    case _ => 0
  }

  def evaluate(b: Array[Array[Char]]): Int = {

    // Checking for Rows for X or O victory.
    val rows: Seq[Int] =
    for (row <- b.indices) yield {
      if (b(row)(0)==b(row)(1) && b(row)(1)==b(row)(2)) value(b(row)(0)) else 0
    }

    // Checking for Columns for X or O victory.
    val cols: Seq[Int] =
    for (col <- b(0).indices) yield {
      if (b(0)(col)==b(1)(col) && b(1)(col)==b(2)(col)) value(b(0)(col)) else 0
    }

    // Checking for Diagonals for X or O victory.
    val (down, up): (Int, Int) = (
        if (b(0)(0)==b(1)(1) && b(1)(1)==b(2)(2)) value(b(0)(0)) else 0
      , if (b(0)(2)==b(1)(1) && b(1)(1)==b(2)(0)) value(b(0)(2)) else 0
    )

    // Else if none of them have won then return 0
    rows.sum + cols.sum + down + up
  }

  // Driver code
 def main(args: Array[String]): Unit = {

   assert(evaluate(
      Array(
         Array('x', ' ', 'o')
        ,Array(' ', 'x', 'o')
        ,Array(' ', ' ', 'x')
      ))==10)

   assert(evaluate(
     Array(
        Array('x', ' ', 'o')
       ,Array(' ', 'o', 'x')
       ,Array('o', ' ', 'x')
     ))==(-10))

   assert(evaluate(
     Array(
       Array('o', ' ', 'o')
       ,Array('x', 'x', 'x')
       ,Array(' ', ' ', 'o')
     ))==10)

   assert(evaluate(
     Array(
        Array('o', ' ', 'o')
       ,Array('x', ' ', 'x')
       ,Array(' ', 'o', 'o')
     ))==0)

   assert(evaluate(
     Array(
        Array('x', ' ', 'o')
       ,Array('x', 'o', 'x')
       ,Array('x', 'o', 'o')
     ))==10)
  }

}
