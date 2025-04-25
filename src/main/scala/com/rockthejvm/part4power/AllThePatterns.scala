package com.rockthejvm.part4power

import com.rockthejvm.practice.{Cons, Empty,  LList}

object AllThePatterns {

  object mySingleton

  // 1 - constants
  val someValue: Any = "Scala"
  val constants = someValue match {
    case 42 => "a number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case mySingleton => "a singleton object"
  }

  // 2 - match anything

  val matchAnythingVar = someValue match {
    case something => s"I have matched anything, its $something"
  }

  val matchAnything = someValue match {
    case _ => "I can match anything at all."
  }

  // 3 - tuples
  val aTuple = (1, 4)
  val matchTuple = aTuple match {
    case(1, somethingElse) => s"A tuple with 1 and $somethingElse"
    case(something, 2) => "A tuple with 2 as its second field."
  }

  // Pattern matching can be nested
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case(_, (2, something)) => "A nested tuple ..."
  }

  //  4 - case classes
  val aList: LList[Int] = Cons(1, Cons(2, Empty()))

  val matchList = aList match {
    case Empty() => " an empty list"
    case Cons(head, Cons(_, tail)) => s"a non empty list starting with $head"
  }

  val anOption: Option[Int] = Option(2)
  val matchOption = anOption match {
    case None => "nothing"
    case Some(value) => s"non empty, have $value"
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 4, 42)
  val aMatchStandardList = aStandardList match {
    case List(1, _, _, _) => "List with 4 elements, first is 1"
    case List(2, _*) => " List starting with 1"
    case List(1, 2, _) :+ 42 => "List ending in 42"
    case 1 :: tail => s"list starting with 1 and ending at $tail"
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val matchTyped = unknown match {
    case anInt: Int => s"I matched an int, I can add 2 to it: ${anInt + 2}"
    case aString: String => "I matched a String"
    case _: Double => "I matched a double I don't care about"
  }

  // 7 - name binding: name a subpattern using - rest @
  val bindingNames = aList match {
    case Cons(head, rest @ Cons(_, tail)) => s"a non empty list starting with $head. can use $rest"
  }

  // 8 - multipatters: chained patterns
  val multiMatch = aList match {
    case Empty() | Cons(0, _) => "an empty list to me"
    case _ => "else anything"
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement > 5 => "second element is big enough."
    case _ => "else anything"
  }

  /**
   *
   * example
   */
  val aSimpleInt = 42
  val simpleInt = aSimpleInt match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // anti-pattern: convoluted, hard to read

  // anti-pattern 2: if (condition) true else false
  val isEven_bad_v2 = if (aSimpleInt % 2 == 0) true else false
  // better - return the boolean expression, it has everything you need!
  val isEven = aSimpleInt % 2 == 0

  // trick question
  val numbers: List[Int] = List(1, 2, 3, 4)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfInts: List[Int] => "a list of numbers"
  }

  /*
    PM runs at runtime
    - reflection
    - generic types are erased at runtime : because generics were added later and modern java is made to be compatible with old code
        List[String] => List
        List[Int] => List
        Function1[Int, String] => Function1
        etc.
   */


  def main(args: Array[String]): Unit = {
    println(numbersMatch) // displays list of strings
  }

}
