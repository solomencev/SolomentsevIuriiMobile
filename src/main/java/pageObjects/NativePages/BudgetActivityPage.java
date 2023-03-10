package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BudgetActivityPage extends BaseNativePage {

    private static final String ID_ALL_BUDGET_ACTIVITY = ALL_ELEMENTS_ID_SELECTOR_COMMON_PART + "main_content";

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='Budget']")
    @FindBy(xpath = "//*[contains(@text,'BudgetActivity')]")
    private WebElement budgetActivityLabel;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Add']")
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    private WebElement addExpenseBtn;

    public BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getAddExpenseBtn() {
        return addExpenseBtn;
    }

    public WebElement getBudgetActivityText() {
        return budgetActivityLabel;
    }

    public String getIdAllBudgetActivity() {
        return ID_ALL_BUDGET_ACTIVITY;
    }
}
