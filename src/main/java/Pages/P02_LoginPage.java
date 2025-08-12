package Pages;

import DriverFactory.DriverFactory;
import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class P02_LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By loginIcon = By.id("menuUser");
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By signInButton = By.id("sign_in_btn");
    private By errorMessage = By.cssSelector(".login-error.ng-binding");

    public P02_LoginPage(WebDriver driver) {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public void clickLoginIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuUser"))).click();
    }

    public void enterUsername(String username) {
        LogsUtils.info("Entering username: " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        LogsUtils.info("Entering password: " + password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader")));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("sign_in_btn"))).click();
    }
    public String getLoginErrorMessage() {
        LogsUtils.info("Getting login error message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }
}