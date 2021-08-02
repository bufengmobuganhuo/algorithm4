package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex894_1 {

    // 节点数为key时能组成的满二叉树
    private Map<Integer, List<TreeNode>> map = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (!map.containsKey(n)) {
            List<TreeNode> ans = new ArrayList<>();
            // 节点数=1，只有一种情况
            if (n == 1) {
                ans.add(new TreeNode(0));
                // 只有奇数才能组成满二叉树
            } else if (n % 2 == 1) {
                // 枚举左子树节点个数（0～n-1）要留一个做根节点
                for (int leftNodeNum = 0; leftNodeNum < n; leftNodeNum++) {
                    int rightNodeNum = n - 1 - leftNodeNum;
                    for (TreeNode left : allPossibleFBT(leftNodeNum)) {
                        for (TreeNode right : allPossibleFBT(rightNodeNum)) {
                            TreeNode root = new TreeNode(0);
                            root.left = left;
                            root.right = right;
                            ans.add(root);
                        }
                    }
                }
            }
            map.put(n, ans);
        }
        return map.get(n);
    }
}
