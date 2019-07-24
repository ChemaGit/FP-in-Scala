package in_scala

object DemoHigherOrderFunctions {
	def main(args: Array[String]): Unit = {
		println("sum3(7,5,8): " + sum3(7,5,8))
		println("mult3(7,5,8): " + mult3(7,5,8))
		println("min3(7,5,8): " + min3(7,5,8))
		// Higher order functions
		println("combine3(7,5,8): " + combine3(7,5,8,(x,y)=>x+y))
		println("combine3(7,5,8): " + combine3(7,5,8,(x,y)=>x*y))
		println("combine3(7,5,8): " + combine3(7,5,8,(x,y)=>x.min(y)))
		println("combine3(7,5,8): " + combine3(7,5,8,_*_))
		println("combine3(7,5,8): " + combine3(7,5,8,_.max(_)))
		println("combine3(7,5,8): " + combine3(7,5,8,_+_))
	}

	def sum3(x: Double, y: Double, z: Double): Double = x + y + z
	def mult3(x: Double, y: Double, z: Double): Double = x * y * z
	def min3(x: Double, y: Double, z: Double): Double = x.min(y).min(z)
	// Higher order functions
	def combine3(x: Double, y: Double, z: Double, f:(Double,Double) => Double) = f(f(x,y),z)
}
