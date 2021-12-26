package cl.fbd.json

import io.circe.{Encoder, Decoder}
import cats.effect.kernel.Concurrent
import io.circe._, io.circe.generic.semiauto.deriveDecoder
import org.http4s.EntityDecoder
import org.http4s.client._
import org.http4s.circe.jsonOf

import cl.fbd.domain.{PokemonSvc, PokemonData, PokemonAblility}

object FromJson {
  implicit val PokemonSvcDecoder: Decoder[PokemonSvc] = deriveDecoder[PokemonSvc]
  implicit def PokemonSvcEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, PokemonSvc] = jsonOf

  implicit val PokemonDataDecoder: Decoder[PokemonData] = deriveDecoder[PokemonData]
  implicit def PokemonDataEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, PokemonData] = jsonOf

  implicit val PokemonAblilityDecoder: Decoder[PokemonAblility] = deriveDecoder[PokemonAblility]
  implicit def PokemonAblilityEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, PokemonAblility] = jsonOf
}
