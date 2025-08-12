package Tests;

import Base.BaseTest;
import Pages.P06_CheckoutPage_Login;
import Utilities.DataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;



public class TC06_CheckoutPage_Login extends BaseTest {


    @Test
    public void verifyRedirectToLoginWhenNotLoggedIn() {
        driver.get(DataUtils.getProperty("Checkout.url"));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"), "Expected to be redirected to login page when not logged in");
    }

    @Test
    public void verifyCheckoutSafePayFlow() {
        P06_CheckoutPage_Login checkoutPage = new P06_CheckoutPage_Login(driver);

        if (!checkoutPage.isUserLoggedIn()) {
            checkoutPage.login();
        }

        driver.get(DataUtils.getProperty("Checkout.url"));

        if (checkoutPage.isCartEmpty()) {
            driver.get(DataUtils.getProperty("base.url"));
            Assert.fail("Cart is empty. Redirected to Home page.");
        }

        Assert.assertTrue(checkoutPage.isCheckoutFormDisplayed(),
                "Checkout form should be displayed for logged in users.");

        checkoutPage.clickNextButton();

        checkoutPage.fillSafePayDetails();

        checkoutPage.clickPayNowButton();

    }
}
