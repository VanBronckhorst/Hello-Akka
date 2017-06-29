package com.packt.chapter3

import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.TailChoppingPool

import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await

class TailChoppingActor extends Actor {
  override def receive = {
    case msg: String =>
      println(s"Hello, I am ${self.path.name}")
      sender ! "I say hello back to you"
    case _ => println(s" I don't understand the message")
  }
}

object TailChoppingPoolApp extends App {

  implicit val timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem("routing")
  val router  = actorSystem.actorOf(TailChoppingPool(5, within = 10 seconds, interval = 20 millis).props(Props[TailChoppingActor]))
  println( Await.result((router ? "hello").mapTo[String], 10 seconds))
}
