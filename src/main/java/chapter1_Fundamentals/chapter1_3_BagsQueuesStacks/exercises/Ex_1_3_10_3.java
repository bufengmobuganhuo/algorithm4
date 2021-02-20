package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/1 上午8:51
 * TODO
 */
public class Ex_1_3_10_3 {
    public static void main(String[] args) {
        String exp = "2*3/(2-1)+3*(4-1)";
        infixToPrefix(exp);
        infixToPostfix(exp);
    }

    /**
     * 1. 中序变前序，前序表达式：操作符在前面的表达式
     * 2. 例：2*3/(2-1)+3*(4-1) -> +/*23-21*3-41
     * 3. 前序的计算方法：从右到左开始遍历，如果遇到操作数则入栈，
     * 如果遇到操作符，则从栈中取出相应个数的操作数计算，并将计算结果入栈
     * 4. 转换方法：从右到左遍历
     * （1）如果遇到"）"，入栈
     * （2）如果遇到操作数，直接输出
     * （3）如果遇到"（"，出栈，直到遇到"）'为止，这样括号内的就被处理完了
     * （4）如果遇到操作符，则看栈顶元素：
     * 若操作符优先级>=栈顶元素，则入栈
     * 否则一直出栈，直到满足上述条件为止
     * （前序就是按照操作符越靠右，越先开始计算，那么优先级低的就要放到优先级高的操作符左边）
     * 5. 遍历完后，如果栈不为空，则直接输出
     */
    public static void infixToPrefix(String exp) {
        if (exp == null || exp.length() == 0) {
            return;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char chr = exp.charAt(i);
            if (Character.isDigit(chr)) {
                res.append(chr);
            } else if (')' == chr) {
                stack.push(chr);
            } else if ('(' == chr) {
                char pop = stack.pop();
                while (!stack.isEmpty() && ')' != pop) {
                    res.append(pop);
                    pop = stack.pop();
                }
            } else {
                while (!stack.isEmpty() && !isHigherOrEqual(chr, stack.peek())) {
                    res.append(stack.pop());
                }
                stack.push(chr);
            }
        }
        while (!stack.isEmpty()) {
            char chr = stack.pop();
            if (')' != chr && '(' != chr) {
                res.append(chr);
            }
        }
        System.out.println(res.reverse().toString());
    }

    private static boolean isHigherOrEqual(char opt1, char opt2) {
        if (opt1 == '*' || opt1 == '/') {
            return true;
        } else if (opt2 == '*' || opt2 == '/') {
            return false;
        }
        return true;
    }

    /**
     * 1. 中序变后序：后序表达式：操作符在后面的表达式
     * 2. 例：2*3/(2-1)+3*(4-1) -> 23*21-/341-*+
     * 3. 后序表达式的计算方法：从左到右遍历，遇到操作数，入栈；遇到操作符，从栈中取出相应数量的操作数计算并入栈
     * 4. 转换方法，与中序转换方法一样，只是遍历顺序不同
     */
    public static void infixToPostfix(String exp) {
        if (exp == null || exp.length() == 0) {
            return;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char chr = exp.charAt(i);
            if (Character.isDigit(chr)) {
                res.append(chr);
            } else if ('(' == chr) {
                stack.push(chr);
            } else if (')' == chr) {
                char pop = stack.pop();
                while (!stack.isEmpty() && '(' != pop) {
                    res.append(pop);
                    pop = stack.pop();
                }
            } else {
                while (!stack.isEmpty() && isHigherOrEqual(stack.peek(), chr) && '(' != stack.peek()) {
                    res.append(stack.pop());
                }
                stack.push(chr);
            }
        }
        while (!stack.isEmpty()) {
            char chr = stack.pop();
            if (')' != chr && '(' != chr) {
                res.append(chr);
            }
        }
        System.out.println(res.toString());
    }
}
