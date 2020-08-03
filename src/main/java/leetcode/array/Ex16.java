package leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/7/15 3:53 下午
 * leetcode16
 */
public class Ex16 {
    public static void main(String[] args) {
        int[] nums={-1,2,1,-4};
        Ex16 ex16=new Ex16();
        System.out.println(ex16.threeSumClosest(nums,1));
    }
    public int threeSumClosest(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return -1;
        }
        Arrays.sort(nums);
        Long res= (long) (target + Integer.MAX_VALUE);
        for (int i = 0; i < nums.length - 2; i++) {
            int sum=0;
            int leftPtr=i+1,rightPtr=nums.length-1;
            while(leftPtr<rightPtr){
                sum=nums[i]+nums[leftPtr]+nums[rightPtr];
                if (sum==target){
                    return sum;
                }else if(sum>target){
                    rightPtr--;
                }else{
                    leftPtr++;
                }
                res=Math.abs(sum-target)>Math.abs(res-target)?res:sum;
            }
        }
        return Integer.parseInt(res.toString());
    }
}
