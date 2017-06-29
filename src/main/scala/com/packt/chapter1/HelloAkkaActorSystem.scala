package com.packt.chapter1

import akka.actor.ActorSystem

/**
  * Created by Filippo on 6/15/17.
  */
object HelloAkkaActorSystem extends App{
  val actorSystem = ActorSystem("HelloAkka")
  println(actorSystem)
}
