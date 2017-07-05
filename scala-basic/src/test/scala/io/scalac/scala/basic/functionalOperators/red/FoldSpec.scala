package io.scalac.scala.basic.functionalOperators.red

import org.scalatest.{MustMatchers, WordSpec}

class FoldSpec extends WordSpec with MustMatchers {

  def sumEven(numbers: Seq[Int]): Int = ???

  def totalLength(strings: Seq[String]): Int = ???

  def myMkString(strings: Seq[String], separator: String): String = ???

  "FunctionalOperators.Fold" should {
    "sum even numbers" in {
      val numbers = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9)

      val sum = sumEven(numbers)
      sum mustBe 20
      val sumEmpty = sumEven(Seq.empty)
      sumEmpty mustBe 0
    }

    "calculate total length" in {
      val strings = Seq("dog", "foo", "bar", "goat", "cat")

      val result = totalLength(strings)
      result mustBe 16
    }

    "implement mkString" in {
      val example = Seq("mkString", "binds", "elements", "together")
      example.mkString(" ") mustBe "mkString binds elements together"
      example.mkString("^") mustBe "mkString^binds^elements^together"

      example.mkString(" ") mustBe myMkString(example, " ")
      example.mkString("^") mustBe myMkString(example, "^")
      Seq.empty.mkString("!") mustBe myMkString(Seq.empty, "!")
    }
  }
}
