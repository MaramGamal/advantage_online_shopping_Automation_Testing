package Tests;

import Base.BaseTest;
import Pages.P01_HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;

import java.io.ByteArrayInputStream;
import java.time.Duration;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})


public class TC01_HomePage extends BaseTest  {
    @Test(priority = 1, description = "Verify logo is displayed on Home Page")
    public void testLogoDisplayed() {
        LogsUtils.info("===== Starting Home Page Logo Display Test =====");
        P01_HomePage homePage = new P01_HomePage(driver);

        driver.get(DataUtils.getProperty("base.url"));

        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on Home Page");
        LogsUtils.info("Logo is displayed successfully");
    }

    @Test(priority = 2, description = "Verify user menu is clickable on Home Page")
    public void clickMenuUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

    }

    @Test(priority = 3, description = "Verify Tablet category redirects to Tablets page")
    public void testTabletCategoryRedirect() {
        LogsUtils.info("===== Starting Tablet Category Redirect Test =====");
        P01_HomePage homePage = new P01_HomePage(driver);

        driver.get(DataUtils.getProperty("base.url"));

        homePage.clickOnTabletsCategory();
        Assert.assertTrue(homePage.isTabletPageOpened(), "Tablet page did not open correctly");
        LogsUtils.info("Tablet page opened successfully");
    }

    @Test(priority = 4, description = "Verify Speakers category redirects to Speakers page")
    public void testSpeakersCategoryRedirect() {
        LogsUtils.info("===== Starting Speakers Category Redirect Test =====");
        P01_HomePage homePage = new P01_HomePage(driver);

        driver.get(DataUtils.getProperty("base.url"));

        homePage.clickOnSpeakersCategory();
        Assert.assertTrue(homePage.isSpeakerPageOpened(), "Speakers page did not open correctly");
        LogsUtils.info("Speakers page opened successfully");
    }
    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment("Screenshot on Failure - " + result.getName(),
                        new ByteArrayInputStream(screenshot));

                LogsUtils.info("Screenshot attached to Allure report for failed test: " + result.getName());
            } catch (Exception e) {
                LogsUtils.error("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }


}
