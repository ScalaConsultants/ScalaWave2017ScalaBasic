package io.scalac.scala.basic.functions.red

import org.scalatest._

class HigherOrderFunctionSpec extends WordSpec with MustMatchers {

  def buildQuadraticFunction(a: Double, b: Double, c: Double): (Double)=>Double = {
    ???
  }

  def countByFunction(countPerItem: (String) => Int)
                     (data: Seq[String]): Int = {
    ???
  }

  "Function.Function" should {
    "construct quadratic function" in {
      val quadraticFunction = buildQuadraticFunction(2, 3, -1) // y = 2*x^2 +3*x -1
      quadraticFunction(0) mustBe -1
      quadraticFunction(2) mustBe 13
      quadraticFunction(4) mustBe 43

      val negativeQuadraticFunction = buildQuadraticFunction(-1, 0, 5) // y = -1*x^2 +5
      negativeQuadraticFunction(0) mustBe 5
      negativeQuadraticFunction(2) mustBe 1
      negativeQuadraticFunction(4) mustBe -11
    }

    "construct an aggregating function" in {
      val words = Seq("foo", "bar", "baz", "")

      val characterLength = (s: String) => s.length
      val countByCharacterLength = countByFunction(characterLength)_
      countByCharacterLength(words) mustBe 9

      val countExistence = (s: String) => if(s.isEmpty) 0 else 1
      val countByExistence = countByFunction(countExistence)_
      countByExistence(words) mustBe 3
    }
  }
}
