package com.rockthejvm.part1basics

object StringOps {

  val aString : String = "Hello, how are you?"

  //string functions(methods)
  val secondChar = aString.charAt(1)
  val firstWord = aString.substring(0, 5) // Hello
  val words = aString.split(" ") // array["Hello,", "how", ..]
  val startsWithHello = aString.startsWith("Hello") // true
  val allDashes = aString.replace(' ', '-')
  val allUppercase = aString.toUpperCase()// also to lowercase
  val n = aString.length

  //other functions
  val reversed = aString.reverse
  val aBunchOfChars = aString.take(10) // take first 10 chars of the string
  // parse to numeric
  val numberAsString = "2"
  val number = numberAsString.toInt

  // s-interpolation
  val name = "Ujwal"
  val age = 25
  val greeting = "Hello, I am " + name + " and I am " + age + " years old. "
  val greetings_v2 = s" Hello I am $name and I am $age years old"
  println(greetings_v2)
  val greetings_v3 = s" Hello I am $name and I am ${age + 1} years old" // inject the expression and evaluate it.

  // f-interpolation   // can control the format of interpolators
  val speed = 1.2122f
  val myth = f"$name can eat $speed%2.2f burgers per minute. "
  println(myth)

  // raw-interpolation
  val escapes = "This is a \n newline"
  println(escapes)
  val escapes_v2 = raw"This is a \n newline"
  println(escapes_v2)

  def main(args: Array[String]): Unit = {

  }
}
