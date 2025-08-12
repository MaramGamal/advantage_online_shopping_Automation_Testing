package Tests;

import Base.BaseTest;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_LoginPage extends BaseTest {
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test(description = "Login with valid credentials")
    public void testValidLogin() {
        LogsUtils.info("===== Starting Valid Login Test =====");

        loginPage.clickLoginIcon();
        loginPage.enterUsername(DataUtils.getProperty("valid.username"));
        loginPage.enterPassword(DataUtils.getProperty("valid.password"));
        loginPage.clickSignIn();

        LogsUtils.info("Assert user is logged in (to be improved later)");
    }

    @Test(description = "Login with invalid credentials")
    public void testInvalidLogin() {
        LogsUtils.info("===== Starting Invalid Login Test =====");

        loginPage.clickLoginIcon();
        loginPage.enterUsername(DataUtils.getProperty("invalid.username"));
        loginPage.enterPassword(DataUtils.getProperty("invalid.password"));
        loginPage.clickSignIn();

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.contains("Incorrect"), "Expected error message not displayed");
        LogsUtils.info("Login error message displayed as expected");
    }
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
