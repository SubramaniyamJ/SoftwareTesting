package com.example;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTestOrangeHR {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        System.out.println("Test Started");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @DataProvider(name = "dataprovider")
    public Object[][] fetchData() throws Exception{
        Object[][] data = new Object[2][2];
        data[0][0] = "Admin";
        data[0][1] = "admin123";
        data[1][0] = "Hello";
        data[1][1] = "hola123";
        return data;
    }

    @Test(dataProvider = "dataprovider")
    public void Apptest1(String username, String password) throws Exception{

        //FileInputStream fis = new FileInputStream("D:\\credentails.xlsx");

        // XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // XSSFSheet sheet = workbook.getSheet("OrangeHR");
        // XSSFRow row = sheet.getRow(1);
        // String username = row.getCell(0).getStringCellValue();
        // String password = row.getCell(1).getStringCellValue();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
    }

    @AfterTest 
    public void setdown(){
        driver.quit();
    }
}
