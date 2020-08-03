package hands_on_scala.collections

object CommonInterfaces extends App {
  /**
    * In many cases, a piece of code does not care exactly what collection it is working on. For example, code
    * that just needs something that can be iterated over in order can take a Seq [T] :
    */
  def iterateOverSomething[T](items: Seq[T]) = {
    for (i <- items) println(i)
  }

  iterateOverSomething(Vector(1, 2, 3))
  // 1
  // 2
  // 3

  iterateOverSomething(List(("one", 1), ("two", 2), ("three", 3)))
  // (one,1)
  // (two,2)
  // (three,3)

  /**
    * Code that needs something which provides efficient indexed lookup doesn't care if it's an Array or
    * Vector , but cannot work with a List. In that case, your code can take an IndexedSeq [T]
    */
  def getIndexTwoAndFour[T](items: IndexedSeq[T]) = (items(2), items(4))

  getIndexTwoAndFour(Vector(1, 2, 3, 4, 5))
  // Int, Int) = (3, 5)
  getIndexTwoAndFour(Array(2, 4, 6, 8, 10))
  // (Int, Int) = (6, 10)
}
