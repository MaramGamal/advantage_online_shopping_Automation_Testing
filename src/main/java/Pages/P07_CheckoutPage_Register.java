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

public class P07_CheckoutPage_Register {
    WebDriver driver;

    public P07_CheckoutPage_Register(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "login-modal div#loginMiniTitle")
    public WebElement loginSectionTitle;

    @FindBy(css = "#login_btn")
    public WebElement loginButton;

    @FindBy(css = "login-modal .create-new-account")
    public WebElement registerSection;

    @FindBy(css = "#registration_btn")
    public WebElement registerButton;

    @FindBy(css = "checkout-page div#userDetails")
    public WebElement checkoutForm;

    // الزر Next بالـ CSS اللي انت بعتهولي
    @FindBy(css = "body > div:nth-child(8) > section:nth-child(2) > article:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)")
    public WebElement nextButton;

    // حقول SafePay اليوزرنيم والباسورد
    @FindBy(css = "input[name='safepay_username']")
    public WebElement safepayUsernameInput;

    @FindBy(css = "input[name='safepay_password']")
    public WebElement safepayPasswordInput;

    // زر Pay Now
    @FindBy(css = "#pay_now_btn_SAFEPAY")
    public WebElement payNowButton;


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
        return driver.findElements(By.id("menuUserLink")).size() > 0;
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

    public void clickRegisterButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }


    public void proceedSafePayFlow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();

        wait.until(ExpectedConditions.visibilityOf(safepayUsernameInput));
        wait.until(ExpectedConditions.visibilityOf(safepayPasswordInput));

        String safepayUsername = DataUtils.getProperty("safepay.username");
        String safepayPassword = DataUtils.getProperty("safepay.password");

        safepayUsernameInput.sendKeys(safepayUsername);
        safepayPasswordInput.sendKeys(safepayPassword);

        wait.until(ExpectedConditions.elementToBeClickable(payNowButton));
        payNowButton.click();
    }
}
