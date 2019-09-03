package com.wiki.search.service;

import com.wiki.search.model.DriverType;
import org.apache.poi.util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Reads the configuration file and retruns
 */

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "configuration.properties";
    private static final ConfigFileReader CONFIG_READER = new ConfigFileReader();


    public ConfigFileReader() {
        properties = new Properties();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(ConfigFileReader.class.getClassLoader().getResourceAsStream(propertyFilePath)));
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public static ConfigFileReader getInstance() {
        return CONFIG_READER;
    }

    /**
     * Reads the configuration file and
     *
     * @return driver path
     */
    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else
            throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) {
            try {
                return Long.parseLong(implicitlyWait);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
            }
        }
        return 30;
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else
            throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
    }


    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if (browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
        else
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }


    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

}