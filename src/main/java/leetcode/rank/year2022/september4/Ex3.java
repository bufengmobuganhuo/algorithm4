package leetcode.rank.year2022.september4;

/**
 * @author yuzhang
 * @date 2022/9/4 11:37
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 8, 48, 10};
        System.out.println(new Ex3().longestNiceSubarray(nums));
    }
    public int longestNiceSubarray(int[] nums) {
        int len = nums.length;
        int leftPtr = 0, rightPtr = 1;
        int maxLen = 1;
        while (rightPtr < len) {
            int idx = rightPtr - 1;
            for (; idx >= leftPtr; idx--) {
                if ((nums[idx] & nums[rightPtr]) != 0) {
                    break;
                }
            }
            if (idx >= leftPtr) {
                maxLen = Math.max(rightPtr - leftPtr, maxLen);
                leftPtr = idx + 1;
            }
            rightPtr++;
        }
        return Math.max(rightPtr - leftPtr, maxLen);
    }
}
