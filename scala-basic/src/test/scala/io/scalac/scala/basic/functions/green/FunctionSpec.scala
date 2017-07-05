package io.scalac.scala.basic.functions.green

import org.scalatest._

class FunctionSpec extends WordSpec with MustMatchers {

  // functions are identified by package, names AND parameters
  def double(i: Int) = i * 2
  def double(s: String) = s + s

  "Function.Function" should {
    "construct double function" in {
      double(2) mustBe 4
      double(3) mustBe 6
      double("abc") mustBe "abcabc"
      double("QAZ ") mustBe "QAZ QAZ "
    }
  }
}
