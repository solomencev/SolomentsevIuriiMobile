package pageObjects;

import io.appium.java_client.AppiumDriver;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import pageObjects.NativePages.LogInPage;
import pageObjects.WebPages.WebPageObject;
import setup.IPageObject;
import java.lang.reflect.Field;

public class PageObject implements IPageObject {
    protected static final Logger LOGGER = Logger.getGlobal();

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        LOGGER.info("Current app type: "+appType);
        switch(appType){
            case "web":
                somePageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new LogInPage(appiumDriver);
                break;
            default: throw new Exception("Can't create a page object for "+appType);
        }
    }

    @Override
    public WebElement getWelement(String weName) {
        // use reflection technique
        try {
            Field field = somePageObject.getClass().getDeclaredField(weName);
            field.setAccessible(true);
            return (WebElement) field.get(somePageObject);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.info("No such field");
            return null;
        }
    }
}
