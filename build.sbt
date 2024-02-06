lazy val Scala3   = "3.3.1"
lazy val Scala213 = "2.13.6"

Global / scalaVersion         := Scala3
Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / version := "0.0.1"

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

ThisBuild / organization         := "xyz.didx"
ThisBuild / organizationName     := "DIDx"
ThisBuild / organizationHomepage := Some(url("https://www.didx.co.za/"))

ThisBuild / githubOwner      := "didx-xyz"
ThisBuild / githubRepository := "dawn-market"
githubTokenSource            := TokenSource.GitConfig("github.token") || TokenSource.Environment(
  "GITHUB_TOKEN"
)

lazy val root = project
  .in(file("."))
  .settings(
    commonSettings,
    scalafixSettings
  )

lazy val commonSettings   = Seq(
  resolvers ++= Seq(
    Resolver.mavenLocal,
    "github" at "https://maven.pkg.github.com/didx-xyz",
    "jitpack" at "https://jitpack.io",
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "releases" at "https://oss.sonatype.org/content/repositories/releases"
  ),
  libraryDependencies ++= Seq(
    "org.typelevel"                 %% "toolkit"                        % "0.1.21",
    "org.typelevel"                 %% "cats-effect"                    % "3.5.2",
    "io.circe"                      %% "circe-core"                     % "0.14.6",
    "io.circe"                      %% "circe-parser"                   % "0.14.6",
    "io.circe"                      %% "circe-generic"                  % "0.14.6",
    "com.github.pureconfig"         %% "pureconfig-core"                % "0.17.4",
    "com.github.pureconfig"         %% "pureconfig-cats-effect"         % "0.17.4",
    "com.softwaremill.sttp.client3" %% "core"                           % "3.9.1",
    "com.softwaremill.sttp.client3" %% "circe"                          % "3.9.1",
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % "3.9.1",
    "xyz.didx"                      %% "castanet"                       % "0.1.10",
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % "1.9.1",
    "org.http4s"                    %% "http4s-ember-server"            % "0.23.24",
    "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"        % "1.9.1",
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % "1.9.1",
    "ch.qos.logback"                 % "logback-classic"                % "1.4.14",
    "com.github.geirolz"            %% "erules-core"                    % "0.1.0",
    "org.http4s"                    %% "http4s-blaze-server"            % "0.23.15",
    "org.http4s"                    %% "http4s-dsl"                     % "0.23.24",
    "org.slf4j"                      % "slf4j-nop"                      % "2.0.9"
  ) ++ Seq(
    "org.typelevel"                 %% "toolkit-test"                   % "0.1.21"  % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-sttp-stub-server"         % "1.9.1"   % Test,
    "org.scalatest"                 %% "scalatest"                      % "3.2.17"  % Test,
    "com.softwaremill.sttp.client3" %% "circe"                          % "3.9.1"   % Test,
    "org.typelevel"                 %% "toolkit"                        % "0.1.21"  % Test,
    "org.typelevel"                 %% "cats-effect"                    % "3.5.2"   % Test,
    "io.circe"                      %% "circe-core"                     % "0.14.6"  % Test,
    "io.circe"                      %% "circe-parser"                   % "0.14.6"  % Test,
    "io.circe"                      %% "circe-generic"                  % "0.14.6"  % Test,
    "com.github.pureconfig"         %% "pureconfig-core"                % "0.17.4"  % Test,
    "com.github.pureconfig"         %% "pureconfig-cats-effect"         % "0.17.4"  % Test,
    "com.softwaremill.sttp.client3" %% "core"                           % "3.9.1"   % Test,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % "3.9.1"   % Test,
    "xyz.didx"                      %% "castanet"                       % "0.1.10"  % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % "1.9.1"   % Test,
    "org.http4s"                    %% "http4s-ember-server"            % "0.23.24" % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"        % "1.9.1"   % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % "1.9.1"   % Test,
    "ch.qos.logback"                 % "logback-classic"                % "1.4.14"  % Test,
    "com.github.geirolz"            %% "erules-core"                    % "0.1.0"   % Test,
    "org.http4s"                    %% "http4s-blaze-server"            % "0.23.15" % Test,
    "org.http4s"                    %% "http4s-dsl"                     % "0.23.24" % Test,
    "org.slf4j"                      % "slf4j-nop"                      % "2.0.9"   % Test
  )
)
lazy val scalafixSettings = Seq(semanticdbEnabled := true)
