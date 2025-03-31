package utility;

import io.qameta.allure.Step;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import pages.CustomersPage;

public class DeleteCustomer extends helpers.CustomerHelper {
    protected CustomersPage customersPage;

    public DeleteCustomer(CustomersPage customersPage) {
        this.customersPage = customersPage;
    }

    @Step("Удалить клиента, длина имени которого ближе всего к среднему арифметическому")
    public void deleteCustomerWithClosestAvgLength() {
        List<String> names = customersPage.getCustomerFirstNames();
        Optional<String> nameToDelete = DeleteCustomer.findCustomerToDelete(names);

        nameToDelete.ifPresent(name -> {
             IntStream.range(0, names.size())
                    .filter(i -> names.get(i).equals(name))
                    .forEach(i -> customersPage.deleteCustomerByIndex(i));

        });
    }
}