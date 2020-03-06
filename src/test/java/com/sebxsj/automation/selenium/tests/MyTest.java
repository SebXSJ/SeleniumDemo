package com.sebxsj.automation.selenium.tests;

import com.sebxsj.automation.selenium.pages.HomePage;
import com.sebxsj.automation.selenium.pages.LoggingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    @DisplayName("Logging to page test as Admin account")
    @Tag("Logging")
    @RepeatedTest(2)
    public void test001_SingIn() {

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
    @RepeatedTest(2)
    public void test002_DashboardDefaultViewAfterLogging() {

    }
}
