package com.rockthejvm.practice

import javax.sql.rowset.Predicate
import scala.annotation.tailrec

// singly linked list
abstract class LList[A] {
  def head : A
  def tail : LList[A]
  def isEmpty: Boolean
//  def add(element: Int): LList // add at the front: to practice immutability, return a new list instead of modifying the original one
  def add(element: A): LList[A] = Cons(element, this) // define here as its same in both implementations of LList: git rid of 'new' because of case class: uses 'apply'
//  override def toString: String = super.toString

  // concatenation
//  def concatenate(anotherList: LList[A]): LList[A]
  infix def ++(anotherList: LList[A]): LList[A]

//  def map[B](transformer: Transformer[A, B]) : LList[B]
//  def filter(predicate: Predicate[A]): LList[A]
//  def flatMap[B](transformer: Transformer[A, LList[B]]): LList[B]

  def map[B](transformer: A => B): LList[B] // moving from trait definitions of transformers to functions

  def filter(predicate: A => Boolean): LList[A]

  def flatMap[B](transformer: A => LList[B]): LList[B]

  def forEach(transformer: A => Unit): Unit

  def sort(compare: (A, A) => Int) : LList[A]

  def zipWith[B, T](list2: LList[T], function: (A, T) => B ): LList[B]

  def foldLeft[B](start: B)(operator: (B, A) => B) : B

  def withFilter(predicate: A => Boolean): LList[A] = filter(predicate)
//  def withFilter(predicate: A => Boolean): LList[A]
}

case class Empty[A]() extends LList[A] {
  override def head: A = throw new NoSuchElementException()
  override def tail: LList[A] = throw new NoSuchElementException()
  override def isEmpty: Boolean = true
  override def toString: String = "[]"
//  override def add(element: Int): LList =  new Cons(element, this)

  //concatenation
  override infix def ++(anotherList: LList[A]): LList[A] = anotherList

  override def map[B](transformer: A => B): LList[B] = Empty()
  override def filter(predicate: A => Boolean): LList[A] = this // or return new Empty[A] if u want: TRY

  override def flatMap[B](transformer: A => LList[B]): LList[B] = Empty()

  override def forEach(transformer: A => Unit): Unit = ()

  override def sort(compare: (A, A) => Int): LList[A] = this

  override def zipWith[B, T](list2: LList[T], function: (A, T) => B): LList[B] = {
    if( !list2.isEmpty) {
      throw new IllegalArgumentException("List passed too long: Empty list Zipping with no empty list")
    }
    new Empty[B]
  }

  override def foldLeft[B](start: B)(operator: (B, A) => B): B = start

//  override def withFilter(predicate: A => Boolean): LList[A] = this
}

