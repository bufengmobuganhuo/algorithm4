package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/10/30 10:39 上午
 * TODO
 */
public class Ex_2_5_2_1 {
    public static void main(String[] args) {
        Ex_2_5_2_1 ex_2_5_2_1 = new Ex_2_5_2_1();
        String[] words={"afterthought","after","thought","after","other","thought","others","s"};
        System.out.println(solution(words));
    }
    public static List<String[]> solution(String[] words){
        if (words==null||words.length==0){
            return new ArrayList<>();
        }
        List<String[]> ans = new ArrayList<>();
        sort(words);
        // 找到可能的组合词的索引
        int mayCompoundIdx=0;
        while (mayCompoundIdx<words.length&&words[mayCompoundIdx].length()<words[0].length()*2){
            mayCompoundIdx++;
        }
        // 所有可能的组合词的索引
        for (int i = mayCompoundIdx; i < words.length; i++) {
            // 转化为2-sum问题
            int leftPtr=0,rightPtr=i-1,targetSum=words[i].length();
            while (leftPtr<rightPtr){
                int sum = words[leftPtr].length()+words[rightPtr].length();
                if (sum==targetSum){
                    String compound1 = words[leftPtr]+words[rightPtr];
                    String compound2 = words[rightPtr]+words[leftPtr];
                    if (words[i].equals(compound1)||words[i].equals(compound2)){
                        String[] res = new String[2];
                        res[0]=words[leftPtr];
                        res[1]=words[rightPtr];
                        ans.add(res);
                    }
                    leftPtr++;
                    rightPtr--;
                }else if (sum>targetSum){
                    rightPtr--;
                }else{
                    leftPtr++;
                }
            }
        }
        return ans;
    }

    private static void sort(String[] words) {
        for (int i = 1; i < words.length; i++) {
            for (int j = i; j > 0 && words[j].length() < words[j-1].length(); j--) {
                exchange(words, j, j - 1);
            }
        }
    }

    private static void exchange(String[] words, int i, int j) {
        String tmp = words[i];
        words[i] = words[j];
        words[j] = tmp;
    }
}
