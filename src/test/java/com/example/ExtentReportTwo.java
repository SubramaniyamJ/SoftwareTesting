
package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v121.indexeddb.model.Key;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportTwo {

    WebDriver driver;
    ExtentReports reports;
    ExtentSparkReporter reporter;
    ExtentTest test;
    @BeforeTest
    public void setting() throws Exception{
        driver = new ChromeDriver();
        reports = new ExtentReports();
        reporter = new ExtentSparkReporter("D:\\testing\\src\\test\\java\\com\\ExtentReports\\ExtentReportsTwoIxigo.html");
        reports.attachReporter(reporter);
        driver.manage().window().maximize();
        
    }
    
    @BeforeMethod
    public void setup(){
        driver.get("https://www.ixigo.com/");
    }

    public void getDate(WebElement date, String nextXpath, String dateXpath, String month, String year){

        while(true){
            String dateString = date.getText();
            String[] parts = dateString.split(" ");
            month = parts[0];
            year = parts[1];
            if(month.equals("November") && year.equals("2024")){
                driver.findElement(By.xpath(dateXpath)).click();
            }

            driver.findElement(By.xpath(nextXpath)).click();

        }
        
    }
    @Test
    public void test1() throws Exception{

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

            test = reports.createTest("testcase 1 is started...");
            FileInputStream fr = new FileInputStream("D:\\Software Testing\\credentails.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fr);
            String from = workbook.getSheet("ixigo").getRow(1).getCell(0).getStringCellValue();
            String to = workbook.getSheet("ixigo").getRow(1).getCell(1).getStringCellValue();

            WebElement roundTripButton = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]"));
            roundTripButton.click();

            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div")).click();
            WebElement fromBox = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input"));
            fromBox.sendKeys(from);
            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/li")).click(); 
                
            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div")).click();
            WebElement toBox = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input"));
            toBox.sendKeys(to);
            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/li")).click();


            driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div")).click();

            
            WebElement date = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[2]/span[1]"));
            
            getDate(date, "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[3]", "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[11]", "November", "2024");
            
            getDate(date, "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[3]", "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[13]", "November", "2024");


            WebElement travellersClassSection = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div/div/div/div/p[2]"));
            travellersClassSection.click();

            WebElement adultsInput = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[3]"));
            adultsInput.click();

            WebElement childInput = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[3]"));
            childInput.click();

            WebElement businessClassOption = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[5]/div/div[3]/p"));
            businessClassOption.click();

            // if (returnDate.getAttribute("value").contains("08 Nov")) {
            //     System.out.println("Return date contains '08 Nov'. Test Case 1 passed.");
            //     test.log(Status.PASS, "The return date is 08 Nov. Test case is Succesfully verified");
            // } else {
            //     System.out.println("Return date does not contain '08 Nov'. Test Case 1 failed.");
            //     test.log(Status.FAIL, "The return date is not 08 Nov. Test case is unsuccessful");
            // }
    }
    
    @Test
    public void testcase2() throws Exception{
        test = reports.createTest("testcase2 is started");
        WebElement aboutUsLink = driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div/div[2]/div[1]/p[1]/a"));
        aboutUsLink.click();
    
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("about")) {
            test.log(Status.PASS, "About page is present. Test is passsed");
            System.out.println("Redirected URL contains 'about'. Test Case 2 passed.");
        } else {
            System.out.println("Redirected URL does not contain 'about'. Test Case 2 failed.");
            test.log(Status.FAIL, "About page is not present. Test Failed");
        }
        
    }
    @AfterTest
    public void setdown() throws Exception{
        reports.flush();
        driver.close();
    }
}
