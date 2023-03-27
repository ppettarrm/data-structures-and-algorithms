package divideAndConquer;

import java.util.Arrays;
import java.util.Comparator;
public class MinDistance2D {
    private Point2D[] points;
    public MinDistance2D(Point2D[] points) {
        if (points.length < 2) {
            throw new IllegalArgumentException("Bar dve tacke");
        }
        this.points = points;
    }
    private static class XComparator implements Comparator<Point2D> {
        public int compare(Point2D arg0, Point2D arg1) {
            double d = arg0.x - arg1.x;
            if (d < 0) return -1;
            else if (d > 0) return 1;
            else return 0;
        }
    }
    private static class YComparator implements Comparator<Point2D> {
        public int compare(Point2D arg0, Point2D arg1) {
            double d = arg0.y - arg1.y;
            if (d < 0) return -1;
            else if (d > 0) return 1;
            else return 0;
        }
    }

    public double findDQ() {
        Arrays.sort(points, new XComparator());
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].coincident(points[i + 1]))
                return 0.0;
        }
        return findDQ(0, points.length - 1);
    }
    private double findDQ(int from, int to) {
        if (to == from) {
            return Double.MAX_VALUE;
        }
        if (to - from == 1) {
            return points[from].dist(points[to]);
        }
        int median = (from + to) / 2;
        double dl = findDQ(from, median);
        double dr = findDQ(median + 1, to);
        double minDist = Math.min(dl, dr);
        double minCrossing = findMinCrossing(from, to, median, minDist);
        return Math.min(minDist, minCrossing);
    }


    private double findMinCrossing(int from, int to, int median, double delta) {
        double medianX = points[median].x;
        Point2D[] box = new Point2D[points.length];
        int boxCnt = 0;
        for (int i = from; i <= to; i++) {
            if (Math.abs(points[i].x - medianX) < delta)
                box[boxCnt++] = points[i];
        }
        Arrays.sort(box, 0, boxCnt, new YComparator());
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < boxCnt - 1; i++) {
            Point2D current = box[i];
// ova ce se petlja izvrsiti najvise 15 puta
            for (int j = i + 1; j < boxCnt; j++) {
                if (box[j].y - current.y >= delta)
                    break;
                double d = current.dist(box[j]);
                if (d < minDist)
                    minDist = d;
            }
        }
        return minDist;
    }

}
