package com.rockthejvm.part2oop

object CaseClasses {

  //light data structure
  case class Person(name: String, age: Int)

  //1 - class args are not fields. "val" not required to make args fields
  val daniel = new Person("Daniel", 23)
  val danielsAge: Int = daniel.age

  // 2 - toString, equals, hashCode
  val danielToString = daniel.toString // Person("Daniel", 23)
  val danielDuped = new Person("Daniel", 23)
  val isSameDaniel: Boolean = daniel == danielDuped // true in case of case class
  val code = daniel.hashCode()

  // 3- utility methods
  val danielYounger = daniel.copy(age = 16) // new person("Daniel", 16)

  // 4- CCs have companion objects
  val thePersonSingletion = Person
  val daniel_v2 = Person.apply("Daniel", 99) // "constructor"

  // 5- CCs are serializable
  // use case: Akka actor framework

  // 6- CCs have extractor patterns for PATTERN MATCHING

//  case class CCWithNoArgs { // not allowed
//    //some code
//  }
//
//  val ccna = new CCWithNoArgs
//  val ccna_v2 = new CCWithNoArgs // all instances would be same as no args to compare.

  case object UnitedKingdom {
    def name: String = "The UK. HAHA"
  }

  case class CCWithArgListNoArgs[A]() // legal, mainly used in context of generics
  val ccna = new CCWithArgListNoArgs
  val ccna_v2 = new CCWithArgListNoArgs

  /*
  Check where in LList.scala can we use case calsses or case objects and how it would impact the code.
   */



  def main(args: Array[String]): Unit = {
    println(daniel) // intelligible toString representaion if Person() is a case class
    println(isSameDaniel)
    println(code)
  }
}
