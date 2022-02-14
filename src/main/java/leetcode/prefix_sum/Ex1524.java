package leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex1524 {

    private static final int MOD = (int) (Math.pow(10, 9) + 7);

    public int numOfSubarrays(int[] arr) {
        int evenCount = 1, oddCount = 0;
        int sum = 0;
        int count = 0;
        for (int num : arr) {
            sum += num;
            if (sum % 2 == 0) {
                count = (oddCount + count) % MOD;
                evenCount++;
            } else {
                count = (evenCount + count) % MOD;
                oddCount++;
            }
        }
        return count;
    }
}
