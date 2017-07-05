package io.scalac.scala.basic.functionalOperators.green

import org.scalatest.{MustMatchers, WordSpec}

class FlatMapSpec extends WordSpec with MustMatchers {

  def funkyRoot(num: Double): Seq[Double] = {
    val squareRoot = Math.sqrt(num)
    Seq(-squareRoot, squareRoot)
  }

  def funkyRootsForSeq(nums: Seq[Double]): Seq[Double] = {
    nums.flatMap(funkyRoot)
  }

  // get unique characters from the sentence and make the upper-case
  def turnIntoNormalizedCharactersSet(sentence: Seq[String]): Set[Char] = {
    sentence.flatMap(_.toUpperCase).toSet
  }

  "FunctionalOperators.FlatMap" should {
    "flatten collections together" in {
      funkyRootsForSeq(Seq.empty) mustBe Seq.empty
      funkyRootsForSeq(Seq(1,4)) mustBe Seq(-1,1,-2,2)
      funkyRootsForSeq(Seq(9)) mustBe Seq(-3,3)
    }

    "help us combat deep nesting" in {
      val sentence1 = Seq("To", "be", "or", "not", "to", "be")
      turnIntoNormalizedCharactersSet(sentence1) mustBe Set('T','O','B','E','R','N')
      val sentence2 = Seq("Veni", "Vidi", "Vici", "")
      turnIntoNormalizedCharactersSet(sentence2) mustBe Set('V','E','N','I','D','C')
    }
  }
}
