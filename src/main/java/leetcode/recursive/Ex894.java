package leetcode.recursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/7/8 10:32 上午
 * leetcode 894
 */
public class Ex894 {
    // 当结点数为key时，可能的情况
    Map<Integer,List<TreeNode>> map=new HashMap<>();
    public List<TreeNode> allPossibleFBT(int N) {
        // 如果已经计算过了，则不再计算
        if (!map.containsKey(N)){
            List<TreeNode> ans=new ArrayList<>();
            // 如果只有一个结点，则只有一种可能
            if (N==1){
                ans.add(new TreeNode(0));
                return ans;
                // 只有奇数的才可能组成满二叉树
            }else if(N%2==1){
                // 加入使用x个结点构建左子树
                for (int x = 0; x < N; x++) {
                    // 一个作为根结点，剩下的N-1-x个构建右子树
                    int y=N-1-x;
                    // 用两个数分别构建出左右子树，然后开始构造数
                    for (TreeNode left:allPossibleFBT(x)){
                        for(TreeNode right:allPossibleFBT(y)){
                            TreeNode root=new TreeNode(0);
                            root.left=left;
                            root.right=right;
                            ans.add(root);
                        }
                    }
                }
            }
            map.put(N,ans);
        }
        return map.get(N);
    }


    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
