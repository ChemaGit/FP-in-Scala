package in_scala

import scala.io.StdIn._

object TryCatch {
	def main(args: Array[String]): Unit = {
		val i = safeReadInt
		println(s"Value of i: $i")
		println("************************************")
		try {
			println("Enter a number: ")
			val num = readInt
			println(s"Num: $num")
		} catch {
			case e:NumberFormatException => println(s"Wrong Number: " + e.getMessage)
		} finally {
			println("See you later")
		}	
	}

	def safeReadInt: Int = {
		try {
			println("Enter a number: ")
			readInt
		} catch {
			case e: NumberFormatException => println("Try again")
			safeReadInt
		} finally {
			println("See you Later....")
		}
	}
}
