package io.scalac.scala.basic.flowControl

import scala.util.Random

object MatchDemo extends App {
  if (1 == 2) {
    println("Should not happen")
  } else {
    println("1 is NOT equal 2")
  }
  (1 == 2) match {
    case true => println("Should not happen")
    case false => println("1 is NOT equal 2")
  }

  val randomNumber = Random.nextInt(4) - Random.nextInt(4)
  val numberSignFound = randomNumber match {
    case 0 =>
      println("We received 0 - handle the special case")
      0
    case num if num > 0 =>
      println(s"We received some number $num which is higher than 0")
      1
    case num =>
      println(s"We received $num - since other cases caught positive number and 0 we can assume this number is negative")
      -1
  }
  println(s"Sign for $randomNumber is $numberSignFound")

  val randomNumbersSeq = Seq.fill(5)(Random.nextInt(4) - Random.nextInt(4))
  val numberSigns = randomNumbersSeq.map {
    case 0 =>
      0
    case num if num > 0 =>
      1
    case num =>
      -1
  }
  println(s"Match seamlessly integrates with functional operators: signs for $randomNumbersSeq is $numberSigns")

  // we've seen this syntax before

  val dataMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
  val transformedMapPairsPretty = dataMap.map {
    case (key, value) => (key + "+1", value + 1)
  }
  println(s"When maping/folding/filtering we can use match syntax to peek into the elements $transformedMapPairsPretty")

  //we can push it even further
  val inspectionResult = randomNumbersSeq match {
    case firstElement :: otherElements if firstElement % 2 == 0 =>
      println(s"Removing even head element $firstElement. Leaving $otherElements")
      otherElements
    case firstElement :: secondElement :: otherElements if secondElement % 2 == 0 =>
      println(s"$firstElement is odd, $secondElement is even. Dropping them. Leaving $otherElements")
      otherElements
    case _ :: otherElements if otherElements.length == 4 =>
      println(s"We've got odd head followed by collection with 4 elements $otherElements. Prepending 0")
      0 +: otherElements
  }
  println(s"We used match to peek into a sequence and update it $inspectionResult")
}
