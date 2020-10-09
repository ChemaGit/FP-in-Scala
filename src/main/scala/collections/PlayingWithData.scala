package collections

import io.StdIn._
import io.Source

object PlayingWithData {
	def main(args: Array[String]): Unit = {
		val fich = "/home/chema/files/1800_1801.csv"
		val nameLines = readFile(fich)
		val nameData = nameLines.map(parseLine)

		nameData.foreach(println)

		val filt = nameData.filter(nd => nd._1 == "EZE00100082" && nd._3 == "TMAX")
		filt.foreach(println)

		val maxTemps = filt.map(nd => nd._4).max
		println(maxTemps)
		val rep = filt.filter(nd => nd._4 == maxTemps)
		rep.foreach(println)
	}

	type NameData = (String,String,String,Int)

	def readFile(file: String): List[String] = {
		val lines = Source.fromFile(file).getLines.toList
		lines
	}

	def parseLine(line: String):NameData = {
		val parts = line.split(",")
		(parts(0),parts(1),parts(2),parts(3).toInt)
	}
}
