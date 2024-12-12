package pages;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EditorPage extends BasePage {
    @FindBy(css = "ul[data-testid=\"list-first\"]")
    private WebElement menu;
    @FindBy(css = "div[data-testid=\"block-body-root\"]")
    private WebElement layouts;
    @FindBy(css = "div.konvajs-content")
    private WebElement canvas;
    @FindBy(css = "div[data-testid=\"registration-modal-container\"]")
    private WebElement signUpModal;
    @FindBy(css = "svg[data-testid=\"modal-close-icon\"]")
    private WebElement closeSignUpModal;

    public EditorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifySignUpPlusAsset() {
        waitForElementClickable(closeSignUpModal);
        Assert.assertTrue(isVisible(signUpModal));
        click(closeSignUpModal);
    }
    public void verifyContent() {
        Assert.assertTrue(isVisible(menu));
        Assert.assertTrue(isVisible(layouts));
        Assert.assertTrue(isVisible(canvas));
    }
}
