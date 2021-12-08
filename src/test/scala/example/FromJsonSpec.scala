package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import io.circe.parser._

import cl.fbd.domain._
import cl.fbd.json.FromJson._

class FromJsonSpec extends AnyFlatSpec with Matchers {
  "pokemon row with null's" should "be decoded by circe" in {
    val pokemonData = """
      {
        "count" : 1118,
        "next" : null,
        "previous" : null    
      }
    """

    val pokemonSvc = decode [PokemonSvc] (pokemonData)

    println (pokemonSvc.toString ())

    pokemonSvc.isRight shouldEqual true

    pokemonSvc.toOption.get.count shouldEqual 1118
  }

  "pokemon row without null's" should "be decoded by circe" in {
    val pokemonData = """
      {
        "count" : 1118,
        "next" : "",
        "previous" : ""
      }
    """

    val pokemonSvc = decode [PokemonSvc] (pokemonData)

    println (pokemonSvc.toString ())

    pokemonSvc.isRight shouldEqual true

    pokemonSvc.toOption.get.count shouldEqual 1118
  }
}
