package leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex303 {
    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
        Ex303 ex303 = new Ex303(nums);
        System.out.println(ex303.sumRange(0,2));
        System.out.println(ex303.sumRange(2,5));
        System.out.println(ex303.sumRange(0,5));
    }
    private int[] prefixSum;

    public Ex303(int[] nums) {
        prefixSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i] += (i > 0 ? prefixSum[i - 1] : 0) + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);
    }
}