//class Cons(element: Int, next: LList) extends LList {
//  override def head: Int = this.element
//  override def tail: LList = this.next
//  override def isEmpty: Boolean = false
//  override def add(element: Int): LList = new Cons(element, this)
//}

  case class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
    override def isEmpty: Boolean = false
  //  override def add(element: Int): LList = new Cons(element, this)
    override def toString: String = {
      @tailrec
      def concatenateElements(remainder: LList[A], acc: String): String = {
        if(remainder.isEmpty) acc
  //      else concatenateElements(remainder.tail, remainder.head + acc)
        else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")
      }
      s"[${concatenateElements(this.tail, s"$head")}]"
    }

    //concatenation
  /*
    [1, 2, 3] ++ [4, 5, 6]
  new Cons(1, [2, 3] ++ [4, 5, 6])
  new COns(1, new Cons(2, [3] ++ [4, 5, 6]))
  ...
  [1, 2, 3, 4, 5, 6]
   */
    override def ++(anotherList: LList[A]): LList[A] = {
      Cons(head, tail ++ anotherList)
    }

    /*
      [1,2,3].map(n*2)
      new Cons(2, [2,3].map(n*2)) =
      new Cons(2, new Cons(4, [3].map(n*2)) =
      new Cons(2, new Cons(4, new Cons(6, [].map(n*2))) =
      new Cons(2, new COns(4, new Cons(6, [] ))) =
      [2, 4, 6]
     */
    override def map[B](transformer: A => B): LList[B] = {
      Cons(transformer.apply(head), tail.map(transformer))
    }

    /*
    example:
    [1,2,3].fiter(n%2 == 0) =
    [2, 3].filter(n % 2 == 0) =
    new Cons(2, [3].filter(n%2 == 0) ) =
    new Cons(2, [].filter(n%2 == 0) =
    new Cons(2, [])
    [2]
     */
    override def filter(predicate: A => Boolean): LList[A] = {
      if( predicate.apply(head))  Cons(head, tail.filter(predicate)) // test is now apply
      else tail.filter(predicate)
    }

    /*
    [1, 2, 3].flatMap(n => [n, n+1]) =
    [1, 2] ++ [2, 3].flatMap(n => [n, n+1]) =
    [1, 2] ++ [2, 3] ++ [3].flatMap(n => [n, n+1] =
    ..
    [1, 2, 2, 3, 3, 4]
     */
    override def flatMap[B](transformer: A => LList[B]): LList[B] = {
      transformer.apply(head) ++ tail.flatMap(transformer) // transform is now apply after converting transformer from a trait to a function
    }

    override def forEach(transformer: A => Unit): Unit = {
      transformer(head);
      this.tail.forEach(transformer)
    }

    override def sort(compare: (A, A) => Int): LList[A] = {
      // insertion sort: O(n^2) : stack recursive
      /*
      compare = x - y
      insert(3, [1, 2, 4]) =
      COns(1, insert(3, [2, 4])) =
      ...
       */
      def insert(elem: A, sortedList: LList[A]) : LList[A] =
        if(sortedList.isEmpty) Cons(elem, Empty())
        else if(compare(elem, sortedList.head) <= 0) Cons(elem, sortedList)
        else Cons(sortedList.head, insert(elem, sortedList.tail))

      val sortedTail = tail.sort(compare)
      insert(head, sortedTail)
    }

    override def zipWith[B, T](list2: LList[T], zip: (A, T) => B): LList[B] = {
      if (list2.isEmpty ) {
        throw new IllegalArgumentException("List passed too short")
      }
      new Cons[B](zip(head, list2.head), tail.zipWith[B, T](list2.tail, zip))
    }

    /*
    [1, 2, 3, 4].foldLeft(0)(x + y)
    = [2, 3, 4].foldLeft(1)(x + y)
    = [3, 4].foldLeft(3)(x + y)
    ...
    = [].foldLeft(10)(x + y)
    = 10 // from Empty implementation
     */
    override def foldLeft[B](start: B)(operator: (B, A) => B): B = {
      tail.foldLeft(operator(start, head))(operator)
    }

//    override def withFilter(predicate: A => Boolean): LList[A] = {
//      if(predicate(head)) Cons(head, tail.withFilter(predicate))
//      else tail.withFilter(predicate)
//    }
  }

  /**
   * Exercise: LList extension
   *
   * 1.  Generic trait Predicate[T] with a little method test(T) => Boolean
   * 2.  Generic trait Transformer[A, B] with a method transform(A) => B
   * 3.  LList:
   * - map(transformer: Transformer[A, B]) => LList[B]
   * - filter(predicate: Predicate[A]) => LList[A]
   * - flatMap(transformer from A to LList[B]) => LList[B]
   *
   * class EvenPredicate extends Predicate[Int]
   * class StringToIntTransformer extends Transformer[String, Int]
   *
   * [1,2,3].map(n * 2) = [2,4,6]
   * [1,2,3,4].filter(n % 2 == 0) = [2,4]
   * [1,2,3].flatMap(n => [n, n+1]) => [1,2, 2,3, 3,4]
 */
//
//  trait Predicate[T] {
//    def test(element: T): Boolean // T => Boolean
//  }
//
//  def makePredicate[A]: Function1[A, Boolean] = new Function1[A, Boolean] {
//    override def apply(v1: A): Boolean = true
//  }
//
//  val alwaysTruePredicate: Int => Boolean = makePredicate[Int]
//
////
////val Predicate = new Function1[A, Boolean] {
////  override def apply(v1: A): Boolean = true // wrong way; first make a generic functio, then a specific val object for that function
////}
//
//  class EvenPredicate extends Predicate[Int] {
//    override def test(element: Int): Boolean =
//      element % 2 == 0
//  }
//
//  trait Transformer[A, B] {
//    def transform(value: A): B // A => B
//  }
//
////  val transformer = new Function1[A, B] {
////    override def apply(v1: A): B = ???
////  }
//
////  class Doubler extends Transformer[Int, Int] {
////    override def transform(element: Int): Int =
////      2 * element
////  }
//
//  class StringToIntTransformer extends Transformer[String, Int] {
//    override def transform(value: String): Int = value.toInt
//  }
//
//  class DoublerList extends Transformer[Int, LList[Int]] {
//    override def transform(value: Int): LList[Int] = new Cons[Int](value, new Cons[Int](value+1, new Empty)) // can get rif of new becasue of case class that automatically
//    // has apply method
//  }

  object LList {
    @tailrec
    def find[A](list: LList[A], predicate: A => Boolean): A = {
      if(list.isEmpty) throw new NoSuchElementException
      else if (predicate.apply(list.head)) list.head // test is now called apply : can also just skip apply() and do predicate(list.head)
      else find(list.tail, predicate)
    }

  }




