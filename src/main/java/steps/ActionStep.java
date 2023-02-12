package steps;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Keys;
import pageObjects.WebPages.GooglePage;

public class ActionStep extends BaseStep {

    public ActionStep(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void openWebSite(String url) {
        getDriver().get(url);
    }

    public void typeValueInSearchFiled(String value) {
        GooglePage page = new GooglePage(getDriver());
        page.fillSearchField(value);
    }

    public void clickEnterInSearchFiled() {
        getDriver().getKeyboard().pressKey(Keys.ENTER);
    }

    public void clickRegistrationButton() {
        getLogInPage().clickRegisterButton();
    }

    public void submitRegistrationForm() {
        waitContentLoadById(getRegisterPage().getIdAllRegisterForm());
        getRegisterPage().fillRegisterForm(email, username, password);
        getRegisterPage().getRegisterBtn().click();
    }

    public void logIn() {
        waitContentLoadById(getLogInPage().getIdAllLoginForm());
        getLogInPage().signIn(email, password);
    }

}
