package io.scalac.scala.basic.flowControl.red

import org.scalatest._

class IfSpec extends WordSpec with MustMatchers {

  //TODO: can we do it without functions?
  def classifyHttpStatus(status: Int): String = {
    ???
  }

  "FlowControl.If" should {
    "classify 100-199 responses as INFORMATION" in {
      classifyHttpStatus(100) mustBe "INFORMATION"
      classifyHttpStatus(150) mustBe "INFORMATION"
      classifyHttpStatus(199) mustBe "INFORMATION"
    }

    "classify 200-299 responses as SUCCESS" in {
      classifyHttpStatus(200) mustBe "SUCCESS"
      classifyHttpStatus(250) mustBe "SUCCESS"
      classifyHttpStatus(299) mustBe "SUCCESS"
    }

    "classify 300-399 responses as REDIRECT" in {
      classifyHttpStatus(300) mustBe "REDIRECT"
      classifyHttpStatus(350) mustBe "REDIRECT"
      classifyHttpStatus(399) mustBe "REDIRECT"
    }

    "classify 400-499 responses as CLIENT_ERROR" in {
      classifyHttpStatus(400) mustBe "CLIENT_ERROR"
      classifyHttpStatus(450) mustBe "CLIENT_ERROR"
      classifyHttpStatus(499) mustBe "CLIENT_ERROR"
    }

    "classify 500-599 responses as SERVER_ERROR" in {
      classifyHttpStatus(500) mustBe "SERVER_ERROR"
      classifyHttpStatus(550) mustBe "SERVER_ERROR"
      classifyHttpStatus(599) mustBe "SERVER_ERROR"
    }
  }
}
