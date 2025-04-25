package com.rockthejvm.part3fp

import scala.util.Random

object Options {

  // options: mini collections with at most one value
  val anOption: Option[Int] = Option(32)
  val anEmptyOption: Option[Int] = Option.empty

  //alt version
  val aPresentValue : Option[Int] = Some(4)
  val anEmptyOption_v2 : Option[Int] = None

  val isEmpty = anOption.isEmpty
  val innerValue = anOption.getOrElse(90)
  val anotherOption: Option[Int] = Option(46)
  val aChainedOption = anEmptyOption.orElse(anotherOption)

  // map, flatmap, filter, for
  val anIncrementedOption = anOption.map(_+1) // SOme(43)
  val aFilteredOption = anIncrementedOption.filter(_%2 == 0) // None
  val aFlatMapOption = anOption.flatMap(value => Option(value * 10)) // Some(420)




  // WHY options? to work with unsafe API returning NULL
  def unsafeMehtod(): String = null
  def fallbackMethod(): String = "some valid result"

  // defensive stype
  val stringLength = if(unsafeMehtod() == null) -1 else unsafeMehtod()

  // option style: no need for null checks
  val stringLengthOption: Option[Int] = Option(unsafeMehtod()).map(_.length)

  // usecase for or-else
  val someResult = Option(unsafeMehtod()).orElse(Option(fallbackMethod()))

  // DESIGN
  def betterUnsafeMethod(): Option[String] = None
  def betterFallback(): Option[String] = Some("A valid result")

  // example: Map.get
  val phonebook = Map(
    "daniel" -> 1234
  )
  val marysPhoneNo = phonebook.get("Mary") // None
  // no need to crash, check for nulls or if Mary is present in the Map

  /**
   * Exercise:
   *
   *
   */

  val config: Map[String, String] = Map(
    // comes from elsewhere
    "host" -> "176.45.32.1",
    "port" -> "8081"
  )

  class Connection {
    def connect(): String = "Connection Successful"
  }

  object Connection {
    val random = new Random()

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }
  // defensive style (in an imperative language e.g. Java)
  /*
    String host = config("host")
    String port = config("port")
    if (host != null)
      if (port != null)
        Connection conn = Connection.apply(host, port)
        if (conn != null)
          return conn.connect()
        // ... that's just the happy path, we need to add the rest of the branches
   */

  // options style
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connStatus = connection.map(_.connect())

  // compact
  val connStatus_v2: Option[String] =
    config.get("host").flatMap(h =>
      config.get("port").flatMap(p =>
        Connection(h, p).map(_.connect())
      )
    )

    // for-comprehension
  val connStatus_v3: Option[String] = for {
    h <- config.get("host")
    p <- config.get("port")
    conn <- Connection(h, p)
  } yield conn.connect()





  def main(args: Array[String]): Unit = {
    println(connStatus.getOrElse("Failed to establish connection"))
    println(connStatus_v2.getOrElse("Failed"))
    println(connStatus_v3.getOrElse("Failed"))
  }

}
