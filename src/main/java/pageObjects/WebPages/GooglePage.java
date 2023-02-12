package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends WebPageObject {

    public static final String TEXT = "EPAM";
    public static final String SEARCH_TEXT = "EPAM";
    public static final String GOOGLE_URL = "https://www.google.com";

    @FindBy(xpath = "//input")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResultsList;

    public GooglePage(AppiumDriver driver) {
        super(driver);
    }

    public void fillSearchField(String search) {
        searchField.click();
        searchField.sendKeys(search);
    }

    public boolean isResultsContainsText(String search) {
        for (WebElement result : searchResultsList) {
            String text = result.getText();
            if (text.contains(search)) {
                return true;
            }
        }
        return false;
    }
}
