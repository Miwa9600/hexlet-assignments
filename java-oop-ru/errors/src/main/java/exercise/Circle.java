package exercise;

// BEGIN
public class Circle {
    private Point center;
    private int radius;

    public Circle(Point center, int radius) throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("");
        }
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() {
        return Math.PI * radius * radius;
    }
}

// END
