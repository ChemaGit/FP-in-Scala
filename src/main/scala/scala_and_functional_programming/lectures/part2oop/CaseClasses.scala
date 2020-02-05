package scala_and_functional_programming.lectures.part2oop
/**
TAKEAWAYS

		Quick lightweight data structures with little boilerplate

		case class Person(name: String, age: Int)

		Companions already implemented ==> val bob = Person("Bob", 26)

		Sensible equals, hashCode, toString

		Auto-promoted params to fields ==> bob.name

		Cloning, Serializable and Extractable in PATTER MATCHING

		And we have also: case objects
	*/

object CaseClasses extends App {

	/*
		equals, hashCode, toString

	*/

	case class Person(name: String, age: Int)

	// 1. class parameters are fields
	val jim = new Person("Jim",34)
	println(jim.name)

	// 2. sensible toString
	// println(instance) = println(instance.toString) // syntactic sugar
	println(jim)
	println(jim.name)

	// 3. equals and hashCode implemented out of the box
	val jim2 = new Person("Jim", 34)
	println(jim == jim2) // it's true

	// 4. case class have handy copy method
	val jim3 = jim.copy()
	val jim4 = jim.copy(age = 45)
	println(jim3)
	println(jim4)

	// 5. case classes has compaion objects
	val thePerson = Person
	val mary = Person("Mary", 23) //Factory of objects

	// 6. case classes are serializable
	// Akka

	// 7. case classes have extractor patterns = case classes can used in PATTERN MATCHING

	case object UnitedKingdom {
		def name: String = "the UK of GB and NI"
	}

	/*
		Expand MyList - use case classes and case objects.
	*/
}


