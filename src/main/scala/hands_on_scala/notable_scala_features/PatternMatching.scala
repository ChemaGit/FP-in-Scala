package hands_on_scala.notable_scala_features

object PatternMatching {

  def getDayMonthYear(s: String) = s match {
    case s"$day-$month-$year" => println(s"found day: $day, month: $month, year: $year")
    case _ => println("not a date")
  }

  def main(args: Array[String]): Unit = {
    getDayMonthYear("9-8-1965")
    // found day: 9, month: 8, year: 1965

    getDayMonthYear("9-8")
    // not a date
  }

}
