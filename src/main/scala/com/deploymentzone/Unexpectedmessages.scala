package com.deploymentzone

import akka.actor._
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout

case object On
case object Off
case class TransmitData(data : Any)
case object Transmitted

class Throttler extends Actor with ActorLogging {

  def inactive : Receive = {
    case On => context.become(active)
  }

  def active : Receive = {
    case Off => context.become(inactive)
    case TransmitData(data) =>
      log.info(s"transmitting $data")
      sender ! Transmitted
  }

  def receive = inactive
}


