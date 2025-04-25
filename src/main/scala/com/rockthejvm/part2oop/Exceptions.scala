package com.rockthejvm.part2oop

object Exceptions {

  val aString: String = null
  // aString.length crashes with a NPE

  // 1 - throw exceptions
//   val aWeirdValue: Int = throw new NullPointerException // returns Nothing : NOTHING is a proper replacement for any type of exception

  // Exception hieararchy:
  //
  // Throwable:
  //    Error, e.g. SOError, OOMError
  //    Exception, e.g. NPException, NSEException, ....
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // 2 - catch exceptions
  val potentialFail = try {
    // code that might fail
    getInt(true) // an Int
  } catch {
    // most specific exceptions first
    case e: NullPointerException => 35
    case e: RuntimeException => 54 // an Int
    // ...
  } finally {
    // executed no matter what
    // closing resources
    // Unit here
  }

  // 3 - custom exceptions
  class MyException extends RuntimeException {
    // fields or methods
    override def getMessage = "MY EXCEPTION"
  }

  val myException = new MyException

  // SOError
//  def callBack(): Unit = {
//    val a: Int = 100
//    callBack()
//    val b: Int = 101
//  }
//  callBack()

//  // creating a : OOM: Out of memory error
//  def oomCrash(): Unit = {
//    def bigString(n: Int, acc: String) : String = {
//      if (n == 0) acc
//      else bigString(n - 1, acc + acc)
//    }
//    bigString(5678765, "Scalaa")
//  }

//  oomCrash()





  def main(args: Array[String]): Unit = {

  }
}
