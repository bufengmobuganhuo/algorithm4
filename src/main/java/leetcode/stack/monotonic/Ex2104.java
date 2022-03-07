package leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex2104 {
    /**
     * 1. 为了保证最大最小值唯一，约定：如果nums[i]>=nums[j] && i > j => 则nums[i]大于nums[j]
     * 2. 结果=所有子数组的最大值和-所有子数组的最小值和
     * 3. 对于nums[i]，如果左边离他最近的小于它的元素下标为j，右边离他最近的小于它的元素下标为k,
     * 则可以组成(k-i)*(i-j)个以nums[i]为最小值的子数组（因为左边界有(i-j)种选择，有边界有(k-i)种选择，根据乘法原理得到）
     * 那么以nums[i]为最小值组成的子数组的"最小值和"=(i-j)*(k-i)*nums[i]
     * 最大值同理
     * 4. 查找左右边界时可以使用单调栈
     * 寻找左边界时：维护递增栈，从左到右遍历数组，则待插入元素的左边界就是第一个符合条件的栈顶元素
     * 寻找右边界时：维护递增栈，从右到左遍历数组，则待插入元素的右边界就是第一个符合条件的栈顶元素
     */
    public long subArrayRanges(int[] nums) {
        int len = nums.length;
        // minLeft[i]：左边比nums[i]小的最近的元素
        int[] minLeft = new int[len];
        int[] minRight = new int[len];
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        // 递增栈，寻找小边界
        Stack<Integer> minStack = new Stack<>();
        // 递减栈，寻找大边界
        Stack<Integer> maxStack = new Stack<>();

        // 从左到右遍历，寻找最左边的小边界和大边界
        for (int i = 0; i < len; i++) {
            // 先找小边界
            // minStack.peek的下标一定小于i，则如果peek > nums[i]，一定是比他大，"="则不行
            while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);

            // 后找大边界
            // maxStack.peek下标一定小于i，则如果nums[peek]=nums[i]，也是小于
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            // 找小边界
            // minStack.peek的下标一定大于i，则nums[peek]=nums[i]时也算是大于
            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? len : minStack.peek();
            minStack.push(i);

            // 找大边界
            // maxStack.peek的下标一定大于i，则如果nums[peek]=nums[i]，是大于，而不是小于
            while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? len : maxStack.peek();
            maxStack.push(i);
        }
        long minSum = 0, maxSum = 0;
        for (int i = 0; i < len; i++) {
            minSum += (long) (i - minLeft[i]) * (minRight[i] - i) * nums[i];
            maxSum += (long) (i - maxLeft[i]) * (maxRight[i] - i) * nums[i];
        }
        return maxSum - minSum;
    }
}
