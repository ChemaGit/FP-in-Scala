package in_scala

import scala.io.StdIn._

object RecursiveFizzBuzz {
	def main(args: Array[String]): Unit = {
		println("Enter an Integer: ")
		val num = readInt
		println()
		fizzBuzz(num)
	}

	def fizzBuzz(i: Int): Unit = {
		if(i <= 100) {
			(i % 3, i % 5) match {
				case(0,0) => println("fizz buzz")
				case(0,_) => println("fizz")
				case(_,0) => println("buzz")
				case (_, _) => println(i)
			}
			fizzBuzz(i + 1)
		}
	}
}
