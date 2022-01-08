package cl.fbd.httpclient

import cats.effect.IO
import org.http4s.client.Client

import cl.fbd.domain.PokemonData
import cl.fbd.domain.PokemonAblility
import cl.fbd.json.FromJson._
import cl.fbd.domain.PokemonSvc

object PokemonApiClient {
  val pokemonApiURL = "https://pokeapi.co/api/v2/pokemon?limit="
  val pokemonAbilityURL = "https://pokeapi.co/api/v2/ability/"

  //
  def callPokemonAbility (client: Client [IO], data: PokemonData): IO [PokemonAblility] = {
    val urlSplitted = data.url.split ("/")

    val id = urlSplitted (urlSplitted.length - 1)

    client.expect[PokemonAblility](pokemonAbilityURL + id)
  }
    
  def callPokemonSvc (client: Client [IO], limit: Int): IO[PokemonSvc] = client.expect[PokemonSvc](pokemonApiURL + limit)
}
