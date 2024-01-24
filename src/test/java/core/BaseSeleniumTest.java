package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeAll
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10000));

        BaseSeleniumTestPage.setDriver(driver);


    }

    @AfterAll
    public void tearDown (){
        driver.close();
        driver.quit();
        driver = null;
    }














}
