package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/3/9 上午10:06
 * TODO
 */
public class Ex77_2 {
    private List<List<Integer>> ans;

    public List<List<Integer>> combine(int n, int k){
        ans = new ArrayList<>();
        backtracking(n,k,1,new LinkedList<>());
        return ans;
    }

    private void backtracking(int n, int k, int index, LinkedList<Integer> track){
        if (track.size()==k){
            List<Integer> tmp = new ArrayList<>(track);
            ans.add(tmp);
            return;
        }
        for (int i = index; i < n + 1; i++) {
            track.offerLast(i);
            backtracking(n,k,i+1,track);
            track.pollLast();
        }
    }
}
