package leetcode.greedy;

/**
 * @author yuzhang
 * @date 2020/12/15 上午9:07
 * TODO
 */
public class Ex738 {
    public static void main(String[] args) {
        Ex738 ex738 = new Ex738();
        System.out.println(ex738.monotoneIncreasingDigits(332));
    }
    public int monotoneIncreasingDigits(int N) {
        char[] nums = String.valueOf(N).toCharArray();
        int idx = 1;
        // 对于单调递增的数字，一直向后遍历
        while (idx < nums.length && nums[idx] >= nums[idx - 1]) {
            idx++;
        }
        if (idx < nums.length) {
            // 对于那些不是单调递增的数字，需要-1
            while (idx > 0 && nums[idx - 1] > nums[idx]) {
                nums[idx - 1] -= 1;
                idx--;
            }
            for (int i = idx + 1; i < nums.length; i++) {
                nums[i] = '9';
            }
        }
        return Integer.parseInt(String.valueOf(nums));
    }
}
