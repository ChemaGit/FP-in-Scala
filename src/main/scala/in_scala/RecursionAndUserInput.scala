package in_scala

import scala.io.StdIn._

object RecursionAndUserInput {
	def main(args: Array[String]): Unit = {
		println("Sum five numbers: " + sum(5))
		println("sumPositive")
		println(sumPositive())
		println("sumUntilQuit")
		println(sumUntilQuit)
		println("sumAndCount")
		println(sumAndCount)
		println("average")
		val (s,c) = sumAndCount()
		val average = s/c.toDouble
		println(s"Average: $average")
	}
	def sum(n: Int): Int = {
		if(n < 1) 0
		else {
			print(s"Input for $n: ")
			val input = readInt
			input + sum(n - 1)
		}
	}	

	def sumPositive(): Int = {
		val input = readInt
		if(input >= 0) input + sumPositive()
		else 0
	}

	def sumUntilQuit(): Int = {
		val input = readLine.toLowerCase.trim
		if(input == "quit") 0
		else input.toInt + sumUntilQuit()
	}

	def sumAndCount():(Int, Int) = {
		val input = readLine.toLowerCase.trim
		if(input == "quit") (0,0)
		else {
			val (sum, count) = sumAndCount()
			(input.toInt + sum, count + 1)
		}
	}
}
