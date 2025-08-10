package Pages;

import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P06_CheckoutPage_Login {
    WebDriver driver;

    public P06_CheckoutPage_Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "login-modal div#loginMiniTitle")
    public WebElement loginSectionTitle;

    @FindBy(css = "#login_btn")
    public WebElement loginButton;

    @FindBy(css = "login-modal .create-new-account")
    public WebElement registerSection;

    @FindBy(css = "checkout-page div#userDetails")
    public WebElement checkoutForm;


    public boolean isLoginSectionDisplayed() {
        try {
            return loginSectionTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegisterSectionDisplayed() {
        try {
            return registerSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckoutFormDisplayed() {
        try {
            return checkoutForm.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getCheckoutFormElement() {
        return checkoutForm;
    }

    public void printPageState() {
        if (isLoginSectionDisplayed()) {
            LogsUtils.info("The Checkout page displays the login section.");
        }
        if (isRegisterSectionDisplayed()) {
            LogsUtils.info("The Checkout page displays the new account creation section.");
        }
        if (isCheckoutFormDisplayed()) {
            LogsUtils.info("The Checkout page displays the payment form directly.");
        }
    }


    public boolean isUserLoggedIn() {
        return driver.findElements(By.id("menuUserLink")).size() == 0;
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.cssSelector(".emptyCart")).size() > 0;
    }

    public void login() {
        driver.get("https://advantageonlineshopping.com/#/login");

        String username = DataUtils.getProperty("valid.username");
        String password = DataUtils.getProperty("valid.password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='usernameInOrderPayment']")));

        driver.findElement(By.cssSelector("input[name='usernameInOrderPayment']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='passwordInOrderPayment']")).sendKeys(password);

        driver.findElement(By.cssSelector("#login_btn")).click();

        wait.until(ExpectedConditions.urlContains("orderPayment"));
    }


}
