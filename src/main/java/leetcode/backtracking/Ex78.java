package leetcode.backtracking;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/31 8:54 上午
 * TODO
 */
public class Ex78 {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        Ex78 ex78=new Ex78();
        List<List<Integer>> ans = ex78.subsets(nums);
        for (List<Integer> list: ans){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    private List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        if (nums==null||nums.length==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            backtracking(nums,0,i,new LinkedList<>());
        }
        return ans;
    }

    private void backtracking(int[] nums,int first, int num, LinkedList<Integer> track){
        // 结束条件
        if (track.size()==num){
            List<Integer> trackList=new ArrayList<>(track);
            ans.add(trackList);
            return;
        }
        // 做选择
        for (int i = first; i < nums.length; i++) {
            track.offer(nums[i]);
            backtracking(nums,i+1,num,track);
            track.removeLast();
        }
    }

}
