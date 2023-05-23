package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/28 10:59 上午
 * TODO
 */
public class Ex77 {
    public static void main(String[] args) {
        Ex77 ex77=new Ex77();
        ex77.combine(3,2);
        for (List<Integer> list :
                ex77.ans) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    private List<List<Integer>> ans;
    private int n;
    private int k;
    public List<List<Integer>> combine(int n, int k) {
        ans=new ArrayList<>();
        this.n=n;
        this.k=k;
        backtracking(1,new LinkedList<>());
        return ans;
    }

    /**
     *
     * @param track 回溯中的路径
     */
    private void backtracking(int first, LinkedList<Integer> track){
        if (track.size()==k){
            List<Integer> combineList=new ArrayList<>(track);
            ans.add(combineList);
            return;
        }
        for (int i = first; i <= n; i++) {
            track.offer(i);
            // 上一层选过的，这一层不再选
            backtracking(i+1,track);
            track.removeLast();
        }
    }
}
