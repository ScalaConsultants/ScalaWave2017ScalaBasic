package io.scalac.scala.basic.functions

object FunctionDemo extends App {
  def functionAsMethod() = {
    println(s"Function as method")
  }

  val functionAsVariable = () => {
    println(s"Function as variable")
  }

  println(s"Both functions can be called the same way ${functionAsMethod()} ${functionAsVariable()}")

  val nextWithTypeSugar: (Int) => String = (num:Int) => (num + 1).toString
  val nextWithType: Function1[Int, String] = (num:Int) => (num + 1).toString


  val add = (numberA: Int, numberB: Int) => {
    numberA + numberB // the return type is inferred from the last expression
  }
  println(s"Functions can be called with multiple arguments ${add(11,20)}")

  def multiply(numberA: Int)(numberB: Int) = {
    numberA * numberB
  }
  println(s"Functions also support param groups - 2 groups in this example: ${multiply(2)(4)}")

  val double = multiply(2) _ // only one param group is filled, the other is "left for later" using _
  println(s"Param groups allow us to fill some params, but leave filling the rest for later ... ")
  println(s"... ${double(2)} ${double(3)} ${double(4)}")
  val triple = multiply(3) _
  println(s"... ${triple(2)} ${triple(3)} ${triple(4)}")

  val dataSource: Seq[Int] = Seq(1, 2, 3, 4, 5, 6)
  val filterFunction = (number: Int) => number % 2 == 0
  val keepEvenNumbers = dataSource.filter(filterFunction)
  println(s"We were using functions even before for functional operators: $keepEvenNumbers")

  val keepEvenNumbersInline = dataSource.filter(_ % 2 == 0)
  println(s"The shorter syntax with _ is possible thanks to interference based on the collection type: $keepEvenNumbersInline")

  def subtractAndCallback(numberA: Int, numberB: Int)(callback: (Int) => Unit) = {
    val result = numberA - numberB
    callback(result)
  }
  subtractAndCallback(10, 5) { result =>
    println(s"Since functions are first class citizens we can pass functions to functions: $result")
  }
  val subtract99From100 = subtractAndCallback(100, 99) _
  subtract99From100 { result =>
    println(s"Many parameter groups help us to use the same result in many contexts: $result")
  }
  subtract99From100{println}


  val squareRoot: PartialFunction[Int, Double] = {
    case number if number >= 0 =>
      Math.sqrt(number)
    // no default case, we defined our function only for positive arguments
  }
  println("An interesting property of functions is partial application\n" +
    "They can be defined for some argument, but not others: \n"+
    s"${squareRoot.isDefinedAt(1)} ${squareRoot.isDefinedAt(0)} ${squareRoot.isDefinedAt(-1)}")

  val partialFunctionToTraverseMap: PartialFunction[(Int, String), Unit] = {
    case (key, value) =>
      println(s"Got key: $key")
    // no default case, this partial function can only handle pairs
  }
  Map(1 -> "one", 2 -> "two", 3 -> "three").map(partialFunctionToTraverseMap)
}
