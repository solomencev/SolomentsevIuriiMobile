package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.NativePages.BudgetActivityPage;
import pageObjects.NativePages.LogInPage;
import pageObjects.NativePages.RegisterPage;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import steps.ActionStep;
import steps.AssertStep;

public class BaseTest implements IDriver {

    public static AssertStep assertStep;
    public static ActionStep actionStep;
    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    LogInPage logInPage;
    RegisterPage registerPage;
    AppiumFluentWait webDriverWait;
    BudgetActivityPage budgetActivityPage;
    static final int waitTime = 10;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName,
                      @Optional("") String browserName, @Optional("") String app) throws Exception {

        actionStep = new ActionStep(appiumDriver);
        assertStep = new AssertStep(appiumDriver);

        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);
        appiumDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        try {
            System.out.println("After");
            appiumDriver.closeApp();
        } catch (NullPointerException nullPointerException) {
            System.err.println("Appium driver is null: " + nullPointerException);
        }
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app) {

        WebDriverManager wdm = WebDriverManager.chromedriver();
        wdm.setup();
        String chromedriverPath = wdm.getDownloadedDriverPath();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        capabilities.setCapability("chromedriverExecutable", chromedriverPath);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) {
        try {
            po = new PageObject(appType, appiumDriver);
        } catch (Exception e) {
            throw new RuntimeException("Exception while Page Object setting:" + e);
        }
    }

    //Getters for pages
    public LogInPage getLogInPage() {
        if (logInPage == null) {
            return new LogInPage(appiumDriver);
        } else {
            return logInPage;
        }
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            return new RegisterPage(appiumDriver);
        } else {
            return registerPage;
        }
    }

    public BudgetActivityPage getBudgetActivityPage() {
        if (budgetActivityPage == null) {
            return new BudgetActivityPage(appiumDriver);
        } else {
            return budgetActivityPage;
        }
    }

    public AppiumFluentWait getWebDriverWait() {
        if (webDriverWait == null) {
            return new AppiumFluentWait(appiumDriver);
        } else {
            return webDriverWait;
        }
    }

    public void waitContentLoadById(String idSelector) {
        getWebDriverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(idSelector)));
    }
}
