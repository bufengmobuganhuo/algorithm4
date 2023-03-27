package leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1630 {

    public static void main(String[] args) {
        int[] nums = {4,6,5,9,3,7}, l = {0, 0 ,2}, r = {2, 3, 5};
        System.out.println(new Ex1630().checkArithmeticSubarrays2(nums, l , r));
    }

    // 利用等差数列的性质
    public List<Boolean> checkArithmeticSubarrays2(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int leftPtr = l[i], rightPtr = r[i];
            ans.add(check(nums, leftPtr, rightPtr));
        }
        return ans;
    }

    private Boolean check(int[] nums, int leftPtr, int rightPtr) {
        // 找到最大，最小值
        int min = nums[leftPtr], max = nums[leftPtr];
        for (int i = leftPtr; i < rightPtr + 1; i++) {
            int num = nums[i];
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 如果最大值==最小值，则说明是差数为0的等差数列
        if (min == max) {
            return Boolean.TRUE;
        }
        // 找到差数
        int diff = (max - min) / (rightPtr - leftPtr);
        // max = min + n*diff，那么 (max - min) % n == 0，即diff得是一个整数
        if ((max - min) % (rightPtr - leftPtr) != 0) {
            return Boolean.FALSE;
        }
        // 对于每一个数num, num = min + n * diff，则(num - min) % diff == 0，并且他们得到的n不能相等
        boolean[] map = new boolean[rightPtr - leftPtr + 1];
        for (int i = leftPtr; i < rightPtr + 1; i++) {
            int num = nums[i];
            if ((num - min) % diff != 0) {
                return Boolean.FALSE;
            }
            int n = (num - min) / diff;
            if (n >= map.length || map[n]) {
                return Boolean.FALSE;
            }
            map[n] = true;
        }
        return Boolean.TRUE;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int left = l[i], right = r[i];
            int[] tmp = new int[right - left + 1];
            System.arraycopy(nums, left, tmp, 0, right - left + 1);
            ans.add(check(tmp));
        }
        return ans;
    }

    private Boolean check(int[] nums) {
        Arrays.sort(nums);
        int diff = nums[1] - nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i-1] != diff) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
