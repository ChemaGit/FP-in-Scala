package in2_scala

object ArgumentPassing{
	def main(args: Array[String]): Unit = {
		def zeroArray(a: Array[Int], i: Int): Unit = {
			if(i < a.length){
				a(i) = 0
				zeroArray(a,i+1)
			}
		}
		val arr = Array(1,2,3,4,5,6,7,8,9)
		println(zeroArray(arr,0))
		println(arr.mkString(","))	
	}
	
}
