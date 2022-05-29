package leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex1855 {
    public static void main(String[] args) {
        int[] nums1 = {55, 30, 5, 4, 2};
        int[] nums2 = {100, 20, 10, 10, 5};
        System.out.println(new Ex1855().maxDistance(nums1, nums2));
    }

    /**
     * 1. 假设i <= j && nums1[i] <= nums2[j] && nums1[i]是第一个<= nums2[j]的元素
     * 那么当j=j+1，此时nums2[j]会变小，那么对于nums1[i]来说，可能有三种情况：
     * nums1[i] <= nums2[j]：因为nums1[i]是第一个<= 上一个nums2[j]的元素，那么此时的i也是距离j最远的
     * nums1[i] > nums2[j]：此时i应当向后遍历，直到遇到第一个<=nums2[j]的元素
     */
    public int maxDistance2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int i = 0, distance = 0;
        for (int j = 0; j < len2; j++) {
            // 找第一个<= nums2[j]的元素
            while (i < len1 && nums1[i] > nums2[j]) {
                i++;
            }
            if (i < len1) {
                // 就算出现i > j 的情况，也没关系
                distance = Math.max(distance, j - i);
            }
        }
        return distance;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int distance = -1;
        for (int i = 0; i < nums1.length; i++) {
            int left = i, right = nums2.length - 1;
            while (left < right) {
                int mid = left + (right - left + 1) / 2;
                if (nums2[mid] >= nums1[i]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            distance = Math.max(distance, left - i);
        }
        return distance;
    }
}
