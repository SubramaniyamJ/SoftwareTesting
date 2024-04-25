package com.example;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class SeliniumLocator {
    ExtentReports extent;
    ExtentTest test;
    ExtentSparkReporter reporter;
    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception{
        extent = new ExtentReports();
        reporter = new ExtentSparkReporter("D:\\Software Testing");
        extent.attachReporter(reporter);
        driver = new ChromeDriver();
    }

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        test = extent.createTest("Test Cart", "Testing cart fuctionality");
        driver.get("https://demoblaze.com/");
        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.linkText("MacBook air")).click();
        driver.findElement(By.linkText("Add to cart")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.linkText("Cart")).click();
        driver.quit();


    }

}