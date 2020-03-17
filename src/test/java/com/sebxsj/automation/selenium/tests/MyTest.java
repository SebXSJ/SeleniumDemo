package com.sebxsj.automation.selenium.tests;

import com.sebxsj.automation.selenium.components.Utility;
import com.sebxsj.automation.selenium.pages.HomePage;
import com.sebxsj.automation.selenium.pages.LoggingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MyTest extends Utility {

    ClassLoader classLoader = getClass().getClassLoader();
    private WebDriver driver;
    private final String pathDriver = classLoader.getResource("chromedriver.exe").getPath();
    private final String page_url_domain = "https://opensource-demo.orangehrmlive.com/";


    @BeforeEach
    void init() throws InterruptedException {
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
    // @RepeatedTest(1)
    public void test001_SingIn() throws InterruptedException {

        LoggingPage
                .using(driver)
                .launch()
                .inputTextToUserNameField("Admin")
                .inputTextToPasswordTextField("admin123")
                .clickOnLoggingButton();
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

        LoggingPage
                .using(driver)
                .launch()
                .cookieTokenLoggingBy();
        assertAll(
                () -> assertEquals(HomePage.using(driver).launch().getLoggingUserName(), "Welcome Admin"),
                () -> assertEquals(true, true)
        );

    }
}
