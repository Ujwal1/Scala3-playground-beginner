package com.rockthejvm.part2oop

object PreventingInheritance {

  class Person(name: String) {
    final def enjoyLife() : Int = 42
  }

  class Adult(name: String) extends Person(name) {
//    override def enjoyLife() = 999

  }

  final class Animal // can not be inherited
//  class Cat extends Animal

  // sealing a type hierarchy = ingeritance only permitted inside this file
  sealed class Guitar(nStrings: Int)
  class electricGuitar(nStrings: Int) extends Guitar(nStrings)
  class AcousticGuitar extends Guitar(6)

  // no modifier = not encouraginf inheritance
  // use open keyword as a practice is extending permissible
  open class ExtensibleGuitar(nStrings: Int)

  def main(args: Array[String]): Unit = {

  }
}
