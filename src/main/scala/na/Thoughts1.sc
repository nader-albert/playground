val pf = new PartialFunction[(String, Int), String] {
    def apply(wordFrequency: (String, Int)) = wordFrequency match {
        case (word, freq) if freq > 3 && freq < 25 => word
    }
    def isDefinedAt(wordFrequency: (String, Int)) = wordFrequency match {
        case (word, freq) if freq > 3 && freq < 25 => true
        case _ => false
    }
}

val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
    ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

wordFrequencies.collect(pf)
