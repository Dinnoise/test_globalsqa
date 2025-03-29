package utility;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddCustomerGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Генерируем 10-значный postCode.
     */
    public static String generatePostCode() {
        return IntStream.range(0, 10)
                .mapToObj(i -> String.valueOf(RANDOM.nextInt(10)))
                .collect(Collectors.joining());
    }

    /**
     * Преобразуем Post Code в First Name.
     */
    public static String generateFirstName(String postCode) {
        return IntStream.range(0, 5)
                .mapToObj(i -> {
                    int num = Integer.parseInt(postCode.substring(i * 2, i * 2 + 2));
                    char letter = (char) ('a' + (num % 26));
                    return String.valueOf(letter);
                })
                .collect(Collectors.joining());
    }
}
