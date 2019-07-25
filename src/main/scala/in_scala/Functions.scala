package in_scala

import io.StdIn._

object DemoFunctions {
	def main(args: Array[String]): Unit = {
		val sqrts= (1 to 10).map(x => sqrt)
		println(sqrts.mkString("[",",","]"))
		sqrts.foreach(x => println(x.toString()))
		val squares = (1 to 10).map(v => square(v))
		println(squares.mkString("[",",","]"))

		println("Function g: " + g(5, 6))
		println("Enter a time with colons between the values of hours, minutes, and seconds")
		val time = readLine
		val (hours, minutes, seconds) = breakDownTime(time)
		val totalSeconds = calcTotalSeconds(hours,minutes,seconds)
		println(s"Total seconds: $totalSeconds")

		val order = readLine
		val size = readLine
		val result = calcConcessionCost(order, size)
		println(s"result = $result")
	}
	def square(x: Double):Double = x * x
	def sqrt = (x: Double) => x * x

	def g(a: Int, b: Int): Int = 3 * a - b

	def breakDownTime(time: String):(Int,Int,Int) = {
		val firstColon = time.indexOf(":")
		val secondColon = time.lastIndexOf(":")
		val hours = time.substring(0,firstColon).toInt
		val minutes = time.substring(firstColon + 1, secondColon).toInt
		val seconds = time.substring(secondColon + 1).toInt
		(hours,minutes,seconds)
	}
	def calcTotalSeconds(hours: Int, minutes: Int, seconds: Int): Int = {
		hours * 3600 + minutes * 60 + seconds
	}

	def calcConcessionCost(order: String, size: String): Double = {
		val res  = if(order == "food"){
			if(size.toLowerCase.startsWith("S")) {
				10.5
			} else if(size.toLowerCase.startsWith("M")) {
				12.5
			} else if(size.toLowerCase.startsWith("L")) {
				14.8
			} else {
				20.2
			}
		}else if(order == "drink"){
			if(size.toLowerCase.startsWith("S")) {
				10.5
			} else if(size.toLowerCase.startsWith("M")) {
					12.5
			} else if(size.toLowerCase.startsWith("L")) {
					14.8
			} else {
				30.5
			}
			}else if(order == "combo"){
				if(size.toLowerCase.startsWith("S")) {
					10.5
				} else if(size.toLowerCase.startsWith("M")) {
					12.5
				} else if(size.toLowerCase.startsWith("L")) {
					14.8
				} else {
					50.5
				}
			}else if(order == "burguer"){
				if(size.toLowerCase.startsWith("S")) {
					10.5
				} else if(size.toLowerCase.startsWith("M")) {
					12.5
				} else if(size.toLowerCase.startsWith("L")) {
					14.8
				} else {
					21.3
				}
			}else{
				30.2
			}
		res
	}

}
