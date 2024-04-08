package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex894 {

    public static void main(String[] args) {
        System.out.println(new Ex894().allPossibleFBT(7));
    }

    private Map<Integer, List<TreeNode>> map;

    public Ex894() {
        map = new HashMap<>();
    }

    public List<TreeNode> allPossibleFBT(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else if (n % 2 == 1) {
            List<TreeNode> ans = new ArrayList<>();
            if (n == 1) {
                ans.add(new TreeNode());
            } else {
                for (int leftNodeNum = 0; leftNodeNum < n; leftNodeNum++) {
                    int rightNodeNum = n - 1 - leftNodeNum;
                    for (TreeNode left : allPossibleFBT(leftNodeNum)) {
                        for (TreeNode right : allPossibleFBT(rightNodeNum)) {
                            TreeNode root = new TreeNode();
                            root.left = left;
                            root.right = right;
                            ans.add(root);
                        }
                    }
                }
            }
            map.put(n, ans);
            return ans;
        }
        return new ArrayList<>();
    }
}
