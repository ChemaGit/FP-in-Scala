package scala_and_functional_programming.exercises

abstract class MyListCaseClass[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */
  def head: A
  def tail: MyListCaseClass[A]
  def isEmpty: Boolean
  def add[B >: A](v: B): MyListCaseClass[B]
  def printElements: String
  // polymorphic call
  override def toString: String = s"[ $printElements ]"

  def map[B](transfomer: MyTransformerB[A, B]): MyListCaseClass[B]
  def flatMap[B](transformer: MyTransformerB[A, MyListCaseClass[B]]): MyListCaseClass[B]
  def filter(predicate: MyPredicateB[A]): MyListCaseClass[A]

  // concatenation
  def ++[B >: A](list: MyListCaseClass[B]): MyListCaseClass[B]
}

case object EmptyCaseClass extends MyListCaseClass[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListCaseClass[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](v: B): MyListCaseClass[B] = new ConsCaseClass(v, EmptyCaseClass)
  def printElements: String = "[]"

  def map[B](transfomer: MyTransformerB[Nothing, B]): MyListCaseClass[B] = EmptyCaseClass
  def flatMap[B](transfomer: MyTransformerB[Nothing, MyListCaseClass[B]]): MyListCaseClass[B] = EmptyCaseClass
  def filter(predicate: MyPredicateB[Nothing]): MyListCaseClass[Nothing] = EmptyCaseClass

  def ++[B >: Nothing](list: MyListCaseClass[B]): MyListCaseClass[B] = list
}

case class ConsCaseClass[+A](h: A, t: MyListCaseClass[A]) extends MyListCaseClass[A] {
  def head: A = h
  def tail: MyListCaseClass[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](v: B): MyListCaseClass[B] = new ConsCaseClass(v, this)
  def printElements: String =
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  /*
	[1,2,3].map(n * 2) = new ConsGB(2, [2,3].map(n*2)
			   = new ConsGB(2, new ConsGB(4, [3].map(n * 2)))
			   = new ConsGB(2, new ConsGB(4, new ConsGB(6, EmptyGB.map(n * 2))))
			   = new ConsGB(2, new ConsGB(4, new ConsGB(6, EmptyGB)))
  */
  def map[B](transformer: MyTransformerB[A, B]): MyListCaseClass[B] = {
	  new ConsCaseClass(transformer.transform(h), t.map(transformer))
  }

  /*
	[1,2].flatMap(n => [n, n+1])
	= [1,2] ++ [2].flatMap(n => [n, n+1])
	= [1,2] ++ [2,3] ++ EmptyGB.flatMap(n => [n, n+1])
	= [1,2] ++ [2,3] ++ EmptyGB
	= [1,2,2,3]
  */
  def flatMap[B](transformer: MyTransformerB[A, MyListCaseClass[B]]): MyListCaseClass[B] = {
	  transformer.transform(h) ++ t.flatMap(transformer)
  }


	/*
		[1,2,3].filter(n % 2 == 0) =
		[2,3].filter(n % 2 == 0) =
		= new ConsGB(2, [3].filter(n % 2 == 0))
		= new ConsGB(2, Empty.filter(n % 2 == 0))
		= new ConsGB(2, Empty)
	*/
  def filter(predicate: MyPredicateB[A]): MyListCaseClass[A] = {
	if(predicate.test(h)) new ConsCaseClass(h, t.filter(predicate))
	else t.filter(predicate)
  }

  /*
	[1,2] ++ [3,4,5]
	= new ConsGB(1, [2] ++ [3,4,5])
	= new ConsGB(1,[2, EmptyGB ++ [3,4,5]])
	= new ConsGB(1, new ConsGB(2, new ConsGB(3, new ConsGB(4, new ConsGB(5)))))
  */
  def ++[B >: A](list: MyListCaseClass[B]): MyListCaseClass[B] = new ConsCaseClass[B](h, t ++ list)
}

/*
	1. Generic trait MyPredicate[-T] with a little method test[T] => Boolean
	2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
	3. MyGenericListB:
		- map(transformer) => MyGenericListB
		- filter(predicate) => MyGenericListB
		- flatMap(transformer from A to MyGenericListB[B] => MyGenericListB[B])

		class EvenPredicate extends MyPredicate[Int]
		class StringToIntTransformer extends MyTransformer[String, Int]

		[1,2,3].map(n * 2) = [2,4,6]
		[1,2,3,4].filter(n % 2) = [2,4]
		[1,2,3].flatMap(n => [n,n+1]) => [1,2,2,3,3,4]
*/

trait MyPredicateB[-T] {
	def test(elem: T): Boolean
}

trait MyTransformerB[-A, B] {
	def transform(elem: A): B
}

object MyListCaseClass extends App {
  val list = new ConsCaseClass(1, EmptyCaseClass)
  println(list.head)

  val list2 = new ConsCaseClass(1, new ConsCaseClass(2, new ConsCaseClass(3, EmptyCaseClass)))
  println(list2.tail.head)
  println(list2.add(4).head)
  println(list2.isEmpty)

  println(list2.toString)

  val listOfIntegers: MyListCaseClass[Int] = new ConsCaseClass(1, new ConsCaseClass(2, new ConsCaseClass(3, EmptyCaseClass)))
  val cloneListOfIntegers: MyListCaseClass[Int] = new ConsCaseClass(1, new ConsCaseClass(2, new ConsCaseClass(3, EmptyCaseClass)))
  val listOfStrings: MyListCaseClass[String] = new ConsCaseClass("Hello", new ConsCaseClass("Scala", EmptyCaseClass))
  val anotherListOfIntegers: MyListCaseClass[Int] = new ConsCaseClass(4, new ConsCaseClass(5, EmptyCaseClass))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformerB[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicateB[Int]{
	  override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfIntegers ++ anotherListOfIntegers).toString 

  println(listOfIntegers.flatMap(new MyTransformerB[Int, MyListCaseClass[Int]]{
	  override def transform(elem: Int): MyListCaseClass[Int] = new ConsCaseClass(elem, new ConsCaseClass(elem + 1, EmptyCaseClass))
  }).toString)

	println(cloneListOfIntegers == listOfIntegers) // true -> because are case classes
}

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

/*
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

/*
TAKEAWAYS
Scala offers class-based inheritance
	- access modifiers: private, protected, default(none = public)
	- need to pass in constructor arguments to parent class

class Animal
class Cat extends Animal

Derived classes can override members or methods

Reuse parent fields/methods with super

Prevent inheritance with final and sealed

abstract classes

traits

Inheriting from a class and multiple traits
*/


