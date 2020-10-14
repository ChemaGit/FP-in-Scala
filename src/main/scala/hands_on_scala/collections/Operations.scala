package hands_on_scala.collections

/**
  * Scala collections provide many common operations for constructing them,
  * querying them, or transforming them.
  */
object Operations extends App{
  /**
    * Builders
    *
    * Builders let you efficiently construct a collection of unknown length, "freezing" it into the collection you
    * want at the end. This is most useful for constructing Array s or immutable collections where you cannot
    * add or remove elements once the collection has been constructed.
    */

  val b = Array.newBuilder[Int]
  b += 1
  b += 2
  println(b.result().mkString(","))

  val c = List.newBuilder[String]
  c += "Hello"
  c += "World"

  /**
    * Factory Methods
    *
    * Factory methods provide another way to instantiate collections: with every element the same, with each
    * element constructed depending on the index, or from multiple smaller collections. This can be more
    * convenient than using Builders in many common use cases.
    */
  Array.fill(5)("hello") // Array with "hello" repeated 5 times
  // Array[String] = Array("hello", "hello", "hello", "hello", "hello")
  Array.tabulate(5)(n => s"hello $n") // Array with 5 items, each computed from its index
  // Array[String] = Array("hello 0", "hello 1", "hello 2", "hello 3", "hello 4")
  Array(1, 2, 3) ++ Array(4, 5, 6) // Concatenating two Arrays into a larger one
  // Array[Int] = Array(1, 2, 3, 4, 5, 6)

  /**
    * Transforms
    *
    * Transforms take an existing collection and create a new collection modified in some way. Note that these
    * transformations create copies of the collection, and leave the original unchanged. That means if you are
    * still using the original array, its contents will not be modified by the transform:
    */
  Array(1, 2, 3, 4, 5).map(i => i * 2) // Multiply every element by 2
  // Array[Int] = Array(2, 4, 6, 8, 10)
  Array(1, 2, 3, 4, 5).filter(i => i % 2 == 1) // Keep only elements not divisible by 2
  // Array[Int] = Array(1, 3, 5)
  Array(1, 2, 3, 4, 5).take(2) // Keep first two elements
  // Array[Int] = Array(1, 2)
  Array(1, 2, 3, 4, 5).drop(2) // Discard first two elements
  // Array[Int] = Array(3, 4, 5)
  Array(1, 2, 3, 4, 5).slice(1, 4) // Keep elements from index 1-4
  // Array[Int] = Array(2, 3, 4)
  Array(1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7, 8).distinct // Removes all duplicates
  // Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8)
  val a = Array(1, 2, 3, 4, 5)
  // Array[Int] = Array(1, 2, 3, 4, 5)
  val a2 = a.map(x => x + 10)
  // Array[Int] = Array(11, 12, 13, 14, 15)
  println(a(0)) // Note that `a` is unchanged!

  println(a2(0))

  /**
    * Queries
    *
    * Queries let you search for elements without your collection, returning either a Boolean indicating if a
    * matching element exists, or an Option containing the element that was found. This can make it convenient
    * to find things inside your collections without the verbosity of writing for-loops to inspect the elements one
    * by one.
    */
  Array(1, 2, 3, 4, 5, 6, 7).find(i => i % 2 == 0 && i > 4)
  // Option[Int] = Some(6)
  Array(1, 2, 3, 4, 5, 6, 7).find(i => i % 2 == 0 && i > 10)
  // Option[Int] = None
  Array(1, 2, 3, 4, 5, 6, 7).exists(x => x > 1) // are any elements greater than 1?
  // Boolean = true
  Array(1, 2, 3, 4, 5, 6, 7).exists(_ < 0) // same as a.exists(x => x < 0)
  // Boolean = false

  /**
    * Aggregations
    */
  Array(1, 2, 3, 4, 5, 6, 7).mkString(",")
  // String = "1,2,3,4,5,6,7"
  Array(1, 2, 3, 4, 5, 6, 7).mkString("[", ",", "]")
  // String = "[1,2,3,4,5,6,7]"

  Array(1, 2, 3, 4, 5, 6, 7).foldLeft(0)((x, y) => x + y) // sum of all elements
  // Int = 28
  Array(1, 2, 3, 4, 5, 6, 7).foldLeft(1)((x, y) => x * y) // product of all elements
  // Int = 5040
  Array(1, 2, 3, 4, 5, 6, 7).foldLeft(1)(_ * _) // same as above, shorthand syntax
  // Int = 5040

  /**
    * In general, foldLeft is similar to a for -loop and accumulator
    * foldLeft call can equivalently be written as
    */
  {
    var total = 0
    for (i <- Array(1, 2, 3, 4, 5, 6, 7)) total += i
    total
  }
  // Int = 28

