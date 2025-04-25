package com.rockthejvm.part2oop

import scala.language.postfixOps

object MethodNotation  {

  class Person(val name: String, val age: Int, favouriteMovie: String) {
    infix def likes(movie: String) : Boolean =  {
      movie == favouriteMovie
    }

    def +(person: Person) =
      s"${this.name} is hanging out with ${person.name}"

    def !!(progLanguage: String) : String  = {
      s"$name , how cool is $progLanguage"
    }

    //exercise 1
    def +(nickName: String) : Person = {
      new Person(nickName, age, favouriteMovie)
    }

    // prefix position
    // unary operators +, -, ~, ! : unary_OPERATOR : no arguments or paranthesis
    def unary_- :String =
      s"$name's alter ego'"

    // exercise 2

    def unary_+ : Person = {
      new Person(name, age+1, favouriteMovie)
    }

    def isAlive: Boolean = true

    //special methods: apply
    def apply(): String = {
      s"Hi my name is $name and this is apply."
    }

    def apply(count: Int) : String = { // apply will make an object be invoked like a function
      s"$name watched $favouriteMovie $count times."
    }
  }

  val mary: Person = new Person("Mary", 43, "Inception")
  val john : Person = new Person("John", 33, "Welcome Home")

  val negativeOne = -1

  def main(args: Array[String]): Unit = {
    println(mary.likes("Fight Club"))
    // infix notation = for methods with one argument
    println(mary likes "Fight Club") // because of infix - identical as above
    //operator
    println(mary.+(john))
    println(mary + john)

    println(2 + 3)
    println(2.+(3)) // operators are mehtods in Scala

    println(mary !! "Scala") // infix notation. Scala 3.0 allows infix even if not defined. not strict at the monment. Recommended  tho.

    println(-mary)

    // postfix notation
    println(mary.isAlive)
    println(mary isAlive) // discourages

    //apply
    println(mary.apply())
    println(mary())

    // testing exercises
    println(mary.+("The Hippie").name )
    println(+mary.age)
    val maryOlder = +mary
    println(maryOlder.age)
    println(mary.apply(23))
    println(mary(100))

  }
}
