package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/8/21 8:47 上午
 * TODO
 */
public class Ex22 {
    public static void main(String[] args) {
        Ex22 ex22=new Ex22();
        System.out.println(Arrays.toString(ex22.generateParenthesis(3).toArray()));
    }
    private List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans=new ArrayList<>();
        StringBuilder sb=new StringBuilder("(");
        backtracking(n,1,0,sb);
        return ans;
    }

    private void backtracking(int n, int left, int right, StringBuilder sb){
        if (sb.length()==2*n){
            ans.add(sb.toString());
            return;
        }
        // 做选择
        if (left==right){
            sb.append("(");
            backtracking(n,left+1,right,sb);
            sb.deleteCharAt(sb.length()-1);
        }else if (left==n){
            sb.append(")");
            backtracking(n,left,right+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }else{
            sb.append("(");
            backtracking(n,left+1,right,sb);
            sb.deleteCharAt(sb.length()-1);
            sb.append(")");
            backtracking(n,left,right+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
