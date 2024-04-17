package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeliniumWebDriver 
{    
    @Test
    public void shouldAnswerWithTrue() throws MalformedURLException
    {
        WebDriver driver;
        ChromeOptions co=new ChromeOptions();
        driver=new RemoteWebDriver(new URL("http://localhost:4444/"),co);
        driver.get("https://www.shoppersstop.com/");
    }
}