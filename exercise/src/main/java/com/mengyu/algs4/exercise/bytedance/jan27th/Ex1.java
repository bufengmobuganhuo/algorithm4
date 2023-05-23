package com.mengyu.algs4.exercise.bytedance.jan27th;

/**
 * @author yuzhang
 * @date 2021/1/27 上午8:46
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        String equation = "x=2x-x+3+2-1+2+3+33+67-x";
        System.out.println(ex1.solveEquation(equation));
    }
    public String solveEquation(String equation) {
        if (equation == null || equation.length() == 0) {
            return "";
        }
        String[] exps = equation.split("=");
        int[] left = evaluate(exps[0]);
        int[] right = evaluate(exps[1]);
        int factor = left[0]-right[0];
        int constant = right[1]-left[1];
        if (factor==0&&constant==0){
            return "Infinite solutions";
        }else if (factor==0){
            return "No solution";
        }
        return "x="+ constant / factor;
    }

    private int[] evaluate(String equation) {
        int factor = 0, constant = 0;
        StringBuilder tmp = new StringBuilder(equation);
        for (int i = 0; i < tmp.length(); i++) {
            char chr = tmp.charAt(i);
            if (chr == '-') {
                tmp.insert(i, '+');
                i++;
            }
        }
        String[] nums = tmp.toString().split("\\+");
        for (String exp : nums) {
            if (exp.length()==0){
                continue;
            }
            char chr = exp.charAt(exp.length() - 1);
            if (chr == 'x') {
                if (exp.length()==2&&exp.startsWith("-")){
                    factor += -1;
                }else if (exp.length()>1){
                    factor += Integer.parseInt(exp.substring(0,exp.length()-1));
                }else {
                    factor += 1;
                }
            } else {
                constant += Integer.parseInt(exp);
            }
        }
        return new int[]{factor, constant};
    }
}
