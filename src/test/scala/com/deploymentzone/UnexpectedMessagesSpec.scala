package com.deploymentzone

import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import akka.actor.{UnhandledMessage, Props, ActorSystem}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class UnexpectedMessagesSpec
  extends TestKit(ActorSystem("UnexpectedMessagesSpec"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll() {
    system.shutdown()
  }

  "Throttler" should {
    "not respond to TransmitData when inactive" in {
      val subject = TestActorRef(Props(new Throttler))

      system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
      subject ! TransmitData("data")

      expectMsg(UnhandledMessage(TransmitData("data"), testActor, subject))
    }

    "respond to TransmitData when active" in {
      val subject = TestActorRef(Props(new Throttler))

      subject ! On
      subject ! TransmitData("all systems go")

      expectMsg(Transmitted)
    }
  }

}
