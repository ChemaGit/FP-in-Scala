package using_scala

import io.StdIn._

object Demo {
	def main(args: Array[String]): Unit = {
		println("How old are you?")
		val age = readInt
		//use == to check the object value
		val response = if(age == 21) { // we can put !=, <, <=, >, >=
			"Get lost"
		} else {
			println(s"$age is old enough")
			"Come in"
		}
		println(response)
		//use eq to check object identity
		val s1 = "hi there"
		val s2 = "hi " + "there"
		if(s1 eq s2) println("equal") else println("not equal")

		val s3 = "hi there"
		val s4 = "hi " + readLine
		if(s3 eq s4) println("equal") else println("not equal")
	}
}
