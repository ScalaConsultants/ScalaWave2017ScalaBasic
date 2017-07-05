package io.scalac.scala.basic.oop

object ClassDemo extends App {

  class Animal(name: String, numberOfLegs: Int) {
    override def toString() = {
      println(s"In contrast to case classes, ordinary don't ones come with implemented methods")
      s"Animal $name $numberOfLegs legs"
    }
  }

  val cow = new Animal("cow", 4)
  println(s"Class instances are created with new: $cow")

  // but classes bring us inheritance
  class Dog(name: String) extends Animal(name, 4)
  class Squid(name: String, numberOfLegs: Int) extends Animal(name, numberOfLegs)

  val shepherd = new Dog("German Shepherd")
  val humboldt = new Squid("Humboldt Squid", 10)

  val animals: Seq[Animal] = Seq(shepherd, humboldt)
  animals.map(animal => println(animal))
}
