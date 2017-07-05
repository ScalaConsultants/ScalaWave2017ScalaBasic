package io.scalac.scala.basic.functions.red

import org.scalatest._

class FunctionSpec extends WordSpec with MustMatchers {

  // functions are identified by package, names AND parameters
  def double(i: Int): Int = ???
  def double(s: String): String = ???

  "Function.Function" should {
    "construct double function" in {
      double(2) mustBe 4
      double(3) mustBe 6
      double("abc") mustBe "abcabc"
      double("QAZ ") mustBe "QAZ QAZ "
    }
  }
}
