package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex429 {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = que.poll();
                layer.add(node.val);
                if (node.children == null) {
                    continue;
                }
                for (Node child : node.children) {
                    que.offer(child);
                }
            }
            ans.add(layer);
        }
        return ans;
    }


}
