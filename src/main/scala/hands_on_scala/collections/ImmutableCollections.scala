package hands_on_scala.collections

object ImmutableCollections extends App{
  /**
    * Immutable Vectors
    *
    * Vectors are fixed-size, immutable linear sequences. They are a good general-purpose sequence data
    * structure, and provide efficient O(log n) performance for most operations.
    *
    * Vector s support the same set of Operations that Arrays and other collections do: builders,
    * factory methods, transforms, etc.
    *
    * In general, using Vectors is handy when you have a sequence you know will not change, but need
    * flexibility in how you work with it. Their tree structure makes most operations reasonably efficient,
    * although they will never be quite as fast as Arrays for in-place updates or Immutable Lists for
    * adding and removing elements at the front.
    */
  val v = Vector(1, 2, 3, 4, 5)
  // v: Vector[Int] = Vector(1, 2, 3, 4, 5)

  v(0)
  // Int = 1

  val v2 = v.updated(2, 10)
  // v2: Vector[Int] = Vector(1, 2, 10, 4, 5)

  v2
  // Vector[Int] = Vector(1, 2, 10, 4, 5)

  v // note that `v` did not change!
  // Vector[Int] = Vector(1, 2, 3, 4, 5)

  val v3 = Vector[Int]()
  // v3: Vector[Int] = Vector()

  val v4 = v :+ 1
  // v4: Vector[Int] = Vector(1)

  val v5 = 4 +: v4
  // v5: Vector[Int] = Vector(4, 1)

  val v6 = v5.tail
  // v6: Vector[Int] = Vector(1)

  /**
    * Structural Sharing
    *
    * Updating a Vector does always involve a certain amount of copying, and will never be as
    * fast as updating mutable data structures in-place. In some cases where performance is important and you
    * are updating a collection very frequently, you might consider using a mutable ArrayDeque (4.3.1) which
    * has faster O(1) update/append/prepend operations, or raw Arrays if you know the size of your collection
    * in advance.
    */


  /**
    * Immutable Sets
    *
    * Scala's immutable Sets are unordered collections of elements without duplicates, and provide an efficient
    * O(log n) .contains method. Sets can be constructed via + and elements removed by - , or combined via
    * ++ . Note that duplicates elements are discarded:
    *
    * Most immutable Set operations take time O(log n) in the size of the Set . This is fast enough for most
    * purposes, but in cases where it isn't you can always fall back to Mutable Sets  for better
    * performance. Sets also support the standard set of operations common to all collections.
    */
  val s = Set(1, 2, 3)
  // s: Set[Int] = Set(1, 2, 3)

  s.contains(2)
  // Boolean = true

  s.contains(4)
  // Boolean = false

  Set(1, 2, 3) + 4 + 5
  // Set[Int] = HashSet(5, 1, 2, 3, 4)

  Set(1, 2, 3) - 2
  // Set[Int] = Set(1, 3)

  Set(1, 2, 3) ++ Set(2, 3, 4)
  // Set[Int] = Set(1, 2, 3, 4)

  for (i <- Set(1, 2, 3, 4, 5)) println(i)

  /**
    * Immutable Maps
    *
    * Immutable maps are unordered collections of keys and values, allowing efficient lookup by key.
    * You can also use .get if you're not sure whether a map contains a key or not. This returns Some ( v ) if the
    * key is present, None if not.
    *
    * While Maps support the same set of operations as other collections, they are treated as collections of
    * tuples representing each key-value pair. Conversions via .to requires a collection of tuples to convert
    * from, + adds tuples to the Map as key-value pairs, and for loops iterate over tuples.
    *
    * Most immutable Map operations take time O(log n) in the size of the Map .
    */
  val m = Map("one" -> 1, "two" -> 2, "three" -> 3)

  assert(m.contains("two") == true)

  assert(m("two") == 2)

  val m2 = Map("one" -> 1, "two" -> 2, "three" -> 3)

  assert(m2.get("one") == Some(1))

  assert(m.get("four") == None)

  assert(
    Vector(("one", 1), ("two", 2), ("three", 3)).to(Map) ==
      Map("one" -> 1, "two" -> 2, "three" -> 3)
  )

  assert(
    Map[String, Int]() + ("one" -> 1) + ("three" -> 3) ==
      Map("one" -> 1, "three" -> 3)
  )

  for ((k, v) <- m) println(k + " " + v)

  /**
    * Immutable Lists
    *
    * Scala's immutable Lists are a singly-linked list data structure. Each node in the list has a value and pointer
    * to the next node, terminating in a Nil node. Lists have a fast O(1) .head method to look up the first
    * item in the list, a fast O(1) .tail method to create a list without the first element, and a fast O(1) ::
    * operator to create a new List with one more element in front.
    * .tail and :: are efficient because they can share much of the existing List : .tail returns a reference to
    * the next node in the singly linked structure, while :: adds a new node in front. The fact that multiple lists
    * can share nodes means that in the above example, myList , myTail , myOtherList and myThirdList are
    * actually mostly the same data structure.
    *
    * This can result in significant memory savings if you have a large number of collections that have identical
    * elements on one side, e.g. paths on a filesystem which all share the same prefix. Rather than creating an
    * updated copy of an Array in O(n) time, or an updated copy of a Vector in O(log n) time, pre-pending an
    * item to a List is a fast O(1) operation.
    * The downside of Lists is that indexed lookup via myList ( i ) is a slow O(n) operation, since you need to
    * traverse the list starting from the left to find the element you want. Appending/removing elements on the
    * right hand side of the list is also a slow O(n), since it needs to make a copy of the entire list. For use cases
    * where you want fast indexed lookup or fast appends/removes on the right, you should consider using
    * Vectors or mutable ArrayDeques instead.
    */
  val myList = List(1, 2, 3, 4, 5)
  // myList: List[Int] = List(1, 2, 3, 4, 5)

  myList.head
  // Int = 1

  val myTail = myList.tail
  // myTail: List[Int] = List(2, 3, 4, 5)

  val myOtherList = 0 :: myList
  // myOtherList: List[Int] = List(0, 1, 2, 3, 4, 5)

  val myThirdList = -1 :: myList
  // myThirdList: List[Int] = List(-1, 1, 2, 3, 4, 5)

}
