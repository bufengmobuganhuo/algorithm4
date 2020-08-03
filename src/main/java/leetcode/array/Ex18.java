package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/14 3:17 下午
 * leetcode:18
 */
public class Ex18 {
    public static void main(String[] args) {

    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return new ArrayList<>(1);
        }
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        // 使用四个指针:i,k,h,j
        for (int i = 0; i < nums.length - 3; i++) {
            // 去除重复值
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            // 数组从小到大排序，如果当前能组成的最小和>target，则后面的肯定不行
            if (nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target){
                break;
            }
            // 如果当前能组成的最大和<target，则后面的也不行
            if (nums[i]+nums[nums.length-3]+nums[nums.length-2]+nums[nums.length-1]<target){
                continue;
            }
            for (int k = i+1; k < nums.length-2; k++) {
                // 去除重复值
                if (k>i+1&&nums[k]==nums[k-1]){
                    continue;
                }
                // 如果当前能组成的最小和>target，则后面的也不行
                if (nums[i]+nums[k]+nums[k+1]+nums[i+2]>target){
                    break;
                }
                // 如果当前能组成的最大和<target，则后面的也不行
                if (nums[i]+nums[k]+nums[nums.length-2]+nums[nums.length-1]<target){
                    continue;
                }
                int h=k+1;
                int j=nums.length-1;
                while(h<j){
                    // 去除重复值
                    while(h>k+1&&h<=j&&nums[h]==nums[h-1]){
                        h++;
                    }
                    while(j<nums.length-1&&h<=j&&nums[j]==nums[j+1]){
                        j--;
                    }
                    if (h>=j){
                        break;
                    }
                    int sum=nums[i]+nums[k]+nums[h]+nums[j];
                    if (sum==target){
                        List<Integer> res1=new ArrayList<>(4);
                        res1.add(nums[i]);
                        res1.add(nums[k]);
                        res1.add(nums[h]);
                        res1.add(nums[j]);
                        res.add(res1);
                        h++;
                        j--;
                    }else if (sum<target){
                        h++;
                    }else{
                        j--;
                    }
                }
            }
        }
        return res;
    }
}
