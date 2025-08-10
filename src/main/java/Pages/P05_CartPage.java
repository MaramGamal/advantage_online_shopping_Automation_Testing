package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_CartPage {
    private WebDriver driver;

    public P05_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator لعناصر الكارت
    private By cartEmptyMessage = By.xpath("//label[contains(text(),'Your shopping cart is empty')]");
    private By checkoutBtn = By.id("checkOutButton");

    // ✅ طريقة للتحقق إذا الكارت فاضي
    public boolean isCartEmpty() {
        return driver.findElements(cartEmptyMessage).size() > 0;
    }
    public boolean isProductNameDisplayed() {
        return driver.findElement(By.cssSelector(".productName.ng-binding")).isDisplayed();
    }

    public boolean isProductColorDisplayed() {
        return driver.findElement(By.cssSelector(".productColor.ng-binding")).isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        return driver.findElement(By.cssSelector(".price")).isDisplayed();
    }

    public boolean isProductQuantityDisplayed() {
        return driver.findElement(By.cssSelector("label[qa='quantity']")).isDisplayed();
    }

    public boolean isEditButtonDisplayed() {
        return driver.findElement(By.name("edit")).isDisplayed();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(By.name("remove")).isDisplayed();
    }

    // ✅ طريقة للضغط على checkout
    public void clickCheckoutButton() {
        driver.findElement(checkoutBtn).click();
    }

    public boolean isCheckoutButtonDisplayed() {
        try {
            return driver.findElement(By.id("checkOutButton")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInCartPage() {
        return driver.getCurrentUrl().contains("/shoppingCart");
    }

}
