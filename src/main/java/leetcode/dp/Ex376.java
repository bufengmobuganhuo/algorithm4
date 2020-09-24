package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/14 9:39 上午
 * TODO
 */
public class Ex376 {
    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        Ex376 ex376 = new Ex376();
        System.out.println(ex376.wiggleMaxLength4(nums));
    }

    /**
     * 解法四：空间优化的动态规划
     * 1. 由解法三可知，up[i],down[i]只依赖于up[i-1],down[i-1]，故可以使用两个变量来表示，而不是使用数组
     * @param nums
     * @return
     */
    public int wiggleMaxLength4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }
        int up=1,down=1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[i-1]){
                up=down+1;
            }else if (nums[i]<nums[i-1]){
                down=up+1;
            }
        }
        return Math.max(up,down);
    }

    /**
     * 解法三：时间优化的动态规划
     * 1. 同样有up,down两个数组
     * 2. 对于第i个元素，有如下三种情况：
     *  （1）nums[i]>nums[i-1]，说明第i个元素是上升元素，令up[i]=down[i-1]+1,down[i]=down[i-1]
     *  （2）nums[i]<nums[i-1]，说明第i个元素是下降元素，令down[i]=up[i-1]+1,up[i]=up[i-1]
     *  （3）nums[i]=nums[i-1]，令down[i]=down[i-1],up[i]=up[i-1]
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        up[0]=down[0]=1;
        for (int i = 1; i <len; i++) {
            if (nums[i]>nums[i-1]){
                up[i]=down[i-1]+1;
                down[i]=down[i-1];
            }else if (nums[i]<nums[i-1]){
                down[i]=up[i-1]+1;
                up[i]=up[i-1];
            }else {
                down[i]=down[i-1];
                up[i]=up[i-1];
            }
        }
        return Math.max(down[len-1],up[len-1]);
    }

    /**
     * 解法二：动态规划
     * 1. 使用两个数组: up，down；up[i]:以第i个元素结尾的上升序列的长度，down同理
     * 2. 那么可以知道，若nums[i]>nums[j]（0<=j<i），则up[i]=max{down[j]+1}，down同理
     * 3. 最后取max{up[nums.length-1],down[nums.length-1]}
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]){
                    up[i]=Math.max(up[i],down[j]+1);
                }else if (nums[i]<nums[j]){
                    down[i]=Math.max(down[i],up[j]+1);
                }
            }
        }
        return 1+Math.max(up[len-1],down[len-1]);
    }

    /**
     * 解法一；使用回溯算法
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }
        return 1 + Math.max(backtracking(nums, 0, true), backtracking(nums, 0, false));
    }

    /**
     * @param nums
     * @param index
     * @param isUp  当前要找的是上升元素，还是下降元素
     * @return
     */
    private int backtracking(int[] nums, int index, boolean isUp) {
        int maxCount = 0;
        for (int i = index + 1; i < nums.length; i++) {
            // 如果当前元素满足条件，则向下一个元素查找
            if ((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index])) {
                maxCount = Math.max(maxCount, 1 + backtracking(nums, i, !isUp));
            }
        }
        return maxCount;
    }
}
