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
    WebDriverWait wait;

    public P06_CheckoutPage_Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    // زرار Next في صفحة الدفع
    @FindBy(xpath = "//div[@class='mobileBtnHandler']//button[@id='next_btn']")
    public WebElement nextButton;

    // حقول SafePay
    @FindBy(css = "input[name='safepay_username']")
    public WebElement safePayUsernameField;

    @FindBy(css = "input[name='safepay_password']")
    public WebElement safePayPasswordField;

    // زرار الدفع Pay Now
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

        // انتظار ظهور زر الـ Next بعد تسجيل الدخول
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div:nth-child(8) > section:nth-child(2) > article:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)")));
    }


    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // استنى الـ loader يختفي
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        // استنى الزرار يكون clickable
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        // اضغط على الزرار
        nextBtn.click();
    }


    public void fillSafePayDetails() {
        wait.until(ExpectedConditions.visibilityOf(safePayUsernameField));
        safePayUsernameField.clear(); // يمسح المحتوى القديم
        safePayUsernameField.sendKeys(DataUtils.getProperty("safepay.username"));

        safePayPasswordField.clear(); // يمسح المحتوى القديم
        safePayPasswordField.sendKeys(DataUtils.getProperty("safepay.password"));
    }


    public void clickPayNowButton() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton));
        payNowButton.click();
    }
    public String getOrderNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement orderParagraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='roboto-regular ng-binding']"))
        );

        return orderParagraph.getText();
    }


}
