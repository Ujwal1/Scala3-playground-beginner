package com.rockthejvm.part1basics

object ValuesAndTypes {

  // values
  val meaningOfLife: Int = 42 // values cant be reassigned // const int meaningOfLife = 42;
  // meaningOfLife = 45 // not allowed

  // type Inference
  val anInteger = 45 // :Int is optional

  // common types
  val aBoolean : Boolean = false
  val aChar : Char = 'a' // single quote is char
  val aInt : Int = 45// 4 bytes
  val aShort : Short = 5263 // two bytes
  val aLong : Long = 2342345353454353L // end with an L; 8 bytes
  val aFloat : Float = 3.3f // 4 bytes
  val aDouble : Double = 3.13 // 8 bytes

  // string
  val aString : String = "Hello" // double quotes is String



  def main(args: Array[String]): Unit = {

  }
}
