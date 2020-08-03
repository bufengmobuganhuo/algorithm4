package leetcode.backtracking;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/28 8:40 上午
 * TODO
 */
public class Ex47 {
    public static void main(String[] args) {
        int[] nums={1,1,2};
        Ex47 ex47=new Ex47();
        ex47.permuteUnique(nums);
        for (List<Integer> list :
                ex47.ans) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    private List<List<Integer>> ans;
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums==null||nums.length==0){
            return new ArrayList<>();
        }
        ans=new ArrayList<>();
        backtracking(nums,new LinkedList<>());
        return ans;
    }

    private void backtracking(int[] nums, LinkedList<NumInformation> track){
        if (track.size()==nums.length){
            List<Integer> permuteList=new ArrayList<>(track.size());
            track.forEach(x -> permuteList.add(x.val));
            ans.add(permuteList);
            return;
        }
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            NumInformation numInformation=new NumInformation(nums[i],i);
            if (track.contains(numInformation)||set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            track.offer(numInformation);
            backtracking(nums,track);
            track.removeLast();
        }
    }

    static class NumInformation{
        int val;
        int idx;

        public NumInformation(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof NumInformation && ((NumInformation) obj).idx==this.idx&&((NumInformation) obj).val==this.val;
        }
    }
}
