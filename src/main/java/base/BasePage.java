package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static util.Util.convertStringToDimension;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Generic method to click a WebElement
    public void click(WebElement element, int duration) {
        waitForElementClickable(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).pause(Duration.ofSeconds((long) duration)).click().perform();
    }

    public void click(WebElement element) {
        waitForElementClickable(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).pause(Duration.ofSeconds(1)).click().perform();
    }

    // Generic method to click a WebElement
    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Generic method to send keys to a WebElement
    public void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    // Generic method to get text of a WebElement
    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    // Method to wait for an element to be visible
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public boolean isVisible(WebElement element) {
        return element.isDisplayed();
    }
    public void setDimension(String viewPort) {
        Dimension dimension = convertStringToDimension(viewPort);
        driver.manage().window().setSize(dimension);
    }
}
