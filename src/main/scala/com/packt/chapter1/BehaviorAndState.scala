package com.packt.chapter1

import akka.actor.Actor

/**
  * Created by Filippo on 6/16/17.
  */
class SummingActor extends Actor {
  // state inside the actor
  var sum = 0
  // behaviour which is applied on the state
  override def receive: Receive = {
    // receives message an integer
    case x: Int => sum = sum + x
      println(s"my state as sum is $sum")
    // receives default message
    case _ => println("I don't know what are you talking about")
  }
}

class SummingActorWithConstructor(intitalSum: Int)
  extends Actor {
  // state inside the actor
  var sum = 0
  // behaviour which is applied on the state
  override def receive: Receive = {
    // receives message an integer
    case x: Int => sum = intitalSum + sum + x
      println(s"my state as sum is $sum")
    // receives default message
    case _ => println("I don't know what are you talking about")
  }
}

import akka.actor.Props
import akka.actor.ActorSystem

object BehaviourAndState extends App {
  val actorSystem = ActorSystem("HelloAkka")
  // creating an actor inside the actor system
  val actor = actorSystem.actorOf(Props[SummingActor], "summing")
  val actor2 = actorSystem.actorOf(Props(classOf[SummingActorWithConstructor], 10), "summingactor")
  // print actor path

  while (true) {
    Thread.sleep(1000)
    actor ! 1
    actor2 ! 2
  }
}
