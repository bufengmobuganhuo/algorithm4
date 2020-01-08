package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * 将算术表达式由中序表达式变为后序表达式
 * 关键是对括号的处理：
 * 1.如果是操作数，直接输出
 * 2.如果是运算符：
 *  （1）如果是“（”，直接入栈
 *  （2）如果是“）”，出栈，直到遇到“（”
 *  （3）如果是“+ - * /”
 *      （a）循环，如果（栈非空&&栈顶操作符优先级>=待输入操作符&&栈顶不是“（”）：出栈（“（”也需要出栈）
 *      （b）将操作符入栈
 * 3.若栈非空，则依次出栈
 */
public class EX_1_3_10_InfixToPostfix {
    private static Stack<String> options=new Stack<String>();
    public static void main(String[] args) {
        while(!StdIn.isEmpty()){
            String exp=StdIn.readString();
            String res=transform(exp);
            StdOut.println(res);
        }
    }
    public static String transform(java.lang.String exp){
        StringBuilder res=new StringBuilder(exp.length());
        for (int i=0;i<exp.length();i++){
            StringBuilder str=new StringBuilder();
            str.append(exp.charAt(i));
            //如果遇到的是数字，则取出
            while (Character.isDigit(str.charAt(0))&&i+1<exp.length()&&Character.isDigit(exp.charAt(i+1))){
                i++;
                str.append(exp.charAt(i));
            }
            if (Character.isDigit(str.charAt(0))){
                res.append(str);
            }else if("(".equals(str.toString())){
                options.push(str.toString());
            }else if(")".equals(str.toString())){
                //需要将"("也出栈
                String pop=options.pop();
                while (!options.isEmpty()&&!"(".equals(pop)){
                    res.append(pop);
                    pop=options.pop();
                }
            }else{
                while (!options.isEmpty()&&!isHigherOperator(str.toString())&&!"(".equals(options.peek())){
                    res.append(options.pop());
                }
                options.push(str.toString());
            }
        }
        while (!options.isEmpty()){
            res.append(options.pop());
        }
        return res.toString();
    }
    //判断option的优先级是否高于栈顶运算符
    private static boolean isHigherOperator(String option){
        if (options.isEmpty()){
            return true;
        }
        return  (("+".equals(options.peek())||"-".equals(options.peek()))&&("/".equals(option)||"*".equals(option)));
    }
}
