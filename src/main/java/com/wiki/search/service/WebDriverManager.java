package com.wiki.search.service;

import com.wiki.search.model.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        driverType = ConfigFileReader.getInstance().getBrowser();
    }

    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        driver.get(ConfigFileReader.getInstance().getApplicationUrl());
        return driver;
    }

    private WebDriver createDriver() {
        driver = createLocalDriver();
        return driver;
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, ConfigFileReader.getInstance().getDriverPath());
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER:
                driver = new InternetExplorerDriver();
                break;
        }

        if (ConfigFileReader.getInstance().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ConfigFileReader.getInstance().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

}