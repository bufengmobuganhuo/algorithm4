package leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/2/1 上午10:08
 * TODO
 */
public class Ex738_1 {
    public int monotoneIncreasingDigits(int N) {
        char[] nums = String.valueOf(N).toCharArray();
        int idx = 1, len = nums.length;
        // 对于符合条件的，一直想后遍历
        while (idx < len && nums[idx - 1] <= nums[idx]) {
            idx++;
        }
        if (idx < len) {
            // 对于不符合条件的，尽可能向左遍历，找到最左边的不符合条件的数字的正确值
            while (idx > 0 && nums[idx - 1] > nums[idx]) {
                nums[idx - 1] -= 1;
                idx--;
            }
            // 将最高位后边的数字都变为9
            for (int i = idx + 1; i < len; i++) {
                nums[i] = '9';
            }
        }
        return Integer.parseInt(String.valueOf(nums));
    }
}
