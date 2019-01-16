name := "imdb-test"

organization in ThisBuild := "itstar"
version in ThisBuild := "1.0.0"
scalaVersion in ThisBuild := "2.12.8"

lazy val settings = Seq(
  scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-encoding",
    "utf8",
    "-Ypartial-unification"
  ),
  resolvers ++= Seq(
    "Local Maven Repository".at("file://" + Path.userHome.absolutePath + "/.m2/repository"),
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val dependencies =
  new {
    val akkaV         = "2.5.18"
    val akkaHttpV     = "10.1.5"
    val slickVersion  = "3.2.3"
    val catsV         = "1.5.0"
    val scalaLoggingV = "3.7.2"
    val postgresV     = "42.1.4"
    val slickPgV      = "0.16.3"
    val logBackV      = "1.2.3"
    val alpakkaV      = "1.0-M1"
    val shapelessV    = "2.3.3"
    val slickPgVersion = "0.16.3"

    val akkaHttp         = "com.typesafe.akka"          %% "akka-http"                 % akkaHttpV
    val akkaStream       = "com.typesafe.akka"          %% "akka-stream"               % akkaV
    val logging          = "com.typesafe.scala-logging" %% "scala-logging"             % scalaLoggingV
    val slick            = "com.typesafe.slick"         %% "slick"                     % slickVersion
    val postgres         = "org.postgresql"             % "postgresql"                 % postgresV
    val spray            = "com.typesafe.akka"          %% "akka-http-spray-json"      % akkaHttpV
    val catsCore         = "org.typelevel"              %% "cats-core"                 % catsV
    val catsMacros       = "org.typelevel"              %% "cats-macros"               % catsV
    val catsKernel       = "org.typelevel"              %% "cats-kernel"               % catsV
    val slickPgSprayJson = "com.github.tminglei"        %% "slick-pg_spray-json"       % slickPgV
    val slickPg          = "com.github.tminglei"        %% "slick-pg"                  % slickPgV
    val logback          = "ch.qos.logback"             % "logback-classic"            % logBackV
    val alpakkaCSV       = "com.lightbend.akka"         %% "akka-stream-alpakka-csv"   % alpakkaV
    val alpakkaSlick     = "com.lightbend.akka"         %% "akka-stream-alpakka-slick" % alpakkaV
    val shapeless        = "com.chuusai"                %% "shapeless"                 % shapelessV
  }

lazy val global = project
  .in(file("."))
  .aggregate(rest)

lazy val rest =
  project
    .settings(
      settings,
      libraryDependencies ++= Seq(
        dependencies.akkaStream,
        dependencies.akkaHttp,
        dependencies.spray,
        dependencies.catsCore,
        dependencies.catsKernel,
        dependencies.catsMacros,
        dependencies.slick,
        dependencies.postgres,
        dependencies.slickPg,
        dependencies.slickPgSprayJson,
        dependencies.logging,
        dependencies.logback,
        dependencies.alpakkaCSV,
        dependencies.alpakkaSlick,
        dependencies.shapeless
      )
    )

addCommandAlias(
  "fmt",
  ";scalafmtSbt;scalafmt;test:scalafmt"
)

addCommandAlias(
  "wip",
  ";clean;compile;fmt;test"
)

addCommandAlias(
  "rest",
  "; project rest; run"
)
