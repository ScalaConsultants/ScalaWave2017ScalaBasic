package io.scalac.scala.basic.oop

object CaseClassDemo extends App {

  val personJohn = ("John", 25)
  println(s"We could use pairs to represent entities, but it's cumbersome: $personJohn")

  case class Person(name: String, yearsOld: Int)

  val personAnn = Person("Ann", 27)
  val babyMike = Person("Mike", 2)
  println(s"We can use case classes to simplify access to data: $personAnn")

  def sayHello(p: Person) = println(s"Hello ${p.name}")
  sayHello(personAnn)
  sayHello(babyMike)

  val people: Seq[Person] = Seq(personAnn, babyMike)
  people.map {
    case Person(name, age) if age <= 18 =>
      println(s"$name is underage")
    case p: Person =>
      println(s"${p.name} is an adult")
  }

  val adultMike = babyMike.copy(yearsOld = 20)
  println(s"Case classes come with a set of useful functions implemented by default: $adultMike")
}
