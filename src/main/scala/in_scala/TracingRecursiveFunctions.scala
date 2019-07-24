package in_scala

import scala.io.StdIn._

object TracingRecursiveFunctions {
	def main(args: Array[String]): Unit = {
		print("Enter a num to calculate factorial: ")
		val num = readLine
		println(s"factorial $num: " + factorial(num.toInt))
	}

	def factorial(n: Int): Int = if(n < 2) 1 else n * factorial(n - 1)

	/**
		* 5 -> 4 -> 3 -> 2 -> 1 ==> 1 * 2 * 3 * 4 * 5
		*/
}
