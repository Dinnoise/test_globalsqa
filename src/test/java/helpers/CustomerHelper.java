package helpers;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CustomerHelper {
    public static Optional<String> findCustomerToDelete(List<String> names) {
        List<Integer> lengths = names.stream().map(String::length).toList();

        double averageLength = lengths.stream().mapToInt(Integer::intValue).average().orElse(0);

        return names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - averageLength)));
    }
}
