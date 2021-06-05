package leetcode.boyerMoore;

/**
 * @author yuzhang
 * @date 2021/5/27 上午8:57
 * TODO
 */
public class Ex169 {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 分治算法，时间复杂度：O(nlogn)
     * 1. 如果数a是数组的众数，那么当把数组切分成两部分时，a必定是其中一部分的众数
     * 2. 可以使用分治，将数组切分成两部分，分别求出左右两部分的众数a1,a2，然后在二者中选出正确的众数（根据二者在整个数组中出现的次数）
     */
    public int majorityElement2(int[] nums) {
        return majorityElementRecursive(nums, 0, nums.length - 1);
    }

    private int majorityElementRecursive(int[] nums, int lo, int hi) {
        // 如果切分成了只有一个元素的数组，直接返回
        if (lo == hi) {
            return nums[lo];
        }
        // 否则再分成两部分，继续找
        int mid = lo + (hi - lo) / 2;
        int left = majorityElementRecursive(nums, lo, mid);
        int right = majorityElementRecursive(nums, mid + 1, hi);

        // 对于找到的左右部分的众数，如果二者相等，则直接返回
        if (left == right) {
            return left;
        }

        // 否则通过判断出现的次数来确定真正的众数
        int leftCount = countInRange(nums, left, lo, hi);
        return leftCount > (hi - lo + 1) / 2 ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (num == nums[i]) {
                count++;
            }
        }
        return count;
    }
}
