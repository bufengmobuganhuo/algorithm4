package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/30 11:02 上午
 * TODO
 */
public class Ex313 {
    public static void main(String[] args) {
        Ex313 ex313 = new Ex313();
        int[] primes = {2, 7, 13, 19};
        System.out.println(ex313.nthSuperUglyNumber(12, primes));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n + 1];
        ugly[0] = 1;
        int[] pointer = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = 0; j < primes.length; j++) {
                int multi = ugly[pointer[j]] * primes[j];
                if (multi < min) {
                    min = multi;
                    minIndex = j;
                } else if (multi == min) {
                    pointer[j]++;
                }
            }
            ugly[i] = min;
            pointer[minIndex]++;
        }
        return ugly[n - 1];
    }
}
