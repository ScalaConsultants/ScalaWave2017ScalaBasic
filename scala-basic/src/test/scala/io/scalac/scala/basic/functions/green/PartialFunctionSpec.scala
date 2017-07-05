package io.scalac.scala.basic.functions.green

import org.scalatest._

class PartialFunctionSpec extends WordSpec with MustMatchers {

  val squareRootForPositiveNumbers: PartialFunction[Int, Double] ={
    case n if n >= 0 =>
      Math.sqrt(n)
  }

  val zeroForNegativeNumbers: PartialFunction[Int, Double] = {
    case n if n < 0 =>
      0
  }

  val squareRootOfOppositeForNegativeNumbers: PartialFunction[Int, Double] = {
    case n if n < 0 =>
      Math.sqrt(-n)
  }

  "Function.PartialFunction" should {
    "call a partial function where possible" in {
      squareRootForPositiveNumbers(9) mustBe 3
      squareRootForPositiveNumbers(16) mustBe 4

      squareRootForPositiveNumbers.isDefinedAt(100) mustBe true
      squareRootForPositiveNumbers.isDefinedAt(-1) mustBe false

      assertThrows[MatchError]{
        squareRootForPositiveNumbers(-1)
      }
    }

    "compose partial functions(1)" in {
      val composed = squareRootForPositiveNumbers.orElse(zeroForNegativeNumbers)

      composed(9) mustBe 3
      composed(16) mustBe 4

      composed.isDefinedAt(100) mustBe true
      composed.isDefinedAt(-1) mustBe true

      composed(-9) mustBe 0
      composed(-16) mustBe 0
    }

    "compose partial functions(2)" in {
      val composed = squareRootForPositiveNumbers.orElse(squareRootOfOppositeForNegativeNumbers)

      composed(9) mustBe 3
      composed(16) mustBe 4

      composed.isDefinedAt(100) mustBe true
      composed.isDefinedAt(-1) mustBe true

      composed(-9) mustBe 3
      composed(-16) mustBe 4
    }
  }
}
