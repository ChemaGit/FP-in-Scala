package in_scala

object DemosArray {
	def main(args: Array[String]): Unit = {
		val example = Array(1, 2, 3, 4)
		val arr = new Array[Int](6)
		val arrDouble = new Array[Double](6)
		arr(0) = 734
		arr(1) = 34
		arr(2) = 2
		arr(3) = 65
		arr(4) = 7
		arr(5) = 4

		arrDouble(0) = 734
		arrDouble(1) = 34
		arrDouble(2) = 2
		arrDouble(3) = 65
		arrDouble(4) = 7
		arrDouble(5) = 4

		arr.foreach(println)
		arrDouble.foreach(println)
		println(arr(0))
		println(arrDouble(0))
		// Values in arrays are mutable, but you cannot change the length or type
		arr(0) = 99
		println(arr(0))
		println(arr.length)
		println("*********************")
		val nums = new Array[Int](6)
		fillArray(nums,99,0)
		nums.foreach(println)
		println("***************************")
		val nums2 = List(1,2,3,4,5,6).toArray
		val result = operateOnArray(nums2,0,(x,y) => x + y)
		println(s"operateOnArray(nums2,0,(x,y) => x + y)): $result")
		val result2 = operateOnArray(nums2,0, (x, y) => x * y)
		println(s"operateOnArray(nums2,0, (x,y) => x * y): $result2")
		
	}
	def fillArray(arr: Array[Int], v: Int, i: Int): Unit = {
		if(i < arr.length) {
			arr(i) = v
			fillArray(arr,v,i + 1)
		}
	}

	def operateOnArray(arr: Array[Int],i: Int, f:(Int, Int) => Int): Int = {
		if(i < arr.length - 1) {
			f(arr(i), operateOnArray(arr, i + 1, f))
		} else {
			arr(i)
		}
	}
}
