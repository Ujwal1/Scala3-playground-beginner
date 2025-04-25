package com.rockthejvm.part1basics

import scala.annotation.tailrec

object DefaultArgs {

  @tailrec
  def sumUntilTailRec(x: Int, accumulator: Int = 0): Int = {
    if (x <= 0) accumulator
    else sumUntilTailRec(x - 1, accumulator + x)
    // no further stack frames necessary = no more risk of stack overflow
  }
  val sumUntil100 = sumUntilTailRec(100) // additional arg accumulator is passed automatically

  def savePic(dirPath: String, name: String, format: String = "jpg", width: Int = 1920, height: Int = 1920): Unit = {
    println( "HAHA ")
  }



  def main(args: Array[String]): Unit = {
    println(sumUntil100)
    savePic("/a/folder1/b/", "myPhoto")
    savePic("/a/folder1/b/", "myPhoto", "png")
    savePic("/a/folder1/b/", "myPhoto", height = 100)

  }
}
