package leetcode.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import leetcode.stack.Ex341;

/**
 * @author yu zhang
 */
public class Ex385_2 {
    public static void main(String[] args) {
        new Ex385_2().deserialize("[123,456,[788,799,833],[[]],10,[]]");
    }
    public Ex385.NestedInteger deserialize(String s) {
        int idx = 0, endIdx = 0;
        Stack<Integer> leftIdx = new Stack<>();
        List<Ex385.NestedInteger> stack = new ArrayList<>();
        while (idx < s.length()) {
            if (s.charAt(idx) == '[') {
                if (endIdx >= stack.size()) {
                    stack.add(new Ex385.NestedInteger(Integer.MIN_VALUE));
                    leftIdx.push(stack.size() - 1);
                } else {
                    stack.set(endIdx, new Ex385.NestedInteger(Integer.MIN_VALUE));
                    leftIdx.push(endIdx);
                }
                endIdx++;
                idx++;
            } else if (s.charAt(idx) == ']') {
                int startIdx = leftIdx.pop();
                Ex385.NestedInteger nestedInteger = new Ex385.NestedInteger();
                for (int i = startIdx + 1; i < endIdx; i++) {
                    Ex385.NestedInteger num = stack.get(i);
                    nestedInteger.add(num);
                }
                stack.set(startIdx, nestedInteger);
                endIdx = startIdx + 1;
                idx++;
            } else if (s.charAt(idx) == ','){
                idx++;
            } else {
                boolean isNegative = false;
                if (s.charAt(idx) == '-') {
                    isNegative = true;
                    idx++;
                }
                int num = 0;
                while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
                    num = 10 * num + (s.charAt(idx) - '0');
                    idx++;
                }
                if (endIdx >= stack.size()) {
                    stack.add(new Ex385.NestedInteger(isNegative ? -1 * num : num));
                } else {
                    stack.set(endIdx, new Ex385.NestedInteger(isNegative ? -1 * num : num));
                }
                endIdx++;
            }
        }
        return stack.get(0);
    }
}
