package leetcode.stack;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex678 {
    public static void main(String[] args) {
        String s =
            "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        Ex678 ex678 = new Ex678();
        System.out.println(ex678.checkValidString(s));
    }

    /**
     * min: 左括号可能剩余的最小值
     * max: 左括号可能剩余的最大值
     * 在遍历的过程中：
     * chr = '(': min++, max++
     * chr = ')': min--, max--(如果max<0，说明左括号不够)，判断过程中保证min>=0
     * chr = '*': min--, max++(*分别取右括号和左括号)，判断过程中保证min>=0
     */
    public boolean checkValidString2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int min = 0, max = 0;
        for (char chr : s.toCharArray()) {
            if (chr == '(') {
                min++;
                max++;
            } else if (chr == ')') {
                min = Math.max(0, min - 1);
                max--;
                if (max < 0) {
                    return false;
                }
            } else {
                min = Math.max(0, min - 1);
                max++;
            }
        }
        // 最后不能剩余
        return min == 0;
    }

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (chr == '(') {
                leftStack.push(i);
            } else if (chr == '*') {
                starStack.push(i);
            } else if (!leftStack.isEmpty()) {
                leftStack.pop();
            } else if (!starStack.isEmpty()) {
                starStack.pop();
            } else {
                return false;
            }
        }
        while (!leftStack.isEmpty()) {
            int idxLeft = leftStack.pop();
            if (starStack.isEmpty()) {
                return false;
            }
            int idxStar = starStack.pop();
            // 如果*出现的位置比left靠前，则没办法组成有效括号
            if (idxStar < idxLeft) {
                return false;
            }
        }
        return true;
    }
}
