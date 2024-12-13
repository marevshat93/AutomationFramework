package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PicsArtPage extends BasePage {

    private final String url = "https://picsart.com/search/all";

    @FindBy(css = "button[id=\"filter_icon\"]")
    private WebElement filterButton;
    @FindBy(css = "button[data-test=\"picsart-logo\"]")
    private WebElement pixartLogo;
    @FindBy(css = "li[data-testid=\"checkbox-item-root\"]")
    private List<WebElement> licenseFilters;
    @FindBy(css = "div[data-testid=\"search-card-root\"]")
    private List<WebElement> assetCards;
    @FindBy(css = "div[data-testid=\"search-filter-root\"]")
    private WebElement filterColumn;
    @FindBy(css = "button[id=\"onetrust-accept-btn-handler\"]")
    private WebElement cookieConsent;
    @FindBy(css = "iframe[title=\"Mini app SDK\"]")
    private WebElement iframe;
    @FindBy(css = "i[data-testid=\"badge\"]")
    private List<WebElement> plusBadges;
    @FindBy(css = "div[data-testid=\"search-card-root\"]:has(i[data-testid=\"badge\"])")
    private List<WebElement> plusAssets;

    public PicsArtPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step("Navigate to PicsArt search")
    public void getUrl () {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains("Download 100+ Fabulous Handpicked assets"));
        waitForElementClickable(pixartLogo);
    }
    @Step("Click Filter Button")
    public void clickFilter() {

        click(filterButton);
    }

    @Step("Click the cookie consent")
    public void cookieClick() {

        click(cookieConsent);
        waitForElementClickable(pixartLogo);
    }
    @Step("Switch to Iframe")
    public void switchToIFrame() {
        waitForElementClickable(pixartLogo);
        driver.switchTo().frame(iframe);
    }
    @Step("Check if Filters are visible")
    public boolean filtersVisible() {

        return isVisible(filterColumn);
    }
    @Step("Check if any plus badge asset is visible")
    public void plusAssetsVisible(boolean visible) {
        if(visible) {
            Assert.assertTrue(isVisible(plusBadges.getFirst()));
        } else {
            Assert.assertTrue(plusBadges.isEmpty());
        }
    }
    @Step("Choosing the License filter")
    public void clickLicenseFilter(String filterName) {
        if(!isVisible(filterColumn)) {
            openFilterColumn(false);
        }
        String[] licenses = {"All", "Commercial", "Personal"};
        waitForElementClickable(licenseFilters.getFirst());
        if(Arrays.asList(licenses).contains(filterName)) {
            click(findLicenseFilter(filterName));
        } else {
            throw new AssertionError("The filter should be one of [\"All\", \"Commercial\", \"Personal\"]");
        }
    }
    @Step("Open/close filter column")
    public void openFilterColumn(boolean open) {
        clickFilter();
        if(open) {
            Assert.assertFalse(filtersVisible());
        } else {
            Assert.assertTrue(filtersVisible());
        }
    }
    @Step("Finding the correct license filter")
    public WebElement findLicenseFilter(String filterName) {
        for (WebElement license : licenseFilters) {
            System.out.println(license.getText());
            if (license.getText().contains(filterName)) {
                return license;
            }
        }
        throw new AssertionError("The filter was not found");
    }
    public void switchToDefault() {

        driver.switchTo().defaultContent();
    }
    public void clickOneOfTheCards() {
        Dimension size = driver.manage().window().getSize();
        if(size.getWidth() < 1440 && assetCards.isEmpty() && isVisible(filterColumn)){
            openFilterColumn(true);
            click(assetCards.getFirst());
        } else {
            click(assetCards.getFirst());
        }
    }
    public void clickOneOfThePlusCards() {

        click(plusAssets.getFirst());
    }

    public void verifyFiltersDisappear() {
        if(isVisible(filterColumn)) {
            openFilterColumn(true);
            openFilterColumn(false);
        } else {
            openFilterColumn(false);
            openFilterColumn(true);
        }
    }

    public void verifyPlusAssetsDisappear() throws InterruptedException {
        clickLicenseFilter("Personal");
        TimeUnit.SECONDS.sleep(5);
        plusAssetsVisible(false);
    }
}
