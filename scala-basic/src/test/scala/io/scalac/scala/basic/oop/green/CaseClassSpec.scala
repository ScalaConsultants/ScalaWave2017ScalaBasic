package io.scalac.scala.basic.oop.green

import org.scalatest._

class CaseClassSpec extends WordSpec with MustMatchers {

  "OOP.caseclass" should {

    "update nested case class" in {
      case class Engine(horsePower: Int)

      case class Car(model: String, engine: Engine)


      val car = Car("Subaru Impreza", Engine(horsePower = 220))

      val afterTuning: Car = car.copy(engine = car.engine.copy(horsePower = 400))

      afterTuning.engine.horsePower mustBe 400
    }

    "player classes" in {
      case class Position(x: Int, y: Int)

      case class InventoryItem(name: String, count: Int)

      case class Player(position: Position, inventory: Map[String, InventoryItem]) {
        /** Returns the number of inventory items with given name **/
        def countInventoryItems(name: String): Int = inventory.get(name).map(_.count).getOrElse(0)

        /** Moves n fields to the right (x axis) **/
        def moveRight(n: Int): Player = copy(position = position.copy(x = position.x + n))

        /** Adds another item to the inventory **/
        def addToInventory(name: String): Player = {
          val currentItem = inventory
            .getOrElse(name, InventoryItem(name, 0))
          val updated = currentItem.copy(count = currentItem.count + 1)
          copy(inventory = inventory + (name -> updated))
        }
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

      case class Account(balance: Map[String, Double]) {
        def updateBalance(update: Double, currency: String): Account = {
          val newCurrencyBalance = balance.getOrElse(currency, 0.0) + update
          copy(balance = balance + (currency -> newCurrencyBalance))
        }
      }

      case class BankingSystem(accounts: Map[String, Account]) {
        def withdraw(from: String, amount: Double, currency: String): BankingSystem = {
          updateAccount(from)(_.updateBalance(-amount, currency))
        }

        def deposit(to: String, amount: Double, currency: String): BankingSystem = {
          updateAccount(to)(_.updateBalance(amount, currency))
        }

        private def updateAccount(id: String)(fn: Account => Account): BankingSystem = {
          val updated = fn(accounts.getOrElse(id, Account(Map())))
          copy(accounts = accounts + (id -> updated))
        }

        def transfer(from: String, to: String, amount: Double, currency: String): BankingSystem = {
          withdraw(from, amount, currency).deposit(to, amount, currency)
        }

        def balanceOf(id: String, currency: String): Double = {
          accounts.get(id).flatMap(_.balance.get(currency)).getOrElse(0)
        }
      }


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
    }

  }
}
