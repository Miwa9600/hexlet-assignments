package exercise;

import lombok.Data;
import lombok.Value;

// BEGIN
@Data
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
