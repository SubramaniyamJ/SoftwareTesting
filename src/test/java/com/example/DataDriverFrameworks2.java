package com.example;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataDriverFrameworks2 {
        WebDriver driver;

        @BeforeTest
        public void setup() throws Exception{
            driver = new ChromeDriver();
            driver.get("https://www.demoblaze.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        
        
        @Test
        public void cartCheck() throws Exception{

            driver.findElement(By.linkText("Laptops")).click();
            driver.findElement(By.linkText("MacBook air")).click();
            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            driver.findElement(By.linkText("Cart")).click();
            String productName = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
            
            assertEquals("MacBook air", productName);    
        }
        
        @Test
        public void loginCheck() throws Exception{
            
            FileInputStream fis = new FileInputStream("D:\\Software Testing\\credentails.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet2");
            XSSFRow row = sheet.getRow(1);
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            
            driver.findElement(By.linkText("Log in")).click();
            driver.findElement(By.id("loginusername")).sendKeys(username);
            driver.findElement(By.id("loginpassword")).sendKeys(password);
            driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
            Thread.sleep(3000);
            String user = driver.findElement(By.xpath("//*[@id='nameofuser']")).getText();
            assertEquals(user, "Welcome " + username);

        }

        @AfterMethod
        public void setMethodDown() throws Exception{
            Thread.sleep(4000);
        }

        @AfterTest
        public void setdown(){
            driver.quit();
        }
}
