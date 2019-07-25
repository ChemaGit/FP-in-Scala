package in_scala

import scala.io.StdIn._

object AbstractingRecursion {
	def main(args: Array[String]): Unit = {
		println("multAndCount. enter quit to exit.")
		println(multAndCount)
		println("****************")
		println("inputAndCount sum: " + inputAndCount(5,(x,v) => x + v))
		println("inputAndCount prod: " + inputAndCount(5,(x,v) => x * v))
		println("inputAndCount: min" + inputAndCount(Integer.MAX_VALUE,(x,v) => x.min(v)))
	}

	def multAndCount(): (Int, Int) = {
		val input = readLine.toLowerCase.trim
		if(input == "quit") (1, 0)
		else {
			val (prod, count) = multAndCount()
			(input.toInt * prod, count + 1)
		}
	}

	def inputAndCount(base: Int,op:(Int,Int) => Int): (Int, Int) = {
		val input = readLine.toLowerCase.trim
		if(input == "quit") (base, 0)
		else {
			val (value, count) = inputAndCount(base, op)
			(op(input.toInt,value),count + 1)
		}
	}
}
