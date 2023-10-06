package dev.buckle.core;

import java.lang.reflect.Constructor;

/**
 * A factory to serve UI's.
 */
public final class UIFactory {
    private static final String DEFAULT_IMPL = "dev.buckle.internal.javafx.JavaFXUI";

    private UIFactory() {
    }

    public static UI getDefault() {
        return get(DEFAULT_IMPL);
    }

    private static UI get(String fqn) {
        try {
            return tryGet(fqn);
        }
        catch (Exception ex) {
            throw new IllegalStateException("Failed to load UI type " + fqn, ex);
        }
    }

    private static UI tryGet(String fqn) throws Exception {
        Class<?> clazz = Class.forName(fqn, true, Thread.currentThread().getContextClassLoader());
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return (UI) constructor.newInstance();
    }
}
