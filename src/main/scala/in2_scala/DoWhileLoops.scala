package in2_scala

import io.StdIn._

object DoWhileLoops {

	def calcAverage(tests: List[Int], assns: List[Int]): Double = {
		0.5 * tests.sum / tests.length + 0.5 * assns.sum / assns.length
	}

	def main(args: Array[String]): Unit = {
		// do {
		// body
		// } while(cond)

		def printMenu: Unit = {
			println("""
			1. Enter a test grade.
			2. Enter an assigment grade.
			3. Print average.
			4. Quit.
			""")
		}
		var testGrades = List[Int]()
		var assnGrades = List[Int]()
		var input = 0
		do {
			printMenu
			input = readInt
			input match {
				case 1 => println("Enter a grade.")
					  testGrades ::= readInt
				case 2 => println("Enter an assigment grade.")
                                          assnGrades ::= readInt
				case 3 => println(s"The average is ${calcAverage(testGrades, assnGrades)}.")
				case 4 => 
				case _ => println("What was that?")
			}
		}while(input != 4)
	}
}
