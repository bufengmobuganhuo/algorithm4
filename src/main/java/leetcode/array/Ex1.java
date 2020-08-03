package leetcode.array;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/14 10:33 上午
 * leetcode1：两数之和
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums={-1,-2,-3,-4,-5};
        Ex1 ex1=new Ex1();
        ex1.twoSum(nums,-8);
    }
    public int[] twoSum(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return new int[2];
        }
        int[] res=new int[2];
        TreeMap<Integer, LinkedList<Integer>> map=new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> idxList = map.getOrDefault(nums[i],new LinkedList<>());
            idxList.offer(i);
            map.put(nums[i],idxList);
        }
        while(!map.isEmpty()){
            int sum=map.firstKey()+map.lastKey();
            if (sum==target){
                res[0]=map.firstEntry().getValue().removeFirst();
                res[1]=map.lastEntry().getValue().removeFirst();
                break;
            }else if(sum>target){
                map.pollLastEntry();
            }else{
                map.pollFirstEntry();
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return new int[2];
        }
        int[] res=new int[2];
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                res[0]=map.get(target-nums[i]);
                res[1]=i;
                break;
            }
            map.put(nums[i],i);
        }
        return res;
    }
}
