package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/12/24 上午9:51
 * TODO
 */
public class Ex968 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        Ex968 ex968 = new Ex968();
        System.out.println(ex968.minCameraCover(root));
    }

    /**
     * 1. 树形DP的通用解法
     * 2. 状态定义
     *  （1）状态0：当前节点安装相机时，需要的最少相机数
     *  （2）状态1：当前节点不安装相机，但是能被覆盖时，需要的最少相机数
     *  （3）状态2：当前节点不安装相机，也无法被覆盖时，需要的最少相机数
     * 3. 状态转移方程
     *  （1）当前节点安装相机的话，其左右子节点可装可不装，并且相机数+1
     *      dp[0]=Math.min(left[0],Math.min(left[1],left[2]))+
     *            Math.min(right[0],Math.min(right[1],right[2]))+
     *            1
     * （2）当前节点不安装相机但是被覆盖到，说明其左右子节点中至少有一个安装了相机（同时需要保证左右子节点被覆盖到）
     *      (左子节点安装相机，右子节点要么自己安装了相机，要么已经被覆盖到)
     *      dp[1]=Math.min(left[0]+Math.min(right[0],right[1]),
     *            right[0]+Math.min(left[0],left[1]))
     * （3）当前节点不安装相机，也无法被覆盖到，说明其左右子节点都没有安装相机，但是得保证其左右子节点被覆盖到
     *      dp[2]=left[1]+right[1]
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int[] dp = minCamera(root);
        // 对根节点来说，只有两种情况
        return Math.min(dp[0], dp[1]);
    }

    private int[] minCamera(TreeNode node) {
        // 3种状态
        int[] dp = new int[3];
        if (node == null) {
            // 节点=null，当前节点无法安装相机，最大值表示非法
            dp[0] = Integer.MAX_VALUE / 2;
            // 节点=null，认为已经被覆盖了，并且不需要装额外的相机
            dp[1] = 0;
            // 节点=null，当前节点没有被无法覆盖一说，最大值表示非法
            dp[2] = Integer.MAX_VALUE / 2;
            return dp;
        }
        int[] left = minCamera(node.left);
        int[] right = minCamera(node.right);
        dp[0] = Math.min(left[0], Math.min(left[1], left[2])) + Math.min(right[0], Math.min(right[1], right[2])) + 1;
        dp[1] = Math.min(left[0] + Math.min(right[0], right[1]), right[0] + Math.min(left[0], left[1]));
        dp[2] = left[1] + right[1];
        return dp;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
