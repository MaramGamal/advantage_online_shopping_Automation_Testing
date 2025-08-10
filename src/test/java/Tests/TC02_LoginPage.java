package Tests;

import Base.BaseTest;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P02_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})


public class TC02_LoginPage extends BaseTest {

    @Test(description = "Login with valid credentials")
    public void testValidLogin() {
        LogsUtils.info("===== Starting Valid Login Test =====");
        P02_LoginPage loginPage = new P02_LoginPage();

        loginPage.clickLoginIcon();
        loginPage.enterUsername(DataUtils.getProperty("valid.username"));
        loginPage.enterPassword(DataUtils.getProperty("valid.password"));
        loginPage.clickSignIn();


        LogsUtils.info("Assert user is logged in (to be improved later)");
    }

    @Test(description = "Login with invalid credentials")
    public void testInvalidLogin() {
        LogsUtils.info("===== Starting Invalid Login Test =====");
        P02_LoginPage loginPage = new P02_LoginPage();

        loginPage.clickLoginIcon();
        loginPage.enterUsername(DataUtils.getProperty("invalid.username"));
        loginPage.enterPassword(DataUtils.getProperty("invalid.password"));
        loginPage.clickSignIn();

        String errorMsg = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMsg.contains("Incorrect"), "Expected error message not displayed");
        LogsUtils.info("Login error message displayed as expected");
    }

}

