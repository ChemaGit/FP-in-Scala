package scala_and_functional_programming.lectures.part3fp

object Sequences extends App {
  // Seq
  val aSequence = Seq(2,4,1,3)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // retrieves the value at this particular value
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges
}
