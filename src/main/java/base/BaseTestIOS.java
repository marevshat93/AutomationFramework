package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

import static drivers.WebDriverFactory.createWebDriverAndroid;
import static drivers.WebDriverFactory.createWebDriverIOS;

public class BaseTestIOS {

    protected IOSDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        String platform = System.getProperty("platform", "IOS-local"); // Default to Android
        String appiumServerUrl = System.getProperty("appiumServerUrl", "http://localhost:4723/wd/hub");
        String browser = System.getProperty("browser", "safari"); // Default to Chrome for Android
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Use the DriverFactory to create the WebDriver
        driver = createWebDriverIOS(browser, headless, appiumServerUrl, platform);
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
