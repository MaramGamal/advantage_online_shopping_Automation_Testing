package Base;

import DriverFactory.DriverFactory;
import Pages.*;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {
    protected P01_HomePage homePage;
    protected P02_LoginPage loginPage;
    protected P03_ProductsPage productsPage;
    protected P04_ProductDetailsPage productDetailsPage;
    protected P05_CartPage cartPage;
    protected  P06_CheckoutPage_Login checkoutPageLogin;
    public WebDriver driver;


    @BeforeMethod
    public void setUp() {
        String browser = DataUtils.getProperty("browser");
        driver = DriverFactory.setupDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(DataUtils.getProperty("base.url"));
        homePage = new P01_HomePage(driver);
        loginPage=new P02_LoginPage(driver);
        productsPage=new P03_ProductsPage(driver);
        productDetailsPage=new P04_ProductDetailsPage(driver);
        cartPage=new P05_CartPage(driver);
        checkoutPageLogin= new P06_CheckoutPage_Login(driver);




    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment("Screenshot on Failure - " + result.getName(),
                        new ByteArrayInputStream(screenshot));

                LogsUtils.info("Screenshot attached to Allure report for failed test: " + result.getName());
            } catch (Exception e) {
                LogsUtils.error("Exception while taking screenshot: " + e.getMessage());
            }
        }
    }
}
