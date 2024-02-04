package core;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.Properties;



abstract public class BaseSeleniumTestPage {

    protected static WebDriver driver;
    public static void setDriver (WebDriver webDriver){
        if (driver == null){
            driver = webDriver;
        }


    }
    @Test
    public void initDriver() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        if ("remote".equalsIgnoreCase(System.getProperty("type.driver"))){
        initRemoteDriver();
        }else {
            if (OS.isFamilyWindows()){
                initDriverWindowsOsFamily();
            } else if (OS.isFamilyMac()){
                initDriverMacOSFamily();
            } else if (OS.isFamilyUnix()){
                initDriverUnixOsFamily();
            }

        }
    }
    private void initRemoteDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));

        capabilities.setBrowserName(System.getProperty("type.browser"));
        capabilities.setVersion("109.0");
        /*capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);*/
        try {
            driver = new RemoteWebDriver(URI.create(System.getProperty("selenoid.url")).toURL(),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // ниже должны были быть указаны пути к драйверам, но всё работает и без них



    private void initDriverUnixOsFamily() {
    }

    private void initDriverMacOSFamily() {
    }

    private void initDriverWindowsOsFamily() {
    }



}