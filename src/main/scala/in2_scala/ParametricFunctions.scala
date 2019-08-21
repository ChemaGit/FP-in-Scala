package in2_scala

object ParametricFunctions {
	def main(args: Array[String]): Unit = {
		println(ident("pepe"))
		println(ident(3.8))
		println(ident(5))
		println(ident(List("pepe","Fran")).mkString(","))

		println(makeTuple(1,1))
		println(makeTuple(1,"Lucia"))
		println(makeTuple("Ana",List(1,2,3,4)))
		println(makeTuple("Carmen",1.4))

		println(threeList(1,1,1))
		println(threeList("Lucia","Mari","Carmen"))

		val r = ourFold(List(1,2,3,4,5),0)( (a,b) => a + b)
		println(r)

		val res = ourFold(List(1,2,3,4,5),"")( (a,b) => a + b)
		println(res)
	}

	def ident[A](i: A):A = i
	def makeTuple[A,B](a: A,b: B):(A,B) = (a,b)
	def threeList[A](a:A, b:A, c:A):List[A]= List(a,b,c)
	def ourFold[A,B](lst: List[A],base:B)(f:(A,B)=>B):B = lst match {
		case Nil => base
		case (h :: t) => f(h,ourFold(t,base)(f))
	}
}
