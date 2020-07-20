package hands_on_scala.basic_scala

object FizzBuzz {
  /**
    * The popular FizzBuzz programming challenge in Scala
    * @param args
    */
  def main(args: Array[String]): Unit = {
    for (i <- Range.inclusive(1, 100)) {
      println(
        if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
        else if (i % 3 == 0) "Fizz"
        else if (i % 5 == 0) "Buzz"
        else i
      )
    }
  }
}
