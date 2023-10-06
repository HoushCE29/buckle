package dev.buckle;

import dev.buckle.core.UIConfiguration;
import dev.buckle.core.UIFactory;

/**
 * Main API to launch UI. This is the place to bind objects,
 * define web resources, and set application properties.
 */
public class UILauncher {
    private final UIConfiguration configuration;

    public UILauncher() {
        configuration = new UIConfiguration();
    }

    public UILauncher setTitle(String title) {
        configuration.setTitle(title);
        return this;
    }

    public UILauncher setWidth(int width) {
        configuration.setWidth(width);
        return this;
    }

    public UILauncher setHeight(int height) {
        configuration.setHeight(height);
        return this;
    }

    public UILauncher setResizable(boolean resizable) {
        configuration.setResizable(resizable);
        return this;
    }

    public UILauncher setFullScreen(boolean fullScreen) {
        configuration.setFullScreen(fullScreen);
        return this;
    }

    public UILauncher bindJavaObject(String jsName, Object object) {
        configuration.addObject(jsName, object);
        return this;
    }

    public UILauncher setHtmlResource(String htmlResource) {
        configuration.setHtmlUrl(Thread.currentThread()
                .getContextClassLoader()
                .getResource(htmlResource)
                .toExternalForm());
        return this;
    }

    public UILauncher setIconResource(String iconResource) {
        configuration.setIconUrl(Thread.currentThread()
                .getContextClassLoader()
                .getResource(iconResource)
                .toExternalForm());
        return this;
    }

    public void launch() {
        UIFactory.getDefault().launch(configuration);
    }

    public static UILauncher builder() {
        return new UILauncher();
    }
}
