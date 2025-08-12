package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.apache.commons.io.FileUtils.waitFor;

public class P04_ProductDetailsPage {

    WebDriver driver;

    public P04_ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Elements ==========

    @FindBy(css = ".roboto-regular.screen768.ng-binding")
    public WebElement productName;

    @FindBy(css = "body div[class='uiview ng-scope'] section[class='ng-scope'] article[class='max-width '] div[id='product_2'] div[id='Description'] h2:nth-child(1)") // السعر
    public WebElement productPrice;

    @FindBy(css = "p[class='roboto-light ng-binding']")
    public WebElement productDescription;

    @FindBy(css = ".plus")
    public WebElement plusButton;

    @FindBy(css = ".minus")
    public WebElement minusButton;

    @FindBy(css = "input[name='quantity']")
    public WebElement quantityInput;

    @FindBy(xpath = "(//input[@name='quantity'])[1]")
    public WebElement defaultColor;

    @FindBy(css = "button[name='save_to_cart']")
    public WebElement addToCartButton;

    @FindBy(css = ".minus.disableBtn")
    public WebElement minusDisabled;
    @FindBy(css = "#menuCart")  // ده السلكتور بتاع أيقونة الكارت
    public WebElement cartIcon;
    private final By checkoutPopUpButton = By.cssSelector("#checkOutPopUp");





    // ========== Actions ==========

    public String getProductName() {
        return productName.getText().trim();
    }

    public String getProductPrice() {
        return productPrice.getText().trim();
    }

    public String getProductDescription() {
        return productDescription.getText().trim();
    }

    public String getDefaultColor() {
        return defaultColor.getAttribute("title").trim(); // لو اللون متخزن كـ title
    }

    public void clickPlus() {
        plusButton.click();
    }

    public void clickMinus() {
        minusButton.click();
    }

    public int getQuantity() {
        try {
            return Integer.parseInt(quantityInput.getAttribute("value"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public boolean isMinusDisabled() {
        return minusButton.getAttribute("class").contains("disableBtn");
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public boolean isQuantityFieldDisplayed() {
        return quantityInput.isDisplayed();
    }

    public boolean isColorDisplayed() {
        return defaultColor.isDisplayed();
    }
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickCartIconTwice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }
    public void clickCheckoutPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        wait.until(ExpectedConditions.elementToBeClickable(checkoutPopUpButton));
    }
}
