package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BankManagerPage;
import pages.CustomersPage;
import utility.DeleteCustomer;
import utility.SortingFirstName;

import static com.codeborne.selenide.Selenide.page;

@Epic("Управление клиентами")
public class SortingAndDeletionTest extends BaseTest
{

    CustomersPage customersPage;

    @Feature("Сортировка клиентов")
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
        softAssert.assertEquals(namesDesc, SortingFirstName.sortDesc(namesBefore), "Сортировка Z->A не является корректной");

        customersPage
                .clickFirstNameHeader();
        var namesAsc = customersPage
                .getCustomerFirstNames();
        softAssert.assertEquals(namesAsc, SortingFirstName.sortAsc(namesBefore), "Сортировка A->Z не является корректной");
    }

    @Feature("Удаление клиентов")
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
        Assert.assertTrue(updatedNames < initialQuantityCustomers, "Клиент не был удален");
    }
}
