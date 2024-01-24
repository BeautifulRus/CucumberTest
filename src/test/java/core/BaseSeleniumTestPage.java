package core;

import org.openqa.selenium.WebDriver;

abstract public class BaseSeleniumTestPage {

    protected static WebDriver driver;
    public static void setDriver (WebDriver webDriver){
        if (driver == null){
            driver = webDriver;
        }

    }

}