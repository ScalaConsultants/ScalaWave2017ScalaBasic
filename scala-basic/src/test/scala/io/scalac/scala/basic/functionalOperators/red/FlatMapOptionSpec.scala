package io.scalac.scala.basic.functionalOperators.red

import org.scalatest.{MustMatchers, WordSpec}

class FlatMapOptionSpec extends WordSpec with MustMatchers {

  // introduce `case` keyword (or change, not to use it here)?
  def sumOpt(a: Option[Int], b: Option[Int]): Option[Int] = ???


  "FunctionalOperators.FlatMap" should {
    "sum integers contained within Option and return new Option with result" in {

      val result = sumOpt(Some(1), Some(1))
      result mustBe Some(2)
    }

    "return None if any of the values is missing" in {
      val result = sumOpt(Some(1), None)
      result mustBe None
    }
  }
}
