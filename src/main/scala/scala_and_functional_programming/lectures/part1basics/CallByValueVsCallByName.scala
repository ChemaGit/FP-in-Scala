package scala_and_functional_programming.lectures.part1basics
/** TAKEAWAYS
  Call by value:
    value is computed before call
    same value used everywhere

  Call by name:
    expression is passed literally
    expression is evaluated at every use within
*/
object CallByValueVsCallByName {

	def calledByValue(x: Long): Unit = {
		println("by value: " + x)
		println("by value: " + x)
	}

	def calledByName(x: => Long): Unit = {
		println("by name: " + x)
		println("by name: " + x)
	}

	def infinite(): Int = 1 + infinite()
	def printFirst(x: Int, y: => Int) = println(x)

	def main(args: Array[String]): Unit = {
		calledByValue(System.nanoTime())
		// by value: 32509348498157
		// by value: 32509348498157

		calledByName(System.nanoTime())
		// by name: 32509560877589
		// by name: 32509560945259

		// printFirst(infinite(), 34) StackOverflow
		printFirst(34, infinite())


	}
}
