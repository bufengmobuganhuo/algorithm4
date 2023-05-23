package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/17 10:56 上午
 * leetcode228
 */
public class Ex228 {
    public static void main(String[] args) {
        int[] nums={0,1,2,4,5,7};
        Ex228 ex228=new Ex228();
        List<String> list= ex228.summaryRanges(nums);
        System.out.println(Arrays.toString(list.toArray()));
    }
    public List<String> summaryRanges(int[] nums) {
        if (nums==null||nums.length==0){
            return new ArrayList<>();
        }
        List<String> res=new ArrayList<>();
        int start=nums[0];
        int startIdx=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]!=start+i-startIdx){
                String range=i-startIdx>1?start+"->"+nums[i-1]:String.valueOf(start);
                res.add(range);
                start=nums[i];
                startIdx=i;
            }
        }
        String range=nums.length-1-startIdx>=1?start+"->"+nums[nums.length-1]:String.valueOf(start);
        res.add(range);
        return res;
    }
}
