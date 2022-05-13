package leetcode.array;

/**
 * @author yu zhang
 */
public class Ex307 {
    public static void main(String[] args) {
        int[] nums = {7, 2, 7, 2, 0};
        Ex307 ex307 = new Ex307(nums);
        //System.out.println(ex307.sumRange(0, 2));
        ex307.update(4, 6);
        ex307.update(0, 2);
        ex307.update(0, 9);
        System.out.println(ex307.sumRange(4, 4));
    }
    private int[] tree;

    private int[] nums;

    private int len;

    public Ex307(int[] nums) {
        this.len = nums.length;
        tree = new int[len + 1];
        this.nums = nums;
        for (int i = 1; i <= len; i++) {
            updateTree(i, nums[i-1]);
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        updateTree(index + 1, delta);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int leftSum = query(left);
        int rightSum = query(right + 1);
        return rightSum - leftSum;
    }

    private int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    private void updateTree(int i, int delta) {
        while (i <= len) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    private int lowbit(int i) {
        return i & (-i);
    }
}
