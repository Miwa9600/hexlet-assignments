package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {

        homes.sort((h1, h2) -> Double.compare(h1.getArea(), h2.getArea()));
        List<Home> firstNHomes = homes.subList(0, n);
        List<String> result = new ArrayList<>();
        for (Home home : firstNHomes) {
            result.add(home.toString());
        }

        return result;
    }
}


// END
