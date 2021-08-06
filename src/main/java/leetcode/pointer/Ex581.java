package leetcode.pointer;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex581 {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        Ex581 ex581 = new Ex581();
        System.out.println(ex581.findUnsortedSubarray(nums));
    }

    /**
     * 1. 正序遍历数组，遍历到的第i个元素"应该"是nums[0...i]中最大的元素max，
     *  如果正序遍历过程中max(nums[0...i-1]的最大元素) > nums[i]，说明nums[i]不在正确的位置，取最大的i，就可以知道需要排序的右边界
     * 2. 逆序遍历数组，遍历到的第i个元素"应该"是nums[i...len-1]中最小的元素min，
     *  如果逆序遍历过程中min(nums[i-1...len-1的最小元素]) < nums[i]，说明nums[i]不再正确的位置，取最小的i，就可以知道需要排序的左边界
     */
    public int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int max = nums[0], min = nums[len - 1];
        int startPtr = 0, endPtr = -1;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]){
                endPtr = i;
                // 符合正序遍历的条件，就更新max
            }else {
                max = nums[i];
            }
            if (min < nums[len - i - 1]){
                startPtr = len - i - 1;
            }else {
                min = nums[len - i - 1];
            }
        }
        return endPtr - startPtr - 1;
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 栈底 -> 栈顶:递增, 正序遍历数组，可以找到最小的需要调整位置的索引
        Stack<Integer> ascStack = new Stack<>();
        // 栈底 -> 栈顶：递减，逆序遍历数组，可以找到最大的需要调整位置的索引
        Stack<Integer> descStack = new Stack<>();
        int startPtr = Integer.MAX_VALUE, endPtr = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            while (!ascStack.isEmpty() && nums[ascStack.peek()] > nums[i]) {
                startPtr = Math.min(startPtr, ascStack.pop());
            }
            while (!descStack.isEmpty() && nums[descStack.peek()] < nums[len - i - 1]) {
                endPtr = Math.max(endPtr, descStack.pop());
            }
            descStack.push(len - i - 1);
            ascStack.push(i);
        }
        return startPtr == Integer.MAX_VALUE ? 0 : endPtr - startPtr + 1;
    }
}
