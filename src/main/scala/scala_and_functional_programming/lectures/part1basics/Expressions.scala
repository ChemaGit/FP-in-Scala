package scala_and_functional_programming.lectures.part1basics

object Expressions {
	def main(args: Array[String]): Unit = {
		val x = 1 + 2 // EXPRESSION
		println(x)

		println(2 + 3 * 4)
		// + - * / & | ^ << >> >>> (right shift with zero extension)
		
		println(1 == x)
		// == != > >= < <=

		println(!(1 == x))
		// ! && ||

		var aVariable = 2
		aVariable += 3 // also works with -= *= /= .....side effects
		println(aVariable)

		// Instructions (DO) vs Expressions (VALUE)

		// IF expression
		val aCondition = true
		val aConditionValue = if(aCondition) 5 else 3 // IF EXPRESSION
		println(aConditionValue) 
		println(if(aCondition) 5 else 3)

		var i = 0
		while(i < 10) {
			println(i)
			i += 1
		}

		// NEVER WRITE THIS AGAIN.

		// EVERYTHING in Scala is an Expression!

		val aWeirdValue = (aVariable = 3) // Unit === void
		println(aWeirdValue)

		// side effects: println(), whiles, reassigning

		// Code blocks
		val aCodeBlock = {
			val y = 2
			val z = y + 1

			if(z > 2) "hello" else "goodbye"
		}

		// 1. difference between "Hello world" vs println("hello world")?
		// "Hello World" ==> String type
		// println("Hello World") ==> Unit expression
		// 2.

		val someValue = { //Return a Boolean
			2 < 3
		}

		println(someValue)

		val someOtherValue = { // Return an Integer
			if(someValue) 239 else 986
			42
		}
		println(someOtherValue)
	}
}
/*
 TAKEAWAYS
 - Basic expressions: operators

	val x = 3 + 5
	val xIsEven = x % 2 == 0
	val xIsOdd = !xIsEven


 - If in Scala is an expression

	val cond: Boolean = true/false
	val i = if(cond) 42 else 0


 - Code blocks in Scala are expressions
 - The value of the block is the value of its last expression

	val x = {
		val cond: Boolean = true/false
		if(cond) 42 else 0
	}



	** Expressions vs. instructions
		- Instructions are executed(think Java), expressions are evaluated(Scala)
		- In Scala we'll think in terms of expressions
*/
