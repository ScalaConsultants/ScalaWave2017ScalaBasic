package io.scalac.scala.basic.flowControl

import scala.util.Random

object IfDemo extends App {
  if (1 == 1) {
    println("1 is equal 1")
  }

  if (1 == 2) {
    println("Should not happen")
  } else {
    println("1 is NOT equal 2")
  }

  if (1 != 2) {
    println("1 is NOT equal 2")
  } else {
    println("Should not happen")
  }

  val randomNumber = Random.nextInt(100) - Random.nextInt(100)
  val numberSignFound = if (randomNumber >= 0) {
    1
  } else {
    -1
  }
  println(s"Number $randomNumber has sign $numberSignFound")

  val randomNumberSeq = Seq.fill(10)(Random.nextInt(100) - Random.nextInt(100))
  val numbersSignsFound = randomNumberSeq.map {number =>
    if (number >= 0) {
      1
    } else {
      -1
    }
  }
  println(s"Signs for $randomNumberSeq are $numbersSignsFound")
}
