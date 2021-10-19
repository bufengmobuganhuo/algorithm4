package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/22 9:00 上午
 * leetcode222：完全二叉树的结点个数
 * 1. 计算完全二叉树的高度（从0开始）depth，可知对于从0~depth-1层，所有结点都是满的，0～depth-1的结点总数=(2^depth)-1
 * 2. 对于最后一层，最多会有2^depth个，为该层每个结点从左到右编号（0开始，最多到2^depth-1），
 *    使用二分查找（left=0,right=2^depth-1）来确定某个结点编号为idx的结点是否存在：
 *                      / 根结点 \
 *                     / \     / \
 *                    /\ /\   /\ /\
 *                   0 1 2 3 4 5 6 7
 *
 *      例如要找idx=4的结点：
 *      1⃣️ 从根结点开始，midIdx=0+(7-0)/2=3，idx>midIdx，则向右移动（到达depth=1层）
 *      2⃣️ 此时midIdx=3+（7-3）/2=5>idx，向左移动（到达depth=2层）
 *      3⃣️ 此时midIdx=3+(5-3)/2=4=idx，找到索引为idx的结点，如果这个结点存在，则从idx+1~2^depth-1二分查找结点，直到left>right
 *          那么left的值就是最后一层结点的个数
 *
 */
public class Ex222 {
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        int depth=getDepth(root);
        if (depth==0){
            return 1;
        }
        int left=0,right= (int) (Math.pow(2,depth)-1);
        // 使用二分查找，判断出哪个结点不存在
        int midIdx;
        while(left<=right){
            midIdx=left+(right-left)/2;
            if (exists(midIdx,depth,root)){
                left=midIdx+1;
            }else{
                right=midIdx-1;
            }
        }
        return (int) (Math.pow(2,depth)-1+left);
    }

    /**
     * 判断最后一层中，某个编号为idx的结点是否存在
     * @param idx
     * @param depth
     * @param root
     * @return
     */
    private boolean exists(int idx,int depth,TreeNode root){
        int left=0,right= (int) (Math.pow(2,depth)-1);
        int midIdx;
        for (int i = 0; i < depth; i++) {
            midIdx=left+(right-left)/2;
            if (idx<=midIdx){
                root=root.left;
                right=midIdx;
            }else{
                root=root.right;
                left=midIdx+1;
            }
        }
        return root!=null;
    }

    private int getDepth(TreeNode root){
        int depth=0;
        while(root.left!=null){
            root=root.left;
            depth++;
        }
        return depth;
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
