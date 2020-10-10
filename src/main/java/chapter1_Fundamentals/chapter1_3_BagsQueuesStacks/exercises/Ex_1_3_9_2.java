package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/9/27 10:13 上午
 * TODO
 */
public class Ex_1_3_9_2 {
    public static void main(String[] args) {
        String exp = "13+2)*3-4)*54-6)))";
        System.out.println(solution(exp));
    }

    public static String solution(String exp) {
        if (exp == null || exp.length() == 0) {
            return exp;
        }
        Stack<String> numStack = new Stack<>();
        Stack<String> optStack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char chr = exp.charAt(i);
            if (isOpt(chr)) {
                optStack.push(String.valueOf(chr));
            } else if (Character.isDigit(chr)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (Character.isDigit(exp.charAt(i))) {
                    stringBuilder.append(exp.charAt(i++));
                }
                numStack.push(stringBuilder.toString());
                i--;
            } else {
                String opt = optStack.pop();
                String num1 = numStack.pop();
                String num2 = numStack.pop();
                numStack.push("(" + num2 + opt + num1 + ")");
            }
        }
        return numStack.pop();
    }

    private static boolean isOpt(char chr) {
        return chr == '+' || chr == '-' || chr == '*' || chr == '/';
    }
}
