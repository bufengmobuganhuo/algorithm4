package leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex2044 {
    public static void main(String[] args) {
        int[] nums = {3, 1};
        System.out.println(new Ex2044().countMaxOrSubsets(nums));
    }
    private int max = 0;
    private int cnt = 0;

    public int countMaxOrSubsets(int[] nums) {
        backtracking(nums, 0, 0);
        return cnt;
    }

    private void backtracking(int[] nums, int idx, int track) {
        if (idx >= nums.length) {
            if (track > max) {
                max = track;
                cnt = 1;
            } else if (track == max) {
                cnt++;
            }
            return;
        }
        int tmp = track;
        track |= nums[idx];
        backtracking(nums, idx + 1, track);
        track = tmp;
        backtracking(nums, idx + 1, track);
    }
}