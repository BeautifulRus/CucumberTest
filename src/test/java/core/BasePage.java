package core;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends BaseSeleniumTestPage{


    protected static WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10000));

}
