package steps;

import io.appium.java_client.AppiumDriver;
import pageObjects.NativePages.BaseNativePage;
import pageObjects.NativePages.BudgetActivityPage;
import pageObjects.NativePages.LogInPage;
import pageObjects.NativePages.RegisterPage;
import pageObjects.WebPages.GooglePage;
import pageObjects.WebPages.WebPageObject;
import setup.BaseTest;

public class BaseStep extends BaseTest {

    BaseNativePage baseNativePage;
    BudgetActivityPage budgetActivityPage;
    LogInPage logInPage;
    RegisterPage registerPage;
    GooglePage googlePage;
    WebPageObject pageObject;

    protected BaseStep(AppiumDriver driver) {
        baseNativePage = new BaseNativePage(driver);
        budgetActivityPage = new BudgetActivityPage(driver);
        logInPage = new LogInPage(driver);
        registerPage = new RegisterPage(driver);
        googlePage = new GooglePage(driver);
        pageObject = new WebPageObject(driver);
    }

}
