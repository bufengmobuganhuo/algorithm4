package leetcode.stack.monotonic;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/5 5:04 下午
 * leetcode:下一个更大元素，单调栈
 */
public class Ex496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1==null||nums2==null){
            return null;
        }
        Stack<Integer> stack=new Stack<>();
        int[] res=new int[nums1.length];
        Map<Integer, Integer> map=new HashMap<>(nums2.length);
        // 使用栈底 -> 栈顶 递减的单调栈，对于nums2中的每个元素，都找他们的下一个更大元素，保存到map中
        for (int i = nums2.length-1; i >= 0; i--) {
            while (!stack.isEmpty()&&nums2[i]>=stack.peek()){
                stack.pop();
            }
            int nextBiggerInt=stack.isEmpty()?-1:stack.peek();
            map.put(nums2[i],nextBiggerInt);
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i]=map.get(nums1[i]);
        }
        return res;
    }
}
