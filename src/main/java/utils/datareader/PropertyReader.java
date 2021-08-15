package utils.datareader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;

public class PropertyReader {
   private Properties properties;
    private File file;
    public PropertyReader(String filepath) {
   try{
       FileInputStream inputStream = new FileInputStream(new File(filepath));
       properties = new Properties();
       properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

   }catch (IOException e)
   {e.printStackTrace();}

    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
