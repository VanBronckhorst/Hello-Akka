package com.packt.chapter4

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by Filippo on 6/24/17.
  */
object AddFuture extends App {
  val future = Future(1+2).mapTo[Int]
  val sum = Await.result(future, 10 seconds)
  println(s"Future Result $sum")
}