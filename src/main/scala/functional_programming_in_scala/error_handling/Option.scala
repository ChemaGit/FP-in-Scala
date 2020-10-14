package functional_programming_in_scala.error_handling

//hide std library `Option` and `Either`, since we are writing our own
import scala.{Option => _, Either => _, _}



sealed trait Option[+A] {
  // Apply f if the Option is not None
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }
  // Apply f, which may fail, to the Option if not None
  def flatMap[B](f: A => Option[B]): Option[B] = {
    map(f).getOrElse(None)
  }
  def flatMap2[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  /**
    * Returns the result the Some case of the Option, or if the
    * Option is None, returns the given default value
    * @param default
    * @tparam B
    * @return
    */
  // The B >: A says that the B type parameter must be a supertype of A
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(a) => a
  }
  /**
    * Returns the first Option if it's defined; otherwise, it returns
    * the second Option
    * @param ob
    * @return
    */
  // Don't evaluate ob unless needed
  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case Some(a) => Some(a)
    case None => ob
  }

  def orElse2[B>:A](ob: => Option[B]): Option[B] =
    this.map(Some(_)).getOrElse(ob)


  // Convert Some to None if the value doesn't satisfy f.
  def filter(f: A => Boolean): Option[A] = {
    this.map(f).getOrElse(false) match {
      case false => None
      case _ => this
    }
  }
  def filter2(f: A => Boolean): Option[A] = this match {
    case Some(a) if f(a) => this
    case _ => None

  }
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  /**
    * Implement the variance function in terms of flatMap.
    * If the mean of a sequence is m, the variance is the mean
    * of math.pow(x - m, 2) for each element x
    * in the sequence
    * @param xs
    * @return
    */
  def variance(xs: Seq[Double]): Option[Double] = {
    xs match {
      case Nil => None
      case ls => {
        mean(ls).flatMap(d => mean(xs.map(ds => Math.pow(ds - d,2))))
      }
    }
  }

  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f
  val abs0: Option[Double] => Option[Double] = lift(math.abs)

  /**
    * Write a generic function map2 that combines two Option values using
    * a binary function. If either Option value is None , then the return value is too.
    *
    * @param a
    * @param b
    * @param f
    * @tparam A
    * @tparam B
    * @tparam C
    * @return
    */
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    a.flatMap (aa => b.map(bb => f(aa, bb)))

  // And here’s the exact same code written as a for-comprehension:
  def map3[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C):
  Option[C] =
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  /*A for-comprehension consists of a sequence of bindings, like aa <- a, followed by a
  yield after the closing brace, where the yield may make use of any of the values
    on the left side of any previous <- binding. The compiler desugars the bindings to
    flatMap calls, with the final binding and yield being converted to a call to map.*/

  def Try[A](a: => A): Option[A] = {
    try Some(a)
    catch {case e: Exception => None}
  }

  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double = ???

  def parseInsuranceRateQuote(age: String, numberOfSpeedingTickets: String): Option[Double] = {
    val optAge: Option[Int] = Try(age.toInt)
    val optTickets: Option[Int] = Try(numberOfSpeedingTickets.toInt)
    // If either parse fails, this will immediately return none
    map2(optAge, optTickets)(insuranceRateQuote)
  }

  /**
    * Write a function sequence that combines a list of Option s into one Option containing
    * a list of all the Some values in the original list. If the original list contains None even
    * once, the result of the function should be None ; otherwise the result should be Some
    * with a list of all the values.
    * @param a
    * @tparam A
    * @return
    */
  def sequence[A](a: List[Option[A]]): Option[List[A]] =
    a match {
      case Nil => Some(Nil)
      case h :: t => h.flatMap(hh => sequence(t).map(v => hh :: v))
    }
  /*
  It can also be implemented using `foldRight` and `map2`. The type annotation on `foldRight` is needed here; otherwise
  Scala wrongly infers the result type of the fold as `Some[Nil.type]` and reports a type error (try it!). This is an
  unfortunate consequence of Scala using subtyping to encode algebraic data types.
  */
  def sequence_1[A](a: List[Option[A]]): Option[List[A]] =
    a.foldRight[Option[List[A]]](Some(Nil))((x,y) => map2(x,y)((a, b) => a :: b))

  /*
  Unfortunately, this is inefficient, since it traverses the list twice, first to convert each
  String to an Option[Int] , and a second pass to combine these Option[Int] values
  into an Option[List[Int]]
   */
  def parseInts(a: List[String]): Option[List[Int]] =
    sequence(a.map(i => Try(i.toInt)))

  /**
    * try
    * for a more efficient implementation that only looks at the list once. In fact,
    * implement sequence in terms of traverse .
    * @param a
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a match {
      case Nil => Some(Nil)
      case h::t => map2(f(h), traverse(t)(f))(_ :: _)
    }
}


object Main extends App {

  println(Option.abs0.apply(Some(-5)))

  println(Some(List(1,2,3,4)).map(a => a.sum))
  println(Some(List(1,2,3,4)).flatMap(a => Some(a.sum)))
  println(Some(List(1,2,3,4)).flatMap2(a => Some(a.sum)))
  println(None.orElse(Some(List(1,2,3))))
  println(None.orElse2(Some(List(1,2,3))))

  println(Some(List(1,2,3,4)).filter(a => a.contains(7)))
  println(Some(List(1,2,3,4)).filter2(a => a.contains(2)))

  println( Option.sequence(List(Some(1), Some(2), Some(3), Some(4))) )

  println( Option.sequence_1(List(Some(1), Some(2), Some(3), Some(4), Some(5))) )
}