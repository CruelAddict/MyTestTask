package com.wrike;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    public SeleniumConfig() {
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/mikhailprobochkin/IdeaProjects/TestTask/my_test_project/src/main/resources/chromedriver");
    }

}