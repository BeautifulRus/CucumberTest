package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;


abstract public class DriverInit {


    protected static RemoteWebDriver driver;

    protected static WebDriverWait wait;


    public void initDriver() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        if ("remote".equalsIgnoreCase(System.getProperty("type.driver"))) {
            initRemoteDriver();
        } else {
            initLocalDriver();
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

        driver = new RemoteWebDriver(URI.create(System.getProperty("selenoid.url")).toURL(), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));

    }


    private void initLocalDriver() {
        if ("firefox".equalsIgnoreCase(System.getProperty("type.browser"))) {
            driver = new FirefoxDriver();
        }else
        if ("chrome".equalsIgnoreCase(System.getProperty("type.browser"))){
            driver = new ChromeDriver();
        }else
        if ("opera".equalsIgnoreCase(System.getProperty("type.browser"))){
            driver = new OperaDriver();// опера кажется не стыкована с драйвером. Не работает
        }else
        if ("edge".equalsIgnoreCase(System.getProperty("type.browser"))){
            driver = new EdgeDriver();
        }else Assertions.fail("I don't work with that browser. Please change the variable |type.browser| from file |application.properties| to the prescribed option in the code");

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));



    }




}