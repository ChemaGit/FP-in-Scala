package in_scala

import scala.io.StdIn._

object RecursionAndUserInput {
	def main(args: Array[String]): Unit = {
		println(sum(5))

		println(sumPositive)
	}
	def sum(n: Int): Int = {
		if(n < 1) 0
		else {
			val input = readInt
			input.toInt + sum(n - 1)
		}
	}	

	def sumPositive(): Int = {
		val input = readInt
		if(input >= 0) input + sumPositive()
		else 0
	}
}
