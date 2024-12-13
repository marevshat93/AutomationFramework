package tests;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import pages.GooglePlay;
import pages.PicsArtPage;
import pages.Viewer;

import java.util.concurrent.TimeUnit;

public class MobileTest extends BaseTest {
    @Test
    public void testLogin() throws InterruptedException {
        driver.get("https://picsart.com/search/all");
        PicsArtPage page = new PicsArtPage(driver);
        page.cookieClick();
        page.switchToIFrame();
        page.clickLicenseFilter("Personal");
        page.openFilterColumn(true);
        page.plusAssetsVisible(false);
        page.clickOneOfTheCards();
        page.switchToDefault();
        Viewer viewerPage = new Viewer(driver);
        viewerPage.verifySignUpAfterLike();
        navigateBack();
        page = new PicsArtPage(driver);
        page.switchToIFrame();
        page.openFilterColumn(false);
        page.clickLicenseFilter("Personal");
        page.openFilterColumn(true);
        page.clickOneOfThePlusCards();
        page.switchToDefault();
        TimeUnit.SECONDS.sleep(5);
        GooglePlay googlePage = new GooglePlay((AndroidDriver) driver);
        googlePage.switchContext("NATIVE_APP");
        googlePage.verifyGPTitle("Picsart AI Photo Editor, Video");
    }

}
