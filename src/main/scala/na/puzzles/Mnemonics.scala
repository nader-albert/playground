package na.puzzles

/***
 * Assume you are given a dictionary words as a list of words.
 * Design a method translate such that translate(phoneNumber) produces all phrases of words that can serve as mnemonics for the phone number.
 */
class Mnemonics {

    val mnemonics = Map(
        '2' -> "ABC",
        '3' -> "DEF",
        '4' -> "GHI",
        '5' -> "JKL",
        '6' -> "MNO",
        '7' -> "PQRS",
        '8' -> "TUV",
        '9' -> "WXYZ")

    val words = Seq("nader" , "noha", "rami", "youssef")

    val charCode: Map[Char, Char] = for ((digit, str) <- mnemonics; letter <- str) yield letter -> digit

    /**
     * maps a word to the digit string it can represent e.g. "Java" -> "5883"
     */
    def wordCode(word: String): String = word.toUpperCase map charCode

    /**
     * a map from digit strings to the word that represents them
     * e.g. "5282" -> List("java", "Kata")
     */
    val wordsForNum: Map[String, Seq[String]] = {
        words groupBy wordCode
    }

    def encode(number: String): Set[List[String]] = {
        if(number.isEmpty) Set(List()) else {
            for {
                split <- 1 to number.length
                word  <- wordsForNum(number take split)
                rest  <- encode(number drop split)
            } yield word :: rest
        }.toSet
    }
}

object Mnemonics extends App {
    val mnemonics = new Mnemonics

    mnemonics.encode("727377")
}