  val grouped = Array(1, 2, 3, 4, 5, 6, 7).groupBy(_ % 2)
  // Map[Int, Array[Int]] = Map(0 -> Array(2, 4, 6), 1 -> Array(1, 3, 5, 7))
  grouped(0)
  // Array[Int] = Array(2, 4, 6)
  grouped(1)
  // Array[Int] = Array(1, 3, 5, 7)

  /**
    * Combining Operations
    *
    * It is common to chain more than one operation together to achieve what you want. For example, here is a
    * function that computes the standard deviation of an array of numbers:
    */
  def stdDev(a: Array[Double]): Double = {
    val mean = a.foldLeft(0.0)(_ + _) / a.length
    val squareErrors = a.map(_ - mean).map(x => x * x)
    math.sqrt(squareErrors.foldLeft(0.0)(_ + _) / a.length)
  }
  stdDev(Array(1, 2, 3, 4, 5))
  // Double = 1.4142135623730951
  stdDev(Array(3, 3, 3))
  // Double = 0.0

  /**
    * Scala collections provide a convenient helper method . sum that is equivalent to . foldLeft (0.0)( _
    * so the above code can be simplified to
    */
  def stdDev1(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(_ - mean).map(x => x * x)
    math.sqrt(squareErrors.sum / a.length)
  }
  stdDev1(Array(1, 2, 3, 4, 5))
  // Double = 1.4142135623730951
  stdDev1(Array(3, 3, 3))
  // Double = 0.0

  /**
    * As another example, here is a function that uses . exists , . map and . distinct to check if an incoming
    * grid of numbers is a valid Sudoku grid:
    *
    * This implementation receives a Sudoku grid, represented as a 2-dimensional Array [ Array [Int]] . For each
    * i from 0 to 9 , we pick out a single row, column, and 3x3 square. It then checks that each such
    * row/column/square has 9 unique numbers by calling . distinct to remove any duplicates, and then
    * checking if the . length has changed as a result of that removal.
    *
    * Chaining collection transformations in this manner will always have some overhead, but for most use cases
    * the overhead is worth the convenience and simplicity that these transforms give you. If collection
    * transforms do become a bottleneck, you can optimize the code using Views, In-Place Operations
    * , or finally by looping over the raw Arrays yourself.
    */
  def isValidSudoku(grid: Array[Array[Int]]): Boolean = {
    !Range(0, 9).exists{i =>
      val row = Range(0, 9).map(grid(i)(_))
      val col = Range(0, 9).map(grid(_)(i))
      val square = Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
      row.distinct.length != row.length ||
        col.distinct.length != col.length ||
        square.distinct.length != square.length
    }
  }

  assert(
    isValidSudoku(Array(
      Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
      Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
      Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
      Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
      Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
      Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
      Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
      Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
    )) ==
      true
  )

  assert(
    isValidSudoku(Array(
      Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
      Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
      Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
      Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
      Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
      Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
      Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
      Array(3, 4, 5, 2, 8, 6, 1, 7, 8)
    )) ==  // bottom right cell should be 9
      false
  )

  /**
    * Converters
    *
    * You can convert among Array s and other collections like Vector (4.2.1)s and Set (4.2.3) using the .to
    * method:
    */
  Array(1, 2, 3).to(Vector)
  // Vector[Int] = Vector(1, 2, 3)
  Vector(1, 2, 3).to(Array)
  // Array[Int] = Array(1, 2, 3)
  Array(1, 1, 2, 2, 3, 4).to(Set)
  // Set[Int] = Set(1, 2, 3, 4)

  /**
    * Views
    *
    * When you chain multiple transformations on a collection, we are creating many intermediate collections
    * that are immediately thrown away. For example, in the following snippet:
    */
  val myArray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val myNewArray = myArray.map(x => x + 1).filter(x => x % 2 == 0).slice(1, 3)
  // Array[Int] = Array(4, 6)

  /**
    * The chain of .map .filter .slice operations ends up traversing the collection three times, creating three
    * new collections, but only the last collection ends up being stored in myNewArray and the others are
    * discarded.
    * This creation and traversal of intermediate collections is wasteful. In cases where you have long chains of
    * collection transformations that are becoming a performance bottleneck, you can use the .view method
    * together with .to to "fuse" the operations together:
    */
  val myNewArrayView = myArray.view.map(_ + 1).filter(_ % 2 == 0).slice(1, 3).to(Array)
  // Array[Int] = Array(4, 6)

  /**
    * Using .view before the map / filter / slice transformation operations defers the actual traversal and
    * creation of a new collection until later, when we call . to to convert it back into a concrete collection type.
    * This allows us to perform this chain of map / filter / slice transformations with only a single traversal, and
    * only creating a single output collection. This reduces the amount of unnecessary processing and memory
    * allocations.
    */
}
