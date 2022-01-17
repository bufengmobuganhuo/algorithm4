package offer.bit;

/**
 * @author yu zhang
 */
public class Ex56 {
    public static void main(String[] args) {
        System.out.println(new Ex56().singleNumber(new int[]{3,4,3,3}));
    }
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int j = 0; j < nums.length; j++) {
                total += (nums[j] & 1);
                nums[j] >>= 1;
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
