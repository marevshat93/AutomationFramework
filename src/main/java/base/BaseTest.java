package base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

import static drivers.WebDriverFactory.*;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("testingPlatform")
    @BeforeMethod
    public void setUp(String testingPlatform) throws MalformedURLException {
        if(testingPlatform.toLowerCase().contains("web")) {
            driver = createWebDriver("chrome", false);
        } else if(testingPlatform.toLowerCase().contains("android") || testingPlatform.toLowerCase().contains("ios")) {
            String platform = System.getProperty("platform", testingPlatform); // Default to Android
            String appiumServerUrl = System.getProperty("appiumServerUrl", "http://localhost:4723/wd/hub");
            String browser = System.getProperty("browser", testingPlatform.toLowerCase().contains("android") ? "Chrome" : "safari"); // Default to Chrome or safari
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            // Use the DriverFactory to create the WebDriver
            driver = testingPlatform.toLowerCase().contains("android") ? createWebDriverAndroid(browser, headless, appiumServerUrl, platform) : createWebDriverIOS(browser, headless, appiumServerUrl, platform);
        }
    }
    @Step("Navigate back in the browser")
    public void navigateBack() {
        driver.navigate().back();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
