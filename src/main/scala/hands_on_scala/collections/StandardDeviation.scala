package hands_on_scala.collections

/**
  * calculating the standard deviation of an array using Scala Collection operations
  */
object StandardDeviation extends App{

  def stdDev(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(x => x - mean).map(x => x * x)
    math.sqrt(squareErrors.sum / a.length)
  }

  println(stdDev(Array(1, 2, 3, 4, 5)))
  println(math.round(stdDev(Array(3, 3, 3))))

  /**
    * Scala collections provide a convenient helper method . sum that is equivalent to . foldLeft (0.0)( _
    * so the above code can be simplified to
    */
  def stdDev1(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(_ - mean).map(x => x * x)
    math.sqrt(squareErrors.sum / a.length)
  }

  println(stdDev(Array(1, 2, 3, 4, 5)))
  println(math.round(stdDev(Array(3, 3, 3))))
}
