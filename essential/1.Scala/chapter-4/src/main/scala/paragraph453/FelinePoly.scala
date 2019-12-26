package paragraph453

/**
  * structural recursion using polymorphism example
  */
sealed trait FelineP {
  def dinner: Food
}

final case class LionP() extends FelineP {
  def dinner: Food = Antelope
}

final case class TigerP() extends FelineP {
  def dinner: Food = TigerFood
}

final case class PantherP() extends FelineP {
  def dinner: Food = Licorice
}

final case class CatP(favouriteFood: String) extends FelineP {
  def dinner: Food = CatFood(favouriteFood)
}
