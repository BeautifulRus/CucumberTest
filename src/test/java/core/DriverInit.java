package core;

import org.apache.commons.exec.OS;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;


abstract public class DriverInit {


    protected static RemoteWebDriver remoteWebDriver;
    protected static WebDriverWait wait;


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

    private void initRemoteDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        capabilities.setBrowserName(System.getProperty("type.browser"));
        capabilities.setVersion("109.0");

           remoteWebDriver = new RemoteWebDriver(URI.create(System.getProperty("selenoid.url")).toURL(),capabilities);
            remoteWebDriver.manage().window().maximize();
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            remoteWebDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            wait = new WebDriverWait(remoteWebDriver, Duration.ofSeconds(10), Duration.ofMillis(500));

        }





    // ниже должны были быть указаны пути к драйверам, но всё работает и без них



    private void initDriverUnixOsFamily() {
    }

    private void initDriverMacOSFamily() {
    }

    private void initDriverWindowsOsFamily() {
    }



}