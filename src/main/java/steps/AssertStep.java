package steps;

import static java.sql.DriverManager.getDriver;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import pageObjects.WebPages.GooglePage;

public class AssertStep extends BaseStep {
    public AssertStep(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public void ResultsContainsEpamText(String epam) {
        GooglePage page = new GooglePage(getDriver());
        Assert.assertTrue(page.isResultsContainsText(epam), "Messages in result don't match search request");

    }
}
