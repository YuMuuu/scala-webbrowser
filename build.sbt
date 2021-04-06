name := "scalafx-webbrowser"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R21"

lazy val javaFXModules = {
  // Determine OS version of JavaFX binaries
  lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            =>
      throw new Exception("Unknown platform!")
  }
  // Create dependencies for JavaFX modules
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map( m=> "org.openjfx" % s"javafx-$m" % "15.0.1" classifier osName)
}

scalacOptions += "-Ymacro-annotations"

libraryDependencies ++= javaFXModules ++ Seq("org.scalafx" %% "scalafxml-core-sfx8" % "0.5")
