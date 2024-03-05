package xyz.didx

import cats.effect._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router

import scala.concurrent.ExecutionContext

/**
 * PetriRunner
 *
 * @param interfaceName
 * @param logger
 */

object PetriRunner extends IOApp:
  given logger: org.log4s.Logger = org.log4s.getLogger
  given ec: ExecutionContext     =
    scala.concurrent.ExecutionContext.Implicits.global

  def run(args: List[String]): IO[ExitCode] =

    val interfaceName = args.headOption.getOrElse("purchase")
    val compiler      = PetriCompiler[IO](interfaceName)
    compiler.generateConversationAgents() &>
      BlazeServerBuilder[IO]
        .withExecutionContext(ec)
        .bindHttp(8080, "localhost")
        .withHttpApp(Router("/" -> (compiler.routes)).orNotFound)
        // .withSocketKeepAlive(true)
        .resource
        .use { _ =>
          IO {
            println("Go to: http://localhost:8080/docs")
            println("Press any key to exit ...")
            scala.io.StdIn.readLine()
          }
        }
        .as(ExitCode.Success)
