package io.scalac.scala.basic.functionalOperators.red

import org.scalatest.{MustMatchers, WordSpec}

class MapSpec extends WordSpec with MustMatchers {

  def firstAndThird(items: Seq[(String, String, String)]): Seq[(String, String)] = ???

  def doubleEvenNumbers(numbers: Seq[Int]): Seq[Int] = ???

  "FunctionalOperators.Map" should {
    "get first and third items" in {
      val words = Seq(("one", "two", "three"), ("John", "Rick", "Josh"), ("dog", "cat", "cow"))

      val result = firstAndThird(words)
      result mustBe Seq(("one", "three"), ("John", "Josh"), ("dog", "cow"))
    }

    "double only even numbers" in {
      val numbers = Seq(1, 2, 5, 6, 10, 11)

      val doubledEvens = doubleEvenNumbers(numbers)
      doubledEvens mustBe Seq(1, 4, 5, 12, 20, 11)
    }
  }
}
