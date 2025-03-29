package tests;

import helpers.PropertyProvider;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;
import pages.BankManagerPage;
import pages.CustomersPage;
import utility.AddCustomerGenerator;
import utility.DeleteCustomer;
import utility.SortingFirstName;


import static com.codeborne.selenide.Selenide.*;

public class AddCustomerTest extends BaseTest
{
    AddCustomerPage addCustomerPage;
    CustomersPage customersPage;

    @BeforeMethod
    public final void setup()
    {
        open(PropertyProvider.getBaseUrl());
    }

    @DataProvider(name = "customerData")
    public Object[][] dpMethod() {
        String postCode = AddCustomerGenerator.generatePostCode();
        String firstName = AddCustomerGenerator.generateFirstName(postCode);
        return new Object[][] {
                {firstName, PropertyProvider.getProperty("customer1.lastName"), postCode},
        };
    }

    @Test(description = "Проверка на кликабельность кнопки Add Customer и успешного добавления нового клиента", dataProvider = "customerData")
    public void addNewCustomer(String firstName, String lastName, String postCode)
    {
        var softAssert = new SoftAssert();

        addCustomerPage = page(BankManagerPage.class)
                .goToAddCustomerPage()
                .enterCustomerData(firstName, lastName, postCode)
                .submitCustomer();

        var alertText = addCustomerPage.alertIsCustomerAdded();
        softAssert.assertNotNull(alertText, "Alert didn't have any text");

            softAssert.assertTrue(alertText.contains("Customer added successfully") ||
                            alertText.contains("Please check the details. Customer may be duplicate."),
                    "The alert text is incorrect: " + alertText);
        softAssert.assertAll();
    }

    @Test(description = "Проверка сортировки клиентов по First Name")
    public void sortCustomersByFirstName() {
        var softAssert = new SoftAssert();
        customersPage = page(BankManagerPage.class)
                .goToCustomersPage();

        var namesBefore = customersPage
                .getCustomerFirstNames();

        customersPage
                .clickFirstNameHeader();


        var namesDesc = customersPage
                .getCustomerFirstNames();
        softAssert.assertEquals(namesDesc, SortingFirstName.sortDesc(namesBefore), "Sorting Z->A is not correct");

        customersPage
                .clickFirstNameHeader();
        var namesAsc = customersPage
                .getCustomerFirstNames();
        softAssert.assertEquals(namesAsc, SortingFirstName.sortAsc(namesBefore), "Sorting A->Z is not correct");
    }

    @Test(description = "Проверка на нахождение клиента по средней арифметической длине и его удаление")
    public void testDeleteCustomer() {
        customersPage = page(BankManagerPage.class)
                .goToCustomersPage();
        var helper = new DeleteCustomer(customersPage);

        var initialQuantityCustomers = customersPage
                .getCustomerFirstNames()
                .size();
        helper.deleteCustomerWithClosestAvgLength();
        var updatedNames = customersPage
                .getCustomerFirstNames()
                .size();
        Assert.assertTrue(updatedNames < initialQuantityCustomers, "The client was not removed");
    }

    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }
}
