package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class P04_ProductDetailsPage {

    WebDriver driver;

    public P04_ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ========== Elements ==========

    @FindBy(css = ".roboto-regular.screen768.ng-binding") // اسم المنتج
    public WebElement productName;

    @FindBy(css = "body div[class='uiview ng-scope'] section[class='ng-scope'] article[class='max-width '] div[id='product_2'] div[id='Description'] h2:nth-child(1)") // السعر
    public WebElement productPrice;

    @FindBy(css = "p[class='roboto-light ng-binding']") // وصف المنتج
    public WebElement productDescription;

    @FindBy(css = ".plus") // زر زيادة الكمية
    public WebElement plusButton;

    @FindBy(css = ".minus") // زر تقليل الكمية
    public WebElement minusButton;

    @FindBy(css = "input[name='quantity']") // خانة الكمية نفسها
    public WebElement quantityInput;

    @FindBy(xpath = "(//input[@name='quantity'])[1]") // اللون الافتراضي (أسود مثلاً)
    public WebElement defaultColor;

    @FindBy(css = "button[name='save_to_cart']") // زر Add to Cart
    public WebElement addToCartButton;

    @FindBy(css = ".minus.disableBtn") // الماينص disabled
    public WebElement minusDisabled;

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
            return 1; // fallback
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

}
