package com.rockthejvm.part1basics

object CBNvsCBV {

  // CBV = call by value = arguments are evaluated  before function invocation
  def aFunction(arg: Int): Int = arg + 1
  val aComputation: Int = aFunction(66 + 99)

  // CBN = Call by name = argumants are passed LITERALLY , evaluated at every reference
  def aByNameFunction( arg : => Int) : Int = arg + 1
  val anotherComputation = aByNameFunction(66 + 99)

  def printTwiceByValue(x : Long) : Unit = {
    println("By value " + x)
    println("By value " + x)
  }


  // argumant is evaluated every time it is used
  // delated evaluation of argument
  def printTwiceByName(x: => Long): Unit = {
    println("By name " + x)
    println("By name " + x)
  }

  def infinite() : Int = 1 + infinite()
  def printFirst(a: Int, b: => Int) = println(a)

// the variables passed are actually expressions in pass by name


  def main(args: Array[String]): Unit = {
//    println(aComputation)
//    println(anotherComputation)

    printTwiceByValue(System.nanoTime())
    printTwiceByName(System.nanoTime()) // different values printed

//    printFirst(infinite(), 10) // crashes
    printFirst(10, infinite()) // runs smoothly as infinite() is passed by name/reference and is never evaluated as its never used
  }
}
