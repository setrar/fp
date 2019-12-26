
package africa.valiha.ttt.game

object Demo {

  def main(args: Array[String]): Unit = {
    val game = new TicTacToeGame(
      new HumanTicTacPlayer(),
      new MinMaxStrategyFinder[TicTacToeBoard]())

    game.play
  }
}
