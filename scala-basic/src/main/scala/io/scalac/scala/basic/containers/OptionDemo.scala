package io.scalac.scala.basic.containers

import scala.util.Random


object OptionDemo extends App {
  def makeUpAName(): String = Random.alphanumeric.take(5).mkString
  def askNameCache(id: Int): Option[String] = {
    val recentlyAccessed = Map(7 -> "James Bond", 42 -> "Deep Thought", 451 -> "Guy Montag")
    // naive example, you will improve it later in the exercises
    if(recentlyAccessed.contains(id)){
      val found = recentlyAccessed(id)
      Some(found)
    } else {
      None
    }
  }



  askNameCache(42) match {
    case Some(name) =>
      println(s"Found $name")
    case None =>
      println(s"Nothing in cache for 42")
  }
  askNameCache(101) match {
    case Some(name) =>
      println(s"Found $name")
    case None =>
      println(s"Nothing in cache for 101")
  }
  askNameCache(42) match {
    case Some(name) if name.contains("James") =>
      println(s"Found $name")
    case _ =>
      println(s"No James in cache for 7")
  }



  val cachedName = askNameCache(42).getOrElse("Default Name")
  println(cachedName)
  val cachedNameOrDefault = askNameCache(101).getOrElse("Default Name")
  println(cachedNameOrDefault)
  val cachedNameOrRandom = askNameCache(101).getOrElse(makeUpAName())
  println(cachedNameOrRandom)



  val combinedWithFunctionalOperators = askNameCache(101).orElse(askNameCache(42)).map(_.toUpperCase)
  println(combinedWithFunctionalOperators)
  val filteredWithDefault = askNameCache(42).filter(_.contains("James")).orElse(askNameCache(7))
  println(filteredWithDefault)



  val withFold = askNameCache(42).fold("Nothing found")(foldedValue => foldedValue)
  val withGetOrElse = askNameCache(42).getOrElse("Nothing found")
  println(s"$withFold == $withGetOrElse")

  val emptyWithFold = askNameCache(999).fold("Nothing found")(foldedValue => foldedValue)
  val emptyWithGetOrElse = askNameCache(999).getOrElse("Nothing found")
  println(s"$emptyWithFold == $emptyWithGetOrElse")

}
