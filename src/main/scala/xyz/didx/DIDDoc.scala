package xyz.didx

import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import magnolia1.Monadic.given_Monadic_Try
import sttp.tapir.Schema
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*

enum DID(did: String):
  case DIDWeb(did: String)     extends DID(did)
  case DIDKey(did: String)     extends DID(did)
  case DIDUnknown(did: String) extends DID(did)

  def getDIDMethod: String                    = did.split(":")(1)
  def getDIDMethodSpecificId: String          = did.split(":")(2)
  def setDIDMethodSpecificId(id: String): DID = id match
    case id if id.startsWith("http") => DIDWeb(s"did:web:$id")
    case id                          => DIDKey(s"did:key:$id")

object DID:
  def apply(did: String): DID = did.split(":")(1) match
    case "web" => DIDWeb(did)
    case "key" => DIDKey(did)
    case _     => DIDUnknown(did)

enum Algorithm:
  case zDn, z82, z2J9, z6LS, z6Mk, z13
given algorithmEncoder: Encoder[Algorithm] = new Encoder[Algorithm] {
  final def apply(a: Algorithm): Json = Json.fromString(a.toString)
}
given algorithmDecoder: Decoder[Algorithm] = new Decoder[Algorithm] {
  final def apply(c: HCursor): Decoder.Result[Algorithm] =
    for {
      algorithm <- c.as[String]
    } yield algorithm match
      case "zDn"  => Algorithm.zDn
      case "z82"  => Algorithm.z82
      case "z2J9" => Algorithm.z2J9
      case "z6LS" => Algorithm.z6LS
      case "z6Mk" => Algorithm.z6Mk
      case "z13"  => Algorithm.z13
}

object Algorithm:
  def apply(kty: String, crv: String) =
    (kty, crv) match
      case ("EC", "P-256")    => zDn
      case ("EC", "P-384")    => z82
      case ("EC", "P-521")    => z2J9
      case ("OKP", "Ed25519") => z6Mk
      case ("OKP", "X25519")  => z6LS
      case ("RSA", _)         => z13

case class DIDDoc(
  `@context`: List[String] = List(
    "https://www.w3.org/ns/did/v1",
    "https://w3id.org/security/suites/ed25519-2020/v1",
    "https://w3id.org/security/suites/x25519-2020/v1"
  ),
  id: String = "did:web",
  controller: List[String] = List.empty[String]
)

given didDocDecoder: Decoder[DIDDoc] = new Decoder[DIDDoc] {
  final def apply(c: HCursor): Decoder.Result[DIDDoc] =
    for {
      context    <- c.downField("@context").as[List[String]]
      id         <- c.downField("id").as[String]
      controller <- c.downField("controller").as[List[String]]
    } yield DIDDoc(context, id, controller)
}
given didDocEncoder: Encoder[DIDDoc] = new Encoder[DIDDoc] {
  final def apply(a: DIDDoc): Json = Json.obj(
    ("@context", a.`@context`.asJson),
    ("id", a.id.asJson),
    ("controller", a.controller.asJson)
  )
}
