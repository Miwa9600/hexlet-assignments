package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static Map<String, String> genDiff(Map<String, ?> first, Map<String, ?> second) {
        Map<String, String> result = new LinkedHashMap<>();
        Map<String, Object> sortedFirst = new TreeMap<>(first);
        Map<String, Object> sortedSecond = new TreeMap<>(second);

        for (Map.Entry<String, Object> entry : sortedFirst.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!sortedSecond.containsKey(key)) {
                result.put(key, "deleted");
            } else if (!sortedSecond.get(key).equals(value)) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }

        for (Map.Entry<String, Object> entry : sortedSecond.entrySet()) {
            String key = entry.getKey();
            if (!sortedFirst.containsKey(key)) {
                result.put(key, "added");
            }
        }

        return result;
    }
}
//END
