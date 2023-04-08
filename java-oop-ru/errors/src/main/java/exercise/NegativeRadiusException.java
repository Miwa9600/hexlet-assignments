package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException(String s) {
        super("Radius cannot be negative");
    }
}


// END
