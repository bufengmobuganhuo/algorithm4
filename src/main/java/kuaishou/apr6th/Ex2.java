package kuaishou.apr6th;

/**
 * @author yuzhang
 * @date 2021/4/6 上午8:37
 * TODO
 */
public class Ex2 {
    public double myPow(double x, int n) {
        return n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    private double quickMul(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        return y % 2 == 0 ? y * y : x * y * y;
    }
}
