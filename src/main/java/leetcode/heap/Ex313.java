package leetcode.heap;

/**
 * @author yuzhang
 * @date 2021/3/15 上午9:49
 * TODO
 */
public class Ex313 {
    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        Ex313 ex313 = new Ex313();
        System.out.println(ex313.nthSuperUglyNumber(12, primes));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] ugly = new long[n + 1];
        int[] pointers = new int[primes.length];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;
            int minIdx = 0;
            for (int j = 0; j < pointers.length; j++) {
                if (ugly[pointers[j]] * primes[j] < min) {
                    min = ugly[pointers[j]] * primes[j];
                    minIdx = j;
                    // 这里是为了去重，之前已经能算出来这个值了，就不需要再算了
                } else if (ugly[pointers[j]] * primes[j] == min) {
                    pointers[j]++;
                }
            }
            ugly[i] = min;
            pointers[minIdx]++;
        }
        return (int) ugly[n - 1];
    }
}
