package Tests;

import Base.BaseTest;
import Pages.P07_CheckoutPage_Register;
import Pages.P08_RegisterPage;
import Utilities.DataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class TC09_CheckoutFlow extends BaseTest {


    @Test
    public void checkoutScenario() {
        P07_CheckoutPage_Register checkoutPage = new P07_CheckoutPage_Register(driver);

        driver.get(DataUtils.getProperty("Checkout.url"));

        if (checkoutPage.isCartEmpty()) {
            driver.get(DataUtils.getProperty("base.url"));
            Assert.fail("Cart is empty. Redirected to Home page.");
        }

        if (!checkoutPage.isUserLoggedIn()) {
            checkoutPage.clickRegisterButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.urlContains("/register"));

            Assert.assertTrue(driver.getCurrentUrl().contains("/register"),
                    "Expected to be redirected to register page after clicking register button");

            P08_RegisterPage registerPage = new P08_RegisterPage(driver);

            wait.until(ExpectedConditions.visibilityOf(registerPage.getUsernameField()));

            registerPage.enterUsername(DataUtils.getProperty("Register_username"));
            registerPage.enterEmail(DataUtils.getProperty("Register_email"));
            registerPage.enterPassword(DataUtils.getProperty("Register_password"));
            registerPage.enterConfirmPassword(DataUtils.getProperty("Register_Cpassword"));
            registerPage.enterFirstName(DataUtils.getProperty("Register_firstName"));
            registerPage.enterLastName(DataUtils.getProperty("Register_lastName"));
            registerPage.enterPhoneNumber(DataUtils.getProperty("Register_phone"));
            registerPage.selectCountry(DataUtils.getProperty("Register_country"));
            registerPage.enterCity(DataUtils.getProperty("Register_city"));
            registerPage.enterAddress(DataUtils.getProperty("Register_address"));
            registerPage.enterStateProvinceRegion(DataUtils.getProperty("Register_state"));
            registerPage.enterPostalCode(DataUtils.getProperty("Register_postalCode"));
            registerPage.checkAgreeTerms();
            registerPage.clickRegisterButton();

            wait.until(ExpectedConditions.urlContains("orderPayment"));
        }

        driver.get(DataUtils.getProperty("Checkout.url")); // نرجع صفحة الـ Checkout لو راحنا صفحة أخرى بعد التسجيل

        checkoutPage.proceedSafePayFlow();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("orderCompletion"));

        Assert.assertTrue(driver.getCurrentUrl().contains("orderCompletion"),
                "Expected to reach order completion page after payment");
    }
}
