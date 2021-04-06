package application

import netscape.javascript.JSObject
import scalafx.scene.web.WebEngine

//JavaScriptの関数を実装するクラス
object JSFunctionManager { //JavaScriptの関数を実装します
  def doCreate(engine: WebEngine): JSFunctionManager = {
    val manager = JSFunctionManager(engine, new JSFunctions())
    val obj: JSObject = engine.executeScript("window").asInstanceOf[JSObject]
    obj.setMember("_java", manager.functions)
    //関数のセッティング
    engine.executeScript("console.log = function(msg){window._java.log(msg);}")
    manager
  }
}

case class JSFunctionManager(engine: WebEngine, functions: JSFunctions) {
  def unload(): Unit = {
    val obj: JSObject = engine.executeScript("window").asInstanceOf[JSObject]
    obj.removeMember("_java")
  }
}

//JavaScriptからconsole.logが実行された時の処理
class JSFunctions {
  def log(s: String): Unit = {
    System.out.println(s)
  }
}

