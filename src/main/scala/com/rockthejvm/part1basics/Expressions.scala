package com.rockthejvm.part1basics

object Expressions {

  // expressions are structures that can be evalusted to a value
  val meaningOfLife = 40 + 2

  // mathematical expressions: +, -, *, bitwise |, bitwise &, <<, >>, >>>
  val mathExpression = 2 + 3 * 4

  // comparasion operations : < , <=, >, >=, ==, !=
  val equalityTest = 1 == 1

  // boolean expressions: !, ||, &&
  val nonEqualityTest = !equalityTest

  // instructions vs expressions
  // expressions are evaluated, isntructions are executed
  // we think in terms of expressions

  // ifs are expressions
  val aCondition = true
  val anIfExpression = if (aCondition) 45 else 99 // we have values at end of branches and not instructions

  // code blocks
  val aCodeBlock = {
    val localVal = 78
    // more expressions
    // ..
    // last expressions is the value of the block
    /* return */ localVal + 54
  }

  // everything is an expression
  val someVal = {
    2 < 3
  } // true

  val someOtherValue = {
    if(someVal) 239 else 986
    42
  } //42

  val yetAnotherVal = println("Scala") // it is of type Unit


  def main(args: Array[String]): Unit = {

//    println(meaningOfLife)

//    println(anIfExpression)
//    println(if (aCondition) 45 else 99)
    println(someVal) // true
    println(someOtherValue) // 42
    println(yetAnotherVal) // () : the unit value
  }
}
