package pageObjects.WebPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import setup.BaseTest;

public class GooglePage extends BaseTest {

    public static final String TEXT = "EPAM";
    public static final String SEARCH_TEXT = "EPAM";
    public static final String GOOGLE_URL = "https://www.google.com";

    @FindBy(xpath = "//input")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchString;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//*[@id='rso']/*")
    private List<WebElement> searchResults;

    //Change "Olvasson" to your scroll down button text if it differs
    @FindBy(xpath = "//button//*[contains(text(),'Olvasson')]")
    private WebElement scrollButton;

    //Change "eluta" to your accept cookie button text if it differs
    @FindBy(xpath = "//button//*[contains(text(),'eluta')]")
    private WebElement acceptButton;

    /*public GooglePage(AppiumDriver driver) {
        super(driver);
    }*/

    public GooglePage(AppiumDriver appiumDriver) {

        PageFactory.initElements(appiumDriver, this);

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

    public WebElement getScrollButton() {
        return scrollButton;
    }

    public WebElement getAcceptButton() {
        return acceptButton;
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public void acceptHungaryCookie(AppiumFluentWait wait) {
        if (getScrollButton().isDisplayed()) {
            getScrollButton().click();
            wait.until(ExpectedConditions.visibilityOf(getScrollButton()));
            getScrollButton().click();
            wait.until(ExpectedConditions.visibilityOf(getAcceptButton()));
            getAcceptButton().click();
        }
    }

    public void runSearchQuery(String text) {

        String platformName = (String) getDriver().getCapabilities().getCapability("platformName");
        System.out.println("PLATFORM NAME = " + platformName);

        if (platformName.equals("Android")) {

            searchString.sendKeys(text, Keys.ENTER);

        } else if (platformName.equals("iOS")) {

            //this actually does not work on Mobitru website (still looking for workaround)
            searchString.sendKeys(text);
            searchString.sendKeys(Keys.RETURN);

        }

        //waitUntilPageIsLoaded();

    }


}
