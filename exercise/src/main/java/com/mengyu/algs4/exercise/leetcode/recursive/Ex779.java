package com.mengyu.algs4.exercise.leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/7/7 9:17 上午
 * leetcode779
 */
public class Ex779 {
    public static void main(String[] args) {
        Ex779 ex779=new Ex779();
        System.out.println(ex779.kthGrammar(200,2));
    }
    public int kthGrammar(int N, int K){
        String res=recursive("0",1,N,K);
        return Integer.parseInt(res);
    }

    private String recursive(String str,int line,int N,int K){
        if (line==N){
            return String.valueOf(str.charAt(K-1));
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String appender=str.charAt(i)=='0'?"01":"10";
            stringBuilder.append(appender);
        }
        return recursive(stringBuilder.toString(),line+1,N,K);
    }
}
