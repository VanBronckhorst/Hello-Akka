package com.packt.chapter1

import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.Actor.Receive


class BecomeUnBecomeActor extends Actor {
  def receive: Receive = {
    case true => context.become(isStateTrue)
    case false => context.become(isStateFalse)
    case _ => println("don't know what you want to say !! ")
  }
  def isStateTrue: Receive  = {
    case msg : String => println(s"$msg")
    case false => context.become(isStateFalse)
  }
  def isStateFalse: Receive  = {
    case msg : Int => println(s"$msg")
    case true =>  context.become(isStateTrue)
  }
}

object BecomeUnBecomeApp extends App {
  val actorSystem = ActorSystem("HelloAkka")
  val becomeUnBecome =
    actorSystem.actorOf(Props[BecomeUnBecomeActor])
  becomeUnBecome ! true
  becomeUnBecome ! "Hello how are you?"
  becomeUnBecome ! 900
  becomeUnBecome ! false
  becomeUnBecome ! 1100
  becomeUnBecome ! true
  becomeUnBecome ! "What do u do?"
}