package example

import java.io.IOException

import scalaz.zio.{App, IO}
import scalaz.zio.console.{putStrLn, getStrLn}

import scalaz.zio.IO

object Hello extends App {

  final def run(args: List[String]): IO[Nothing, ExitStatus] =
    myAppLogic.attempt.map(_.fold(_ => 1, _ => 0)).map(ExitStatus.ExitNow(_))

  def myAppLogic: IO[IOException, Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- getStrLn
      _ <- putStrLn("Hello, " + n + ", good to meet you!")
    } yield ()

}
