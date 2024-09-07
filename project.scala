//> using scala 3.3.1
//> using toolkit typelevel:latest
////> using option scalacOptions -Xmax-inlines:64
//> using packaging.output "dawnctl"
//> using nativeMode "release-fast"
//> using repository https://jitpack.io

//> using dep org.typelevel::cats-effect:3.5.2
//> using dep io.circe::circe-core:0.14.10
//> using dep io.circe::circe-parser:0.14.10
//> using dep io.circe::circe-generic:0.14.10
//> using dep com.github.pureconfig::pureconfig-core:0.17.4
//> using dep com.github.pureconfig::pureconfig-cats-effect:0.17.4

//> using dep com.softwaremill.sttp.client3::core:3.9.1
//> using dep com.softwaremill.sttp.client3::circe:3.9.1
//> using dep com.softwaremill.sttp.client3::async-http-client-backend-cats:3.9.1
//> using dep xyz.didx::castanet:0.1.14
//> using dep com.softwaremill.sttp.tapir::tapir-http4s-server:1.10.8
//> using dep org.http4s::http4s-ember-server:0.23.24
//> using dep com.softwaremill.sttp.tapir::tapir-swagger-ui-bundle:1.10.8
//> using dep com.softwaremill.sttp.tapir::tapir-json-circe:1.10.8
//> using dep ch.qos.logback:logback-classic:1.4.14
//> using test.dep com.softwaremill.sttp.tapir::tapir-sttp-stub-server:1.10.8
//> using test.dep org.scalatest::scalatest:3.2.19
//> using test.dep com.softwaremill.sttp.client3::circe:3.9.1
//> using dep com.github.geirolz::erules-core:0.1.0
//> using dep org.http4s::http4s-blaze-server:0.23.15
//> using dep org.http4s::http4s-dsl:0.23.24

//> using dep org.slf4j:slf4j-nop:2.0.9
