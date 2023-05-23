package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/6/12 11:16
 * 练习5.3.9
 */
public class EX_5_3_9 {
    public static void main(String[] args) {
        String txt="ABRABCADABRABC";
        String pattern="ABC";
        EX_5_3_9 ex_5_3_9=new EX_5_3_9(pattern);
        List<Integer> res=ex_5_3_9.searchAll(txt);
        System.out.println(Arrays.toString(res.toArray()));
        System.out.println(ex_5_3_9.count(txt));
    }
    private Map<Character,Integer> right;
    private String pattern;

    public EX_5_3_9(String pattern) {
        this.pattern = pattern;
        right=new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i),i);
        }
    }

    public int search(String target){
        int skipStep=0;
        for (int i = 0; i <= target.length(); i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (pattern.charAt(j)!=target.charAt(j+i)){
                    skipStep=j-right.getOrDefault(target.charAt(j+i),-1);
                    skipStep=skipStep<1?1:skipStep;
                    break;
                }
            }
            if (skipStep==0){
                return i;
            }
        }
        return target.length();
    }

    public List<Integer> searchAll(String target){
        List<Integer> res=new ArrayList<>();
        int skipStep=0;
        for (int i = 0; i <= target.length()-pattern.length()+1; i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (target.charAt(i+j)!=pattern.charAt(j)){
                    skipStep=j-right.getOrDefault(target.charAt(i+j),-1);
                    skipStep=skipStep<1?1:skipStep;
                    break;
                }
            }
            if (skipStep==0){
                res.add(i);
                skipStep=1;
            }
        }
        return res;
    }

    public int count(String target){
        int skipStep=0;
        int count=0;
        for (int i = 0; i <= target.length()-pattern.length()+1; i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (pattern.charAt(j)!=target.charAt(i+j)){
                    skipStep=j-right.getOrDefault(target.charAt(i+j),-1);
                    skipStep=skipStep<1?1:skipStep;
                    break;
                }
            }
            if(skipStep==0){
                count++;
                skipStep=1;
            }
        }
        return count;
    }
}
