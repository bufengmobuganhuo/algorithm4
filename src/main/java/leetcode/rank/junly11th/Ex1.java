package leetcode.rank.junly11th;

/**
 * @author yuzhang
 * @date 2021/7/11 上午10:16
 * TODO
 */
public class Ex1 {
    public int[] getConcatenation(int[] nums) {
        if (nums == null || nums.length == 0){
            return new int[0];
        }
        int[] ans = new int[2 * nums.length];
        System.arraycopy(nums,0,ans,0,nums.length);
        System.arraycopy(nums,0,ans,nums.length,nums.length);
        return ans;
    }
}
