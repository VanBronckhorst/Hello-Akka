package com.packt.chapter3

import akka.actor.{Props, Actor, ActorSystem}
import akka.routing.{RoundRobinPool, DefaultResizer}

case class Load(n:Int)

class LoadActor extends Actor {
  def receive = {
    case Load(n) =>
      println(s"Handing loads of requests, ${self.path.name}")
      println(fibonacci(n))
  }


  def fibonacci(n: Int):Int = {
    if (n == 0 || n == 1) {
      1
    } else {
      fibonacci(n-1) + fibonacci(n-2)
    }
  }
}

object ResizablePool extends App {
  val system = ActorSystem("Hello-Akka")
  val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)
  val router = system.actorOf(RoundRobinPool(5, Some(resizer)).props(Props[LoadActor]))

  for (i <- 1 to 500) {
      router ! Load(50)
  }

}