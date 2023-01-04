package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        String[] words = sentence.split(" ");
        Map<String, Integer> map = new HashMap<String, Integer>();

        if (sentence.length() == 0) {
            return map;
        }

        for (String word : words) {
            int wordCount = map.getOrDefault(word, 0);
            wordCount += 1;
            map.put(word, wordCount);
        }

        return map;
    }

    public static String toString(Map<String, Integer> wordsCount) {

        if (wordsCount.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{\n");

        for (Object key : wordsCount.keySet()) {
            result.append("  " + key + ": " + wordsCount.get(key) + "\n");
        }

        result.append("}");
        return result.toString();
    }
}
//END
