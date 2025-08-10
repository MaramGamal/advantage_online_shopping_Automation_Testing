package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class P01_HomePage {
    WebDriver driver;
    WebDriverWait wait;

    private final By logo =  By.cssSelector("a[ng-click='go_up()']");
    private final By searchButton = By.id("search_button");
    private final By menuUser = By.id("menuUser");
    private final By tabletsCategory = By.id("tabletsImg");
    private final By speakersCategory = By.id("speakersImg");
    private final By tabletHeader = By.cssSelector("h3.categoryTitle");
    private final By speakersHeader = By.cssSelector("h3[class*='Speaker']");


    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isLogoDisplayed() {
        LogsUtils.info("Checking if logo is displayed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        return driver.findElement(logo).isDisplayed();
    }

    public void clickSearchButton() {
        LogsUtils.info("Clicking on search button");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickMenuUser() {
        LogsUtils.info("Clicking on user menu");
        wait.until(ExpectedConditions.elementToBeClickable(menuUser)).click();
    }
    public void clickOnTabletsCategory() {
        LogsUtils.info("Clicking on Tablets category");
        wait.until(ExpectedConditions.elementToBeClickable(tabletsCategory)).click();
    }

    public  void clickOnSpeakersCategory() {
        LogsUtils.info("Clicking on Speakers category");
        wait.until(ExpectedConditions.elementToBeClickable(speakersCategory)).click();
    }
    public boolean isTabletPageOpened() {
        LogsUtils.info("Checking if Tablet page header is visible");
        try {
            Thread.sleep(1000); // optional wait if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.categoryTitle")));
        return true;
    }

    public boolean isSpeakerPageOpened() {
        LogsUtils.info("Checking if Speaker page header is visible");
        try {
            Thread.sleep(1000); // optional wait if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.categoryTitle")));
        return true;
    }


}