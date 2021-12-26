package cl.fbd

import cats.implicits._

import cats.effect.{IO, IOApp, SyncIO}
import cats.effect.unsafe.IORuntime

import scala.concurrent.ExecutionContext.global

import org.http4s.blaze.client.BlazeClientBuilder

import cl.fbd.domain.{PokemonSvc, PokemonData}
import cl.fbd.json.FromJson._
import cl.fbd.domain.PokemonAblility

// see https://github.com/PendaRed/scala3-fs2/blob/main/src/main/scala/com/jgibbons/fs2/a

object HttpClient extends IOApp.Simple {
  val pokemonApiURL = "https://pokeapi.co/api/v2/pokemon?limit=5"
  val pokemonAbilityURL = "https://pokeapi.co/api/v2/ability/"

  def run = {
    
    val builder = BlazeClientBuilder[IO](scala.concurrent.ExecutionContext.global)
    
    builder.resource.use { client => {
        val expected = client.expect[PokemonSvc](pokemonApiURL)

        val ret = expected.flatMap (pokemonSvc => {
            println (pokemonSvc.toString ())

            val res = for (pokemonData <- pokemonSvc.results) yield {
              val urlSpiitted = pokemonData.url.split ("/")

              val id = urlSpiitted (urlSpiitted.length - 1)

              val expectedAbility = client.expect[PokemonAblility](pokemonAbilityURL + id)

              expectedAbility.map (ability => println (ability.toString ()))
            }

            res.sequence.map(_ => ())
            // pokemonSvc
          })

        ret
      }
    }
  }
}
