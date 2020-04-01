package com.sample.test.demo.utils;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
    /*
    Created ConfigurationReader to read property files
     */
    private static Properties prop;
    private static Logger logger = Logger.getLogger(ConfigurationReader.class);

    static {
        try (InputStream input = new FileInputStream("configuration.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Failed to load property file");
        }
    }

    public static synchronized String getProperty(String property) {
        return prop.getProperty(property);
    }
}
