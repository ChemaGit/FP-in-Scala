package scala_and_functional_programming.lectures.part2oop
/**
TAKEAWAYS

- Use the same code on many (potentially unrelated)
types: trait List[T] {
 	def add(elem: T)
}

- Generic methods
object List {
	def single[A](element: A): List[A] = ???
}

- Multiple type parameters
trait Map[Key, Value] {
...
}

- Variance: if B extends A, should List[B] extend List[A]?
trait List[+A] yes(covariant)
trait List[A] no(invariant) - default
trait List[-A] hell no!(contravariant)

- Bounded types
class Car
class Supercar extends Car
class Garage[T <: Car](car: T)

- An annoying variance problem that we resolve with bounded types
	*/
object Generics extends App {

	class MyList[A] {
		// use the type A
		def add[B >: A](element: B): MyList[B] = ???
		/*
			A = Cat
			B = Dog = Animal
		*/
	}

	class MyMap[Key, Value]

	val listOfIntegers = new MyList[Int]
	val listOfStrings = new MyList[String]

	// generics methods
	object MyList {
		def empty[A]: MyList[A] = ???
	}

	val emptyListOfIntegers = MyList.empty[Int]

	// variance problem
	class Animal
	class Cat extends Animal
	class Dog extends Animal

	// 1. yes List[Cat] extends List[Animal] = COVARIANCE
	class CovariantList[+A]
	val animal: Animal = new Cat
	val animalList: CovariantList[Animal] = new CovariantList[Cat]
	// animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

	// 2. no = INVARIANCE
	class InvariantList[A]
	val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

	// 3. Hell, no! CONTRAVARIANCE
	class Trainer[-A]
	val trainer: Trainer[Cat] = new Trainer[Animal]

	// bounded types
	class Cage[A <: Animal](animal: A)
	val cage = new Cage(new Dog)

	class Car
	// generic types need proper bounded type
	//val newCage = new Cage(new Car)

	// expand MyList to be generic
}


