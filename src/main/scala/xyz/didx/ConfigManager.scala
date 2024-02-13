package xyz.didx

import cats.effect.IO
import cats.effect.*
import cats.effect.kernel.Sync
import cats.implicits.*
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import pureconfig.*
import pureconfig.generic.derivation.default.*

object ConfigManager {
  given Logger[IO]                = Slf4jLogger.getLogger[IO]
  def info[T](value: T): IO[Unit] = Logger[IO].info(s"$value")
  def err[T](value: T): IO[Unit]  = Logger[IO].error(s"$value")

  case class WeightConf(
    start: String,
    transition: String,
    end: String,
    action: String,
    actionParams: List[String],
    agentScript: Option[String] = None, // Script passed to OpenAI agent
    conversationResult: Option[String] = None // Case class describing result obtained from a conversation
  ) derives ConfigReader:
    override def toString(): String =
      s"""
         |start: $start
         |transition: $transition
         |end: $end
         |action: $action
         |actionParams: $actionParams
         |agentScript: $agentScript
         |conversationResult: $conversationResult
         |""".stripMargin

  case class StartConf(
    place: String,
    weight: Int,
    initialParams: List[String]
  ) derives ConfigReader:
    override def toString(): String =
      s"""
         |place: $place
         |weight: $weight
         |initialParams: $initialParams
         |""".stripMargin

  case class ProtocolConf(
    places: List[String],
    transitions: List[String],
    start: StartConf,
    end: String,
    weights: List[WeightConf]
  ) derives ConfigReader:
    override def toString(): String =
      s"""
         |places: $places
         |transitions: $transitions
         |start: $start
         |end: $end
         |weights: $weights
         |""".stripMargin

  def protocolConf(interfaceName: String): ProtocolConf =
    ConfigSource.file("src/main/resources/application.conf").at(s"$interfaceName-proto").load[ProtocolConf] match
      case Left(error) =>
        err(s"$error")
        ProtocolConf(List(), List(), StartConf("", 0, List()), "", List())
      case Right(conf) => conf

}
