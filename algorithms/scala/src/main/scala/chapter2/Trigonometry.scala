package chapter2

import scala.math.BigDecimal.RoundingMode

object Trigonometry {

  def round(dd: Double, round: Int): Double = BigDecimal(dd).setScale(round, RoundingMode.HALF_UP).doubleValue

  def sin(d: Int): Double = round(d.toDouble / 180 * Math.PI,7)

//  def cos(d: Int): Double = round(d.toDouble / 180 / Math.PI,7)
}
