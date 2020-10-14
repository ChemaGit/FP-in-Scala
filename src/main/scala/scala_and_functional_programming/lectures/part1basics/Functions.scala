package scala_and_functional_programming.lectures.part1basics

object Functions {

	def aFunction(a: String, b: Int): String = {
		a + " " + b
	}

	def aParameterlessFunction(): Int = 42

	def aRepeteatedFunction(aString: String, n: Int): String = {
		if(n == 1) aString
		else aString + aRepeteatedFunction(aString, n - 1)
	}

	def aFunctionWithSideEffects(aString: String): Unit = println(aString)

	def aBigFunction(n: Int): Int = {
		def aSmallerFunction(a: Int, b: Int): Int = a + b

		aSmallerFunction(n, n - 1)
	}

	def greeting(name: String, age: Int): String = {
		s"Hi, my name is $name and I am $age years old."
	}

	def factorial(n: Int): Int = {
		if(n == 1) 1
		else n * factorial(n - 1)
	}

	def fibonacci(n: Int): Int = {
		if(n == 1) 1
		else if(n == 2) 1
		else fibonacci(n - 1) + fibonacci(n - 2)
	}

	def isPrime(n: Int): Boolean = {
		def isPrimeUntil(t: Int): Boolean = {
			if(t <= 1) true
			else n % t != 0 && isPrimeUntil(t - 1)
		}
		isPrimeUntil(n / 2)
	}

	def main(args: Array[String]): Unit = {
		println(aFunction("Hello", 3))
		println(aParameterlessFunction())
		println(aParameterlessFunction)

		println(aRepeteatedFunction("hello", 3))

		// WHEN YOU NEED LOOPS, USE RECURSION

		aFunctionWithSideEffects("Hello again")

		/*
		1. A greeting function (name, age) => "Hi, my name is $ name and I am $ age years old."
		*/
		println(greeting("Chema", 50))

		/*
		2. factorial function 1 * 2 * 3 * .. * n
		*/
		println(s"Factorial of 6 is:  ${factorial(6)}")

		/*
		3. A Fibonacci function
			f(1) = 1
			f(2) = 1
			f(n) = f(n - 1) + f(n - 2)
		*/
		println(s"Fibonacci of 7 is: ${fibonacci(7)}")
		/*
		4. Tests if a number is prime
		*/
		println(s"Is prime 37: ${isPrime(37)}")
		println(s"Is prime 37: ${isPrime(2003)}")
		println(s"Is prime 37: ${isPrime(37 * 17)}")
	}
}
