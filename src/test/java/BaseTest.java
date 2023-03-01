
import java.io.*;
import java.util.Properties;

public class BaseTest {

    Properties prop =  new Properties();
    public String fileName = "config.properties";

    public void writeFile() throws IOException {
        OutputStream os = new FileOutputStream(fileName);
        prop.setProperty("username", "dexcomvnv+181@gmail.com");
        prop.setProperty("password", "Info@123");
        prop.store(os, null);
    }

    public Properties readFile() throws IOException {
        writeFile();
        InputStream is = null;
        try {
            is =  new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.load(is);
        return prop;
    }

    public String getUserName() throws IOException {
        readFile();
        return prop.getProperty("username");
    }

    public String getPassword() throws IOException {
        readFile();
        return prop.getProperty("password");
    }
}
