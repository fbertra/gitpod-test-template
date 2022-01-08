package cl.fbd

import cats.implicits._

import cats.effect.{IO, IOApp, SyncIO}
import cats.effect.unsafe.IORuntime

import scala.concurrent.ExecutionContext.global

import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client

import cl.fbd.domain.{PokemonSvc, PokemonData}
import cl.fbd.domain.PokemonAblility

import cl.fbd.httpclient.PokemonApiClient

object YetAnotherPokemonApiClient extends IOApp.Simple {
  import PokemonApiClient._

  def logPokemonAbility (ability: PokemonAblility): IO [Unit] = IO (println (ability.toString ()))
  def logPokemonSvc (pokemonSvc: PokemonSvc): IO [Unit] = IO (println (pokemonSvc.toString ()))

  def callServices (client: Client [IO]) = {
    val ioPokemonSvc = callPokemonSvc (client, 10)

    val ret = ioPokemonSvc
      .flatMap (pokemonSvc => logPokemonSvc (pokemonSvc) *> IO.pure (pokemonSvc))
      .flatMap (pokemonSvc => {
        val res = for (pokemonData <- pokemonSvc.results) yield {
            val ioPokemonAbility = callPokemonAbility (client, pokemonData)

            val log = ioPokemonAbility.flatMap (logPokemonAbility _)

            log *> ioPokemonAbility
          }
          
        res.sequence.map (_ => ())
      })

    ret
  }

  def run = {    
    val builder = BlazeClientBuilder[IO](scala.concurrent.ExecutionContext.global)
    
    builder.resource.use { client => callServices(client) }
  }
}