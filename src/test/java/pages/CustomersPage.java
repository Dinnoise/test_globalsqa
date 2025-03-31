package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static helpers.Wait.waitUntilClickable;
import static helpers.Wait.waitUntilVisible;

public class CustomersPage extends BasePage {
    @FindBy(xpath = "//a[contains(., 'First Name')]")
    private SelenideElement firstNameHeader;

    @FindBy(xpath = "//table//tbody//tr/td[1]")
    private ElementsCollection firstNameCells;

    @FindBy(xpath = "//table//tbody//tr/td[last()]/button")
    private ElementsCollection deleteButtons;

    @Step("Клик по заголовку столбца First Name")
    public void clickFirstNameHeader() {
        waitUntilClickable(firstNameHeader);
        clickElement(firstNameHeader);
    }

    @Step("Получить список First Name клиентов из таблицы")
    public List<String> getCustomerFirstNames() {
        waitUntilVisible(firstNameHeader);
        return firstNameCells.texts();
    }

    @Step("Удалить клиента по индексу: {index}")
    public void deleteCustomerByIndex(int index) {
        deleteButtons.get(index).shouldBe(Condition.visible).click();
    }
}
