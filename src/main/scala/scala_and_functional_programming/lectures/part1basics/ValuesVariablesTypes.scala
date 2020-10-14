package scala_and_functional_programming.lectures.part1basics

object ValuesVariablesTypes {
	def main(args: Array[String]): Unit = {
		val x: Int = 42
		println(x)

		// VALS ARE IMMUTABLE
		// COMPILER can infer types
		val y = 56
		println(y)

		val aString: String = "hello"
		val anotherString = "goodbye"

		val aBoolean: Boolean = true
		val aChar: Char = 'a'
		val anInt: Int = x
		val aShort: Short = 4613
		val aLong: Long = 525698745L
		val aFloat: Float = 2.0F
		val aDouble: Double = 3.14

		// variables
		// variables are mutable
		var aVariable: Int = 4
		aVariable = 5  // side effects
	}
}
