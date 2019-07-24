package in_scala
/**Our goal is to write side effects free functions*/
object DemoSideEffects {
	def main(args: Array[String]): Unit = {
		greet("Chema")
	}
	/**Function with side effects*/
	def greet(name: String): Unit = {
		println(s"Hello, $name")
	}
}
