package io.scalac.scala.basic.oop.red

import org.scalatest._

class ClassSpec extends WordSpec with MustMatchers {

  case class UserId(id: String) // id wrapped for type safety
  class Person(val id: UserId, val name: String, val surname: String) {
    //??? - implement toString and equals here
  }

  val bond = new Person(UserId("007"), "James", "Bond")
  val johny = new Person(UserId("00001"), "Johny", "English")

  class GovernmentAgency(employees: Seq[Person]) {
    def reportNumberOfEmployees(): Int = ???
    // returns sorted by surname - IDE will suggest you the method to sort
    def reportListOfEmployees(): String = ???
  }
  class SpyAgency(employees: Seq[Person]) extends GovernmentAgency(employees) {
    // spy agency should hide it's employees ... implement it below
    // ???
  }

  "OOP.classes" should {
    "allow overrides of functions - toString" in {
      bond.toString mustBe "Bond, James Bond"
      johny.toString mustBe "English, Johny English"
    }

    "allow overrides of functions - equals" in {
      bond mustBe bond
      bond mustNot be(johny)

      val undercoverBond = new Person(UserId("007"), "???", "???")
      bond mustBe undercoverBond
      val fakeBond = new Person(UserId("007b"), "James", "Bond")
      bond mustNot be(fakeBond)
    }

    "allow building hierarchies" in {
      val bob = new Person(UserId("12345"), "Bob", "Clerk")
      val roadSafetyAgency = new GovernmentAgency(Seq(johny, bob))
      val mi6 = new SpyAgency(Seq(johny, bond))

      roadSafetyAgency.reportNumberOfEmployees() mustBe 2
      roadSafetyAgency.reportListOfEmployees() mustBe "Clerk, Bob Clerk|English, Johny English"

      mi6.reportNumberOfEmployees() mustBe 0 //mi6 doesn't expose the information about their agents
      mi6.reportListOfEmployees() mustBe ""
    }
  }
}
