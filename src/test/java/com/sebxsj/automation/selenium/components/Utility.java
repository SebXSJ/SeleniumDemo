package com.sebxsj.automation.selenium.components;

import com.sebxsj.automation.selenium.pages.LoggingPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class Utility {

    public String page_url = "https://opensource-demo.orangehrmlive.com/";


    public boolean waitUntil(int endTime) {
        long startTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis() - startTime;
            if (currentTime >= endTime) {
                return true;
            }
        }
    }

    public void readCookieLogging(WebDriver driver) throws InterruptedException {
        LoggingPage
                .using(driver)
                .launch()
                .inputTextToUserNameField("Admin")
                .inputTextToPasswordTextField("admin123")
                .clickOnLoggingButton();
        File file = new File("Cookies.data");
        try {
            file.delete();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write(cookie.getName() + ";" + cookie.getValue()/*
                        + ";" + cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure() + ";"+ cookie.isHttpOnly()*/);
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cookie writeCookieLogging() throws InterruptedException {
        try {
            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strline;
            while ((strline = bufferedReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strline, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    //String domain = token.nextToken();
                    //String path = token.nextToken();


                    //Date expiry = null;


                   /* String tokenContains;
                    if (!(tokenContains = token.nextToken()).equals("null")) {
                        expiry = new Date(tokenContains);
                    }

                    Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
                    Boolean isHttpsOnly = new Boolean(token.nextToken()).booleanValue();*/
                    Cookie cookieNew = new Cookie(name, value/*, domain, path, expiry, isSecure, isHttpsOnly*/);
                    System.out.println(cookieNew);
                    return cookieNew;
                    //driver.manage().addCookie(cookieNew);
                   /* driver.get(page_url);
                    utility.waitUntil(18000);*/


                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return null;
    }


}
