package cl.fbd.domain

final case class PokemonData (
  name: String,
  url: String
)

final case class PokemonSvc (
  count: Integer,
  next: Option [String],
  previous: Option [String]

  // results: Seq [PokemonData]
)