package using_scala

import io.StdIn._

object DemoNestingIfs {
	def main(args: Array[String]): Unit = {
		val order = readLine
		val size = readLine
		val res = if(order == "food"){
			if(size.toLowerCase.startsWith("S")) {
				"10.5$"
			} else if(size.toLowerCase.startsWith("M")) {
				"12.5$"
			} else if(size.toLowerCase.startsWith("L")) {
				"14.8$"
			}
		}else if(order == "drink"){
			if(size.toLowerCase.startsWith("S")) {
				"10.5$"
			} else if(size.toLowerCase.startsWith("M")) {
				"12.5$"
			} else if(size.toLowerCase.startsWith("L")) {
				"14.8$"
			}
		}else if(order == "combo"){
			if(size.toLowerCase.startsWith("S")) {
				"10.5$"
			} else if(size.toLowerCase.startsWith("M")) {
				"12.5$"
			} else if(size.toLowerCase.startsWith("L")) {
				"14.8$"
			}
		}else if(order == "burguer"){
			if(size.toLowerCase.startsWith("S")) {
				"10.5$"
			} else if(size.toLowerCase.startsWith("M")) {
				"12.5$"
			} else if(size.toLowerCase.startsWith("L")) {
				"14.8$"
			}
		}else{
			"30$"
		}
		println(s"You have to pay the bill: $res")
	}
}
