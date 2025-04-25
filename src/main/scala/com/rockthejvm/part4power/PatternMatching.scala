package com.rockthejvm.part4power

import scala.util.Random

object PatternMatching {

  // switch on steroids
  val random = new Random()
  val aValue: Int = random.nextInt()

  val description: Any = aValue match {
    case 1 => "the first"
    case 2 => 2
    case _ => s"something else: $aValue"
  }// throws a match error if there is no match and case _ is omitted

  // decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  // case classes allow for pattern matching because it has extractor object(unapply). while regular classes do not.
  val greeting = bob match {
    case Person(n, a) if a < 18 => s"Hi there, my name is $n and I am a kid." // guards, filters
    case Person(n, a) => s"Hello there, my name is $n and age is $a"
    case _ => " I don't know who I am here. "
  }
  // patterns are matched in order of cases
  // ehat if no case matched? match error
  // whats the type returned? the lowest common ancestorof all types on the RHS of each branch

  // PM on sealed heirarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(meowStyle: String) extends Animal

  val anAnimal: Animal = Dog("Terra Nova")
  val animalPM = anAnimal match {
    case Dog(someBreed) => "Its a dog"
    case Cat(meow) => "Its a cat"
  }

  /**
   * Exercise
   * show(Sum(Number(2), Number(3)) = "2 + 3"
   * show(Sum(Sum(Number(2), Number(3)), Sum(4)) = "2 + 3 + 4"
   * show(Prod(Sum(Number(2), Number(3)), Sum(4)) = "(2 + 3) * 4"
   * show(Sum(Prod(Number(2), Number(3)), Sum(4)) = "2 * 3 + 4"
   */
  sealed trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Number(n) => s"$n"
    case Sum(left, right) => show(left) + " + " + show(right)
    case Prod(left, right) => {
      def maybeShowParenthesis(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case Sum(_, _) => s"(${show(exp)})"
      }
      maybeShowParenthesis(left) + " * " + maybeShowParenthesis(right)
    }
  }


  def main(args: Array[String]): Unit = {
    println(description)
    println(greeting)

    println(show(Sum(Number(2), Number(3))))
    println( show(Prod(Sum(Number(2), Number(3)), Number(4))) )

  }
}
