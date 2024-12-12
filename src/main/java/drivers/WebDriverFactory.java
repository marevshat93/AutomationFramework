package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverFactory {

    /**
     * Creates a WebDriver instance based on the specified browser type.
     *
     * @param browser     The browser to use ("chrome", "chrome-mobile", "safari-mobile", "android-mobile", "ios-mobile").
     * @param headless    If true, launches the browser in headless mode.
     * @return Configured WebDriver instance.
     * @throws Exception If there is an issue creating the WebDriver instance.
     */
    public static WebDriver createWebDriver(String browser, boolean headless){
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }

    public static AndroidDriver createWebDriverAndroid(String browser, boolean headless, String appiumServerUrl, String platform) throws MalformedURLException {
        AndroidDriver driver;

        switch (platform.toLowerCase()) {


            case "android-local":

                // Appium for Chrome on Android (real device or emulator)
                UiAutomator2Options androidCapabilities = new UiAutomator2Options();
                androidCapabilities.setCapability("platformName", "Android");
                androidCapabilities.setCapability("deviceName", "emulator-5554"); // Can be changed for real devices
                androidCapabilities.setCapability("browserName", browser);
                androidCapabilities.setCapability("newCommandTimeout", 60);
                if (headless) {
                    androidCapabilities.setCapability("headless", true);
                }

                driver = new AndroidDriver(new URL(appiumServerUrl), androidCapabilities);
                break;
            case "android-browserstack":

                //This is the lecavy implementation of Browserstack integratiom,
                // however more suitable for the framework automation
                MutableCapabilities capabilities = new MutableCapabilities();
                HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
                capabilities.setCapability("browserName", "chrome");
                bstackOptions.put("osVersion", "11.0");
                bstackOptions.put("deviceName", "Google Pixel 4");
                bstackOptions.put("userName", "mikayelarevshaty_5pRJdA");
                bstackOptions.put("accessKey", "FqwB9zjXX2jPLjUsq6a3");
                bstackOptions.put("consoleLogs", "info");
                capabilities.setCapability("bstack:options", bstackOptions);


                driver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
    public static IOSDriver createWebDriverIOS(String browser, boolean headless, String appiumServerUrl, String platform) throws MalformedURLException {
        IOSDriver driver;

        switch (platform.toLowerCase()) {


            case "ios-local":

                // Appium for Safari on iOS (real device or emulator)
                XCUITestOptions iOSCapabilities = new XCUITestOptions();
                iOSCapabilities.setCapability("platformName", "Android");
                iOSCapabilities.setCapability("deviceName", "emulator-5554"); // Can be changed for real devices
                iOSCapabilities.setCapability("browserName", "Chrome");
                iOSCapabilities.setCapability("newCommandTimeout", 60);
                if (headless) {
                    iOSCapabilities.setCapability("headless", true);
                }

                driver = new IOSDriver(new URL(appiumServerUrl), iOSCapabilities);
                break;
            case "ios-browserstack":

                //This is the lecavy implementation of Browserstack integratiom,
                // however more suitable for the framework automation
                MutableCapabilities capabilities = new MutableCapabilities();
                HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
                capabilities.setCapability("browserName", "safari");
                bstackOptions.put("osVersion", "16");
                bstackOptions.put("deviceName", "iPhone 14 Pro Max");
                bstackOptions.put("userName", "mikayelarevshaty_5pRJdA");
                bstackOptions.put("accessKey", "FqwB9zjXX2jPLjUsq6a3");
                capabilities.setCapability("bstack:options", bstackOptions);

                driver = new IOSDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
