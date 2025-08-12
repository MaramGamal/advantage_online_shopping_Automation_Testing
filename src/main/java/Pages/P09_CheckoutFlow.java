package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class P09_CheckoutFlow {
    WebDriver driver;
    WebDriverWait wait;

    public P09_CheckoutFlow(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "body > div:nth-child(8) > section:nth-child(2) > article:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)")
    WebElement nextButton;

    @FindBy(css = "input[name='safepay_username']")
    WebElement safePayUsername;

    @FindBy(css = "input[name='safepay_password']")
    WebElement safePayPassword;

    @FindBy(css = "#pay_now_btn_SAFEPAY")
    WebElement payNowButton;

    public boolean isCartEmpty() {
        return driver.findElements(By.cssSelector(".emptyCart")).size() > 0;
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void enterSafePayDetails(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(safePayUsername)).sendKeys(username);
        safePayPassword.sendKeys(password);
    }

    public void clickPayNow() {
        wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
    }
}
