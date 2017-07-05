package io.scalac.scala.basic.flowControl.green

import org.scalatest._

class ForSpec extends WordSpec with MustMatchers {
  val data = Seq(-1,0,1,2,3,4,5,6,7,8,9,10)

  val digits: Seq[Int] = for {
    num <- data
    if num >= 0
    if num <= 9
  } yield num
  val evenDigits: Seq[Int] = for {
    digit <- digits
    if digit%2 == 0
  } yield digit
  val evenDigitsNegativeValues: Seq[Int] = for {
    digit <- evenDigits
  } yield -digit
  val divisibleBy3Pairs: Seq[(Int, Int)] = for {
    num <- data
    if num%3 == 0
  } yield (-num, num)

  "FlowControl.For" should {
    "filter out digits" in {
      digits mustBe Seq(0,1,2,3,4,5,6,7,8,9)
    }

    "filter out even digits" in {
      evenDigits mustBe Seq(0,2,4,6,8)
    }

    "filter out even digits then update the value" in {
      evenDigitsNegativeValues mustBe Seq(0,-2,-4,-6,-8)
    }

    "filter out even divisible numbers then build sub list of positive an negative numbers" in {
      divisibleBy3Pairs mustBe Seq((0,0),(-3,3), (-6,6), (-9, 9))
    }
  }
}