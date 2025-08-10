package Base;
import DriverFactory.DriverFactory;
import Pages.P01_HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;



 public class BaseTest {
     protected P01_HomePage HomePage;
     public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = DataUtils.getProperty("browser");
        driver = DriverFactory.setupDriver(browser);

        String baseUrl = DataUtils.getProperty("base.url");
        driver.get(baseUrl);
         HomePage = new P01_HomePage(driver);

    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
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
