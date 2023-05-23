package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/6/12 17:15
 * TODO
 */
public class EX_5_3_14_BM {
    public static void main(String[] args) {
        String txt="ababababca";
        String pattern="abababcad";
        EX_5_3_14_BM bm=new EX_5_3_14_BM(pattern.toCharArray());
        System.out.println(bm.search(txt.toCharArray()));
    }
    private Map<Character,Integer> right;
    private char[] pattern;

    public EX_5_3_14_BM(char[] pattern) {
        this.pattern = pattern;
        this.right=new HashMap<>();
        for (int i = 0; i < pattern.length; i++) {
            right.put(pattern[i],i);
        }
    }

    public int search(char[] txt){
        int skipStep=0;
        for (int i = 0; i <= txt.length-pattern.length; i+=skipStep) {
            skipStep=0;
            for (int j = pattern.length-1; j >= 0; j--) {
               if (txt[i+j]!=pattern[j]){
                   skipStep=j-right.getOrDefault(txt[i+j],-1);
                   skipStep=skipStep<1?1:skipStep;
                   break;
               }
            }
            if (skipStep==0){
                return i;
            }
        }
        return txt.length;
    }

}
