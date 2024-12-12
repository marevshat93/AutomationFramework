package pages;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Viewer extends BasePage {
    @FindBy(css = "button[data-testid=\"likeComponent\"]")
    private WebElement likeButton;
    @FindAll({
            @FindBy(xpath = "//button/span[contains(text(), 'save')]"),
            @FindBy(css = "button[data-testid=\"saved-to-collection\"]")
    })
    private WebElement saveButton;
    @FindAll({
            @FindBy(xpath = "//button/span[contains(text(), 'Edit this image')]"),
            @FindBy(xpath = "//button/span[contains(text(), 'edit')]")
    })
    private WebElement editThisImageButton;
    @FindBy(css = "div[data-testid=\"registration-modal-container\"]")
    private WebElement signUpModal;
    @FindBy(css = "svg[data-testid=\"modal-close-icon\"]")
    private WebElement closeSignUpModal;

    public Viewer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifySignUpAfterLike() {
        waitForElementClickable(likeButton);
        Assert.assertTrue(isVisible(likeButton));
        Assert.assertTrue(isVisible(saveButton));
        Assert.assertTrue(isVisible(editThisImageButton));
        click(likeButton);
        waitForElementClickable(closeSignUpModal);
        Assert.assertTrue(isVisible(signUpModal));
        click(closeSignUpModal);
    }
}
