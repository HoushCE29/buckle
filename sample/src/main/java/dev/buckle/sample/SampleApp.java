package dev.buckle.sample;

import dev.buckle.UILauncher;

public class SampleApp {

    public static void main(String... args) {
        UILauncher.begin()
                .setTitle("Sample App")
                .setWidth(750)
                .setHeight(500)
                .setHtmlResource("sample.html")
                .setIconResource("tank.jpg")
                .bindJavaObject("sampleService", new SampleService())
                .launch();
    }
}
