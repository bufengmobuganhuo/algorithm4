package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2172 {
    public static void main(String[] args) {
        int[] nums = {8,13,3,15,3,15,2,15,5,7,6};
        System.out.println(new Ex2172().maximumANDSum(nums, 8));
    }
    public int maximumANDSum(int[] nums, int numSlots) {
        // 3^numSlot - 1 = 22222(3进制)，表示每一个篮子都剩2个位置
        return max(nums, nums.length - 1, (int) (Math.pow(3, numSlots) - 1), numSlots);
    }
    // 超出时间限制
    private int max(int[] nums, int i, int mask, int numSlots) {
        if (i < 0) {
            return 0;
        }
        int res = 0;
        // 记录3^(j-1)
        int power3 = 1;
        int temp = mask;
        // 枚举，放到第j个篮子时的获利
        for (int j = 1; j < numSlots + 1; j++) {
            // 222(3进制) % 3 > 0，说明对应的位上可以放入数字
            if (temp % 3 > 0) {
                // 第j个篮子放入了一个数字(j = 2)，那么剩下的位置还有(222(3进制)-3^j-1(10进制))，
                // 要放入i-1个数字的最大值和本次获利的和
                res = Math.max(res, max(nums, i - 1, mask - power3, numSlots) + (j & nums[i]));
            }
            power3 *= 3;
            // 这里除以3, 222 -> 22(3进制),则下次%时就是第2位
            temp /= 3;
        }
        return res;
    }
}
