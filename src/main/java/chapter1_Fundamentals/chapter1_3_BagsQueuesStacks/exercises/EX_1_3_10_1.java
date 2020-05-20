package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.time.temporal.ChronoField;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author zhangyu
 * 2020/5/11 14:51
 * 练习1.3.10：第一次尝试
 */
public class EX_1_3_10_1 {
    public static void main(String[] args) {
        String exp="2*3/(2-1)+3*(4-1)";
        infixToPrefix(exp);
        infixToPostfix(exp);
    }
    /**
     * @param exp 将中序表达式转化为前序表达式
     * 例：2*3/(2-1)+3*(4-1)   ->   +/*23-21*3-41
     * 1.前序表达式计算方法：
     *   （1）从右到左开始扫描，如果遇到操作数，则入栈，
     *   （2）如果遇到操作符，则从栈中取出相应个数操作数操作，并将结果入栈
     * 2.转换方法
     *   （1）将中序表达式翻转：)1-4(*3+)1-2(/3*2
     *   （2）从左到右遍历：
     *        ①如果遇到“）”，入栈：因为它是中序表达式的右括号，而栈是先入后出，则直接入栈
     *        ②如果遇到操作数，直接输出
     *        ③如果遇到“（”，则一直出栈，直到遇到“）”：“（”和“）”正好组成一个完整的括号，
     *            要把里面的东西输出
     *        ④如果遇到操作符，且优先级>=栈顶元素，直接入栈
     *          如果<栈顶元素，则一直出栈，直到满足上述4个条件后入栈：
     *            前序就是按照操作符越靠右，越先开始计算，那么优先级低的就要放到优先级高的操作符左边
     *            因为后面会再反转
     *    （3）将栈中剩余元素输出（除去括号）
     *    （4）将结果再次反转
     */
    public static void infixToPrefix(String exp){
        Stack<Character> stack=new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        //从右开始遍历，就是反转
        for (int i=exp.length()-1;i>=0;i--){
            char chr=exp.charAt(i);
            if (stack.isEmpty()){
                stack.push(chr);
            }else if (')'==chr){
                stack.push(chr);
            }else if(Character.isDigit(chr)){
                stringBuilder.append(exp.charAt(i));
            }else if('('==chr){
                while (!stack.isEmpty()&&stack.peek()!=')'){
                    stringBuilder.append(stack.pop());
                }
            }else if(isHigherOrEqual(chr,stack.peek())){
                stack.push(chr);
            }else{
                while (!isHigherOrEqual(chr,stack.peek())&&!stack.isEmpty()){
                    stringBuilder.append(stack.pop());
                }
                stack.push(chr);
            }
        }
        while (!stack.isEmpty()){
            char chr=stack.pop();
            if (chr!=')'&&chr!='('){
                stringBuilder.append(chr);
            }
        }
        System.out.println(stringBuilder.reverse());
    }

    /**
     * @param chr1
     * @param chr2
     * @return 操作符chr1的优先级是否大于chr2
     */
    private static boolean isHigherOrEqual(Character chr1,Character chr2){
        if (chr1=='/'||chr1=='*'){
            return true;
        }else if(chr2=='/'||chr2=='*'){
            return false;
        }
        return true;
    }

    /**
     * @param exp 将中序表达式转化为后序表达式：
     * 例：2*3/(2-1)+3*(4-1)   ->   23*21-/341-*+
     * 1.后序表达式计算与前序表达式计算相同，只是扫描方向不同
     * 2.转换方法
     *  （1）从左到右扫描：
     *       ①如果遇到操作数，输出
     *       ②如果遇到“（”，入栈
     *       ③如果遇到“）”，一直出栈，直到遇到“（”为止
     *       ④如果是操作符：
     *            如果操作符优先级<栈顶操作符，则一直出栈，直到遇到“（”，或者栈为空，然后将操作符入栈
     *  （2）将栈中剩余元素输出
     */
    public static void infixToPostfix(java.lang.String exp){
        Stack<Character> options=new Stack<>();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<exp.length();i++){
            char chr=exp.charAt(i);
            if (Character.isDigit(chr)){
                stringBuilder.append(chr);
            }else if('('==chr){
                options.push(chr);
            }else if(')'==chr){
                char popChr=options.pop();
                while (!options.isEmpty()&&'('!=popChr){
                    stringBuilder.append(popChr);
                    popChr=options.pop();
                }
            }else{
                while (!options.isEmpty()&&isHigherOrEqual(options.peek(),chr)&&'('!=options.peek()){
                    stringBuilder.append(options.pop());
                }
                options.push(chr);
            }
        }
        while (!options.isEmpty()){
            char chr=options.pop();
            if (chr!=')'&&chr!='('){
                stringBuilder.append(chr);
            }
        }
        System.out.println(stringBuilder);
    }
}
