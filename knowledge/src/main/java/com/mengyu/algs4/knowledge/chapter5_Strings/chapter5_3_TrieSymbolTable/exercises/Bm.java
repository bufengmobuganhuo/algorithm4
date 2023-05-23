package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/8/18 11:36 上午
 * TODO
 */
public class Bm {
    public static void main(String[] args) {
        String target="ababababca";
        String pattern="abababca";
        Bm boyerMoore=new Bm(pattern);
        int start=boyerMoore.search(target);
        if (start<target.length()){
            System.out.println(target.substring(start,start+pattern.length()));
        }
    }
    private Map<Character,Integer> right;
    private String pattern;

    public Bm(String pattern) {
        this.pattern = pattern;
        right=new HashMap<>();
        // 构建right，记录pattern中每个字符的最右边位置
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i),i);
        }
    }

    public int search(String target){
        int skipStep;
        for (int i = 0; i <= target.length() - pattern.length(); i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length()-1; j >= 0; j--) {
                if (target.charAt(i+j)!=pattern.charAt(j)){
                    skipStep=j-right.getOrDefault(target.charAt(i+j),-1);
                    skipStep= Math.max(skipStep, 1);
                    break;
                }
            }
            if (skipStep==0){
                return i;
            }
        }
        return target.length();
    }
}
