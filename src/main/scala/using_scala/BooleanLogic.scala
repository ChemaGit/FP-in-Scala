package using_scala

import io.StdIn._

object Demo2 {
	// && - and
	// || - inclusive or/logical or
	// ^ - exclusive or
	// ! - not
	def main(args: Array[String]): Unit = {
		println("How old are you?")
		val age = readInt
		val cost = if(age < 13 || age >= 55) 8 else 12
		//the same thing would be
		val cost2 = if(age >= 13 && age < 55) 12 else 8
		println(s"cost = $cost -- cost2 = $cost2")

		val n = readInt
		val d = readInt
		val qualify = d > 0 && n / d > 10
		println(s"qualify: $qualify")
	}
}
