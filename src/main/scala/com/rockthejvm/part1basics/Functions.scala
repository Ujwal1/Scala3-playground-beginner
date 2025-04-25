package com.rockthejvm.part1basics

object Functions {

  // the definition of the function is always a single expression. U can cut out the {} after = if the expression is very simple
  def aFunction(a: String, b: Int) : String = {
    a + " " + b
  }

  def bFunction(a: String, b: Int): String =
    a + " " + b

  // function invocation
  val aFunctionInvocation = aFunction("Scala", 1234)

  def aNoArgFunction(): Int = 45
  def aParameterlessFunction: Int = 45

  // functions can be recursive
  def stringConcat(str: String, n: Int): String = {
    if( n == 0) ""
    else if (n == 1) str
    else str + stringConcat(str, n - 1)
  }

  val scalaX3 = stringConcat("Scala", 3)
  // when u need loops, use recursion as the values are immutable here

  // "void" functions
   def aVoidFunctions(aString: String): Unit =
     println(aString)

  def computeDoubleStringWithSideEffect(aString: String): String = {
    aVoidFunctions(aString) // Unit
    aString + aString // meaningful value
  } // discouraging side effects

  def aBigFunction(n:Int) : Int = {
    // small auxilary funcrions inside
    def aSmallerFunction(a:Int, b:Int):Int = a+b

    aSmallerFunction(n, n+1)
  }

  def greetingsFunction(name: String, age: Int) : String = {
    "I am " + name + " and I am " + age + " years old."
  }

  def factorial(n: Int) : Int = {
    if (n == 1) 1
    else n * factorial(n-1)
  }

  def nThFibonacci(n: Int) : Int = {
    if( n == 1) 1
    else if(n == 2) 1
    else nThFibonacci(n-1) + nThFibonacci(n-2)
  }

  def isPrime(n:Int) : Boolean = {
    def isPrimeUntil(t:Int) : Boolean ={
      if(t <= 1 ) true
      else n%t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n/2)
  }
  // this above is actually tail recursiona as the expression on left of && defices. It its false, right side is skipped,
  // else only right side is considered.
  // if ( t <= 1 ) true
  // else if ( n % t == 0 ) false
  // else isPrimeUntil(t-1)
  // simplified easy to read reson as to why it is tail recursion


  def main(args: Array[String]): Unit = {
//    println(scalaX3)

    val a = greetingsFunction("Ujwal", 25)
    println(a)
    println(factorial(4))
    println(nThFibonacci(6))
    println(isPrime(99))
  }
}
