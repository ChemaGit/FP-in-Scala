package lectures.part2oop

object AnonymousClasses extends App {

	abstract class Animal {
		def eat: Unit
	}

	// anonymous class
	val funnyAnimal: Animal = new Animal {
		override def eat: Unit = println("ahahahahahahahahah")
	}
	/*
		equivalent with

		class AnonymousClasses$$anon$1 extends Animal {
			override def eat: Unit = println("ahahahahahahahah")
		}
		val funnyAnimal: Animal = new AnonymousClasses$$anon$1
	*/

	println(funnyAnimal.getClass)

	class Person(name: String) {
		def hi: Unit = println(s"Hi, my name is $name, how can I help?")
	}

	val Jim = new Person("Jim") {
		override def hi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
	}

}
/*
TAKEAWAYS

	- We can instantiate types and override fields of methods on the spot

		trait Animal {
			def eat: Unit
		}
		
		val predator = new Animal {
			override def eat: Unit = println("RAWR!")
		}

	- Rules
		- Pass in required constructor arguments if needed
		- Implement all abstract fields/methods

	- WORKS FOR TRAITS AND CLASSES (abstract or not)
*/
