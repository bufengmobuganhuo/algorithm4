package bytedance.jan4th;

/**
 * @author yuzhang
 * @date 2021/1/4 上午8:46
 * TODO
 */
public class Ex1 {
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] runningSum=new int[nums.length];
        runningSum[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            runningSum[i] = runningSum[i-1]+nums[i];
        }
        return runningSum;
    }
}
