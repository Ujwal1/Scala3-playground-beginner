package com.rockthejvm.part2oop

object AnonymousClasses {

  abstract class Animal {
    def eat(): Unit
  }

  class SomeAnimal extends Animal {
    override def eat(): Unit = println("I am a weird animal.")
  }

  val someAnimal = new SomeAnimal
  private val someAnimal_v2: Animal = new Animal { // anonymous class
    override def eat(): Unit = println("I am a weird animal. NOM NOM!!")
  }

  /*
     equivalent with:

     class AnonymousClasses.AnonClass$1 extends Animal {
       override def eat(): Unit = println("I'm a weird animal")
     }

     val someAnimal_v2 = new AnonymousClasses.AnonClass$1
    */

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("HI MY NAME IS JIM")
  }

  def main(args: Array[String]): Unit = {
    someAnimal_v2.eat()
//    println(someAnimal_v2)
  }
}
