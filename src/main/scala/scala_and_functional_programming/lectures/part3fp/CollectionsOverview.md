# OVERVIEW

````text
- Scala offers both mutable and immutable collections:
    - mutable collections can be updated in place
    - immutable collections never change

- We are using the immutable collections by default
````

````scala
package object scala {
  type List[+A] = immutable.List[A]
}

object Predef {
  type Map[A, +B] = immutable.Map[A, B]
  type Set[A] = immutable.Set[A]
}
````

````text
Immutable Collections
    - Immutable collections are found in scala.collections.immutable package

Mutable Collections
    - Mutable collections are found in scala.collections.mutable package

Traversable
    - Base traint for all collections. Offers a great variety of methods:
        - maps: map, flatMap, collect
        - conversions: toArray, toList, toSeq
        - size info: isEmpty, size, nonEmpty
        - tests: exists, forall
        - folds: foldLeft, foldRight, reduceRight, reduceLeft
        - retrieval: head, find, tail
        - string ops: mkString
````

# Sequences
````scala
trait Seq[+A] {
  def head: A
  def tail: Seq[A]
}
````
````text
A (very) general interface for data structures that
    - have a well defined order
    - can be indexed

Supports various operations:
    - apply, iterator, length, reverse, for indexing and iterating
    - concatenation, appending, prepending
    - a lot of others: grouping, sorting, zipping, searching, slicing
````

# List
````scala
sealed abstract class List[+A]
case object Nil extends List[Nothing]
case class ::[A](val hd: A, val tl: List[A]) extends List[A]
````
````text
A LinearSeq immutable linked list
    - head, tail, isEmpty methods are fast: O(1)
    - most operations are O(n): length, reverse

Sealed - has two subtypes
    - object Nil(empty)
    - class::
````

# Array
````scala
final class Array[T]
    extends java.io.Serializable
with java.lang.Cloneable
````
````text
The equivalent of simple Java arrays
    - can be manually constructed with predefinided lengths
    - can be mutated(updated in place)
    - are interoperable with Java's  T[] arrays
    - indexing is fast

Where's the Seq?!*
````

# Vector
````scala
final class Vector[+A]

val noElements = Vector.empty 
val numbers = noElements :+ 1 :+ 2 :+ 3 // Vector(1,2,3)
val modified = numbers.updated(0, 7) // Vector(7, 2, 3)
````
````text
The default implementation for immutable sequences
    - effectively constant indexed read and write: O(log32(n))
    - fast element addition: append/prepend
    - implemented as a fixed-branched trie(branch factor 32)
    - good performance for large sizes
````



