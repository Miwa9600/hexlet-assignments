package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;
    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }
    public Point getBeginPoint() {
        return beginPoint;
    }
    public Point getEndPoint() {
        return endPoint;
    }
    public Point getMidPoint() {
        double x = (beginPoint.getX() + endPoint.getX()) / 2;
        double y = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point((int) x, (int) y);
    }
}
// END
