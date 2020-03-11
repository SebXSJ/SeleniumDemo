package com.sebxsj.automation.selenium.components;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.*;

public class CookieWrite{
    public static void writeCookieLogging(WebDriver driver) {
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
                    String domain = token.nextToken();
                    String path = token.nextToken();

                    Date expiry = null;

                    String tokenContains;
                    if (!(tokenContains = token.nextToken()).equals("null")) {
                        expiry = new Date(tokenContains);
                    }
                    Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
                    Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                    System.out.println(cookie);
                    driver.manage().addCookie(cookie);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
