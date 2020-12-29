package bytedance.dec24th;

/**
 * @author yuzhang
 * @date 2020/12/24 上午9:30
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.pivotIndex(nums));
    }
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] += sums[i - 1] + nums[i];
        }
        for (int i = 0; i < len; i++) {
            int leftSum = sums[i] - nums[i];
            int rightSum = sums[len - 1] - sums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
