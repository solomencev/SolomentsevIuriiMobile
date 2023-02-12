package scenarios;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setup.BaseTest;

public class nativeTests extends BaseTest {

    @Test(groups = {"native"}, description = "Budget Activity page check")
    public void nativeTest() {

        //Go to register page
        getLogInPage().clickRegisterButton();

        //Wait when the page opens
        //waitUntilPageLoad();
        waitContentLoadById(getRegisterPage().getIdAllRegisterForm());

        //get credentials from environment variables
        String email = System.getenv("email");
        String password = System.getenv("password");
        String username = System.getenv("username");

        //Input credentials
        getRegisterPage().fillRegisterForm(email, username, password);

        //click register
        getRegisterPage().getRegisterBtn().click();
        waitContentLoadById(getLogInPage().getIdAllLoginForm());

        //Try to sign
        getLogInPage().signIn(email, password);

        //Assert that there is text "BudgetActivity" and Add expense button
        waitContentLoadById(getBudgetActivityPage().getIdAllBudgetActivity());

        //Check that there are any element with "BudgetActivity" text
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(getBudgetActivityPage().getBudgetActivityText(), "Budget Activity message was not found");

        //Check that there are button Add Expense
        soft.assertEquals(getBudgetActivityPage().getAddExpenseBtn().getText(), "ADD EXPENSE",
            "Add expense button was not found");

        soft.assertAll();
    }
}