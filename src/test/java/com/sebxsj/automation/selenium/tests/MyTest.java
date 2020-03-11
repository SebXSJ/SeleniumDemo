package com.sebxsj.automation.selenium.tests;

import com.sebxsj.automation.selenium.components.CookieRead;
import com.sebxsj.automation.selenium.components.CookieWrite;
import com.sebxsj.automation.selenium.components.Utility;
import com.sebxsj.automation.selenium.pages.HomePage;
import com.sebxsj.automation.selenium.pages.LoggingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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
    LoggingPage loggingPage;
    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    @DisplayName("Logging to page test as Admin account")
    @Tag("Logging")
    @RepeatedTest(1)
    public void test001_SingIn() {

        LoggingPage
                .using(driver)
                .launch()
                .inputTextToUserNameField("Admin")
                .inputTextToPasswordTextField("admin123")
                .clickOnLoggingButton();
        CookieRead.readCookieLogging(driver);
        assertAll(
                () -> assertEquals(HomePage
                        .using(driver)
                        .getLoggingUserName(), "Welcome Admin"),
                () -> assertEquals(true, true)
        );
    }

    @Test
    @DisplayName("Dashboard display after logging to account")
    @Tag("Dashboard")
    //@RepeatedTest(2)
    public void test002_DashboardDefaultViewAfterLogging() throws InterruptedException {
        CookieWrite.writeCookieLogging(driver);
        assertAll(
                () -> assertEquals(HomePage
                        .using(driver)
                        .getLoggingUserName(), "Welcome Admin"),
                () -> assertEquals(true, true)
        );


    }
}
