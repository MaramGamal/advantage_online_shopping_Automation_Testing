package Base;
import DriverFactory.DriverFactory;
import Utilities.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = DataUtils.getProperty("browser");
        driver = DriverFactory.setupDriver(browser);

        String baseUrl = DataUtils.getProperty("base.url");
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
