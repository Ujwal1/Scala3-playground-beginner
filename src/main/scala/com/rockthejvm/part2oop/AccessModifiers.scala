package com.rockthejvm.part2oop

object AccessModifiers {

  class Person(val name: String) {
    protected def sayHi(): String = s"Hi my name is $name"
    private def watchNetflix(): String = "I love to watch shows. " // only accessible inside this class.
  }
  // protected = access to the class and + children classes
  class Kid(override val name: String, age: Int) extends Person(name) {
    def greetPolitely() : String = // noModifier = public
      sayHi() + " I love to play."
  }
  val aPerson = new Person("ALice")
  val aKid = new Kid("David", 5)

  // complication
  class kidWithParent(override val name: String, age: Int, momName: String, dadName: String) extends Person(name) {
    val mom = new Person(momName)
    val dad = new Person(dadName)
    // can't call a protected method on ANOTHER instance of Person

//        def everyoneSayHi(): String =
//          this.sayHi() + s"Hi, I'm $name, and here are my parents: " + mom.sayHi() + dad.sayHi()
  }

  def main(args: Array[String]): Unit = {
//    println(aPerson.sayHi()) // cant call with projected
      println(aKid.greetPolitely())

  }
}
