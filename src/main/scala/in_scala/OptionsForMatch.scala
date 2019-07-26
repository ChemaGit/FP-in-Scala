package in_scala

import scala.io.StdIn._
object OptionsForMatch {
	def main(args: Array[String]): Unit = {
		readInt match {
			case 1 => println("It's 1")
			case 2 => println("It's 2")
			case 3 => println("It's 3")
			case n => println(s"Something else $n")
		}	
	}
}
