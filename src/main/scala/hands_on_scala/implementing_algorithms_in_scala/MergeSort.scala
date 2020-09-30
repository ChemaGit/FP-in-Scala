package hands_on_scala.implementing_algorithms_in_scala

object MergeSort {

  /**
    * Merge Sort
    *
    */
  def mergeSort(items: Array[Int]): Array[Int] = {
    if (items.length <= 1) items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      val (sortedLeft, sortedRight) = (mergeSort(left), mergeSort(right))

      var (leftIdx, rightIdx) = (0, 0)
      val output = Array.newBuilder[Int]
      while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
        val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) => sortedLeft(leftIdx) < sortedRight(rightIdx)
        }
        if (takeLeft) {
          output += sortedLeft(leftIdx)
          leftIdx += 1
        } else {
          output += sortedRight(rightIdx)
          rightIdx += 1
        }
      }
      output.result()
    }
  }


  /**
    * Generic Merge Sort
    * The merge sort function we defined above is hardcoded to only work with Array [Int] s. However, we do
    * not need to be limited to only that type:
    *  * We could sort any IndexedSeq , not just Array , as we just need to look up elements by index
    *  * We could sort any type that can be compared, not just Int .
    */

  def mergeSort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    if (items.length <= 1) items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      val (sortedLeft, sortedRight) = (mergeSort(left), mergeSort(right))
      var (leftIdx, rightIdx) = (0, 0)
      val output = IndexedSeq.newBuilder[T]
      while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
        val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) => Ordering[T].lt(sortedLeft(leftIdx), sortedRight(rightIdx))
        }
        if (takeLeft) {
          output += sortedLeft(leftIdx)
          leftIdx += 1
        } else {
          output += sortedRight(rightIdx)
          rightIdx += 1
        }
      }
      output.result()
    }
  }

  def main(args: Array[String]): Unit = {
    val input = Vector("banana", "mandarin", "avocado", "apple", "mango", "cherry", "mangosteen")

    val a = mergeSort(input)
    println(a)

    assert(
      mergeSort(input) == Vector("apple", "avocado", "banana", "cherry", "mandarin", "mango", "mangosteen")
    )

    mergeSort(Array(4, 0, 1, 5, 2, 3)).foreach(x => print(s"$x,"))
    println()
  }
}

