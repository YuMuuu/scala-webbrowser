package application

import javafx.scene
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Parent, Scene}
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.scene.layout.BorderPane
import scalafx.scene.layout.LayoutIncludes.jfxBorderPane2sfx

import javafx.application.Application


object Main extends JFXApp {
  val resource = getClass.getResource("application.fxml")
  val loader: FXMLLoader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()
  val _root: BorderPane = loader.getRoot[BorderPane]()


  val css = getClass.getResource("application.css").toExternalForm

  stage = new PrimaryStage {
    scene = new Scene {
      root = _root
      stylesheets = List(css)
    }
  }


}

