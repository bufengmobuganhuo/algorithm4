package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex1037 {
    public boolean isBoomerang(int[][] points) {
        int x1 = points[0][0], y1 = points[0][1];
        int x2 = points[1][0], y2 = points[1][1];
        int x3 = points[2][0], y3 = points[2][1];
        return (x1 - x2) * (y3 - y2) != (x3 - x2) * (y1 - y2);
    }
}
