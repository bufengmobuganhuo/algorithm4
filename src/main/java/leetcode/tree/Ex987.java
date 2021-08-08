package leetcode.tree;

import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2021/7/31 上午10:50
 * TODO
 */
public class Ex987 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);

        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        //root.right.right = new TreeNode(7);
        Ex987 ex987 = new Ex987();
        System.out.println(ex987.verticalTraversal(root));
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<TreeNodeInfo> que = new LinkedList<>();

        que.offer(new TreeNodeInfo(root, 0, 0));
        List<TreeNodeInfo> colList = new ArrayList<>();
        colList.add(que.peek());
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNodeInfo node = que.poll();
                if (node.left != null) {
                    TreeNodeInfo left = new TreeNodeInfo(node.left, node.row + 1, node.col - 1);
                    colList.add(left);
                    que.offer(left);
                }
                if (node.right != null) {
                    TreeNodeInfo right = new TreeNodeInfo(node.right, node.row + 1, node.col + 1);
                    colList.add(right);
                    que.offer(right);
                }
            }
        }
        Collections.sort(colList);
        int lastCol = -1000;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < colList.size(); i++) {
            TreeNodeInfo node = colList.get(i);
            if (node.col != lastCol) {
                ans.add(new ArrayList<>());
                lastCol = node.col;
            }
            ans.get(ans.size() - 1).add(node.val);
        }
        return ans;
    }

    static class TreeNodeInfo extends TreeNode implements Comparable<TreeNodeInfo> {
        int row;
        int col;
        TreeNode left;
        TreeNode right;

        public TreeNodeInfo(TreeNode node, int row, int col) {
            this.row = row;
            this.col = col;
            this.val = node.val;
            this.left = node.left;
            this.right = node.right;
        }

        @Override
        public int compareTo(TreeNodeInfo o) {
            if (this.col != o.col) {
                return this.col - o.col;
            } else if (this.row != o.row) {
                return this.row - o.row;
            }
            return this.val - o.val;
        }
    }
}
