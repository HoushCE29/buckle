# Buckle
A framework to simplify full stack development of desktop apps.

## Build
```
gradlew clean build
```

## Usage
The main class of concern is [`UILauncher`](core/src/main/java/dev/buckle/UILauncher.java).
This class is a builder-style UI setup and finishes in a call to `launch()`.

### Example

```java
import dev.buckle.UILauncher;

public class MyApp {
    public static void main(String[] args) {
        UILauncher.builder()
                .setTitle("My App Title")
                .setWidth(750)
                .setHeight(500)
                .setResizable(true)
                .setFullScreen(true)
                .setHtmlResource("path/to/web/resources/index.html")
                .setIconResource("path/to/web/resources/icon.ico")
                .bindJavaObject("myService", new MyService())
                .launch();
    }
}

```

### Properties

| Property     | Type    | Default        | Description                                                             |
|--------------|---------|----------------|-------------------------------------------------------------------------|
| title        | String  |                | Set the application title.                                              |
| width        | int     | System default | The initial width of the app window.                                    |
| height       | int     | System default | The initial height of the app window.                                   |
| resizable    | boolean | true           | Whether the window is resizable.                                        |
| fullScreen   | boolean | false          | Whether the app launches in full screen.                                |
| htmlResource | String  |                | Resource path to the `index.html` or equivalent HTML landing page/file. |
| iconResource | String  | System default | Resource path to the app icon.                                          |

### Java Bindings

Since this app is not a classic cloud-based style full stack web app (e.g. HTML front end, Java REST backend like Spring),
the front end needs a way to invoke the back end. To do this, Java objects can be attached to JavaScript via bindings.
In the example above, this comes in the form of the `bindJavaObject(...)` method.

Below is an example of invoking a Java object from JavaScript as configured in the above example.

`MyService.java`
```java
public class MyService {
    
    public String getSomeString() {
        return "this is some string";
    }
}
```

`someJavaScript.js`
```javascript
function fetchSomeString() {
    return App.myService.getSomeString();
}
```
