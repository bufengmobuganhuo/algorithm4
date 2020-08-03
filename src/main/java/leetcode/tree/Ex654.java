package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/26 3:38 下午
 * TODO
 */
public class Ex654 {
    public static void main(String[] args) {
        int[] nums={3,2,1,6,0,5};
        Ex654 ex654=new Ex654();
        ex654.constructMaximumBinaryTree(nums);
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums==null||nums.length==0){
            return null;
        }
        return buildTree(nums,0,nums.length-1);
    }

    private TreeNode buildTree(int[] nums,int start,int end){
        if (start>end){
            return null;
        }
        int maxIdx=findMax(nums,start,end);
        TreeNode root=new TreeNode(nums[maxIdx]);
        root.left=buildTree(nums,start,maxIdx-1);
        root.right=buildTree(nums,maxIdx+1,end);
        return root;
    }

    private int findMax(int[] nums,int start,int end){
        int tmp=Integer.MIN_VALUE;
        int tmpIdx=0;
        for (int i = start; i <= end; i++) {
            if (tmp<nums[i]){
                tmp=nums[i];
                tmpIdx=i;
            }
        }
        return tmpIdx;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
