package scala_and_functional_programming.exercises

abstract class MyListGenericB[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */
  def head: A
  def tail: MyListGenericB[A]
  def isEmpty: Boolean
  def add[B >: A](v: B): MyListGenericB[B]
  def printElements: String
  // polymorphic call
  override def toString: String = s"[ $printElements ]"

  def map[B](transfomer: MyTransformer[A, B]): MyListGenericB[B]
  def flatMap[B](transformer: MyTransformer[A, MyListGenericB[B]]): MyListGenericB[B]
  def filter(predicate: MyPredicate[A]): MyListGenericB[A]

  // concatenation
  def ++[B >: A](list: MyListGenericB[B]): MyListGenericB[B]
}

object EmptyGB extends MyListGenericB[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGenericB[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](v: B): MyListGenericB[B] = new ConsGB(v, EmptyGB)
  def printElements: String = "[]"

  def map[B](transfomer: MyTransformer[Nothing, B]): MyListGenericB[B] = EmptyGB
  def flatMap[B](transfomer: MyTransformer[Nothing, MyListGenericB[B]]): MyListGenericB[B] = EmptyGB
  def filter(predicate: MyPredicate[Nothing]): MyListGenericB[Nothing] = EmptyGB

  def ++[B >: Nothing](list: MyListGenericB[B]): MyListGenericB[B] = list
}

class ConsGB[+A](h: A, t: MyListGenericB[A]) extends MyListGenericB[A] {
  def head: A = h
  def tail: MyListGenericB[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](v: B): MyListGenericB[B] = new ConsGB(v, this)
  def printElements: String =
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  /*
	[1,2,3].map(n * 2) = new ConsGB(2, [2,3].map(n*2)
			   = new ConsGB(2, new ConsGB(4, [3].map(n * 2)))
			   = new ConsGB(2, new ConsGB(4, new ConsGB(6, EmptyGB.map(n * 2))))
			   = new ConsGB(2, new ConsGB(4, new ConsGB(6, EmptyGB)))
  */
  def map[B](transformer: MyTransformer[A, B]): MyListGenericB[B] = {
	  new ConsGB(transformer.transform(h), t.map(transformer))
  }

  /*
	[1,2].flatMap(n => [n, n+1])
	= [1,2] ++ [2].flatMap(n => [n, n+1])
	= [1,2] ++ [2,3] ++ EmptyGB.flatMap(n => [n, n+1])
	= [1,2] ++ [2,3] ++ EmptyGB
	= [1,2,2,3]
  */
  def flatMap[B](transformer: MyTransformer[A, MyListGenericB[B]]): MyListGenericB[B] = {
	  transformer.transform(h) ++ t.flatMap(transformer)
  }


	/*
		[1,2,3].filter(n % 2 == 0) =
		[2,3].filter(n % 2 == 0) =
		= new ConsGB(2, [3].filter(n % 2 == 0))
		= new ConsGB(2, Empty.filter(n % 2 == 0))
		= new ConsGB(2, Empty)
	*/
  def filter(predicate: MyPredicate[A]): MyListGenericB[A] = {
	if(predicate.test(h)) new ConsGB(h, t.filter(predicate))
	else t.filter(predicate)
  }

  /*
	[1,2] ++ [3,4,5]
	= new ConsGB(1, [2] ++ [3,4,5])
	= new ConsGB(1,[2, EmptyGB ++ [3,4,5]])
	= new ConsGB(1, new ConsGB(2, new ConsGB(3, new ConsGB(4, new ConsGB(5)))))
  */
  def ++[B >: A](list: MyListGenericB[B]): MyListGenericB[B] = new ConsGB[B](h, t ++ list)
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

trait MyPredicate[-T] {
	def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
	def transform(elem: A): B
}

object OOExpandingOurCollection extends App {
  val list = new ConsGB(1, EmptyGB)
  println(list.head)

  val list2 = new ConsGB(1, new ConsGB(2, new ConsGB(3, EmptyGB)))
  println(list2.tail.head)
  println(list2.add(4).head)
  println(list2.isEmpty)

  println(list2.toString)

  val listOfIntegers: MyListGenericB[Int] = new ConsGB(1, new ConsGB(2, new ConsGB(3, EmptyGB)))
  val listOfStrings: MyListGenericB[String] = new ConsGB("Hello", new ConsGB("Scala", EmptyGB))
  val anotherListOfIntegers: MyListGenericB[Int] = new ConsGB(4, new ConsGB(5, EmptyGB))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int]{
	  override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfIntegers ++ anotherListOfIntegers).toString 

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListGenericB[Int]]{
	  override def transform(elem: Int): MyListGenericB[Int] = new ConsGB(elem, new ConsGB(elem + 1, EmptyGB))
  }).toString)
}

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


