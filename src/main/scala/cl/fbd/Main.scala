package cl.fbd

import cats.implicits._

import cats.effect.{IO, IOApp, SyncIO}
import cats.effect.unsafe.IORuntime

import scala.concurrent.ExecutionContext.global

import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client

import cl.fbd.domain.{PokemonSvc, PokemonData}
import cl.fbd.json.FromJson._
import cl.fbd.domain.PokemonAblility

// see https://github.com/PendaRed/scala3-fs2/blob/main/src/main/scala/com/jgibbons/fs2/a

object HttpClient extends IOApp.Simple {
  val pokemonApiURL = "https://pokeapi.co/api/v2/pokemon?limit=5"
  val pokemonAbilityURL = "https://pokeapi.co/api/v2/ability/"

  def callPokemonAbility (client: Client [IO], data: PokemonData): IO [Unit] = {
    val urlSpiitted = data.url.split ("/")

    val id = urlSpiitted (urlSpiitted.length - 1)

    val expectedAbility = client.expect[PokemonAblility](pokemonAbilityURL + id)

    expectedAbility.flatMap (ability => IO (println (ability.toString ())))
  }

  def run = {    
    val builder = BlazeClientBuilder[IO](scala.concurrent.ExecutionContext.global)
    
    builder.resource.use { client => {
        val expectedSvc = client.expect[PokemonSvc](pokemonApiURL)

        expectedSvc.flatMap (pokemonSvc => {
            val logSvc = IO (println (pokemonSvc.toString ()))

            val res = for (pokemonData <- pokemonSvc.results) yield {
              callPokemonAbility (client, pokemonData)
            }

            logSvc *> res.sequence.map(_ => ())
          })
      }
    }
  }
}
