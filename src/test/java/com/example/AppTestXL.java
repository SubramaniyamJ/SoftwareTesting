
//day POI (Poor Obfusction Implementation)

package com.example;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

// xssf workbook -> xssf sheet -> xssf row -> xssf cell

public class AppTestXL{
    @Test
    public void testingOne() throws Exception{
        WebDriver driver = new ChromeDriver();

        FileInputStream fis = new FileInputStream("D:\\credentails.xlsx");
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(3000);
        driver.findElement(By.id("username")).sendKeys("Hello");

        driver.findElement(By.id("password")).sendKeys("123@123");

        Thread.sleep(3000);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(1);
        String username = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();

        System.out.println(username + " " + password);
        driver.quit();
    }
}
