package io.scalac.scala.basic.oop.red

import org.scalatest._

class CaseClassSpec extends WordSpec with MustMatchers {

  "OOP.caseclass" should {

    "update nested case class" in {
      case class Engine(horsePower: Int)

      case class Car(model: String, engine: Engine)


      val car = Car("Subaru Impreza", Engine(horsePower = 220))

      val afterTuning: Car = ???

      afterTuning.engine.horsePower mustBe 400
    }

    "player classes" in {
      case class Position(x: Int, y: Int)

      case class InventoryItem(name: String, count: Int)

      case class Player(position: Position, inventory: Map[String, InventoryItem]) {
        /** Returns the number of inventory items with given name **/
        def countInventoryItems(name: String): Int = ???

        /** Moves n fields to the right (x axis) **/
        def moveRight(n: Int): Player = ???

        /** Adds another item to the inventory **/
        def addToInventory(name: String): Player = ???
      }


      val player = Player(Position(10, 10), Map("long sword" -> InventoryItem("long sword", 1), "apple" -> InventoryItem("apple", 5)))

      val updated = player
        .moveRight(10)
        .addToInventory("long sword")
        .addToInventory("armor")

      updated.countInventoryItems("long sword") mustBe 2
      updated.countInventoryItems("armor") mustBe 1
      updated.position.x mustBe 20
    }

    "simple banking system" in {

      /* Add needed case classes here: */


      /* Uncomment the following code (don't change it). Make it compile and passing tests. */

      /*
      val bankingSystem = BankingSystem(
        accounts = Map(
          "1" -> Account(balance = Map("USD" -> 100, "EUR" -> 500)),
          "2" -> Account(balance = Map("USD" -> 300))))

      val result = bankingSystem
        .withdraw(from = "1", amount = 50, currency = "EUR")
        .deposit(to = "3", amount = 10, currency = "USD")
        .transfer(from = "3", to = "1", amount = 5, currency = "USD")

      result.balanceOf("1", "EUR") mustBe 450
      result.balanceOf("1", "USD") mustBe 105
      result.balanceOf("2", "USD") mustBe 300
      result.balanceOf("3", "USD") mustBe 5
      result.balanceOf("4", "EUR") mustBe 0
      */
    }

  }
}
