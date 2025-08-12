package Tests;

import Base.BaseTest;
import Pages.P01_HomePage;
import Utilities.LogsUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_HomePage extends BaseTest {

    @Test(description = "Verify logo is displayed on Home Page")
    public void testLogoDisplayed() {
        LogsUtils.info("===== Starting Home Page Logo Display Test =====");
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on Home Page");
        LogsUtils.info("Logo is displayed successfully");
    }

    @Test(description = "Verify user menu is clickable on Home Page")
    public void clickMenuUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
        LogsUtils.info("User menu is clickable");
    }

    @Test(description = "Verify Tablet category redirects to Tablets page")
    public void testTabletCategoryRedirect() {
        LogsUtils.info("===== Starting Tablet Category Redirect Test =====");
        homePage.clickOnTabletsCategory();
        Assert.assertTrue(homePage.isTabletPageOpened(), "Tablet page did not open correctly");
        LogsUtils.info("Tablet page opened successfully");
    }

    @Test(description = "Verify Speakers category redirects to Speakers page")
    public void testSpeakersCategoryRedirect() {
        LogsUtils.info("===== Starting Speakers Category Redirect Test =====");
        homePage.clickOnSpeakersCategory();
        Assert.assertTrue(homePage.isSpeakerPageOpened(), "Speakers page did not open correctly");
        LogsUtils.info("Speakers page opened successfully");
    }

}
