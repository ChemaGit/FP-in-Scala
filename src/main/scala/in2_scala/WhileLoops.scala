package in2_scala

import io.StdIn._

object WhileLoops {
	def main(args: Array[String]): Unit = {
		// while(cond) {
		// body
		//  }

		var i = 0
		while(i < 10) {
			println(i)
			i += 1
		}
		println("*********")

		def readInts: List[Int] = {
			var input = readLine
			var lst = List[Int]()
			while(input != "quit") {
				lst ::= input.toInt
				input = readLine
			}
			lst.reverse
		}
		println(readInts)
	}
}
