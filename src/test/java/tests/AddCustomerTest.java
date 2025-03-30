package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;
import pages.BankManagerPage;
import pages.CustomersPage;
import utility.AddCustomerGenerator;

import static com.codeborne.selenide.Selenide.*;

@Epic("Управление клиентами")
public class AddCustomerTest extends BaseTest
{
    AddCustomerPage addCustomerPage;

    @DataProvider(name = "customerData")
    public Object[][] dpMethod() {
        String postCode = AddCustomerGenerator.generatePostCode();
        String firstName = AddCustomerGenerator.generateFirstName(postCode);
        return new Object[][] {
                {firstName, PropertyProvider.getProperty("customer1.lastName"), postCode},
        };
    }

    @Feature("Создание клиентов")
    @Test(description = "Проверка на кликабельность кнопки Add Customer и успешного добавления нового клиента", dataProvider = "customerData")
    public void addNewCustomer(String firstName, String lastName, String postCode)
    {
        var softAssert = new SoftAssert();

        addCustomerPage = page(BankManagerPage.class)
                .goToAddCustomerPage()
                .enterCustomerData(firstName, lastName, postCode)
                .submitCustomer();

        var alertText = addCustomerPage.alertIsCustomerAdded();
        softAssert.assertNotNull(alertText, "В alert не было никакого текста.");

            softAssert.assertTrue(alertText.contains("Customer added successfully") ||
                            alertText.contains("Please check the details. Customer may be duplicate."),
                    "Текст alert'а неверен: " + alertText);
        softAssert.assertAll();
    }
}
