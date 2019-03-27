package route;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

public class Route {

    private final static String FXML_PATH = "src/main/java/sample/views/";

    public static URL path (String fxmlName) {
        try {
            return new File(FXML_PATH + fxmlName).toURI().toURL();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
