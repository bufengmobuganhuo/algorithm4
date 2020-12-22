package leetcode.math;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/12/15 下午4:28
 * TODO
 */
public class Ex478 {
    public static void main(String[] args) {
        Ex478 ex478 = new Ex478(1, 0, 0);
        System.out.println(Arrays.toString(ex478.randPoint()));
    }
    private double rad, xc, yc;
    public Ex478(double radius, double x_center, double y_center) {
        rad = radius;
        xc = x_center;
        yc = y_center;
    }

    /**
     * 拒绝采样：首先使用正方形的范围随机生成一个点，然后判断这个点是否在圆中，如果不在则继续生成
     * @return
     */
    public double[] randPoint() {
        double x0 = xc - rad;
        double y0 = yc - rad;

        while(true) {
            //
            double xg = x0 + Math.random() * rad * 2;
            double yg = y0 + Math.random() * rad * 2;
            if (Math.sqrt(Math.pow((xg - xc) , 2) + Math.pow((yg - yc), 2)) <= rad) {
                return new double[]{xg, yg};
            }
        }
    }
}
