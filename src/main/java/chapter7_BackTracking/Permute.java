package chapter7_BackTracking;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/27 5:13 下午
 * 给定n个不重复的数，找出它们的全排列
 */
public class Permute {
    public static void main(String[] args) {
        Permute permute=new Permute();
        List<List<Integer>> res= permute.getPermute(new int[]{1,2,3});
        for (List<Integer> list : res) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    List<List<Integer>> ans;
    public List<List<Integer>> getPermute(int[] nums){
        if (nums==null||nums.length==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        backtracking(nums,new LinkedList<>());
        return ans;
    }

    /**
     *
     * @param nums 回溯中的选择列表
     * @param track 回溯中的路径
     */
    private void backtracking(int[] nums, LinkedList<Integer> track){
        // 结束条件：nums中的所有值都在track中
        if (nums.length==track.size()){
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 做选择：只有不在已选择路径内的元素才可以被选择
            if (track.contains(nums[i])){
                continue;
            }
            track.offer(nums[i]);
            // 进入下一层决策树
            backtracking(nums,track);
            // 撤销选择
            track.removeLast();
        }
    }
}
