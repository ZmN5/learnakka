package com.zmn5.chapter1

import akka.actor.ActorSystem

object HelloAkkaActorSystem extends App{
  val actorSystem = ActorSystem("helloAkka")
  println(actorSystem)
}
