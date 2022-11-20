package leetcode.rank.year2022.november13;

/**
 * @author yuzhang
 * @date 2022/11/13 10:19
 * TODO
 */
public class Ex1 {
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius + celsius / 5 * 4 + 32.00};
    }
}
