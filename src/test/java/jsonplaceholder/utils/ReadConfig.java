package jsonplaceholder.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    private static Properties properties;

    public static Properties loadProperties(String path) {
        Properties properties = null;
        try (FileInputStream fis = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fis);
        } catch (Exception ignored) {

        }
        return properties;
    }

    public static String get(String key) {
        if (properties == null) {
            properties = loadProperties("src/main/resources/config.properties");
            return properties.getProperty(key);
        }
        return properties.getProperty(key);
    }
}
