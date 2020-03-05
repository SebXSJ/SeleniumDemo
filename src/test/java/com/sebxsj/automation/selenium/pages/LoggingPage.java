package com.sebxsj.automation.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggingPage {
    public final WebDriver driver;
    public final WebDriverWait wait;
    private static String page_url = "https://opensource-demo.orangehrmlive.com/";

    @FindBy(how = How.ID, using = "btnLogin")
    private WebElement wLoggingButton;
    @FindBy(how = How.ID, using = "txtUsername")
    private WebElement wUserName;
    @FindBy(how = How.ID, using = "txtPassword")
    private WebElement wPassword;

    private LoggingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
        PageFactory.initElements(driver, this);
    }

    public static LoggingPage using(WebDriver driver){
        return new LoggingPage(driver);
    }

    public LoggingPage launch(){
        driver.get(page_url);
        return this;
    }


    public LoggingPage inputTextToUserNameField(String userName) {
        this.wUserName.sendKeys(userName);
        return this;
    }

    public LoggingPage inputTextToPasswordTextField(String password) {
        this.wPassword.sendKeys(password);
        return this;
    }

    public HomePage clickOnLoggingButton() {
        this.wLoggingButton.click();
        return new HomePage(driver);
    }
}
