package io.scalac.scala.basic.oop

object TraitDemo extends App {

  trait Animal {
    def makeSound(): String
  }

  // implementing the trait-interface
  case class Cat(name: String) extends Animal {
    override def makeSound(): String = "Meow"
  }

  val mainCoon: Animal = new Cat("Maine Coon")
  println(s"A cat is an animal: ${mainCoon.makeSound()}")

  // anonymous implementation
  val platypus = new Animal {
    override def makeSound(): String = "Prrrr"
  }
  println(s"There's only one anonymous instance: ${platypus.makeSound()}")


  trait Swims {
    def swim(): Unit = println("Swims just fine")
  }
  case class Dog(name: String) extends Animal with Swims {
    override def makeSound(): String = "Woof woof"
  }
  val shepherd = new Dog("German Shepherd")
  println(s"Dog is an animal and can swim: ${shepherd.makeSound()} ${shepherd.swim()}")
}
