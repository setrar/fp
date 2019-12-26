package chapter2

import org.scalatest._

class TrigonometrySpec extends FlatSpec with Matchers {
  "The TrigonometrySpec" should "not fail" in {
    Trigonometry.sin(23) shouldBe 0.4014257
    Trigonometry.sin(180) shouldBe Trigonometry.round(Math.PI,7)
    Trigonometry.sin(260) shouldBe 4.5378561
    Trigonometry.sin(360) shouldBe Trigonometry.round(Math.PI * 2,7)
    Trigonometry.sin(180 + 360) shouldBe Trigonometry.round(Math.PI * 3,7)
    Trigonometry.sin(90) shouldBe Trigonometry.round(Math.PI / 2,7)
  }

}
