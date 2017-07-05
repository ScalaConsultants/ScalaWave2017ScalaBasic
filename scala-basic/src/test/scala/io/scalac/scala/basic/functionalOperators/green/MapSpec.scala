package io.scalac.scala.basic.functionalOperators.green

import org.scalatest.{MustMatchers, WordSpec}

class MapSpec extends WordSpec with MustMatchers {

  // introduce `case` keyword (or change, not to use it here)?
  def firstAndThird(items: Seq[(String, String, String)]): Seq[(String, String)] =
    items.map { case (first, _, third) => (first, third) }

  def doubleEvenNumbers(numbers: Seq[Int]): Seq[Int] =
    numbers.map {
      case n if n % 2 == 0 => n *2
      case other => other
    }

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
