package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws Exception {
        String json = car.serialize();
        Files.write(path, json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static Car extract(Path path) throws Exception {
        byte[] bytes = Files.readAllBytes(path);
        String json = new String(bytes);
        return Car.unserialize(json);
    }
}
// END
