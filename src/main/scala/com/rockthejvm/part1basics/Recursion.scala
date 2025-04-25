package com.rockthejvm.part1basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion {

  // 'repetition' = recursion
  def sumUntil(n:Int):Int = {
    if(n <= 0) 0
    else n + sumUntil(n-1)
  }

  def sumUntil_v2(n:Int): Int = {

    @tailrec
    def sumUntilTailRec(x : Int, accumulator: Int) : Int = {
      if ( x <= 0) accumulator
      else sumUntilTailRec(x-1, accumulator + x)
      // no further stack frames necessary = no more risk of stack overflow
    }
    sumUntilTailRec(n, 0)
  }

  def sumNumbersBetween(a: Int, b: Int): Int = {
    if(b == a) b
    else b + sumNumbersBetween(a, b-1)
  }

  def sumNumbersBw_tail(a: Int, b:Int): Int = {
    @tailrec
    def sumTail(a: Int, b: Int, accumulator: Int) : Int = {
      if(a > b) accumulator
      else sumTail(a, b-1, accumulator + b)
    }
    sumTail(a, b, 0)
  }

  //concatenate a string n times
  def concatN(s: String, n: Int) : String = {
    if (n <= 0) ""
    else s + concatN(s, n-1)
  }

  def concatN_Tail(s: String, n: Int): String = {
    @tailrec
    def concatTailRec(remainingTimes: Int, accumulator: String) : String = {
      if (remainingTimes <= 0) accumulator
      else concatTailRec(remainingTimes - 1, accumulator + s)
    }
    concatTailRec(n, "")
  }

  // tail recursive fibonacci function
  def fibonacci(n: Int) : Int = {
    @tailrec
    def fib_tailRec(i: Int, last: Int, prev: Int) : Int = {
      if (i >= n) last
      else fib_tailRec(i + 1, last + prev , last)
    }
    if( n <= 2) 1
    fib_tailRec(2, 1, 1)
  }

  def main(args: Array[String]): Unit = {
    println(sumUntil(10))
    println(sumUntil_v2(20000))// tail recursion doesnt use stack
    // TAIL recursion: recursive call occurs last in the code path.

    println(sumNumbersBetween(1, 10))
    println(sumNumbersBw_tail(1, 10))
    println(concatN("Uzbal", 4))
    println(concatN_Tail("Ujwal", 4))
    println(fibonacci(6))
  }
}
