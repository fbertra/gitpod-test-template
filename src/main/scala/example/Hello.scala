package example

object Hello extends Greeting with App {
  println(greeting)

  println (greet ("yo", "hola"))
  println (greet ("me"))
}

trait Greeting:
  lazy val greeting: String = "hello"

  def greet (to: String, msg: String | Null = null): String = 
    val _msg = msg match 
      case msg: String => msg
      case null => greeting

    s"${_msg} $to"
