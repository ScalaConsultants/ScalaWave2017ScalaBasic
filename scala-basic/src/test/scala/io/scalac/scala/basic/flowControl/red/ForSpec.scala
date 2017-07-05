package io.scalac.scala.basic.flowControl.red

import org.scalatest._

class ForSpec extends WordSpec with MustMatchers {
  val data = Seq(-1,0,1,2,3,4,5,6,7,8,9,10)

  //use the data above to build collections that do match the condition
  val digits: Seq[Int] = Seq.empty //???
  val evenDigits: Seq[Int] = Seq.empty //???
  val evenDigitsNegativeValues: Seq[Int] = Seq.empty //???
  val divisibleBy3Pairs: Seq[(Int, Int)] = Seq.empty //???

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

    "filter out even divisible numbers then build sub list of positive and negative numbers" in {
      divisibleBy3Pairs mustBe Seq((0,0),(-3,3), (-6,6), (-9, 9))
    }
  }
}
