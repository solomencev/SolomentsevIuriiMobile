package scenarios;

import static pageObjects.WebPages.GooglePage.GOOGLE_URL;
import static pageObjects.WebPages.GooglePage.SEARCH_TEXT;
import static pageObjects.WebPages.GooglePage.TEXT;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webTests extends BaseTest {

    @Parameters({"platformName"})
    @Test(groups = {"Web"}, description = "Check that the word EPAM is displayed in the search results")
    public void WebTest() {
        actionStep.openWebSite(GOOGLE_URL);
        actionStep.acceptCookie();
        actionStep.typeValueInSearchFiled(SEARCH_TEXT);
        actionStep.clickEnterInSearchFiled();
        assertStep.assertResultsContainsEpamText(TEXT);
    }
}
