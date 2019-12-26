package chapter2


object ComplexNumber {

  case class Complex(re: Double = 0.0, im: Double = 0.0) {
    override def toString: String = re + (if (im < 0) " - " else " + ") + s"${Math.abs(im)%10.0d}" + "i"
  }

}
