package Tests;

import Base.BaseTest;
import Pages.P03_ProductsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
public class TC03_ProductsPage extends BaseTest {

    @Test
    public void verifyProductNavigation() {
        driver.get(DataUtils.getProperty("speakers.url"));

        P03_ProductsPage productPage = new P03_ProductsPage(driver);

        productPage.waitForProductsToLoad();
        productPage.scrollToFirstProduct();
        productPage.clickOnFirstProduct();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlContains("/product/"));

        String expectedUrl = "https://advantageonlineshopping.com/#/product/20";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl, "Didn't navigate to correct product URL!");
    }
}

