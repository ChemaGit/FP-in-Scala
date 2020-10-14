package hands_on_scala.basic_scala

object FizzBuzz {
  /**
    * The popular FizzBuzz programming challenge in Scala
    *
    * Write a short program that prints each number from 1 to 100 on a new line.
    * For each multiple of 3, print "Fizz" instead of the number.
    * For each multiple of 5, print "Buzz" instead of the number.
    * For numbers which are multiples of both 3 and 5, print "FizzBuzz" instead of the number.
    *
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
