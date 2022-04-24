package leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1008 {
    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        new Ex1008().bstFromPreorder2(preorder);
    }
    private int[] preorder;

    private int idx;

    private Map<Integer, Integer> inOrderMap;

    /**
     * 前序遍历的规律是：父节点，左子树(左子树又可以变为：父节点，左子树，右子树)，右子树(右子树可以变为：父节点，左子树，右子树)
     * 所以在从左到右遍历过程中，遇到的第一个节点肯定是父节点
     * 但是有些节点是没有左/右子树的，那么可以利用二叉搜索树的性质：
     * 已知父节点，对于左子树来说，他的上限可以确定；对于右子树来说，他的下限可以确定。那么如果不满足上下限，就说明这个节点没有左/右子树
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        this.preorder = preorder;
        this.idx = 0;
        return build(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int lowerBound, int upperBound) {
        if (idx >= preorder.length) {
            return null;
        }
        int val = preorder[idx];
        if (val > upperBound || val < lowerBound) {
            return null;
        }
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = build(lowerBound, root.val);
        root.right = build(root.val, upperBound);
        return root;
    }

    /**
     * 使用前序遍历+中序遍历构造
     * 二叉搜索树的中序遍历是有序的
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        inOrderMap = new HashMap<>();
        this.preorder = preorder;
        int len = preorder.length;
        int[] inorder = new int[len];
        System.arraycopy(preorder, 0, inorder, 0, len);
        // 二叉搜索树的中序遍历是有序的
        Arrays.sort(inorder);
        for (int i = 0; i < len; i++) {
            inOrderMap.put(inorder[i], i);
        }
        return build(0, len - 1, 0, len - 1);
    }

    /**
     *
     * @param preLeft 前序遍历的左边界
     * @param preRight 前序遍历的右边界
     * @param inLeft 中序遍历的左边界
     * @param inRight 中序遍历的右边界
     * @return
     */
    private TreeNode build(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        // 前序遍历的第一个元素是父节点
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 父节点在中序遍历中的位置
        int fatherIdxInOrder = inOrderMap.get(root.val);
        // 构建左子树
        // preLeft + 1: 前序遍历左边界的下一个元素是左子树的左边界
        // (preLeft) + (fatherIdxInOrder - inLeft)：(前序遍历的左边界)+(左子树的长度) = 中序遍历左子树的右边界
        // inLeft: 中序遍历的左子树的左边界不变
        // fatherIdxInOrder - 1：中序遍历中左子树的右边界是父节点的左边一个元素
        root.left = build(preLeft + 1, preLeft + fatherIdxInOrder - inLeft, inLeft, fatherIdxInOrder - 1);
        // preLeft + fatherIdxInOrder - inLeft + 1: 前序遍历中，右子树的左边界是左子树右边界+1
        // preRight：前序遍历中，右子树的右边界不变
        // fatherIdxInOrder + 1：中序遍历中，父节点的右边一个元素是右子树的左边界
        // inRight：中序遍历中，右边界不变
        root.right = build(preLeft + fatherIdxInOrder - inLeft + 1, preRight, fatherIdxInOrder + 1, inRight);
        return root;
    }
}
