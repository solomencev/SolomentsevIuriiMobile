package steps;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import pageObjects.WebPages.GooglePage;

public class AssertStep extends BaseStep {
    public AssertStep(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void assertResultsContainsEpamText(String text) {
        GooglePage page = new GooglePage(getDriver());
        Assert.assertTrue(page.isResultsContainsText(text), "Messages in result don't match search request");
    }

    public void assertTextBudgetActivity() {
        waitContentLoadById(budgetActivityPage.getIdAllBudgetActivity());
        assertNotNull(budgetActivityPage.getBudgetActivityText(), "Budget Activity message was not found");
    }

    public void assertButtonAddExpense() {
        waitContentLoadById(budgetActivityPage.getIdAllBudgetActivity());
        assertTrue(budgetActivityPage.getAddExpenseBtn().isDisplayed());
    }
}