object LListTest {

  def main(args: Array[String]): Unit = {
    val empty = new Empty[Int] // []
    println(empty)
    println(empty.isEmpty)

    val first3elements = Cons(1, Cons(2, Cons(3, empty)))
    val first3Numbers_v2 = empty.add(1).add(2).add(3)
    println(first3elements)
    println(first3Numbers_v2)
    println(first3elements.isEmpty)

    // using generics to make list of strings
    val someStrings = new Cons[String]("Dog", new Cons[String]("Cat", new Empty[String]))
    println(someStrings)

//    val evenPredicate = new Predicate[Int] {
//      override def test(element: Int): Boolean = element % 2 == 0
//    }

//    val evenPredicate = new Function1[Int, Boolean] {
//      override def apply(element: Int): Boolean = element % 2 == 0
//    }

    val evenPredicate: Int => Boolean = _ % 2 == 0



//    val doubler = new Function1[Int, Int] {
//      override def apply(value: Int): Int = 2*value
//    }

    val doubler: Int => Int = _ * 2

//    val doublerList = new Function1[Int, LList[Int]] {
//      override def apply(value: Int): LList[Int] = new Cons[Int](value, new Cons[Int](value+1, new Empty))
//    }

    val doublerList: Int => LList[Int] = x => new Cons[Int](x, new Cons[Int](x + 1, new Empty)) // replacing with lambdas

    // map testing
    val numbersDoubled = first3elements.map(doubler)
    val numbersDoubled_v2 = first3elements.map(x => x * 2)
    val numbersDoubled_v3 = first3elements.map( _ * 2)

    println(numbersDoubled)
//    val  numbersNested = first3elements.map(new DoublerList)
    val  numbersNested = first3elements.map(doublerList)
    val  numbersNested_v2 = first3elements.map(x => new Cons[Int](x, new Cons[Int](x + 1, new Empty)))
    println(numbersNested)

    // filter testing
    val numbersEven = first3elements.filter(evenPredicate)
    val numbersEven_v2 = first3elements.filter(elem => elem % 2 == 0)
    val numbersEven_v3 = first3elements.filter(_ % 2 == 0)
    println("EVEN: " + numbersEven) // [2]

    // test concatenation
    val listInBothWays = first3elements ++ first3Numbers_v2
    println("Concatenation:" + listInBothWays)

    //test flat-map
    val flattenedList = first3elements.flatMap(doublerList)
    println("Flattened doubler list: "+ flattenedList)

    // find test
    println("FIND: " + LList.find(first3elements, evenPredicate))
//    println("FIND_v2: " + LList.find(first3elements, new Predicate[Int] {
//      override def test(element: Int): Boolean = element > 5
//    })) // throws a no such element exception

    // testing for each
    print("Printing first three numbers using for-each: ")
    first3elements.forEach(x => print(x))
    first3elements.forEach(println) // ** magic ** : methods with just one argument

    // testing for zip with
//    def sum: (a: A, b: A) => a + b
    val zippedSum = first3elements.zipWith(first3Numbers_v2, (x, y) => x + y)
    print("Zipped sum of 1, 2, 3 and 3, 2, 1: ")
    zippedSum.forEach(x => print(x))
    println()

    val twoEle = new Cons[Int](1, new Cons[Int](2, new Empty[Int]))

//    twoEle.zipWith(first3elements, (x, y) => x + y) // gives illegeal arg exception : list passes TOO LONG
//    first3elements.zipWith(twoEle, (x, y) => x + y) // gives illegeal arg exception : list passes TOO short
    val zippedList = someStrings.zipWith(twoEle, (string, number) => s"$number - $string")
    println(zippedList)

    println()

    // testing sorting
    println("Testing Sorting")
    val sorted_numbers = first3Numbers_v2.sort((x, y) => x - y)
    println(sorted_numbers)
    println(first3Numbers_v2.sort(_ - _)) // shorthand

    // testting fold
    println(first3elements.foldLeft(10)((x, y) => x + y))
    println(first3elements.foldLeft(10)(_ + _) )
  }
}


