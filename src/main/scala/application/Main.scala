package application

import javafx.scene
import javafx.scene.{Parent => JavaParent}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, Parent}
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}


import scalafx.scene.Parent.sfxParent2jfx

object Main extends JFXApp {

  val resource = getClass.getResource("application.fxml")


  val loader = new FXMLLoader(resource, NoDependencyResolver)
  loader.load()
  val root1: Parent = loader.getRoot[Parent]()
  val controller1 = loader.getController[Controller]


  val css = getClass.getResource("application.css").toExternalForm

  stage = new PrimaryStage {
    scene = new Scene {
      root = root1
      stylesheets = List(css)
    }
  }
}

