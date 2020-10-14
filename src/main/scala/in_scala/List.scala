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

		// The fill method on Lists
		println("*********fill method***************")
		val fillArray = Array.fill(10)(5)
		println(fillArray.mkString(","))
		val fillArray1 = Array.fill(10)(Math.random)
		println(fillArray1.mkString(","))
		val fillArray2 = List.fill(10)(Math.random)
		println(fillArray2.mkString(","))
		val fillArray3 = List.fill(10)(Math.random)
		println(fillArray3.mkString(","))
		var i = 0
		val fillArray4 = List.fill(10)({i += 1;i})
		println(fillArray4.mkString(","))
		val fillArray5 = List.fill(5)(readInt)
		println(fillArray5.mkString(","))

		// The tabulate Method
		println("******************The tabulate Method********************")
		val fillArray6 = Array.tabulate(10)(i => i)
		println(fillArray6.mkString(","))
		val fillArray7 = Array.tabulate(10)(i => i * 2)
		println(fillArray7.mkString(","))
		val fillList = List.tabulate(10)(i => i * 3)
		println(fillList.mkString(","))
		val fillList1 = List.tabulate(10)(i => Math.random)
		println(fillList1.mkString(","))
		val fillList2 = List.tabulate(10)(i => i * i)
		println(fillList2.mkString(","))
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

	def fillList(n: Int, v: Double):List[Double] = {
		if(n < 1) Nil
		else v :: fillList(n -1, v)
	}
}
