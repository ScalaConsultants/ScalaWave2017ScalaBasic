package io.scalac.scala.basic.dataStructures


object SeqDemo extends App {
  val emptySeq = Seq.empty
  println(s"This Sequence has nothing in it $emptySeq")

  val seq = Seq(1,2,3)
  println(s"This Sequence has elements $seq")

  val appended = 999 +: Seq(1,2,3)
  println(s"Appended Sequences $appended")

  val combined = Seq(1) ++ Seq(2,3)
  println(s"Combined Sequences $combined")

  val filledWithDummyData = Seq.fill(2,3)(0)
  println(s"Matrix of 0s $filledWithDummyData")

  val element = Seq(0,10,20,30)(3)
  println(s"Sequences allow to get an element by index $element, but it's not common")

  val list: Seq[Int] = List(1,2,3)
  println(s"Sequences form a hierarchy - List is an example of Sequences $list")

  println(s"Safe approach to Seq is either iteration (discussed later) or *Option methods ${seq.headOption} ${seq.lastOption}")
}
