package io.scalac.scala.basic.oop.red

import org.scalatest._

class TraitSpec extends WordSpec with MustMatchers {

  trait Run {
    def run(): String = ???
  }

  trait MakesSound {
    def makeSound(): String
  }
  case class Robot() extends Run //???
  case class Dog() extends Run //???


  trait ServerResponse {
    def isSuccess: Boolean
  }
  case object OkResponse extends ServerResponse {
    override def isSuccess: Boolean = true
  }
  //???
  def makeServerRequest(url: String): ServerResponse = {
    ???
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
//      t800.makeSound() mustBe "You are terminated."
      val lessie = Dog()
//      lessie.makeSound() mustBe "Wuff"
    }

    "be used to build hierarchies (ADTs)" in {
      makeServerRequest("post/1").isSuccess mustBe true
      makeServerRequest("post/2").isSuccess mustBe true
      makeServerRequest("").isSuccess mustBe false
    }
  }
}
