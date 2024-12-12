package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

import static drivers.WebDriverFactory.createWebDriverAndroid;
import static drivers.WebDriverFactory.createWebDriverIOS;

public class BaseTestMobile {

    protected WebDriver driver;
    @Parameters("mobilePlatform")
    @BeforeClass
    public void setUp(String mobilePlatform) throws MalformedURLException {

        String platform = System.getProperty("platform", mobilePlatform); // Default to Android
        String appiumServerUrl = System.getProperty("appiumServerUrl", "http://localhost:4723/wd/hub");
        String browser = System.getProperty("browser", mobilePlatform.toLowerCase().contains("android") ? "Chrome" : "safari"); // Default to Chrome or safari
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Use the DriverFactory to create the WebDriver
        driver = mobilePlatform.toLowerCase().contains("android") ? createWebDriverAndroid(browser, headless, appiumServerUrl, platform) : createWebDriverIOS(browser, headless, appiumServerUrl, platform);
    }
    public void navigateBack() {
        driver.navigate().back();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
