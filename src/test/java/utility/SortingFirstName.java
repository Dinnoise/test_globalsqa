package utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingFirstName {

    public static List<String> sortAsc(List<String> list) {
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(Comparator.comparing(String::toLowerCase, Comparator.naturalOrder()));
        return sorted;
    }

    public static List<String> sortDesc(List<String> list) {
        List<String> sorted = new ArrayList<>(list);
        sorted.sort(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder()));
        return sorted;
    }
}
