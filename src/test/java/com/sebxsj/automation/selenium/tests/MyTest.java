package com.sebxsj.automation.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyTest  {
    ClassLoader classLoader = getClass().getClassLoader();
    private WebDriver driver;
    private final String pathDriver = classLoader.getResource("chromedriver.exe").getPath();




    @BeforeEach
    void init() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", pathDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }


    @Test
    public void test001() throws InterruptedException {
        String titlePage = driver.getTitle();
        System.out.println(titlePage);
        Assertions.assertEquals(titlePage, "OrangeHRM");

    }



    @AfterEach
    void tearDown() {
        driver.quit();


    }

}
