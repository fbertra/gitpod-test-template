package cl.fbd.json

import io.circe.{Encoder, Decoder}
import cats.effect.kernel.Concurrent
import io.circe._, io.circe.generic.semiauto.deriveDecoder
import org.http4s.EntityDecoder
import org.http4s.client._
import org.http4s.circe.jsonOf

import cl.fbd.domain.PokemonSvc

object FromJson {
  implicit val PokemonSvcDecoder: Decoder[PokemonSvc] = deriveDecoder[PokemonSvc]
  implicit def PokemonSvcEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, PokemonSvc] = jsonOf
}
