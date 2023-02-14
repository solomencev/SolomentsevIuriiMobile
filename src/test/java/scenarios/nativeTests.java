package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeTests extends BaseTest {
    private String email = System.getenv("email");

    @Test(groups = {"native"}, description = "Check that the Budget Activity page is displayed")
    public void nativeTest() {
        actionStep.clickRegistrationBtn();
        actionStep.submitRegistrationForm();
        actionStep.logIn();
        assertStep.assertTextBudgetActivity();
        assertStep.assertButtonAddExpense();
    }
}
