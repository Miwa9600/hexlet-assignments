package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        double square = circle.getSquare();
        System.out.println(Math.round(square));
        System.out.println("Вычисление окончено");
    }
}

// END
