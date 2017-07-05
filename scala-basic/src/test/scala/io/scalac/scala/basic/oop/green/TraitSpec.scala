package io.scalac.scala.basic.oop.green

import org.scalatest._

class TraitSpec extends WordSpec with MustMatchers {

  trait Run {
    def run(): String = "(Runs to help)"
  }

  trait MakesSound {
    def makeSound(): String
  }
  case class Robot() extends Run with MakesSound {
    override def makeSound(): String = "You are terminated."
  }
  case class Dog() extends Run with MakesSound {
    override def makeSound(): String = "Wuff"
  }


  trait ServerResponse {
    def isSuccess: Boolean
  }
  case object OkResponse extends ServerResponse {
    override def isSuccess: Boolean = true
  }
  case object ServerErrorResponse extends ServerResponse {
    override def isSuccess: Boolean = false
  }
  def makeServerRequest(url: String): ServerResponse = {
    if(url.isEmpty) ServerErrorResponse else OkResponse
  }


  "OOP.traits" should {
    "allow adding custom code to classes" in {
      val t800 = Robot()
      t800.run() mustBe "(Runs to help)"
      val lessie = Dog()
      lessie.run() mustBe "(Runs to help)"
    }

    "work as plain interfaces" in {
      // uncomment and make it green
      val t800 = Robot()
      t800.makeSound() mustBe "You are terminated."
      val lessie = Dog()
      lessie.makeSound() mustBe "Wuff"
    }

    "be used to build hierarchies (ADTs)" in {
      makeServerRequest("post/1").isSuccess mustBe true
      makeServerRequest("post/2").isSuccess mustBe true
      makeServerRequest("").isSuccess mustBe false
    }
  }
}
