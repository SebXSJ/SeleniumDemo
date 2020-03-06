package com.sebxsj.automation.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public final WebDriver driver;
    public final WebDriverWait wait;
    private static String page_url = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";


    @FindBy(how = How.ID, using = "welcome")
    private WebElement wWelcomePanelInfo;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
        PageFactory.initElements(driver, this);
    }

    public static HomePage using(WebDriver driver){
        return new HomePage(driver);
    }

    public String getLoggingUserName (){
        return this.wWelcomePanelInfo.getText();
    }

    

}
