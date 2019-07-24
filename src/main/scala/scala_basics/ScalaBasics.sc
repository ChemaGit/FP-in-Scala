/**
  * Objects and Types
  */
5.6
5.6.toInt
5.toDouble
4+5
4.+(5)
res0

/**
  * Other Types
  */
true
false
'a'
'a'.toInt
'a' + 1
'a' - 1
('a' + 1).toChar
('a' - 1).toChar
'b' - 'a'
"Hello World"
"hi"
"a" + "b"
"hi" + " there"
"hi" + 5
"hi" + 5.2
"hi" * 3
("hi",42)
("hi",42,3.14)
("hi",42,3.14,'c',23)
"hi" -> 42
"hi" -> 42 -> 3.14
res25._1
res25._2
res26._2
res26._1._2

/**
  * Details of Numbers
  */
2000000000+2000000000
1000000000+1000000000
53.toBinaryString



/**
  * Binary Arithmetic
  */
53+78
131.toBinaryString
53*78
4134.toBinaryString

/**
  * Signed Numbers and Negative Values
  */
-53.toBinaryString

/**
  * Other Integer Types
  */
Int.MinValue
Int.MaxValue
Int.MaxValue + 1
Byte.MinValue
Byte.MaxValue
Short.MinValue
Short.MaxValue
2000000000 + 2000000000
2000000000L + 2000000000L
Long.MinValue
Long.MaxValue
Char.MinValue
Char.MinValue.toInt
Char.MaxValue.toInt

/**
  * Hexadecimal and Octal
  */
7582.toBinaryString
2 * 2 * 2 * 2
0x1d9e
7582.toHexString
7582.toOctalString

/**
  * Floating Point Numbers
  */
1.0-0.9-0.1
1/3
1.0f
0.66667-0.33333-0.33333
1.0f-0.9f-0.1f
Math.PI
Math.sqrt(9)
Math.random

/**
  * Values and Variables
  */
val a = 5
var b = 6
val s = "hi"
//val y: Int = 3.6 error
val x: Double = 0
val theAnswer = 42
b = 7
//a = 8 error
val p = (4,"hi",4.7)
p._1
p._2
val (age,word,price) = p

/**
  * Mental Model of Variables
  */
val d = 5
var e = 6
e = 7

/**
  * Details of Chars and Strings
  */
32.toChar
'\t'
'\n'
"\tabc\n123"
"\\"
"\""
"This \" is a double quote"
"""Testing another line \n \t """
"""\\"""
a + " " + b + " " + d +  " " + e
//String interpolation
s"$a $b $d $e"
s"$a+5 stuff $b $d $e"
s"${a+5} stuff $b $d $e"

/**
  * String Methods
  */
val str = "hi"
val str2 = "hi there"
str(0)
str(1)
str2(7)
str2.indexOf("h")
str2.lastIndexOf("h")
str2.indexOf("t")
str2.indexOf("th")
str2
str2.substring(3)
str2.substring(3,6)
str2 substring 3
str2 substring(3,6)
str2.splitAt(2)
str2.splitAt(3)
str2.trim
str2.trim.length
val name = "Chema Nacher"
val spaceIndex = name.indexOf(" ")
name.substring(spaceIndex + 1)
name.substring(spaceIndex + 1) + ", " + name.substring(0, spaceIndex)
val (first, last) = name.splitAt(spaceIndex)
last.trim +  ", " + first
s"${last.trim}, $first"

/**
  * String Immutability
  */
val name2 = "Chema Nacher"
name2.toUpperCase
name2
name2.toLowerCase
name2
//You cannot change a String
val upperName = name2.toUpperCase

/**
  * Sequential Execution
  */
// This will convert a String with time to total seconds.

/*
Code written by Chema
this is a long comment.
It has multiple lines.
*/
//import scala.io.StdIn._
println("Enter a time with colons between the values of hours, minutes, and seconds")
val time = "13:42:16"
//val time = scala.io.StdIn.readLine
val firstColon = time.indexOf(":")
val secondColon = time.lastIndexOf(":")
val hours = time.substring(0,firstColon).toInt
val minutes = time.substring(firstColon + 1, secondColon).toInt
val seconds = time.substring(secondColon + 1).toInt
println(s"$hours,$minutes,$seconds")

val totalSeconds = hours*3600 + minutes*60 + seconds
println(s"Total Seconds = $totalSeconds")