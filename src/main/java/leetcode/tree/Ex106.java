package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/7/20 10:25 上午
 * leetcode106
 */
public class Ex106 {
    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        Ex106 ex106 = new Ex106();
        TreeNode root = ex106.buildTree(inorder, postorder);
        root.right = null;
    }

    private int postIdx;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        // 中序遍历中，元素->索引
        map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postorder.length - 1);
    }

    /**
     * @param postorder
     * @param start     在中序遍历中的索引
     * @param end
     * @return
     */
    private TreeNode buildTree(int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        // 后续遍历，从右边开始往左走，每个结点都是根结点：[左子树，右子树，根结点]
        TreeNode root = new TreeNode(postorder[postIdx]);
        int rootIdx = map.get(root.val);
        postIdx--;
        root.right = buildTree(postorder, rootIdx + 1, end);
        root.left = buildTree(postorder, start, rootIdx - 1);
        return root;
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
