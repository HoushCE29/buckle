package dev.buckle.internal.javafx;

import dev.buckle.core.UI;
import dev.buckle.core.UIConfiguration;

/**
 * UI impl based on JavaFX.
 */
class JavaFXUI implements UI {

    @Override
    public void launch(UIConfiguration configuration) {
        JavaFXApp.launch(configuration);
    }
}
