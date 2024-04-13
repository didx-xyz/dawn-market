lazy val Scala3 = "3.4.1"

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

lazy val castanetVersion    = "0.1.11"
lazy val catsEffectVersion  = "3.5.4"
lazy val circeVersion       = "0.14.2"
lazy val http4sVersion      = "0.23.26"
lazy val http4sBlazeVersion = "0.23.16"
lazy val logbackVersion     = "1.5.5"
lazy val munitVersion       = "1.0.0-M11"
lazy val munitCEVersion     = "2.0.0-M4"
lazy val pureconfigVersion  = "0.17.6"
lazy val scalaTestVersion   = "3.2.18"
lazy val slf4jVersion       = "2.0.13"
lazy val sttpVersion        = "3.9.5"
lazy val tapirVersion       = "1.9.1"

lazy val commonSettings   = Seq(
  resolvers ++= Seq(
    Resolver.mavenLocal,
    "github" at "https://maven.pkg.github.com/didx-xyz",
    "jitpack" at "https://jitpack.io",
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "releases" at "https://oss.sonatype.org/content/repositories/releases"
  ),
  libraryDependencies ++= Seq(
    "xyz.didx"                      %% "castanet"                       % castanetVersion,
    "org.typelevel"                 %% "cats-effect"                    % catsEffectVersion,
    "io.circe"                      %% "circe-core"                     % circeVersion,
    "io.circe"                      %% "circe-parser"                   % circeVersion,
    "io.circe"                      %% "circe-generic"                  % circeVersion,
    "com.github.pureconfig"         %% "pureconfig-core"                % pureconfigVersion,
    "com.github.pureconfig"         %% "pureconfig-cats-effect"         % pureconfigVersion,
    "com.softwaremill.sttp.client3" %% "core"                           % sttpVersion,
    "com.softwaremill.sttp.client3" %% "circe"                          % sttpVersion,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % sttpVersion,
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % tapirVersion,
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % tapirVersion,
    "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"        % tapirVersion,
    "ch.qos.logback"                 % "logback-classic"                % logbackVersion,
    "com.github.geirolz"            %% "erules-core"                    % "0.1.1",
    "org.http4s"                    %% "http4s-blaze-server"            % http4sBlazeVersion,
    "org.http4s"                    %% "http4s-dsl"                     % http4sVersion,
    "org.http4s"                    %% "http4s-ember-server"            % http4sVersion,
    "org.slf4j"                      % "slf4j-nop"                      % slf4jVersion
  ) ++ Seq(
    "xyz.didx"                      %% "castanet"                       % castanetVersion    % Test,
    "org.scalatest"                 %% "scalatest"                      % scalaTestVersion   % Test,
    "org.typelevel"                 %% "cats-effect"                    % catsEffectVersion  % Test,
    "io.circe"                      %% "circe-core"                     % circeVersion       % Test,
    "io.circe"                      %% "circe-parser"                   % circeVersion       % Test,
    "io.circe"                      %% "circe-generic"                  % circeVersion       % Test,
    "com.github.pureconfig"         %% "pureconfig-core"                % pureconfigVersion  % Test,
    "com.github.pureconfig"         %% "pureconfig-cats-effect"         % pureconfigVersion  % Test,
    "com.softwaremill.sttp.client3" %% "circe"                          % sttpVersion        % Test,
    "com.softwaremill.sttp.client3" %% "core"                           % sttpVersion        % Test,
    "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % sttpVersion        % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % tapirVersion       % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % tapirVersion       % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-sttp-stub-server"         % tapirVersion       % Test,
    "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"        % tapirVersion       % Test,
    "ch.qos.logback"                 % "logback-classic"                % logbackVersion     % Test,
    "com.github.geirolz"            %% "erules-core"                    % "0.1.1"            % Test,
    "org.http4s"                    %% "http4s-blaze-server"            % http4sBlazeVersion % Test,
    "org.http4s"                    %% "http4s-dsl"                     % http4sVersion      % Test,
    "org.http4s"                    %% "http4s-ember-server"            % http4sVersion      % Test,
    "org.slf4j"                      % "slf4j-nop"                      % slf4jVersion       % Test,
    "org.scalameta"                 %% "munit"                          % munitVersion       % Test,
    "org.scalameta"                 %% "munit-scalacheck"               % munitVersion       % Test,
    "org.typelevel"                 %% "munit-cats-effect"              % munitCEVersion     % Test
  )
)
lazy val scalafixSettings = Seq(semanticdbEnabled := true)
