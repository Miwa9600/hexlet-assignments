package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {

        Collections.sort(homes, (h1, h2) -> Double.compare(h1.getArea(), h2.getArea()));
        List<Home> firstNHomes = homes.subList(0, n);
        List<String> result = new ArrayList<>();
        for (Home home : firstNHomes) {
            result.add(home.toString());
        }

        return result;
    }
    public static void main(String[] args) {
        List<Home> homes = new ArrayList<>();
        homes.add(new Flat(40.5, 6.0, 3));
        homes.add(new Flat(60.0, 7.5, 5));
        homes.add(new Flat(35.0, 5.0, 1));
        homes.add(new Cottage(120.5, 2));
        homes.add(new Cottage(85.0, 3));

        List<String> result = App.buildApartmentsList(homes, 3);
        for (String str : result) {
            System.out.println(str);
        }
    }

}


// END
