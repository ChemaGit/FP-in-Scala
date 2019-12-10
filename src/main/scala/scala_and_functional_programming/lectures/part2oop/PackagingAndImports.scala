package scala_and_functional_programming.lectures.part2oop

 import scala_and_functional_programming.playground.{Cinderella, PrinceCharming} //{PrinceCharming, Cinderella}
//import playground.{PrinceCharming, Cinderella => Princess}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

	// package members are accesible by their simple name
	val writer = new Writer("Chema", "RocktheJVM",2019)

	// import the package
	val princess = new Cinderella
	val princess2 = new Cinderella // playground.Cinderella = fully qualified name

	// packages are in hiereachy
	// matching folder structure.

	// package object
	sayHello
	println(SPEED_OF_LIGHT)

	// imports
	val prince = new PrinceCharming

	val date = new Date
	val sqlDate = new java.sql.Date(2019,11,21)

	val sqlDate2 = new SqlDate(2019,11,21)

	// default imports
	// java.lang wich contains String, Object, Exception ...
	// scala -> Int, Nothing, Function
	// scala.Predef -> println, ???
}

/**
	TAKEAWAYS

		- package = a group of definitions under the same name

		- To use a definition
			- be in the same package
			- or import the package
		- Best practice - mirror the file structure
		- Fully qualified name

			// Person.scala
			package org.rtjvm.oop

			class Person
			object Person

	- package objects hold standalone methods/constants
		- one per package

	- Name aliasing and imports

		import java.sql.{Date => SqlDate}
		import java.util.{Date => JavaDate}
		import java.{util => ju}
**/
