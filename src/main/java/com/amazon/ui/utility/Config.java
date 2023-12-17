package com.amazon.ui.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testConfig.properties";
    private static final Properties properties = new Properties();

    static {
    	
        try {
        	FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    
    public static String getShortWait() {
    	return properties.getProperty("shortWait");
    }
    
}
