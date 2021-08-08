package leetcode.stack;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/8/8 上午10:24
 * TODO
 */
public class Ex5840 {
    public static void main(String[] args) {
        Ex5840 ex5840 = new Ex5840();
        System.out.println(ex5840.minSwaps("]]][[["));
    }

    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (stack.isEmpty() || chr == '['){
                stack.push(chr);
            }else {
                if (stack.peek() == '['){
                    stack.pop();
                }else {
                    stack.push(chr);
                }
            }
        }
        // stack.size() / 2 有多少对需要调整
        // 向上取整，比如有3对，需要调整2次
        return (stack.size() / 2 + 1) / 2;
    }
}
