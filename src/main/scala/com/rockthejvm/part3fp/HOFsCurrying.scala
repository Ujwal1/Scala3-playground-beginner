package com.rockthejvm.part3fp

import scala.annotation.tailrec
import scala.language.postfixOps

object HOFsCurrying {

  // higher order functions
  val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1
  val anotherHof : Int => (Int => Int) = x => (y => y + 2*x)

  // quick exercise
//  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = {
//    ( (x: Int, (s, y => b)) => x2)  => (z => z + 1)
//  }

  val superFunction1: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = {
    (x: Int, func) => (z => z + 1)
  }

  val superFunction2: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = {
    (x: Int, f1: (s: String, f2: Int => Boolean) => Int) => (z => z + 1)
  }

  // examples: map, filter, flatmap : HOFs :higher order methods
  // more examples
  // f(f(f(...(f(x))))) : apply the function n times on initial argument of x
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int) : Int = {
    if(n == 0) return x
    else nTimes(f, n-1, f(x))
  }


  def sumOne(x: Int): Int = x + 1

  val a: Int = nTimes(sumOne, 10, 11)
  println(a)

  val tenThousand: Int = nTimes(sumOne, 10000, 0)
  println(tenThousand)

  // instead of invoking the function n times, obtain a new function that does function f, n times, on whatever argument I later pass
  /*
  ntv2(sumOne, 3) =
  (x: Int) => ntv2(sumOne, 2)(sumOne(x)) = sumOne(sumOne(sumOne(x)))

  ntv2(sumOne, 2) =
  (x: Int) => ntv2(sumOne, 1)(sumOne(x)) = sumOne(sumOne(x))

  ntv2(sumOne, 1) =
  (x: Int) = ntv2(sumOne, 0)(sumOne(x)) = sumOne(x)

  ntv2(sumOne, 0) = (x: Int) => x
   */
  def nTimes_v2(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimes_v2(f, n - 1)(f(x)) // gives SO error for large arguments
  }

  val plusTenThousand = nTimes_v2(sumOne, 100) // crashes for 10000
  val tenThousand_v2: Int = plusTenThousand(0)
  println(tenThousand_v2)

  // currying = HOFs returning function instances
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3: Int => Int = superAdder(3)
  val invokeSuperAdder: Int = superAdder(3)(100)
  println("Currying HOFs " + invokeSuperAdder)

  // curried mehtods: methods with multiple argument lists
  def curriedFormatter(fmt: String)(x:Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // (x : Double) => "%4.2f".format(x)
  val preceiseFormat: (Double => String) = curriedFormatter("%10.8f") // (x : Double) => "10.8f".format(x)

  /*
  1. LList exercises:
    - foreach(A => Unit)
        [1, 2, 3].foreach(x => println(x))

    - sort((A, A) => Int) : LList[A]
        [1, 3, 2, 4].sort((x - y) => x - y) = [1, 2, 3, 4]
    // use insertion sort

    - zipWith[B](LList[A], (A, A) => B) : LList[B]
        [1, 2, 3].zipWith([4, 5, 6], x * y) => [1* 4, 2*5, 3* 6] = [4, 10, 18]
    // throw an expeption if two lists are not of same length

    - foldLeft[B](start: B)((A, B) => B) : B
        [1, 2, 3, 4].foldLeft[Int](0)(x + y) : Int = 10
        0 + 1 = 1
        1 + 2 = 3
        3 + 3 = 6
        6 + 4 = 10

    2. toCurry(f: (Int, Int) => Int : Int => Int => Int
       fromCurry(f: (Int => Int => Int)): (Int, Int) => Int

    3. compose(f, g) => x => f(g(x))
       andThen(f, g) => x => g(f(x))
   */

  def toCurry(f:(Int, Int) => Int) : Int => Int => Int = {
    (x: Int) => (y: Int) => f(x, y)
  }

  def toCurry_v2[A, B, C](f: (A, B) => C): A => B => C = {
    (x) => (y) => f(x, y)
  }



  def sum(x: Int, y: Int) : Int = x+y

  val curriedFunc = toCurry(sum)
  val superAdder_v2 = toCurry( (x, y) => x + y)
  val superAdder_v3 = toCurry( _ + _)
  val superAdder_v4 = toCurry_v2[Int, Int, Int](_ + _)
  println("Currying a function" + curriedFunc(10)(12))

  def fromCurry(f: (Int => Int => Int)) : (Int, Int) => Int = {
    (x: Int, y: Int) => f(x)(y)
  }

  val simple_adder = fromCurry(superAdder)
  println(simple_adder(2, 34))

  // 3
  def compose(f: Int => Int, g: Int => Int): Int => Int = {
    x => f(g(x))
  }

  def compose_v2[A, B, C](f: B => C, g: A => B): A => C = {
    x => f(g(x))
  }

  val incrementer = (x : Int) => x + 1
  val doubler = (x : Int) => 2 * x

  val composedApp = compose(incrementer, doubler)
  val composedApp_v2 = compose_v2(incrementer, doubler)

  println(composedApp(10))
  println(composedApp_v2(10))

  def andThen(f: Int => Int, g: Int => Int) : Int => Int = {
    x => g(f(x))
  }

  def andThen_v2[A, B, C](f: A => B, g: B => C): A => C = {
    x => g(f(x))
  }

  val andThenApp = andThen(incrementer, doubler)
  println(andThenApp(10))



  def main(args: Array[String]): Unit = {
//    println(standardFormat(Math.PI))
//    println(preceiseFormat(Math.PI))
  }
}
