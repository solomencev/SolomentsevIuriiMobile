package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import steps.ActionStep;
import steps.AssertStep;

public class BaseTest implements IDriver {
    protected static final Logger LOGGER = Logger.getGlobal();
    protected String email = System.getenv("email");
    protected String username = System.getenv("userName");
    protected String password = System.getenv("password");

    public static AssertStep assertStep;
    public static ActionStep actionStep;
    protected static AppiumDriver appiumDriver; // singleton
    IPageObject po;
    AppiumFluentWait webDriverWait;
    static final int waitTime = 10;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity",
        "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        LOGGER.info("Before: app type - "  +  appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        actionStep = new ActionStep(getDriver());
        assertStep = new AssertStep(getDriver());
        setPageObject(appType, appiumDriver);
        appiumDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        LOGGER.info("App is closed");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) {

        WebDriverManager wdm = WebDriverManager.chromedriver();
        wdm.setup();
        String chromedriverPath = wdm.getDownloadedDriverPath();


        DesiredCapabilities capabilities = new DesiredCapabilities();

        /** mandatory Android capabilities. */
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        /** Capabilities for test of Android native app on EPAM Mobile Cloud. */
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        /** Capabilities for test of iOS native app on EPAM Mobile Cloud. */
        capabilities.setCapability("bundleId", bundleId);
        //capabilities.setCapability("chromedriverExecutable", chromedriverPath);

        try {
            String url = String.format("https://%s:%s@app.mobitru.com/wd/hub", System.getenv("EPAM_NAME_SURNAME"),
                URLEncoder.encode(System.getenv("MOBITRU_TOKEN")));
            appiumDriver = new AppiumDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) {
        try {
            po = new PageObject(appType, appiumDriver);
        } catch (Exception exception) {
            LOGGER.log(Level.WARNING, "New page object was not set", exception);
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
