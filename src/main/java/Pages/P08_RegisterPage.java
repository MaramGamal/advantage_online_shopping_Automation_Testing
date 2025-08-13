package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;



public class P08_RegisterPage {

    WebDriver driver;


    public P08_RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "input[name='usernameRegisterPage']")
    WebElement usernameField;

    @FindBy(css = "input[name='emailRegisterPage']")
    WebElement emailField;

    @FindBy(css = "input[name='passwordRegisterPage']")
    WebElement passwordField;

    @FindBy(css = "input[name='confirm_passwordRegisterPage']")
    WebElement confirmPasswordField;

    @FindBy(css = "input[name='first_nameRegisterPage']")
    WebElement firstNameField;

    @FindBy(css = "input[name='last_nameRegisterPage']")
    WebElement lastNameField;

    @FindBy(css = "input[name='phone_numberRegisterPage']")
    WebElement phoneNumberField;

    @FindBy(css = "select[name='countryListboxRegisterPage']")  // لو dropdown
    WebElement countryDropdown;

    @FindBy(css = "input[name='cityRegisterPage']")
    WebElement cityField;

    @FindBy(css = "input[name='addressRegisterPage']")
    WebElement addressField;

    @FindBy(css = "input[name='state_/_province_/_regionRegisterPage']")
    WebElement stateProvinceRegionField;

    @FindBy(css = "input[name='postal_codeRegisterPage']")
    WebElement postalCodeField;

    @FindBy(css = "input[name='i_agree']")
    WebElement agreeTermsCheckbox;

    @FindBy(css = "#register_btn")
    WebElement registerButton;

    public WebElement getUsernameField() {
        return usernameField;
    }

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void enterFirstName(String firstName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterPhoneNumber(String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phone);
    }

    public void selectCountry(String countryName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", countryDropdown);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));

        Select select = new Select(countryDropdown);
        select.selectByIndex(0); }



    public void enterCity(String city) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterAddress(String address) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void enterStateProvinceRegion(String state) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        stateProvinceRegionField.clear();
        stateProvinceRegionField.sendKeys(state);
    }

    public void enterPostalCode(String postalCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void checkAgreeTerms() {
        if (!agreeTermsCheckbox.isSelected()) {
            agreeTermsCheckbox.click();
        }
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
}
