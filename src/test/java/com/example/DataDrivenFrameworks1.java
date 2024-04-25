package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataDrivenFrameworks1 {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    
    @BeforeMethod
    public void signin() throws Exception{
        FileInputStream fis = new FileInputStream("D:\\Software Testing\\credentails.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);
        String username = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("remember-me")).click();
        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void testLogin() throws Exception{
        Thread.sleep(5000);
    }

    @Test
    public void testDeposit() throws Exception{
        driver.findElement(By.linkText("Deposit")).click();
        WebElement selectElement = driver.findElement(By.id("selectedAccount"));
        Select select = new Select(selectElement);
        select.selectByIndex(2);
        driver.findElement(By.id("amount")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(6000);
    }
    
    @Test
    public void testWithdraw() throws Exception{
        driver.findElement(By.linkText("Withdraw")).click();
        WebElement selectElement = driver.findElement(By.id("selectedAccount"));
        Select select = new Select(selectElement);
        select.selectByIndex(2);
        driver.findElement(By.id("amount")).sendKeys("3000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(6000);

    }
    
    @AfterMethod
    public void setdownmethod(){
        driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div[1]/a/img")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
    
    @AfterTest
    public void setdown(){
        driver.quit();
    }
}