package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BaseNativePage {

    final String ID_ALL_REGISTER_FORM = ALL_ELEMENTS_ID_SELECTOR_COMMON_PART + "email_registration_form";

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='user@example.com']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement emailField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='TimApple']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement usernameField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Required']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement passwordField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Repeat please']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement confirmPasswordField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Register new account']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerBtn;

    public RegisterPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }

    public String getIdAllRegisterForm() {
        return ID_ALL_REGISTER_FORM;
    }

    public void fillRegisterForm(String email, String username, String password) {
        emailField.sendKeys(email);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }
}
