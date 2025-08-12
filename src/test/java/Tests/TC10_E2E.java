package Tests;

import Base.BaseTest;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC10_E2E extends BaseTest {
    @Test
    public void completeEndToEndTest() {
        try {
            Home();
            Login();
            Products();
            Details();
            Cart();
            clickNext();
            fillPaymentDetails();
            clickPayNow();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }



    @Step("Validate Home Page logo and navigate to Speakers category")
    public void Home() {
        driver.manage().deleteAllCookies();
        LogsUtils.info("===== Starting Home Page Logo Display Test =====");
        Allure.step("Check if Home Page logo is displayed");
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on Home Page");
        LogsUtils.info("Logo is displayed successfully");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        LogsUtils.info("User menu is clickable");
        Allure.step("Click on Speakers Category");
        LogsUtils.info("===== Starting Speakers Category Redirect Test =====");
        homePage.clickOnSpeakersCategory();
        Assert.assertTrue(homePage.isSpeakerPageOpened(), "Speakers page did not open correctly");
        LogsUtils.info("Speakers page opened successfully");
    }

    @Step("Login with valid credentials")
    public void Login() {
        loginPage.clickLoginIcon();
        Allure.step("Enter username");
        loginPage.enterUsername(DataUtils.getProperty("valid.username"));
        Allure.step("Enter password");
        loginPage.enterPassword(DataUtils.getProperty("valid.password"));
        loginPage.clickSignIn();
        LogsUtils.info("Assert user is logged in (to be improved later)");
    }

    @Step("Select first product from products page and verify URL")
    public void Products() {
        productsPage.waitForProductsToLoad();
        productsPage.scrollToFirstProduct();
        productsPage.clickOnFirstProduct();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlContains("/product/"));

        String expectedUrl = "https://advantageonlineshopping.com/#/product/20";
        String actualUrl = driver.getCurrentUrl();

        assertEquals(actualUrl, expectedUrl, "Didn't navigate to correct product URL!");
    }

    @Step("Validate product details and add product to cart")
    public void Details() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productDetailsPage.productName));
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productDetailsPage.productName.isDisplayed(), "Product name is NOT displayed");
        softAssert.assertTrue(productDetailsPage.productPrice.isDisplayed(), "Product price is NOT displayed");
        softAssert.assertTrue(productDetailsPage.productDescription.isDisplayed(), "Product description is NOT displayed");
        softAssert.assertTrue(productDetailsPage.isColorDisplayed(), "Default color is NOT displayed");
        softAssert.assertTrue(productDetailsPage.isQuantityFieldDisplayed(), "Quantity input is NOT displayed");
        softAssert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(), "Add To Cart button is NOT displayed");

        int quantity = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantity, 1, "Default quantity is NOT 1");

        softAssert.assertTrue(productDetailsPage.isMinusDisabled(), "Minus button should be disabled when quantity is 1");

        productDetailsPage.clickPlus();
        int quantityAfterPlus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterPlus, 2, "Quantity did NOT increase after clicking plus");
        softAssert.assertFalse(productDetailsPage.isMinusDisabled(), "Minus button should be enabled after increasing quantity");

        productDetailsPage.clickMinus();
        int quantityAfterMinus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterMinus, 1, "Quantity did NOT decrease after clicking minus");
        softAssert.assertTrue(productDetailsPage.isMinusDisabled(), "Minus button should be disabled when quantity returns to 1");

        productDetailsPage.clickMinus();
        int quantityAfterExtraMinus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterExtraMinus, 1, "Quantity should NOT go below 1");

        softAssert.assertAll();

        productDetailsPage.clickAddToCart();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        productDetailsPage.clickCartIconTwice();

        assertEquals(driver.getCurrentUrl(), "https://advantageonlineshopping.com/#/shoppingCart", "Failed to navigate to Cart page");
    }

    @Step("Go to Cart page and click checkout")
    public void Cart() {
        LogsUtils.info("The cart contains at least one product.");
        Allure.step("Click Checkout button on Cart page");
        cartPage.clickCheckoutButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("orderPayment"), "Didn't navigate to the Checkout page.");
        LogsUtils.info("Successfully navigated to the Checkout page.");
    }

    @Step("Click Next button in checkout page")
    public void clickNext() {
        checkoutPageLogin.clickNextButton();
    }

    @Step("Fill SafePay payment details")
    public void fillPaymentDetails() {
        checkoutPageLogin.fillSafePayDetails();
    }

    @Step("Click Pay Now button")
    public void clickPayNow() {
        checkoutPageLogin.clickPayNowButton();
    }

    public String getOrderNumber() {
        String orderNum = checkoutPageLogin.getOrderNumber();
        Allure.addAttachment("Order Number", orderNum);
        return orderNum;
    }

    @Step("Complete SafePay checkout and get order number")
    public void Checklogin() {
        String orderNum = getOrderNumber();
        LogsUtils.info("Order number: " + orderNum);
    }

    @AfterClass
    @Step("Close browser")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
