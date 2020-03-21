package com.phptravels.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class BaseTestClass {

    String className;
    String methodName;

    WebDriver driver;

    @BeforeSuite
    public void setDriver() {

        String drivePath = "/home/claudiu/Downloads/PHPTravels/src/main/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", drivePath);
    }
}
