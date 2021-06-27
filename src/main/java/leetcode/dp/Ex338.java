package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex338 {
    /**
     * 对于一个数x，令x = x & (x-1),可以让x的最低位变成0
     * 一直循环直到x=0，就可以知道x中1的个数，时间复杂度O(nlog(n))
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            res[i] = countOnes(i);
        }
        return res;
    }

    /**
     * 1. 对于一个数x(如13：1101), 寻找一个数y，令y <= x且y是2的整数次幂数(y=8:1000)，称为最高有效位，
     * 可知y的最高位是1，其他位都是0，令z = x - y(z=5: 0101)，则可知z <= x，并且x的1的个数比z的多1
     * 2. 如果x & (x-1)=0，x就是最高有效位
     */
    public int[] countBits2(int n) {
        int highBit = 0;
        int[] res = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
                res[i] = 1;
                continue;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }

    private int countOnes(int x) {
        int count = 0;
        while (x > 0) {
            x &= (x - 1);
            count++;
        }
        return count;
    }
}
