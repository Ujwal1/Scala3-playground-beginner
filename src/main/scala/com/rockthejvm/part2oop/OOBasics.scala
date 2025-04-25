package com.rockthejvm.part2oop

import scala.language.postfixOps

object OOBasics {

  // this class has constructor arguments
  class Person(name: String, age: Int)

  val aPerson: Person = new Person("John", 26)
//  val john = aPerson.name // wong. scala makes distinction bw constructor args and potential fields of the class

  class Person_v2(val  name: String = "Jane Doe", age: Int = 0) {
    //fields
    val allCaps = name.toUpperCase()

    // methods
    def greet(name: String) : Unit = {
      println(s"${this.name} says : Hi, $name") // use this to disambiguate 'name'
    }

    // signature differs
    // OVERLOADING
    def greet() : String =
      s"Hi everyone, my n mae is $name"

    // auxiliary constructor
//    def this (name: String) =
//      this(name, 0)
//
//    def this() =
//      this("Jane Doe")
  }
  val bPerson : Person_v2 = new Person_v2("Mike", 34)
  val bigMike = bPerson.allCaps
  println(bigMike)

  bPerson.greet("Ujwal")
  println( bPerson.greet() )

  // exercises
  class Writer(val fName: String, val lName: String, val yBirth: Int) {
    def fullName() : String =
      s"$fName $lName"
  }

  class Novel(val name: String, val yor: Int, val author: Writer ) {
    def authorAge(): Int =
      this.yor - author.yBirth

    def isWrittenBy(name: String) : Boolean =
      name == this.name

    def copy(newYor: Int) : Novel =
      new Novel(name, newYor, author)
  }

  // exercise 2: immutable counter class
  // constructed with initial count
  // - increment / decrement: => NEW instance of counter
  // val can't be modified: its constant
  // - increment(n)/ decrement(n) => NEW instance of COUNTER
  //

  class Counter(val count: Int)  {
    def increment() : Counter = {
      new Counter(count+1)
    }

    def decrement() : Counter = {
      new Counter(count-1)
    }

    def increment(n: Int) : Counter = {
      new Counter(count + n)
    }

    def decrement(n: Int): Counter = {
      new Counter(count - n)
    }

    def print() = {
      println(s"$count")
    }
  }


  def main(args: Array[String]): Unit = {
    val aCount = new Counter(10)
    aCount.print()
    val bCount = aCount.increment()
    bCount.print()
    val cCount = bCount.decrement(4)
    cCount.print()
    cCount.increment().print()
    cCount.print() // immutable data structures : miracles in distributed environments
  }
}
