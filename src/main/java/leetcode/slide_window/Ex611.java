package leetcode.slide_window;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex611 {
    public static void main(String[] args) {
        Ex611 ex611 = new Ex611();
        int[] nums = {2,2,3,4};
        System.out.println(ex611.triangleNumber(nums));
    }
    /**
     * 1. 三角形的条件a + b > c, a + c > b, b + c > a
     * 2. 如果将数组升序排序，则对于数组中的三个升序的元素a,b,c，他们一定满足上述后两个条件，只要判断第一个条件即可
     * 3. 可以使用滑动窗口leftPtr, midPtr, rightPtr:
     *  leftPtr从0开始取：
     *      midPtr=leftPtr+1:
     *          rightPtr = midPtr+1，并且一直向右遍历，直到不满足leftPtr+midPtr>rightPtr为止
     *          那么从midPtr+1 -> rightPtr-1范围内的都是满足条件的 count += (rightPtr - midPtr - 1)
     */
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length, count = 0;
        for (int leftPtr = 0; leftPtr < len - 2; leftPtr++) {
            for (int midPtr = leftPtr + 1; midPtr < len - 1 && nums[leftPtr] > 0; midPtr++) {
                int rightPtr = midPtr + 1;
                while (rightPtr < len && nums[leftPtr] + nums[midPtr] > nums[rightPtr]){
                    rightPtr++;
                }
                count += (rightPtr - midPtr - 1);
            }
        }
        return count;
    }

}
