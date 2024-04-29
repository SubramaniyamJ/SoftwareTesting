package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportOne {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws Exception{
        driver = new ChromeDriver();
        driver.get("https://groww.in/");
        ExtentSparkReporter reporter = new ExtentSparkReporter("D:\\testing\\src\\test\\java\\com\\ExtentReports\\ExtentReportsOneGroww.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @Test
    public void testcaseone() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        FileInputStream fis = new FileInputStream("D:\\Software Testing\\credentails.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Groww");
        XSSFRow row = sheet.getRow(1);
        double amount = row.getCell(0).getNumericCellValue();
        double interest = row.getCell(1).getNumericCellValue();
        double tenure = row.getCell(2).getNumericCellValue();
        test = extent.createTest("testcase one started");   
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 2500)");
        driver.findElement(By.linkText("Calculators")).click();
        String s = driver.findElement(By.xpath("//*[@id='root']/div[2]/h1")).getText();
        String desPath = "src/test/java/com/ExtentReports/ScreenCapture/extentReportOneCalculator.png";
        File srFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srFile, new File(desPath));
        if(s.equals("Calculators")){
            test.addScreenCaptureFromPath(desPath);
            test.log(Status.PASS, "\"Calculator\" text present");
        }
        else
            test.log(Status.FAIL, "\"Calculator\" text not present");

        js.executeScript("window.scrollBy(0, 700)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div"))).click();
        WebElement amountElement = driver.findElement(By.id("LOAN_AMOUNT"));
        amountElement.clear();
        Thread.sleep(2000);
        amountElement.sendKeys("" + amount);
        Thread.sleep(2000);
        WebElement interestRate = driver.findElement(By.id("RATE_OF_INTEREST"));
        interestRate.clear();
        interestRate.sendKeys(String.valueOf(interest));
        WebElement year = driver.findElement(By.id("LOAN_TENURE"));
        year.clear();
        year.sendKeys(String.valueOf(tenure));

        if(driver.getPageSource().contains("Your Amortization Details (Yearly/Monthly)")){
            test.log(Status.PASS, "Required text displayed");
        }
        else
            test.log(Status.FAIL, "Required text not displayed");
        
    }

    @AfterTest
    public void setdown(){
        extent.flush();
        driver.quit();
    }
}
