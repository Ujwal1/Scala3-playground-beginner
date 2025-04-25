package com.rockthejvm.part3fp

object MapFlatMapFiIterFor {

  val aList = List(1, 2, 3) // 1 -> 2 -> 2 -> 3 -> Nil // [1, 2, 3]
  val firstElement = aList.head
  val restOfElements = aList.tail

  // map, flatmap, filter
  val anIncrementedList = aList.map( _ + 1)

  // filter
  val inlyOddNumbers = aList.filter(_ % 2 != 0)

  // flatmap
  val toPair = (x: Int) => List(x, x+ 1)
  val aFlatMappedList = aList.flatMap(toPair) // [1, 2, 2, 3, 3, 4]

  // generate all possible combinations of all the elements of those lists , in the fformat "1a - black"
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white", "red")

  /*
  Smaller example to join numbers and chars only:
  lambda = num => chars.map(char => s"$num$char")
  [1, 2, 3, 4].flatmap(lambda) = ["1a", "1b", "1c", "1d", "2a", "2b" , "2c", "2d", "3a", "3b", "3c", "3d", "4a", "4b", "4c", "4d"]
  lambda(1) = chars.flatMap(char => s"1$char") = ["1a", "1b", "1c", "1d"]
  lambda(2) = chars.flatMap(chars => s"2$char") = ["2a", "2b", "2c", "2d"]
  ....
  = ["1a", "1b", "1c", "1d", "2a", "2b" , "2c", "2d", "3a", "3b", "3c", "3d", "4a", "4b", "4c", "4d"]
   */

  val combinedList = numbers.flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))
  println(combinedList)

  // for-comprehension : IDENTICAL to flat-map and map chains
  val combinedList_v2 = for { // not iteration: it is an expression
    number <- numbers // generator
    char <- chars
    color <- colors
  } yield s"$number$char - $color"

  println(combinedList_v2)

  // same stuff with just the even numbers with all the chars and all the colors
  // withFilter vs Filter
  val combinedList_v3 = numbers.withFilter(_ % 2 == 0).flatMap(number => chars.flatMap(char => colors.map(color => s"$number$char - $color")))
  println(combinedList_v3)

  val combinedList_v4 = for { // not iteration: it is an expression
    number <- numbers if number % 2 == 0 // generator, IF GUARD. Each generator is a flatMap chain except the final generator which is a map chain
    char <- chars
    color <- colors
  } yield s"$number$char - $color"

  println(combinedList_v4)

  // for comprehensions with Unit
  // if the collection has a :  foreach method, we can skip the yeild and jsut call a function

  /*
  1. LList supports for-comprehension ??
  2. A small collection of atmost ONE element - Maybe[A] // this maybe data strructure is inspired form haskel
    - map
    - flatMap
    - filter
   */
  import com.rockthejvm.practice.*

  val LSimpleNumbers = Cons(1, Cons(2, Cons(3, Empty())))
  // map, flatMap, filter
  val filteredNumbers = LSimpleNumbers.filter(_ % 2 == 0)
  val incrementedNumbers = LSimpleNumbers.map(_ + 1)
  val toPairLList = (x: Int) => Cons(x, Cons(x + 1, Empty()))
  val flatMappedNumbers = LSimpleNumbers.flatMap(toPairLList)

  // map + flatmap = for comprehension. Need to implement withFilter to support if-guards in for-comprehension
  val combinationNumbers: LList[String] = for {
    number <- LSimpleNumbers if number % 2 == 0
    char <- Cons('a', Cons('b', Cons('c', Empty())))
  } yield s"$number - $char"

  println(combinationNumbers)





  def main(args: Array[String]): Unit = {
//    for {
//      num <- numbers
//    } println(num+10)

//    numbers.foreach(println)
  }
}
