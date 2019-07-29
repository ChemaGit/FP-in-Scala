package in_scala

import scala.io.StdIn._

object DemoList {
  	def main(args: Array[String]): Unit = {
		println(l.mkString(","))
		println(lst.mkString(","))
		println(lst1.mkString(","))

		val lst2 = inputList(5)
		println(lst2.mkString(","))

		println(lst2.head)
		println(lst2.tail)

		println("**************")
		val res = operateOnLists(lst2, 0, (x, y) => x + y)
		println(s"operateOnList(lst2, 0, (x, y) => x + y) => $res")
		println("*******************")
		val res2 = operateOnList_2(lst2, 1, (x, y) => x * y)
		println(s"operateOnList_2(lst2, 1, (x, y) => x * y) => $res2")
	}
	// Lists are inmutable
	val l = List(1,2,3,4,5)
	val lst = 21 :: l
	val lst1 = 1 :: 2 :: 3 :: 4 :: Nil

	def inputList(n: Int): List[Int] = {
		if(n < 1) Nil
		else readInt :: inputList(n - 1)
	}

	def operateOnLists(l: List[Int], base:Int, f:(Int, Int) => Int): Int = {
		if(l == Nil) base
		else f(l.head, operateOnLists(l.tail, base, f))
	}

	def operateOnList_2(l: List[Int], base: Int, f:(Int, Int) => Int): Int = {
		l match {
			case Nil => base
			case h :: t => f(h, operateOnList_2(t, base, f))
		}
	}
}
