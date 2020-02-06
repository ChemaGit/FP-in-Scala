package scala_and_functional_programming.lectures.part2oop
/**
TAKEWAYS

	- Exceptions crash your program

	- How to throw exception
		- throwing returns Nothing ==> val someValue = throw new RuntimeException

	- How to catch exceptions
		try {
			// compute a value
		} catch {
			case e: RuntimeException => /* another value */
		} finally {
			// block for side effects
		}

	- Define custom exceptions

		class MyKnife extends Exception
	**/
object Exceptions extends App {

	val x: String = null
	// println(x.length) // this will crash with  NullPointerException

	// throwing and catching exceptions

	// 1. throwing exception

       // throw new NullPointerException // this will crash the program

	// val aWeirdValue: String = throw new NullPointerException

	// throwable classes extend the Throwable class.
	// Exception and Error are the major Throwable subtype

	// 2. How to catch exceptions

	def getInt(withExceptions: Boolean): Int = {
		if(withExceptions) throw new RuntimeException("No int for you")
		else 42
	} 

	val potentialFail = try { // AnyVal variable
		// code that might throw
		getInt(true)
	} catch {
		 case c: RuntimeException => println("caught a Runtime exception")
		// case c: NullPointerException => println("caught a Runtime exception") // the program will crash because we don't catch the RuntimeException
	} finally {
		// code that will got executed NO MATTER WHAT
		// optional
		// does not influence the return type of this expression
		println("finally")
	}

	println(potentialFail)

	// 3. How define your own exceptions

	class MyException extends Exception
	val exception = new MyException
	// throw exception

	/*
		1. Crash your program with an OutMemoryError
		2. Crash with StackOverflowError
		3. PocketCalculator
			- add(x, y)
			- substract(x, y)
			- multiply(x, y)
			- divide(x, y)

		Throw
			- OverflowException if add(x, y) exceeds Int.MAX_VALUE
			- UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
			- MathCalculationException for division by 0
	*/

	class OverflowException extends Exception
	class UnderflowException extends Exception
	class MathCalculationException extends RuntimeException("Division by 0")

	def factorial(x: BigInt): BigInt = {
		if(x <= 1) 1
		else x * factorial(x - 1)
	}
	println(factorial(50)) // If the number is big enough the program will crash with a StackOverflow Exception

	// val array = Array.ofDim(Int.MaxValue) // The program will crash with OutOfMemoryError

	def add(x: Int, y: Int): Int = {
		val result = x + y
		if(x > 0 && y > 0 && result < 0) throw new OverflowException
		else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
		else result
	}

	def substract(x: Int, y: Int): Int = {
		val result = x - y
		if(x > 0 && y < 0 && result < 0) throw new OverflowException
		else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
		else result
	}

	def multiply(x: Int, y: Int): Int = {
		val result = x * y
		if(x > 0 && y > 0 && result < 0) throw new OverflowException
		else if(x < 0 && y < 0 && result > 0) throw new OverflowException
		else if(x > 0 && y < 0 && result > 0) throw new UnderflowException
		else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
		else result
	}

	def divide(x: Int, y: Int): Int = {
		if(y == 0) throw new MathCalculationException
		else x / y
	}

	try {
		add(10,8)
		substract(0,15)
		multiply(15,20)
		divide(10, 0)
	} catch {
		case c: OverflowException => println("The add result is too big")
		case x: UnderflowException => println("The substract result is too low")
		case m: MathCalculationException => println("Division by 0 is forbidden")
	} finally {
		println("End of the exercise")
	}

	object PocketCalculator {
		def add(x: Int, y: Int): Int = {
			val result = x + y
			if(x > 0 && y > 0 && result < 0) throw new OverflowException
			else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
			else result
		}

		def substract(x: Int, y: Int): Int = {
			val result = x - y
			if(x > 0 && y < 0 && result < 0) throw new OverflowException
			else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
			else result
		}

		def multiply(x: Int, y: Int): Int = {
			val result = x * y
			if(x > 0 && y > 0 && result < 0) throw new OverflowException
			else if(x < 0 && y < 0 && result > 0) throw new OverflowException
			else if(x > 0 && y < 0 && result > 0) throw new UnderflowException
			else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
			else result
		}

		def divide(x: Int, y: Int): Int = {
			if(y == 0) throw new MathCalculationException
			else x / y
		}
	}

	// println(PocketCalculator.add(Int.MaxValue, 10))
	println(PocketCalculator.divide(10, 0))

}
