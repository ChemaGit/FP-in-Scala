package hands_on_scala.collections

/**
  * Mutable collections are in general faster than their immutable counterparts when used for in-place
  * operations. However, mutability comes at a cost: you need to be much more careful sharing them between
  * different parts of your program. It is easy to create bugs where a shared mutable collection is updated
  * unexpectedly, forcing you to hunt down which line in a large codebase is performing the unwanted update.
  * A common approach is to use mutable collections locally within a function or private to a class where there
  * is a performance bottleneck, but to use immutable collections elsewhere where speed is less of a concern.
  * That gives you the high performance of mutable collections where it matters most, while not sacrificing
  * the safety that immutable collections give you throughout the bulk of your application logic.
  */
object MutableCollections extends App{
  /**
    * ArrayDeque
    * 
    * ArrayDeque s are general-purpose mutable, linear collections that provide efficient O(1) indexed lookups,
    * O(1) indexed updates, and O(1) insertion and removal at both left and right ends.
    *
    * ArrayDeque s have the standard suite of Operations
    *
    * They can serve many roles:
    * - An Array that can grow: an Array . newBuilder does not allow indexed lookup or modification while
    * the array is being built, and an Array does not allow adding more elements. An ArrayDeque allows
    * both
    * - A faster, mutable alternative to immutable Vector s, if you find adding/removing items from either
    * end using :+ / +: or .tail / .init is a bottleneck in your code. Appending and prepending to
    * ArrayDeque s is much faster than the equivalent Vector operations
    * - A first-in-first-out Queue, by inserting items to the right via .append , and removing items via
    * .removeHead
    * - A first-in-last-out Stack, by inserting items to the right via
    * .append, and removing items via .removeLast
    *
    * If you want to "freeze" a mutable ArrayDeque into an immutable Vector , you can use . to ( Vector )
    */
  val myArrayDeque = collection.mutable.ArrayDeque(1, 2, 3, 4, 5)
  // myArrayDeque: collection.mutable.ArrayDeque[Int] = ArrayDeque(1, 2, 3, 4, 5)

  myArrayDeque.removeHead()
  // Int = 1

  println(myArrayDeque.mkString(","))
  // collection.mutable.ArrayDeque[Int] = ArrayDeque(2, 3, 4, 5, 6)

  myArrayDeque.removeHead()
  // Int = 2

  myArrayDeque
  // collection.mutable.ArrayDeque[Int] = ArrayDeque(3, 4, 5, 6)

  myArrayDeque.to(Vector)
  // Vector[Int] = Vector(3, 4, 5, 6)

  /**
    * Mutable Sets
    *
    * The Scala standard library provides mutable Sets as a counterpart to the immutable Sets we saw earlier.
    * Mutable sets also provide efficient .contains checks (O(1)), but instead of constructing new copies of the
    * Set via + and - , you instead add and remove elements from the Set via .add and .remove
    *
    * You can "freeze" a mutable Set into an immutable Set by using .to(Set), which makes a copy you cannot
    * mutate using .add or .remove , and convert it back to a mutable Set the same way. Note that each such
    * conversion makes a copy of the entire set.
    */
  val s = collection.mutable.Set(1, 2, 3)

  assert(s.contains(2) == true)

  assert(s.contains(4) == false)

  s.add(4)

  s.remove(1)

  assert(s == collection.mutable.Set(2, 3, 4))

  /**
    * Mutable Maps
    *
    * Mutable Maps are again just like immutable Maps, but allow you to mutate the Map by adding or removing
    * key-value pairs
    *
    * Mutable Map s have a convenient getOrElseUpdate function, that allows you to look up a value by key, and
    * compute/store the value if there isn't one already present
    *
    * getOrElseUpdate makes it convenient to use a mutable Map as a cache: the second parameter to getOrElseUpdate
    * is a lazy "by-name" parameter, and is only evaluated when the key is not found in the
    * Map . This provides the common "check if key present, if so return value, otherwise insert new value and
    * return that" workflow built in
    *
    * Mutable Maps are implemented as hash-tables, with m(...) lookups and m(...) = ...
    * updates being efficient O(1) operations.
    **/
  val m = collection.mutable.Map("one" -> 1, "two" -> 2, "three" -> 3)
  // m: mutable.Map[String, Int] = HashMap("two" -> 2, "three" -> 3, "one" -> 1)
  m.remove("two")
  // Option[Int] = Some(2)
  m("five") = 5
  m
  // mutable.Map[String, Int] = HashMap("five" -> 5, "three" -> 3, "one" -> 1)

  val m1 = collection.mutable.Map("one" -> 1, "two" -> 2, "three" -> 3)
  m1.getOrElseUpdate("three", -1) // already present, returns existing value
  // Int = 3
  m1 // `m1` is unchanged
  // mutable.Map[String, Int] = HashMap("two" -> 2, "three" -> 3, "one" -> 1)
  m1.getOrElseUpdate("four", -1) // not present, stores new value in map and returns it
  // Int = -1
  m1 // `m1` now contains "four" -> -1
  //  mutable.Map[String, Int] = HashMap(
  //    "two" -> 2,
  //    "three" -> 3,
  //    "four" -> -1,
  //    "one" -> 1
  // )

  /**
    * In-Place Operations
    *
    * All mutable collections, including Arrays, have in-place versions of many common collection operations.
    * These allow you to perform the operation on the mutable collection without having to make a transformed
    * copy. Apart from those shown above, there is also dropInPlace , sliceInPlace , sortInPlace , etc. Using in-
    * place operations rather than normal transformations avoids the cost of allocating new transformed
    * collections, and can help in performance-critical scenarios.
    */

  val a = collection.mutable.ArrayDeque(1, 2, 3, 4)
  // a: mutable.ArrayDeque[Int] = ArrayDeque(1, 2, 3, 4)
  a.mapInPlace(_ + 1)
  // res92: mutable.ArrayDeque[Int] = ArrayDeque(2, 3, 4, 5)
  a.filterInPlace(_ % 2 == 0)
  // mutable.ArrayDeque[Int] = ArrayDeque(2, 4)
  a // `a` was modified in place
  // mutable.ArrayDeque[Int] = ArrayDeque(2, 4)
}
