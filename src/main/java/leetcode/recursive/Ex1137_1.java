package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2021/8/8 下午12:11
 * TODO
 */
public class Ex1137_1 {
    public static void main(String[] args) {
        Ex1137_1 ex1137_1 = new Ex1137_1();
        System.out.println(ex1137_1.tribonacci(4));
    }
    public int tribonacci(int n) {
        int[] nums = {0, 1, 1};
        for (int i = 3; i <= n; i++) {
            if (i % 3 == 0) {
                nums[0] += nums[1] + nums[2];
            }else if (i % 3 == 1) {
                nums[1] += nums[0] + nums[2];
            }else {
                nums[2] += nums[0] + nums[1];
            }
        }
        return nums[n  % 3];
    }
}
