package scala_and_functional_programming.lectures.part1basics

object Recursion {

	def factorial(n: Int): Int = {
		if(n <= 1) 1
		else {
			println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
 			val result = n * factorial(n - 1)
			println("Computed factorial of " + n)

			result
		}
	}

	def anotherFactorial(n: Int): BigInt = {
		@annotation.tailrec
		def factHelper(x: Int, accumulator: BigInt): BigInt = {
			if(x <= 1) accumulator
			else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression
		}
		factHelper(n, 1)
	}

	// WHEN YOU NEED LOOPS, USE TAIL RECURSION

	/*
		1. Concatenate a string n times
		2. IsPrime function tail recursive
		3. Fibonacci function, tail recursive
	*/
	def concatenate(aString: String, times: Int): String = {
		@annotation.tailrec
		def loop(n: Int,aStr: String, accum: String): String = {
			if(n == 0) accum
			else loop(n-1,aStr,aStr + accum)
		}
		loop(times,aString,"")
	}

	def isPrime(n: Int): Boolean = {
		@annotation.tailrec
		def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
			if(!isStillPrime) false
			else if(t <= 1) true
			else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)			
		}
		isPrimeTailRec(n / 2, true)
	}

	def fibonacci(n: Int): Int = {
		@annotation.tailrec
		def loop(i: Int, last: Int, nextToLast: Int): Int = {
			if(i >= n) last
			else loop(i + 1, last + nextToLast, last)
		}
		if(n <= 2) 1
		loop(2, 1, 1)
	}

	def main(args: Array[String]): Unit = {
		println(factorial(10))
		// println(factorial(5000)) Error: StackOverflow

		println(anotherFactorial(10))
		println(anotherFactorial(5000))

		println(concatenate("chema",10))

		println(isPrime(37))
		println(isPrime(29))

		println(fibonacci(8))
	}
}
