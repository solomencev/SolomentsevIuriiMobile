package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseNativePage {

    static final String allElementsIdSelectorCommonPart = "platkovsky.alexey.epamtestapp:id/";

    //BasePage
    private AppiumDriver driver;

    public BaseNativePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
