package com.sebxsj.automation.selenium.tests;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.sebxsj.automation.selenium.pages.HomePage;
import com.sebxsj.automation.selenium.pages.LoggingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class MyTest {

    ClassLoader classLoader = getClass().getClassLoader();
    private WebDriver driver;
    private final String pathDriver = classLoader.getResource("chromedriver.exe").getPath();

    @BeforeEach
    void init() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", pathDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @Test
    public void test001_SingIn() {

        LoggingPage
                .using(driver)
                .launch()
                .inputTextToUserNameField("Admin")
                .inputTextToPasswordTextField("admin123").clickOnLoggingButton();
        assertEquals(HomePage
                .using(driver)
                .getLoggingUserName(), "Welcome Admin");
        System.out.println("");

    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
