package in_scala

object DemoTypeDeclarations {
	def main(args: Array[String]) {

	}
	// def addVect(v1: (Double,Double,Double),v2:(Double,Double,Double) )
	type Vect3 = (Double, Double, Double)
	def addVect(v1:Vect3, v2: Vect3): Vect3 = {
		(v1._1 + v2._1,v1._2 + v2._2,v1._3 + v2._3)
	}
}
