package com.rockthejvm.part3fp

import scala.language.postfixOps
import scala.util.Random

object LinearCollections {

  // Seq (trait) = well defined ordering and indexing
  def testSeq(): Unit = {
    val aSequence = Seq(2, 1, 3, 4)
    //main API: index an element
    val thirdElement = aSequence.apply(2) // element 3
    // map, flatMap, filter, for-comprehension
    val anIncrementedSequence = aSequence.map(_+ 1)
    val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x+ 1))
    val aFilteredSequence = aSequence.filter(_ % 2 == 0)

    // other methods
    val reversed = aSequence.reverse
    val concatenation = aSequence ++ Seq(5, 6, 7)
    val sortedSequence = aSequence.sorted // [1,2,3,4]

    val sum = aSequence.foldLeft(0)(_ + _) // 10 // foldleft belongs to Seq
    val stringRepresentation = aSequence.mkString("[", ",", "]") // useful to serialise a sequence to a string
    println("String representation of a list: "  + stringRepresentation)


    println(aSequence) // all these three are lists
    println(concatenation)
    println(sortedSequence)
  }

  // lists
  def testLists(): Unit = {
    val aList = List(1, 2, 3)
    // same api as Seq
    val first = aList.head
    val rest = aList.tail
    // appending and prepending
    val aBiggerList = 0 +: aList :+ 4 // plus stays on side of elemt being added
    val prepending = 0 :: aList // equivalent apply method of case class Cons *WOW*

    // utility methods
    val scalax5 = List.fill(5)("Scala")
  }

  // ramges
  def testRanges(): Unit = {
    val aRange: Seq[Int] = 1 to 10
    val aNonInclusiveRange = 1 until 10 // 10 not included
    // same seq API
    (1 to 10 ).foreach( _ => println("Scala"))
  }

  // arrays
  def testArrays(): Unit = {
    val anArray = Array(1, 2, 3, 4, 5, 6) // int[] in the JVM
    // arrays are not seq but have access to most Seq APIs
    val aSequence = anArray.toIndexedSeq
    // arrays are mutable
    anArray.update(2, 30) // no new array is allocated

  }

  // vectors = fast seq for a large amount of data
  def testVectors(): Unit = {
    val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5, 6)
    // the same seq api
  }

  def smallBenchmark(): Unit = {
    val maxRuns = 1000
    val maxCapacity = 1000000

    def getWriteTime(collection: Seq[Int]): Double = {
      val random = new Random()
      val times = for {
        i <- 1 to maxRuns
      } yield {
        val index = random.nextInt(maxCapacity)
        val element = random.nextInt()

        val currentTime = System.nanoTime()
        val updatedCollection = collection.updated(index, element)
        System.nanoTime() - currentTime
      }
      // compute average
      times.foldLeft(0L)(_ + _) * 1.0 / maxRuns
    }
    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))
  }

  // sets
  def testSets() : Unit = {
    val aSet = Set(1, 2, 3, 4, 5, 4) // no ordering gauranteed
    // equals + hashcodes = hashset // doesnt contain duplicates
    val contains3 = aSet.contains(3)
    val contains3_v2 = aSet.apply(3)// same: true
    val contains_v3 = aSet(3) // true
    // adding/removing
    val aBiggerSet = aSet + 4 //[1, 2, 3, 4, 5]
    val aSmaller = aSet - 4 // [1, 2, 3, 5]

    // concatenation
    val anotherSet = Set(4, 5, 6, 7)
    val muchBiggerSet = aSet.union(anotherSet)
    val aMuchBiggerSet_v2 = aSet ++ anotherSet // same
    val aMuchBiggerSet_v4 = aSet | anotherSet // same
    // difference
    val aDiffSet = aSet.diff(anotherSet)
    val aDiffSet_v2 = aSet -- anotherSet
    // intersection
    val anIntersection = aSet.intersect(anotherSet)
    val anIntersection_v2 = aSet & anotherSet

  }






  def main(args: Array[String]): Unit = {
//    testSeq()
//    testRanges()
    smallBenchmark() // vectors a lot lot faster
  }
}
