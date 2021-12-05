package cl.fbd

import cats.effect.{IO, SyncIO}
import cats.effect.unsafe.IORuntime

import scala.concurrent.ExecutionContext.global

import org.http4s.client._
import org.http4s.blaze.client._

// see https://github.com/PendaRed/scala3-fs2/blob/main/src/main/scala/com/jgibbons/fs2/a

val pokemonApiURL = "https://pokeapi.co/api/v2/pokemon?limit=5"

@main def httpClient () =
  val x = BlazeClientBuilder[IO](scala.concurrent.ExecutionContext.global).resource.use { client =>
      client.expect[String](pokemonApiURL)
    }
    .map (sz => 
      println (sz)
      sz
    )

  x.unsafeRunSync () (cats.effect.unsafe.IORuntime.global)