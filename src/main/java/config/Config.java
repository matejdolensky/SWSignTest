package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        String env = System.getProperty("env", "application"); // default: application.properties
        String fileName = env + ".properties";

        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + fileName);
            }

            // Ensure UTF-8 decoding
            props.load(new java.io.InputStreamReader(input, StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + fileName, e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String baseUrl() {
        return get("gui.baseUrl");
    }

    public static String apiUrl() {
        return get("api.baseUrl");
    }

    public static String username() {
        return get("user.username");
    }

    public static String password() {
        return get("user.password");
    }
}
