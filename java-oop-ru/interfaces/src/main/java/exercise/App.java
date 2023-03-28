package exercise;

import java.util.List;
import java.util.stream.Collectors;

class App {

    // new
    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(count)
                .map(Home::toString)
                .toList();
    }
    // old
    public static List<String> buildApartmentsList2(List<Home> apartments, int count) {
        int normalizedCount = Math.min(count, apartments.size());
        apartments.sort(Home::compareTo);
        List<Home> sublist = apartments.subList(0, normalizedCount);
        return sublist.stream()
                .map(appartment -> appartment.toString())
                .collect(Collectors.toList());
    }
}



// END
