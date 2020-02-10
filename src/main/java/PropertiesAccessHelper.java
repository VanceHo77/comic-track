import java.io.*;
import java.util.Properties;

public class PropertiesAccessHelper {

    public static Properties read(String propPath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(propPath)) {
            properties.load(input);
        }
        return properties;
    }

    public static Properties write(Properties properties, String propPath) throws IOException {
        try (OutputStream out = new FileOutputStream(propPath)) {
            properties.store(out, null);
        }
        return properties;
    }
}
