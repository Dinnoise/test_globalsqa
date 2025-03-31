package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.page;

public class BankManagerPage extends BasePage
{
        @FindBy(xpath = "//button[contains(text(), 'Add Customer')]")
        private SelenideElement addCustomerButton;

        @FindBy(xpath = "//button[contains(text(), 'Customers')]")
        private SelenideElement customersButton;

        @Step("Перейти на страницу добавления клиента")
        public AddCustomerPage goToAddCustomerPage()
        {
            clickElement(addCustomerButton);
            return page(AddCustomerPage.class);
        }

        @Step("Перейти на страницу клиентов")
        public CustomersPage goToCustomersPage() {
            customersButton.click();
            return page(CustomersPage.class);
        }
}

