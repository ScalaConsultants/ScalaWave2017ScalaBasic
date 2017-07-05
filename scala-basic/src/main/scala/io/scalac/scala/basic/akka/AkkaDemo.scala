package io.scalac.scala.basic.akka

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorSystem, OneForOneStrategy, Props}

//case classes/objects work well to transmit data signal action
case object IntroduceYourself

case object Ping
case object Pong

case object DoSomethingRisky

class ParentActor() extends Actor {
  override def receive = { //Actors use the power of partial functions!
    case IntroduceYourself =>
      println(s"Hello, my name is ${self}")


    case Ping =>
      // actors can create child actors
      // actors communicate by sending messages
      val child = context.actorOf(Props[ChildActor])
      println(s"${self} says: Starting a ping-pong match with $child")
      child ! Ping
    case Pong =>
      sender() ! Ping

    case DoSomethingRisky =>
      val child = context.actorOf(Props[RisktakerActor])
      println(s"${self} says: Starting $child and making him do dangerous stuff")
      child ! DoSomethingRisky
  }

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10) {
      case ex: Exception =>
        println(s"${self} says: One of the children crashed - restarting!")
        Restart
    }
}

class ChildActor() extends Actor {
  // it's ok to have local, mutable state as only one message is processed at a time
  var pongCount = 0

  override def receive = {
    case Ping if pongCount >= 1000000 =>
      println(s"${self} says: Reached a million pongs - stopping the match now")
    case Ping =>
      pongCount += 1
      sender() ! Pong
  }
}

class RisktakerActor() extends Actor {
  // it's ok to have local, mutable state as only one message is processed at a time
  var pongCount = 0

  override def receive = {
    case DoSomethingRisky =>
      println(s"${self} says: here we go")
      10 / 0 // this will throw an exception and crash the actor
  }

  // if you wish you can hook into the lifecycle of an actor
  override def postRestart(ex: Throwable) = {
    println(s"${self} says: I have been restarted due to $ex")
    super.postRestart(ex) // we delegate the call up the chain
  }
}

object AkkaDemo extends App {
  // actors operate inside an actor system
  val system = ActorSystem("PingPongSystem")
  // we use the system to create new actors
  val parentActor = system.actorOf(Props[ParentActor], name = "parent")

  parentActor ! IntroduceYourself

  parentActor ! Ping //every ping message will start a new children and parent-child conversation
  parentActor ! Ping

  parentActor ! DoSomethingRisky
}
