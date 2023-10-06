package dev.buckle.core;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Configuration for UI.
 */
public class UIConfiguration {
    private final Map<String, Object> jsObjects = new ConcurrentHashMap<>();
    private String htmlUrl;
    private String iconUrl;
    private String title;
    private int width;
    private int height;
    private boolean resizable = true;
    private boolean fullScreen = false;

    public void addObject(String jsName, Object object) {
        jsObjects.put(jsName, object);
    }

    public Map<String, Object> getObjects() {
        return Collections.unmodifiableMap(jsObjects);
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
}
