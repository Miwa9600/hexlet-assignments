package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        // Проверяем входные данные
        if (homes == null || homes.isEmpty() || n <= 0 || n > homes.size()) {
            return Collections.emptyList(); // Возвращаем пустой список, если входные данные некорректны
        }

        // Сортируем список объектов по площади по возрастанию
        Collections.sort(homes, (h1, h2) -> Double.compare(h1.getArea(), h2.getArea()));

        // Берем первые n элементов
        List<Home> firstNHomes = homes.subList(0, n);

        // Создаем список строковых представлений этих объектов
        List<String> result = new ArrayList<>();
        for (Home home : firstNHomes) {
            result.add(home.toString());
        }

        return result;
    }

}



// END
