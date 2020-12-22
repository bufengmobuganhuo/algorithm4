package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/21 上午10:07
 * TODO
 */
public class Ex652_2 {
    public static void main(String[] args) {
        Ex652_2 ex652_2 = new Ex652_2();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);
        ex652_2.findDuplicateSubtrees(root);
    }

    private Map<Integer, Integer> countMap;
    private Map<String, Integer> idMap;
    private List<TreeNode> res;
    private int id;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        idMap = new HashMap<>();
        countMap = new HashMap<>();
        res = new ArrayList<>();
        lookup(root);
        return res;
    }

    private int lookup(TreeNode node) {
        if (node == null) {
            return -1;
        }
        String serialId = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = idMap.computeIfAbsent(serialId, x -> id++);
        countMap.put(uid, countMap.getOrDefault(uid, 0) + 1);
        if (countMap.get(uid) == 2) {
            res.add(node);
        }
        return uid;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
