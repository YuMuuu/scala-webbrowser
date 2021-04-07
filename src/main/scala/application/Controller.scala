package application



import javafx.beans.property.ReadOnlyObjectProperty
import scalafx.application.ConditionalFeature.FXML
import scalafx.scene.control.TextField
import scalafx.scene.input.KeyCode.ENTER
import scalafx.scene.web.WebView
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.control.TextField
import scalafx.scene.input.KeyEvent
import scalafx.scene.web.WebEngine
import scalafx.scene.web.WebView
import javafx.scene.web.WebHistory
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker.State

@sfxml
class Controller(addressBar: TextField, webView: WebView) {
  //JavaScript標準関数実行準備
  JSFunctionManager.doCreate(webView.engine)

  //ページロードイベントでアドレスバーの更新
  webView.engine.getLoadWorker.stateProperty()
    .addListener(
      new ChangeListener[State] {
        def changed(var1: ObservableValue[_ <: State], oldState: State, newState: State): Unit = {
          if (newState == State.SUCCEEDED) {
            addressBar.text_=(webView.engine.location())
          }
        }
      }
    )

  load("https://www.google.co.jp/")

  //アドレスバーでEnter
  def onAddressBarEvent(e: KeyEvent): Unit = {
    e.code match {
      case ENTER => load(addressBar.text.name)
    }
  }

  //URLロード
  def load(search: String): Unit = {
    var a = search
    if (!search.matches("^https{0,1}://.+")) {
      //httpじゃないならgoogle検索を実行
      a = "https://www.google.co.jp/search?q=" + search
    }
    webView.engine.load(a)
  }

  //戻るボタン
  def onBack(e: ActionEvent): Unit = {
    var engine: WebEngine = webView.engine
    var history: WebHistory = engine.getHistory()
    if (history.getCurrentIndex() > 0) history.go(-1)
  }

  //進むボタン
  def onNext(e: ActionEvent): Unit = {
    var engine: WebEngine = webView.engine
    var history: WebHistory = engine.getHistory()
    if (history.getCurrentIndex() < history.getEntries().size() - 1) history.go(1)
  }

}

