package com.zmn5.chapter1

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.util.Random._

object Messages {
  case class Done(randomNum: Int)
  case object GiveMeRandomNum
  case class Start(actorRef: ActorRef)
}

class RandomNumActor extends Actor {
  import Messages._
  override def receive: Receive = {
    case GiveMeRandomNum => println("received a message to generate a random inter")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
  }
}

class QueryActor extends Actor{
  import Messages._

  override def receive: Receive = {
    case Start(actorRef) => println(s"send me the next random number")
      actorRef ! GiveMeRandomNum
    case Done(randomNum) => println(s"receive a random number: $randomNum")
  }
}

object Communication extends App{
  import Messages._
  val actorSystem = ActorSystem("CommunicationAkka")
  val randomNumActor = actorSystem.actorOf(Props[RandomNumActor], "randomNumActor")
  val queryActor = actorSystem.actorOf(Props[QueryActor], "queryActor")
  queryActor ! Start(randomNumActor)
}
