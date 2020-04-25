package com.zmn5.chapter1

import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.actor.{Actor, Props, ActorSystem}

class FibActor extends Actor{
  override def receive: Receive = {
    case num: Int => val fibNum = fib(num)
      sender ! fibNum
  }

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n-1) + fib(n-2)
  }
}

object FibActorApp {
  def main(args: Array[String]): Unit = {
    implicit val timeout: Timeout = Timeout(10 seconds)
    val actorSystem = ActorSystem("FibActorSystem")
    val actor = actorSystem.actorOf(Props[FibActor])
    val future = (actor ? 10).mapTo[Int]
    val fibNum = Await.result(future, 10 seconds)
    println(fibNum)
  }
}
