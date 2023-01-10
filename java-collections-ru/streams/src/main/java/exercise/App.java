package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static List<String> getCountOfFreeEmails(List<String> emails){
        long count = emails.stream()
                .count("@gmail.com", "@yandex.ru", "@hotmail.com");
        return count;
    }
}
// END
