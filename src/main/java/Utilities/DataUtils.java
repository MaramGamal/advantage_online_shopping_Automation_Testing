package Utilities;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    public final static String TEST_DATA_PATH = "src/test/resources/TestData/";

    public static String getJsonData(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            System.err.println(" Error reading JSON field '" + field + "' from file: " + jsonFilename);
            e.printStackTrace();
        }
        return "";
    }

    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(TEST_DATA_PATH + fileName + ".properties");
        properties.load(fis);

        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println(" Key '" + key + "' not found in " + fileName + ".properties file");
        }
        return value;
    }
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/TestData/environment.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load TestData.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
