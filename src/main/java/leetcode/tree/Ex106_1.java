package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex106_1 {
    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        Ex106_1 ex106_1 = new Ex106_1();
        TreeNode root = ex106_1.buildTree(inorder, postorder);
        System.out.println(root);
    }
    private Map<Integer, Integer> map;

    private int postIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        postIdx = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode father = new TreeNode(postorder[postIdx--]);
        int inorderIdx = map.get(father.val);
        father.right = buildTree(postorder, inorderIdx + 1, end);
        father.left = buildTree(postorder, start, inorderIdx - 1);
        return father;
    }
}
