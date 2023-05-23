package com.mengyu.algs4.exercise.leetcode.string;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/20 8:48 上午
 * TODO
 */
public class Ex890 {
    public static void main(String[] args) {
        String[] words={"aba","mee"};
        Ex890 ex890=new Ex890();
        System.out.println(Arrays.asList(ex890.findAndReplacePattern(words,"abb").toArray()));
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
           if (match(word,pattern)){
               ans.add(word);
           }
        }
        return ans;
    }

    private boolean match(String target, String pattern){
        Map<Character,Character> targetToPatternMap=new HashMap<>();
        boolean[] marked=new boolean[26];
        for (int i = 0; i < target.length(); i++) {
            Character value=targetToPatternMap.get(target.charAt(i));
            if (value!=null){
                if (value!=pattern.charAt(i)){
                    return false;
                }
            }else if (marked[pattern.charAt(i)-'a']){
                return false;
            }else{
                targetToPatternMap.put(target.charAt(i),pattern.charAt(i));
                marked[pattern.charAt(i)-'a']=true;
            }
        }
        return true;
    }
}
