package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BaseNativePage {

    final String idAllRegisterForm = allElementsIdSelectorCommonPart + "email_registration_form";

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement emailField;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement usernameField;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement passwordField;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement confirmPasswordField;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerBtn;

    @FindBy(xpath = "//android.widget.CheckedTextView")
    private WebElement iReadCheckbox;

    public RegisterPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }

    public String getIdAllRegisterForm() {
        return idAllRegisterForm;
    }

    public WebElement getReadCheckbox() {
        return iReadCheckbox;
    }

    public void fillRegisterForm(String email, String username, String password) {
        emailField.sendKeys(email);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
    }
}