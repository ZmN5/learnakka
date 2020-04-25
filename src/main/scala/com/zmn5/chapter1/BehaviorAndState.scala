package com.zmn5.chapter1

import akka.actor.{Actor, Props, ActorSystem}

class SummingActor extends Actor {
  var sum = 0
  override def receive: Receive = {
    case x: Int => sum = sum + x
      println(s"my state as sum is $sum")
  }
}

class SummingActorWithConstructor(initialSum: Int) extends Actor{
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum = initialSum + sum + x
      println(s"my state is $sum")
    case _ => println("I don't know what are you talking about")
  }
}

object BehaviorAndState extends App {
  val actorSystem = ActorSystem("BehaviorAkka")
  val actor = actorSystem.actorOf(Props(classOf[ SummingActorWithConstructor], 10), "summingactor")
  println(actor.path)
  while (true) {
    Thread.sleep(2000)
    actor ! 1
  }
}


