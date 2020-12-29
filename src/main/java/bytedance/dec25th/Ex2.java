package bytedance.dec25th;

/**
 * @author yuzhang
 * @date 2020/12/25 上午9:39
 * TODO
 */
public class Ex2 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int value = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
        TreeNode head = new TreeNode(value);
        head.left = mergeTrees(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
        head.right = mergeTrees(t1 != null ? t1.right : null, t2 !=null ? t2.right : null);
        return head;
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
