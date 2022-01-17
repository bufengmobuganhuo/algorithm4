package leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex137 {

    /**
     * https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/
     */
    public int singleNumber2(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num), bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }

    /**
     * 时间复杂度：O(nlogC)
     * 称呼要找的元素位答案
     * 1. 位运算下，对于数组中每个非答案的元素，每一个元素都出现了3次，
     * 对应着第i个二进制位的3个0或3个1，无论是哪一种情况，它们的和都是3的倍数（即和为0或3）
     * 2. 那么可以统计每一位的和，然后与3取余数，就可以知道答案是多少
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        // 最长有31位
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                // 取出第i位,计算和
                total +=((num >> i) & 1);
            }
            // 说明答案对应的这一位不为0
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
