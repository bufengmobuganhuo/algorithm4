package leetcode.array;

/**
 * @author yu zhang
 */
public class Ex747 {
    public static void main(String[] args) {
        int[] nums = {60,48,80,80,6,59,24,55,57,57,32,16,5,35,17,26,73,39,65,39,55,80,77,67,26,70,16,34,9,46,86,47,24
                ,29,90,93,31,0,56,25,40,35,90,22,1,46,67,44,25,73};
        System.out.println(new Ex747().dominantIndex(nums));
    }
    public int dominantIndex(int[] nums) {
        int upper = nums[0] * 2;
        int lower = nums[0] / 2;
        int maxIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= upper) {
                maxIdx = i;
                upper = nums[maxIdx] * 2;
                lower = nums[maxIdx] / 2;
            } else if (nums[i] > lower && nums[i] < upper) {
                maxIdx = -1;
                upper = Math.max(upper, nums[i] * 2);
                lower = Math.max(lower, nums[i] / 2);
            }
        }
        return maxIdx;
    }
}
