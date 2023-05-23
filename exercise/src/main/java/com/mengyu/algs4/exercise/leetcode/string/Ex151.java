package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yuzhang
 * @date 2020/8/24 8:39 上午
 * TODO
 */
public class Ex151 {
    public static void main(String[] args) {
        String str="the  sky is blue";
        Ex151 ex151=new Ex151();
        System.out.println(ex151.reverseWords(str));
    }
    public String reverseWords(String s) {
        if (s==null||s.length()==0){
            return s;
        }
        String[] words=s.split(" ");
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = words.length-1; i >= 0; i--) {
            if ("".equals(words[i])){
                continue;
            }
            stringBuilder.append(words[i]);
            stringBuilder.append("_");
        }
        return  stringBuilder.toString().trim();
    }

}
