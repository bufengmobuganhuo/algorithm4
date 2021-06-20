package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex287 {
    public static void main(String[] args) {

    }
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int n = nums.length - 1, sum = 0;
        int target = n * (n - 1) / 2;
        for (int num : nums) {
            sum += (n - num);
        }

        return sum - target - n;
    }
}
