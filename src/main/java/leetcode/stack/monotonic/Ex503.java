package leetcode.stack.monotonic;

import org.omg.CORBA.INTERNAL;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/5 4:14 下午
 * leetcode 503:下一个更大的元素
 * 使用单调栈:
 * 1. 对于一个不循环的数组（栈底 -> 斩顶：递减）：
 *   1⃣️ 首先把第一个元素arr[0]入栈，随后对于第二个元素arr[1]，如果arr[1]>arr[0],则找到了arr[0]的下一个最大元素。
 *      此时可以把arr[0]出栈，arr[1]入栈
 *   2⃣️ 如果arr[1]<arr[0]，则让arr[1]直接入栈
 *   3⃣️ 对于arr[2]，栈中所有比arr[2]小的元素都找到了下一个最大的元素，因为从栈底到栈顶是递增的，栈顶是最大元素
 * 2. 对于一个循环数组：
 *   1⃣️ 因为是循环数组，所以每个元素要入栈两次
 *   2⃣️ 从右边开始入栈的话，可以使用 (i%nums.length)来获取到在数组中的位置，使用方便
 *   3⃣️ 对于3，8，4，1，2：
 *     1⃣️：2入栈，此时栈空，则2的下一个最大元素暂时未找到：-1
 *     2⃣️：1入栈，此时栈顶元素2>1，那么1的下一个最大元素就是2
 *     3⃣️：4入栈，此时违反了从栈底 -> 栈顶 递减的规律，要依次出栈，
 *          如果出到栈空，则4的下一个最大元素为-1，否则找到一个比4大的元素，就是他的下一个最大元素
 */
public class Ex503 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums==null){
            return null;
        }
        int[] res=new int[nums.length];
        Stack<Integer> stack=new Stack<>();
        //入栈两遍
        for (int i = 2*nums.length-1; i >= 0; i--) {
            // 对于不符合要求的，要依次出栈
            while(!stack.isEmpty()&&nums[i%nums.length]>=stack.peek()){
                stack.pop();
            }
            res[i%nums.length]=stack.isEmpty()?-1:stack.peek();
            stack.push(nums[i%nums.length]);
        }
        return res;
    }
}
