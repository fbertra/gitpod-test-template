package cl.fbd

import cats.effect.{IO, SyncIO}
import cats.effect.unsafe.implicits.global
import fs2.{Pure, Stream}

// see https://github.com/PendaRed/scala3-fs2/blob/main/src/main/scala/com/jgibbons/fs2/a

@main def hello () =
  val g: Stream[Pure, Int] = Stream.emits(List(1,2,3))
  val s: SyncIO[Unit] = g.foreach(i=> SyncIO(println(i)) ).compile.drain

  import cats.effect.unsafe.implicits.global
  s.unsafeRunSync
  println("hi")