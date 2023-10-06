package dev.buckle.internal.javafx;

import dev.buckle.core.UIConfiguration;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The actual JavaFX app.
 */
public class JavaFXApp extends Application {
    private static final Map<String, UIConfiguration> CONFIGS = new ConcurrentHashMap<>();
    private UIConfiguration configuration;

    @Override
    public void init() {
        // extract the configuration meant for this runtime
        List<String> params = getParameters().getRaw();
        // sanity check
        if (params.size() != 1) {
            throw new IllegalStateException("JavaFX failed to init due to incorrect param count; expected 1 but got " + params.size());
        }
        configuration = CONFIGS.get(params.get(0));
        // more sanity check
        if (configuration == null) {
            throw new IllegalStateException("Failed to resolve UI configuration for runtime ID: " + params.get(0));
        }
    }

    @Override
    public void start(Stage stage) {
        WebView view = new WebView();
        WebEngine engine = view.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.getLoadWorker().stateProperty().addListener((value, prev, state) -> {
            if (state == Worker.State.SUCCEEDED) {
                // Create the app object
                engine.executeScript("window.App = {}");
                // Grab the app object
                final JSObject app = (JSObject) engine.executeScript("window.App");
                // Push all Java objects into the JS app object
                configuration.getObjects().forEach(app::setMember);
            }
        });
        if (configuration.getHtmlUrl() != null) {
            engine.load(configuration.getHtmlUrl());
        }
        stage.setTitle(configuration.getTitle());
        stage.setResizable(configuration.isResizable());
        stage.setFullScreen(configuration.isFullScreen());
        if (configuration.getIconUrl() != null) {
            stage.getIcons().add(new Image(configuration.getIconUrl()));
        }
        Scene scene = new Scene(
                new StackPane(view),
                configuration.getWidth(),
                configuration.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    static void launch(UIConfiguration configuration) {
        final String runtimeId = UUID.randomUUID().toString();
        CONFIGS.put(runtimeId, configuration);
        launch(JavaFXApp.class, runtimeId);
    }
}
