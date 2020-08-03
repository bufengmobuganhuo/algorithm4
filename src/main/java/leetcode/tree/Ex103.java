package leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/20 8:53 上午
 * TODO
 */
public class Ex103 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        Ex103 ex103=new Ex103();
        ex103.zigzagLevelOrder(root);
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root==null){
            return new ArrayList<>(1);
        }
        Deque<TreeNode> deque=new LinkedList<>();
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> layer1=new ArrayList<>(1);
        layer1.add(root.val);
        res.add(layer1);
        deque.offerFirst(root);
        int layer=1;
        while(!deque.isEmpty()){
            if (layer%2==0){
                deque.offerFirst(null);
                List<Integer> layerList=new ArrayList<>();
                while(deque.peekLast()!=null){
                    TreeNode father=deque.pollLast();
                    if (father.left!=null){
                        layerList.add(father.left.val);
                        deque.offerFirst(father.left);
                    }
                    if (father.right!=null){
                        layerList.add(father.right.val);
                        deque.offerFirst(father.right);
                    }
                }
                if (!layerList.isEmpty()){
                    res.add(layerList);
                }
                deque.pollLast();
            }else {
                deque.offerLast(null);
                List<Integer> layserList=new ArrayList<>();
                while(deque.peekFirst()!=null){
                    TreeNode father=deque.pollFirst();
                    if (father.right!=null){
                        layserList.add(father.right.val);
                        deque.offerLast(father.right);
                    }
                    if (father.left!=null){
                        layserList.add(father.left.val);
                        deque.offerLast(father.left);
                    }
                }
                if (!layserList.isEmpty()){
                    res.add(layserList);
                }
                deque.pollFirst();
            }
            layer++;
        }
        return res;
    }

      public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
