package steps;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Keys;

public class ActionStep extends BaseStep {

    public ActionStep(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void openWebSite(String url) {
        getDriver().get(url);
    }

    public void clickEnterInSearchFiled() {
        getDriver().getKeyboard().pressKey(Keys.ENTER);
    }

    public void clickRegistrationBtn() {
        logInPage.clickRegisterButton();
    }

    public void submitRegistrationForm() {
        waitContentLoadById(registerPage.getIdAllRegisterForm());
        registerPage.fillRegisterForm(email, username, password);
        registerPage.getRegisterBtn().click();
    }

    public void logIn() {
        waitContentLoadById(logInPage.getIdAllLoginForm());
        logInPage.signIn(email, password);
    }

    public void acceptCookie() {
        googlePage.acceptHungaryCookie(getWebDriverWait());
    }

    public void search(String value) {
        googlePage.runSearchQuery(value);
    }
}
