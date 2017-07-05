package io.scalac.scala.basic.akka.green

import akka.actor._
import akka.testkit._
import org.scalatest._
import org.scalatest.concurrent.Eventually

class MessageSendingSpec() extends TestKit(ActorSystem("MySpec"))
  with WordSpecLike with MustMatchers with BeforeAndAfterAll with ImplicitSender with Eventually {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Akka" should {
    "allow safe, stateful actors" in {
      val buffor = system.actorOf(Props[BufforActor])
      buffor ! BufforString("aaa")
      buffor ! BufforString("bbb")
      buffor ! BufforString("ccc")

      buffor ! GetBufforContent
      expectMsg("aaabbbccc")


      buffor ! BufforString("ddd")
      buffor ! GetBufforContent
      expectMsg("aaabbbcccddd")
    }

    "allow communication between actors using messages" in {
      val sourceBuffor = system.actorOf(Props[BufforActor])
      sourceBuffor ! BufforString("12345")
      sourceBuffor ! GetBufforContent
      expectMsg("12345")

      val destinationBuffor = system.actorOf(Props[BufforActor])
      destinationBuffor ! GetBufforContent
      expectMsg("")

      destinationBuffor ! DuplicateBufforFromActor(sourceBuffor)
      eventually { //"at one point in time this will be true" - we use it to reliably handle concurrent nature of Akka
        destinationBuffor ! GetBufforContent
        expectMsg("12345")
      }
    }
  }
}

case class BufforString(text: String)
case object GetBufforContent
case class DuplicateBufforFromActor(source: ActorRef)

class BufforActor() extends Actor {

  var storedContent = ""

  override def receive: Receive = {
    case BufforString(s) =>
      storedContent = storedContent + s
    case GetBufforContent =>
      sender() ! storedContent

    case DuplicateBufforFromActor(source) =>
      source ! GetBufforContent
    case contentOfOtherActor: String =>
      storedContent = contentOfOtherActor
  }
}