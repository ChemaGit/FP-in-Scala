package scala_and_functional_programming.exercises

abstract class MyListGeneric[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */
  def head: A
  def tail: MyListGeneric[A]
  def isEmpty: Boolean
  def add[B >: A](v: B): MyListGeneric[B]
  def printElements: String
  // polymorphic call
  override def toString: String = s"[ $printElements ]"
}

object EmptyG extends MyListGeneric[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](v: B): MyListGeneric[B] = new ConsG(v, EmptyG)
  def printElements: String = "[]"
}

class ConsG[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  def head: A = h
  def tail: MyListGeneric[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](v: B): MyListGeneric[B] = new ConsG(v, this)
  def printElements: String =
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"
}


object ListTestG extends App {
  val list = new ConsG(1, EmptyG)
  println(list.head)

  val list2 = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  println(list2.tail.head)
  println(list2.add(4).head)
  println(list2.isEmpty)

  println(list2.toString)

  val listOfIntegers: MyListGeneric[Int] = new ConsG(1, new ConsG(2, new ConsG(3, EmptyG)))
  val listOfStrings: MyListGeneric[String] = new ConsG("Hello", new ConsG("Scala", EmptyG))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  // lazy val lst: MyListGeneric[String] = new ConsG("a", lst)
  println(listOfIntegers)
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
