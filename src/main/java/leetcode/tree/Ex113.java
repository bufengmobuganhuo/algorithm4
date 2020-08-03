package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/21 9:17 上午
 * TODO
 */
public class Ex113 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);
        root.left.left=new TreeNode(11);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);
        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(1);
        Ex113 ex113=new Ex113();
        ex113.pathSum(root,22);
    }
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root==null){
            new ArrayList<List<Integer>>(1);
        }
        res=new ArrayList<>();
        dfs(root,sum,0,new ArrayList<>());
        return res;
    }

    private void dfs(TreeNode root,int target,int source,List<Integer> list){
        if (root==null){
            return;
        }
        list.add(root.val);
        if (root.left==null&&root.right==null&&target==root.val+source){
            List<Integer> integers=new ArrayList<>(list.size());
            integers.addAll(list);
            res.add(integers);
        }
        dfs(root.left,target,source+root.val,list);
        dfs(root.right,target,source+root.val,list);
        list.remove(list.size()-1);
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
