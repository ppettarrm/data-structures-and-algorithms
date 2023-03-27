package divideAndConquer;

public class Point2D {
    public double x, y;
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean coincident(Point2D p) {
        return x == p.x && y == p.y;
    }
    public double dist(Point2D p) {
        double x2 = (x - p.x) * (x - p.x);
        double y2 = (y - p.y) * (y - p.y);
        return Math.sqrt(x2 + y2);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
