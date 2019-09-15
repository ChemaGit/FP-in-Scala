package scala_and_functional_programming.lectures.part2oop

object OOBasics {
	def main(args: Array[String]): Unit = {
		val person = new Person("Chema", 50)
		println(person.x)
		person.greet("Daniel")	
		person.greet()

		println("**********************************")
		println("**********************************")

		val author = new Writer("Charles","Dickens", 1812 )
		val imposter = new Writer("Charles","Dickens", 1812)
		val novel = new Novel("Great Expectations", 1861, author)

		println(novel.authorAge)
		println(novel.isWrittenBy(author))
		println(novel.isWrittenBy(imposter))

		println("**********************")
		println("**********************")

		val counter = new Counter
		counter.increment.print
		counter.increment.increment.increment.print
		counter.increment(10).print
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
	def this() = this("John Doe")
}
// class parameters are NOT FIELDS

/*
	Novel and a Writer

	Writer: first name, surname, year
	- method fullname

	Novel: name, year of release, author
	- authorAge
	- isWrittenBy(author)
	- copy(new year of realase) = new instance of Novel
*/
class Novel(name: String, year: Int, author: Writer) {
	def authorAge = year - author.year
	def isWrittenBy(author: Writer) = author == this.author
	def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Writer(firstName: String, lastName: String, val year: Int) {
	def fullName: String = 	s"$firstName $lastName"
	
}
/*
	Counter class
	- receives an int value
	- method current count
	- method to increment/decrement => new Counter
	- overload inc/dec to receive an amount
*/
class Counter(val value: Int = 0) {
	def current() = value
	def increment = {
		println("incrementing")
		new Counter(value + 1) // immutability
	}
	def decrement = {
		println("decrementing")
		new Counter(value - 1) // immutability
	}
	def increment(amount: Int) = new Counter(value + amount)
	def decrement(amount: Int) = new Counter(value - amount)

	def print = println(current)
}

/*
	Takeavays
		- Defining classes => class Person(name: String, age: Int)
		- Instantiating => val bob => new Person("Bob", 25)
		- Parameters vs fields  => class Person(val name: String, age: Int)
		- Defining methods => def greet(): String = {....}
		- Calling methods val bobSaysHi = bob.greet
			- syntax allowed for parameter-less methods
		- The keyword this
*/
