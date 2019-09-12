package scala_and_functional_programming.lectures.part1basics

object DefaultArgs {

	def trFact(n: Int, acc: Int = 1): Int = {
		if(n <= 1) acc
		else trFact(n - 1, n * acc)	
	}

	def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

	def main(args: Array[String]): Unit = {
		val fact10 = trFact(10, 2)

		savePicture("jpg", 800, 800)
		savePicture()
		savePicture(width = 800)
		savePicture(height = 600, width = 800, format = "bmp")

		/*
			1. pass in every leading argument
			2. name the arguments
		*/

		/* TAKEAWAYS
			When 99% of time we call a function with the same params
			Naming parameters
		*/
	}
}
