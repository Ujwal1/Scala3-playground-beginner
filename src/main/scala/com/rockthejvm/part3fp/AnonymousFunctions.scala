package com.rockthejvm.part3fp

object AnonymousFunctions {

  // function instances : instances of functionN : comes standard in Scala
  val doubler: Int => Int = new Function1[Int, Int]:
    override def apply(v1: Int): Int = v1 * 2


  // lambdas = anonymous function instances
  val doubler_v2: Int => Int = (x: Int) => x * 2 // identical
  val doubler_v3 = (x: Int) => x * 2

  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y // new Function2(Int, Int, Int) { override def apply(x: Int, y: Int) : Int = x + y }

  // zero arg functions
  val justDoSomething : () => Int = () => 42

  val anInvocation = justDoSomething()

  // alternate syntax with curly braces
  val stringToInt = { (str: String) =>
    // implementation: code block
    str.toInt
  }

  val stringToInt_Boring = (str: String) => {
    // code block
  }

  // type inference
  val doubler_v4: Int=> Int = x => x * 2 // no need t define type on right of = because of type inference : TYPE INFERRED BY COMPILER
  val adder_v2: (Int, Int) => Int = (x, y) => x + y

  // shortest lambdas
  val doubler_v5 : Int => Int = _ * 2 // x => x * 2 : _ means first arg of the function
  val adder_v3: (Int, Int) => Int = _ + _ // each underscore means a different argument, you cannot reuse them: (x, y) => x + y

  /*
  Exercises:
  1. Replace all FunctioN instantiazations with lambda in LList instantiazations
  2. rewrite the 'special' adder : superadder from WhatsAFunction using lambdas
   */





  def main(args: Array[String]): Unit = {
    println(justDoSomething)
    println(justDoSomething())

  }
}
