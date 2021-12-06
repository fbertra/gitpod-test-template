package cl.fbd.domain

final case class PokemonData (
  name: String,
  url: String
)

final case class PokemonSvc (
  count: Integer,
  next: String,
  previous: String

  // results: Seq [PokemonData]
)