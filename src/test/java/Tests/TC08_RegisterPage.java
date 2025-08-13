package Tests;

import Base.BaseTest;
import Utilities.DataUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.P08_RegisterPage;
import java.time.Duration;



public class TC08_RegisterPage extends BaseTest {

    @Test
    public void testRegisterNewUser() {
        driver.get(DataUtils.getProperty("Register.url"));

        P08_RegisterPage registerPage = new P08_RegisterPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("css_selector_of_loader")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='usernameRegisterPage']")));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250);");




        registerPage.enterUsername(DataUtils.getProperty("Register_username"));
        registerPage.enterEmail(DataUtils.getProperty("Register_email"));
        registerPage.enterPassword(DataUtils.getProperty("Register_password"));
        registerPage.enterConfirmPassword(DataUtils.getProperty("Register_Cpassword"));
        registerPage.enterFirstName(DataUtils.getProperty("Register_firstName"));
        registerPage.enterLastName(DataUtils.getProperty("Register_lastName"));
        registerPage.enterPhoneNumber(DataUtils.getProperty("Register_phone"));
        registerPage.selectCountry(DataUtils.getProperty("Register_country"));
        registerPage.enterCity(DataUtils.getProperty("Register_city"));
        registerPage.enterAddress(DataUtils.getProperty("Register_address"));
        registerPage.enterStateProvinceRegion(DataUtils.getProperty("Register_state"));
        registerPage.enterPostalCode(DataUtils.getProperty("Register_postalCode"));
        registerPage.checkAgreeTerms();
        registerPage.clickRegisterButton();

    }

}
