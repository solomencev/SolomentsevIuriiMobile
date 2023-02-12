package scenarios;

import static pageObjects.WebPages.GooglePage.GOOGLE_URL;
import static pageObjects.WebPages.GooglePage.SEARCH_TEXT;
import static pageObjects.WebPages.GooglePage.TEXT;

import org.testng.annotations.Test;
import setup.BaseTest;

public class webTests extends BaseTest {


    @Test(groups = {"web"}, description = "Check EPAM word search results")
    public void WebTest() {

        actionStep.openWebSite(GOOGLE_URL);
        actionStep.typeValueInSearchFiled(SEARCH_TEXT);
        actionStep.clickEnterInSearchFiled();
        assertStep.ResultsContainsEpamText(TEXT);

    }
}