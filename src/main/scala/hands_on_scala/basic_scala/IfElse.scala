package hands_on_scala.basic_scala

object IfElse extends App{

  var total = 0
   for (i <- Range(0, 10)) {
    if (i % 2 == 0) total += i
    else total += 2
  }
  println(total)

  total = 0
  for (i <- Range(0, 10)) {
    total += (if (i % 2 == 0) i else 2)
  }
  println(total)
}
