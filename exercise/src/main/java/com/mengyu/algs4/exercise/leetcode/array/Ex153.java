package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yuzhang
 * @date 2020/7/17 10:28 上午
 * leetcode153
 * 对于一个数组:[3,4,5,6,0,1,2]
 * 0 是一个关键结点，在这个结点右边的数都比数组第一个元素小，这个结点左边的元素都比第一个元素大
 */
public class Ex153 {
    public static void main(String[] args) {
        int[] nums={2,1};
        Ex153 ex153=new Ex153();
        ex153.findMin(nums);
    }
    public int findMin(int[] nums) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int left=0,right=nums.length-1,mid,leftVal,rightVal;
        while(left<=right){
            mid=left+(right-left)/2;
            leftVal=mid>0?nums[mid-1]:Integer.MAX_VALUE;
            rightVal=mid<nums.length-1?nums[mid+1]:Integer.MAX_VALUE;
            if (nums[mid]<leftVal&&nums[mid]<rightVal){
                return nums[mid];
            }else if (nums[mid]>=nums[0]){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return nums[0];
    }
}
