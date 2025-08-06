package Tests;

import Base.BaseTest;
import Pages.P03_ProductsPage;
import Utilities.DataUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC03_ProductsPage extends BaseTest {


    @Test
    public void verifyProductNavigation() {
        driver.get(DataUtils.getProperty("speakers.url"));

        P03_ProductsPage productPage = new P03_ProductsPage(driver);

        productPage.waitForProductsToLoad();
        productPage.scrollToFirstProduct();
        productPage.clickOnFirstProduct();

        // Explicit wait لحد ما URL يحتوي على "/product/"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlContains("/product/"));

        String expectedUrl = "https://advantageonlineshopping.com/#/product/20";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl, "Didn't navigate to correct product URL!");
    }
}
