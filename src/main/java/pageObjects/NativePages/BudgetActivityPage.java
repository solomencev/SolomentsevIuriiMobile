package pageObjects.NativePages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BudgetActivityPage extends BaseNativePage {

    private static final String idAllBudgetActivity = allElementsIdSelectorCommonPart + "main_content";

    @FindBy(xpath = "//*[contains(@text,'BudgetActivity')]")
    private WebElement budgetActivityText;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    private WebElement addExpenseBtn;

    public BudgetActivityPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement getAddExpenseBtn() {
        return addExpenseBtn;
    }

    public WebElement getBudgetActivityText() {
        return budgetActivityText;
    }

    public String getIdAllBudgetActivity() {
        return idAllBudgetActivity;
    }
}
