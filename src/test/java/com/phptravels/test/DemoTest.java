package com.phptravels.test;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DemoTest extends BaseTestClass {

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.phptravels.net/");
    }


    //@AfterMethod
    public void cleanup() {
        driver.quit();
    }

    @Test
    public void testSearchFunctionHotel()  {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[1]")));
        hotel.click();

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("Bucharest");
        Assert.assertEquals("Bucharest", "Bucharest");

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li/div")).click();

        WebElement checkin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkin\"]")));
        checkin.click();

        WebElement selectStartDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div[2]/div[22]")));
        selectStartDay.click();

        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.name("checkout")));
        checkOut.click();

        WebElement selectEndDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div[2]/div[28]")));
        selectEndDay.click();

        WebElement oneAdult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[1]/div/div[2]/div/span/button[2]")));
        oneAdult.click();

        WebElement oneChild = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div/span/button[1]")));
        oneChild.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button")));
        searchButton.click();

        Assert.assertTrue(driver.getTitle().contains("Hotels Results"));

    }

    @Test
    public void noHotelSelectedError(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button")));
        searchButton.click();

        WebElement hotel = driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[1]"));

        Assert.assertTrue(hotel.getText().contains("Search By Hotel Or City Name"));

    }

    @Test
    public void searchWithZeroPeople(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[1]")));
        hotel.click();

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("Bucharest");
        Assert.assertEquals("Bucharest", "Bucharest");

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li/div")).click();

        Actions action = new Actions(driver);
        WebElement adultsDecrement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[1]/div/div[2]/div/span/button[2]")));
        action.doubleClick(adultsDecrement).perform();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button")));
        searchButton.click();

        WebElement hotelsList = driver.findElement(By.xpath("//*[@id=\"LIST\"]"));

       Assert.assertEquals(hotelsList.getText(), "");

        }

    @Test
    public void unableToSelectPastDates(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[1]")));
        hotel.click();

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("Bucharest");
        Assert.assertEquals("Bucharest", "Bucharest");

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li/div")).click();

        WebElement hotelSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"s2id_autogen1\"]/a/span[1]")));
        hotelSearch.click();

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("Bucharest");
        Assert.assertEquals("Bucharest", "Bucharest");

        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li/div")).click();

        WebElement checkin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkin\"]")));
        checkin.click();

        WebElement pastDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div[2]/div[12]")));
        pastDate.click();

        Assert.assertEquals(pastDate.getAttribute("class"), "datepicker--cell datepicker--cell-day -disabled- -focus-");

        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.name("checkout")));
        checkOut.click();

        WebElement futureDateInThePast = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"datepickers-container\"]/div[2]/div/div/div[2]/div[4]")));
        futureDateInThePast.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button")));
        searchButton.click();

        assert  driver.findElement(By.xpath("//*[@id=\"datepickers-container\"]/div[1]/div/div/div[2]/div[12]")).isDisplayed();

        }
    }
