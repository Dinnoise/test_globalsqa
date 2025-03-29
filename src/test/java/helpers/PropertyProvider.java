package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private static final String PROPERTIES_FILE = "src/test/resources/local_data.properties";
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурационного файла: " + PROPERTIES_FILE, e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
