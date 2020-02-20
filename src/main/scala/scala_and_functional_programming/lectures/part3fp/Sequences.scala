package scala_and_functional_programming.lectures.part3fp

import scala.util.Random

object Sequences extends App {
  // Seq
  val aSequence = Seq(2,4,1,3)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // retrieves the value at this particular value
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10 // instead of to(inclusive) until(exclusive)
  aRange.foreach(println)

  // List
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)
  val preapend = 42 +: aList :+ 89
  println(preapend)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("[","-","]"))

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  /**
    * Benchmarking between Lists and Vectors
    * @param collection
    * @return
    */
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // operation
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numberList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long time
  println(getWriteTime(numberList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
