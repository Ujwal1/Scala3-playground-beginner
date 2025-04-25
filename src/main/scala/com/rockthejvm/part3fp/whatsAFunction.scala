package com.rockthejvm.part3fp

import scala.language.postfixOps

object whatsAFunction {

  // fp: funations are first class citizens
  // JVM: Java: canonical object oriented language

  trait MyFunction[A, B] {
    def apply(arg: A) : B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(arg: Int): Int = arg * 2
  }

  val meaningofLife = 42
  val meaningDoubled = doubler(meaningofLife) // aoubler.apply(meaningOfLife) : apply() is special and can be invoked by the object directly
  // instance of the trait(MyFunction) can be involed as a function
  // hence function is now a first class citizen by creating a trait with an apply method

  // function types
  val doublerStandard = new Function1[Int, Int] {
    override def apply(arg: Int) : Int = arg * 2
  }

  val meaningDoubled_v2 = doublerStandard(meaningofLife)

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  val addition = adder(2, 34)

  // (Int, String, Double, Boolean) => Int ==== Function4(Int, String, Double, Boolean, Int) // hover to see
  val aThreeArgFunction = new Function4[ Int, String, Double, Int, Int] {
    override def apply(v1: Int, v2: String, v3: Double, v4: Int): Int = 100
  }

  // all functions are instances of functionX with apply methods
  /*
  1. a function which takes two strings as args and concatenates them
  2. is Predicate and Transformer equivalent to some kind of functions: how?? replace traits Predicate and transformer in LList
  : to see if those two types can be expressed as functions
  3. Define a fnction that takes an Int as an arg and returns another function as a result

   */

  val concatString: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  val concatenatedString = concatString("Hello ", "World")
  println(concatenatedString)

  // 3
  def getFunc(value: Int): Int => Int = { // takes an int and returns a function
    val func1  = new Function1[Int, Int] {
      override def apply(a: Int): Int = value*100
    }
    func1
  }
  println(getFunc(9).apply(12))
  val a = getFunc(9)
  val a2 = a(12)
  println(a2)

  val superAdder = new Function1[Int, Function1[Int, Int]] { // Function1
    override def apply(x: Int): Int => Int = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder2 = superAdder(2)
  val anAddition_v2 = adder2(67) // 69
  val superadder_v3 = superAdder(10)(90) // currying

  // superadder uisng lambdas
  val superAdder_v2 : Int => Int => Int = x => (y => x + y)
  val superAdder_v22 = (x: Int) => (y: Int) => x + y

  val add_v21 = superAdder_v2(99)
  val add_v22 = add_v21(11)
  println("SuperAdder via lambdas: " + add_v22)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
  println(superAdder_v2(99)(11))
  println(superAdder_v22(99)(11))

  // function values are not the same as methods: def are methods of classes or traits

  // 2. yes: predicate[T] equivalent as Function1[T, Boolean] === t => Boolean
  // yes: Transformer[A, B] equivalent with FUnction1[A, B] === A => B

  def main(args: Array[String]): Unit = {

  }
}
