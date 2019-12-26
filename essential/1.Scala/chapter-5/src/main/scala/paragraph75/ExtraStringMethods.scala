package paragraph75

object ExtraStringMethods {

  implicit class ExtraStringMethods(str: String) {
    val vowels: Seq[Char] = Seq('a', 'e', 'i', 'o', 'u')

    def numberOfVowels: Int = str.toList.count(vowels contains _)
  }

}
