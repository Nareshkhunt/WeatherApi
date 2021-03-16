package org.openweathermap.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;


    public ConfigFileReader(String propertyFilePath){
        propertyFilePath = "configuration/" + propertyFilePath;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(".properties not found at " + propertyFilePath);
        }
    }

    public String getApiEnvironment() {
        String url = properties.getProperty("BASE_URI");
        if(url != null) return url;
        else throw new RuntimeException("base_uri is not specified in the .properties file.");
    }



    public String getApiKey() {
        String url = properties.getProperty("APIKey");
        if (url != null) return url;
        else throw new RuntimeException("api key is not specified in the .properties file.");
    }

    public String getInvalidApiKey() {
        String url = properties.getProperty("Invalid_APIKey");
        if (url != null) return url;
        else throw new RuntimeException("api key is not specified in the .properties file.");
    }

    public String getEmptyApiKey() {
        String url = properties.getProperty("Empty_APIKey");
        if (url != null) return url;
        else throw new RuntimeException("api key is not specified in the .properties file.");
    }
}
