package pages;

import base.BasePageAndroid;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class GooglePlay extends BasePageAndroid {
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[1]/android.widget.TextView[1]")
    private WebElement title;

    public GooglePlay(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void switchContext(String context) {
        driver.context(context);
    }
    public void verifyGPTitle(String gpTitle) {
        Assert.assertTrue(title.getText().contains(gpTitle));
    }
}
