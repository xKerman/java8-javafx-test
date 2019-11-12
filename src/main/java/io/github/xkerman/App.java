/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.xkerman;

import java.util.List;
import netscape.javascript.JSObject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class App extends Application {
    private JavaScriptBridge bridge = new JavaScriptBridge();

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        List<String> args = getParameters().getUnnamed();
        if (args.size() < 1) {
            return;
        }
        String url = args.get(0);

        WebView webView = createWebView(url);
        root.getChildren().add(webView);

        stage.setScene(scene);
        stage.show();
    }

    private WebView createWebView(String url) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);

        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("bridge", bridge);

        return webView;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
