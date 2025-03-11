package com.mengyu.algs4.exercise.leetcode.sort;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex2471 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(49);
        root.left = new TreeNode(45);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(46);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(39);

        root.left.left.left = new TreeNode(27);
        root.right.left.left = new TreeNode(25);

        System.out.println(new Ex2471().minimumOperations(root));
    }

    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int cnt = 0;
        int[] map = new int[100001];
        while (!que.isEmpty()) {
            int size = que.size();
            int[] tmp = new int[size];
            int[] converMap = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                map[node.val] = i;
                tmp[i] = node.val;
                converMap[i] = node.val;
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
            Arrays.sort(tmp);
            for (int i = 0; i < size; i++) {
                int val = tmp[i];
                if (i == map[val]) {
                    continue;
                } else {
                    int tmpI = map[val];
                    int otherVal = converMap[i];
                    map[val] = i;
                    map[otherVal] = tmpI;
                    converMap[i] = val;
                    converMap[tmpI] = otherVal;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
