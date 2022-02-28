package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1994 {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    private static final int NUM_MAX = 30;

    private static final int MOD = 1000000007;

    /**
     * https://leetcode-cn.com/problems/the-number-of-good-subsets/solution/hao-zi-ji-de-shu-mu-by-leetcode-solution-ky65/
     */
    public int numberOfGoodSubsets(int[] nums) {
        // 统计nums中，每个数字出现的次数
        int[] freq = new int[NUM_MAX + 1];
        for (int num : nums) {
            freq[num]++;
        }
        // mask最多有2^10 - 1种可能，再加上边界条件:一个质数都不选
        int[] f = new int[1 << PRIMES.length];
        // 边界条件，对于数字1来说，选或不选都可以是好子集
        f[0] = 1;
        // f[1][0] = 2^freq[1]ds
        for (int i = 0; i < freq[1]; i++) {
            f[0] = f[0] * 2 % MOD;
        }
        for (int i = 2; i <= NUM_MAX; i++) {
            // nums 中没有出现数字i
            if (freq[i] == 0) {
                continue;
            }
            boolean check = true;
            int subset = 0, x = i;
            for (int j = 0; j < PRIMES.length; j++) {
                // i的质因数只能出现一次，出现多次就i就不能参与构成好子集
                if (x % (PRIMES[j] * PRIMES[j]) == 0) {
                    check = false;
                    break;
                }
                // 说明i中包含质因数, 用subset的对应位记录下来
                if (x % PRIMES[j] == 0) {
                    subset |= (1 << j);
                }
            }
            if (!check) {
                continue;
            }
            // 动态规划, 从所有质因数都选开始
            for (int mask = (1 << PRIMES.length) - 1; mask > 0; mask--) {
                // mask 包含了subset中的各位1，才有计算的必要，否则就是上一次的f[i-1][mask]
                if ((mask & subset) == subset) {
                    // f[i][mask]：表示使用(2~i)，并且选用质因数的情况为mask时（mask的某一位=1，说明对应选了质因数），能组成的子集的个数
                    // f[i][mask] = f[i - 1][mask] + f[i-1][mask^subset] * freq[i]
                    // 这次的个数 = 只选用(2~i-1)且质因数选取情况为mask的个数
                    // + 选用(2~i)，那么对于(2~i-1)就不能选用i，则要使用异或剔除i包含的质因数，同时nums有几个i，就可以组成几种子集，所以*freq[i]
                    f[mask] = (int) ((f[mask] + ((long) f[mask ^ subset]) * freq[i]) % MOD);
                }
            }
        }
        int ans = 0;
        // 把各种情况都加起来
        for (int mask = 1, maskMax = (1 << PRIMES.length); mask < maskMax; mask++) {
            ans = (ans + f[mask]) % MOD;
        }
        return ans;
    }
}
