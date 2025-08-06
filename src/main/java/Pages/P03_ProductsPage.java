package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.DataUtils;

import java.time.Duration;
import java.util.List;

public class P03_ProductsPage {
    WebDriver driver;

    public P03_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstProduct = By.cssSelector("div[class='cell categoryRight'] li:nth-child(1)");

    public void waitForProductsToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
    }

    public void scrollToFirstProduct() {
        WebElement product = driver.findElement(firstProduct);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
    }

    public void clickOnFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));

        // Scroll to element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

        // Use FluentWait instead of sleep
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))  // 👈 أقصى وقت هينتظر
                .pollingEvery(Duration.ofMillis(500)) // 👈 كل 0.5 ثانية هيجرب
                .ignoring(ElementClickInterceptedException.class) // 👈 لو العنصر لسه مش جاهز، يتجاهل الغلطة دي
                .ignoring(ElementNotInteractableException.class); // 👈 احتمال يظهر الخطأ دا برده لو مش جاهز

        fluentWait.until(driver -> {
            try {
                product.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}
