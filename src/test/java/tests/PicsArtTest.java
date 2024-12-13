package tests;

import base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.EditorPage;
import pages.PicsArtPage;
import pages.Viewer;

import java.util.concurrent.TimeUnit;

public class PicsArtTest extends BaseTest {

    @Parameters("viewPort")
    @Test
    public void testSearchFilter(String viewPort) throws InterruptedException {
        PicsArtPage page = new PicsArtPage(driver);
        page.getUrl();
        page.setDimension(viewPort);

        page.cookieClick();
        page.switchToIFrame();
        page.verifyFiltersDisappear();
        page.verifyPlusAssetsDisappear();
        page.clickOneOfTheCards();
        page.switchToDefault();

        Viewer viewerPage = new Viewer(driver);
        viewerPage.verifySignUpAfterLike();
        navigateBack();
        page = new PicsArtPage(driver);
        page.switchToIFrame();
        page.clickLicenseFilter("Personal");
        TimeUnit.SECONDS.sleep(5);
        page.clickOneOfThePlusCards();
        page.switchToDefault();
        EditorPage editorPage = new EditorPage(driver);
        editorPage.verifySignUpPlusAsset();
        editorPage.verifyContent();
    }
}