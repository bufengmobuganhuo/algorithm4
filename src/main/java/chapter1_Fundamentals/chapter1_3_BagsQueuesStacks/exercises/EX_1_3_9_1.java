package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import com.sun.deploy.util.StringUtils;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/8 15:56
 * 练习1.3.9：第一次尝试
 */
public class EX_1_3_9_1 {
    public static void main(String[] args) {
        String exp="1+2)*3-4)*5-6)))";
        System.out.println(solution(exp));
    }
    public static String solution(String exp){
        Stack<String> optNumStack=new Stack<>();
        Stack<String> optStack=new Stack<>();
        if (exp==null||exp.length()==0){
            return null;
        }
        for (int i=0;i<exp.length();i++){
            StringBuilder sb=new StringBuilder();
            //把所有数字取出来
            while (Character.isDigit(exp.charAt(i))){
                sb.append(exp.charAt(i));
                i++;
            }
            //如果是操作符或)只取一个
            if (sb.toString().length()==0){
                sb.append(exp.charAt(i));
                //如果是操作数，则放入操作数栈
            }else{
                optNumStack.push(sb.toString());
                //上述判断操作数的时候，会多+一次
                i--;
            }
            //如果是)，则分别从两个栈中取出操作数和操作符，组成一个新的操作数
            if (")".equals(sb.toString())){
                String optNum1=optNumStack.pop();
                String optNum2=optNumStack.pop();
                String opt=optStack.pop();
                String value=String.format("(%s%s%s)",optNum2,opt,optNum1);
                optNumStack.push(value);
            }
            //如果是操作符，直接放入操作符栈
            if ("+".equals(sb.toString())||
            "-".equals(sb.toString())||
            "*".equals(sb.toString())||
            "/".equals(sb.toString())){
                optStack.push(sb.toString());
            }
        }
        return optNumStack.pop();
    }
}
