package scala_and_functional_programming.lectures.part2oop

object OOBasics {
	def main(args: Array[String]): Unit = {
		val person = new Person("Chema", 50)
		println(person.x)
		person.greet("Daniel")	
		person.greet()
	}
}

// val or var variables are class members in the constructor

class Person(name: String, val age: Int = 0) { //constructor
	// body
	val x = 2

	println(1 + 3)

	def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

	// overloading
	def greet(): Unit = println(s"Hi, I am $name")

	// multiple constructors
	def this(name: String) = this(name, 0)
}
// class parameters are NOT FIELDS
