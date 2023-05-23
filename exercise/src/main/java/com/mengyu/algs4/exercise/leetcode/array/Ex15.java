package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/14 11:04 上午
 * leetcode15：三数之和
 */
public class Ex15 {
    public static void main(String[] args) {
        int[] nums={-1, 0, 1, 2, -1, -4};
        Ex15 ex15=new Ex15();
        ex15.threeSum(nums);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums==null||nums.length==0){
            return new ArrayList<>(1);
        }
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果和前面的一样，则会造成重复，过滤
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            // 数组从小到大排序，如果目前最小的元素和>target，则说明后面的也无法组成target
            if (nums[i]+nums[i+1]+nums[i+2]>0){
                break;
            }
            // 使用当前的nums[i]和数组最后两个元素的和是当前最大的，如果<target，说明使用nums[i]无法组成target
            if (nums[i]+nums[nums.length-2]+nums[nums.length-1]<0){
                continue;
            }
            int leftPtr=i+1;
            int rightPtr=nums.length-1;
            int target=-nums[i];
            while(leftPtr<rightPtr){
                // 如果和之前遍历过的一样，则会造成重复，过滤
                while (leftPtr>i+1&&leftPtr<=rightPtr&&nums[leftPtr]==nums[leftPtr-1]){
                    leftPtr++;
                }
                // 如果和之前遍历过的一样，则会造成重复，过滤
                while (rightPtr<nums.length-1&&leftPtr<=rightPtr&&nums[rightPtr]==nums[rightPtr+1]){
                    rightPtr--;
                }
                if (leftPtr>=rightPtr){
                    break;
                }
                int sum=nums[leftPtr]+nums[rightPtr];
                if (sum==target){
                    List<Integer> res1=new ArrayList<>(3);
                    res1.add(nums[i]);
                    res1.add(nums[leftPtr++]);
                    res1.add(nums[rightPtr--]);
                    res.add(res1);
                }else if(sum>target){
                    rightPtr--;
                }else{
                    leftPtr++;
                }
            }
        }
        return res;
    }
}
