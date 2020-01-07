package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.util.Stack;

/**
 * 习题1.3.4：判断有效括号
 * leetcode: https://leetcode-cn.com/problems/valid-parentheses/
 */
public class EX_1_3_4_Parentheses {
    public static void main(String[] args) {
        System.out.println(isValid("{()}[{}{}]())"));

    }
    public static boolean isValid(String s){
        Stack<Character> stack=new Stack<Character>();
        for (char chr:s.toCharArray()){
            if (chr=='{'||chr=='['||chr=='('){
                stack.push(chr);
            } else if (chr == '}'&&!stack.isEmpty()&&stack.peek()=='{') {
                stack.pop();
            }else if(chr==']'&&!stack.isEmpty()&&stack.peek()=='['){
                stack.pop();
            }else if(chr==')'&&!stack.isEmpty()&&stack.peek()=='('){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
