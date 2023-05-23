package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/10/29 3:12 下午
 * TODO
 */
public class Ex662_1 {

    private Map<Integer, Integer> leftPosMap;

    private int res;

    public int widthOfBinaryTree(TreeNode root) {
        // dfs过程中，当前深度遇到的第一个节点，则是当前层的最左边节点
        leftPosMap = new HashMap<>();
        res = 0;
        dfs(root, 0, 0);
        return res;
    }

    /**
     *
     * @param node
     * @param depth 深度
     * @param pos 从0开始为结点编号，子结点.pos=父结点.pos*2 or 父结点.pos*2+1,则对于同一层，宽度=最右边.pos - 最左边.pos + 1
     */
    private void dfs(TreeNode node, int depth, int pos) {
        if (node == null) {
            return;
        }
        // 第depth层遇到的话，是最左边节点
        leftPosMap.putIfAbsent(depth, pos);
        // 计算当前层宽度
        res = Math.max(res, pos - leftPosMap.get(depth) + 1);
        // 左子树
        dfs(node.left, depth + 1, 2 * pos);
        dfs(node.right, depth + 1, 2 * pos + 1);
    }
}
