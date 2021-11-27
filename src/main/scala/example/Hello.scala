package example

@main def hello (to: String, msgs: String*) =
  val msg = msgs.headOption.getOrElse (null)

  import Greeting._

  println (greet ("yo", msg))

object Greeting:
  lazy val greeting: String = "hello"

  def greet (to: String, msg: String | Null = null): String = 
    val _msg = msg match 
      case msg: String => msg
      case null => greeting

    s"${_msg} $to"
