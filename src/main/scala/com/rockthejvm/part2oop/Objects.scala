package com.rockthejvm.part2oop

object Objects {
  // objects are singelton patters in Scala

  object MySingleton { // type + the only instance og this type
    val aField = 45
    def aMethod(x: Int) = x+1
  }

  val theSingleton = MySingleton
  val anotherSingleton = MySingleton
  val isSameSingleton = theSingleton == anotherSingleton

  val theSingletonField = MySingleton.aField
  val theSingletonMethofCall = MySingleton.aMethod(99)

  // instances of classes are not objects here in scala. Objects are singleton here.
  class Person(name: String) {
    def sayHi(): String = {
      s"Hi my name is $name"
    }
  }

  //companions : presence of a class anf an object of same name in the same file.
  // holds stuff that isnt specific to any instance of the corresponding class
  object Person { // companion object
    // can access each other's private fields and methods
    val N_EYES = 2
    def canFly(): Boolean = false

  }

  /// methods and fields in classes are used for instance-dependent functionality
  val mary = new Person("Mary")
  val mary_v2 = new Person("Mary")
  val marysGreeting = mary.sayHi()
  mary == mary

  // methods and fields in objects are used for instance independent functionality - 'static'
  val humansCanFly = Person.canFly()
  val nEyesHuman  = Person.N_EYES

  // equality
  // 1 - equality of reference - usually defined as ==
  val sameMary = mary eq mary_v2 // false, different instances
  val sameSingleton = MySingleton eq MySingleton // true
  // 2 - equality of "sameness" - in Java defined as .equals
  val sameMary_v2 = mary equals mary_v2 // false : same as eq by default. can be overwritten
  val sameMary_v3 = mary == mary_v2 // same as equals - false
  val sameSingleton_v2 = MySingleton == MySingleton // true

  // objects can extend classes
  object BigFoot extends Person("Big Foot")

  //
  /*
    Scala application:
      object Objects {
        def main(args: Array[String]): Unit = { ... }
      }

    Equivalent Java application:
      public class Objects {
        public static void main(String[] args) { ... }
      }
   */

  def main(args: Array[String]): Unit = {
    println(isSameSingleton)
    println(sameMary_v3)
  }
}
