package io.scalac.scala.basic.functionalOperators

object FlatMapDemo extends App {
  val numbers = Seq(1,2,3,4)

  val mapResult: Seq[Seq[Int]] = numbers.map(num => Seq(-num, num))
  val flatMapResult: Seq[Int] = numbers.flatMap(num => Seq(-num, num))
  println(s"Result after mapping $mapResult")
  println(s"Result after flat-mapping $flatMapResult")

  def findDivisorsFor(i: Int): Seq[Int] = {
    (1 to i).filter(num => i % num == 0)
  }
  println(s"Divisors for 5 ${findDivisorsFor(5)}, for 10 ${findDivisorsFor(10)}")

  val divisorsArrayOfArrays = numbers.map(n => findDivisorsFor(n))
  println(s"Map nests collections in collections - $divisorsArrayOfArrays")
  val divisorsArray = numbers.flatMap(n => findDivisorsFor(n))
  println(s"Flatmap flattens nested collections by one level - $divisorsArray")
}
