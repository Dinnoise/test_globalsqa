package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static helpers.Wait.waitUntilVisible;

public class AddCustomerPage extends BasePage
{
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private SelenideElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private SelenideElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private SelenideElement postCodeInput;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private SelenideElement addCustomerButton;

    @Step("Ввод данных клиента: {firstName}, {lastName}, {postCode}")
    public AddCustomerPage enterCustomerData(String firstName, String lastName, String postCode)
    {
        waitUntilVisible(firstNameInput);
        firstNameInput
                .setValue(firstName);
        lastNameInput
                .setValue(lastName);
        postCodeInput
                .setValue(postCode);
        return this;
    }

    @Step("Нажатие на кнопку 'Add Customer'")
    public AddCustomerPage submitCustomer()
    {
        clickElement(addCustomerButton);
        return this;
    }

    @Step("Проверяем, появилось ли всплывающее сообщение об успешном добавлении клиента")
    public String alertIsCustomerAdded() {
        String alertText = getAlertText();
        acceptAlert();
        return alertText;
    }
}
