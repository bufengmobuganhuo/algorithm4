package leetcode.sort;

/**
 * @author yu zhang
 */
public class Ex4 {
    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {3, 4};
        Ex4 ex4 = new Ex4();
        System.out.println(ex4.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int targetIdx = (m + n) / 2;
        double target = mergeFind(nums1, nums2, targetIdx);
        if ((m + n) % 2 == 0) {
            target += mergeFind(nums1, nums2, targetIdx - 1);
            target = target / 2;
        }
        return target;
    }

    private int mergeFind(int[] nums1, int[] nums2, int targetIdx) {
        int i = 0, j = 0;
        int countIdx = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i >= nums1.length) {
                if (countIdx == targetIdx) {
                    return nums2[j];
                }
                j++;
            }else if (j >= nums2.length) {
                if (countIdx == targetIdx) {
                    return nums1[i];
                }
                i++;
            }else if (nums1[i] <= nums2[j]) {
                if (countIdx == targetIdx) {
                    return nums1[i];
                }
                i++;
            } else {
                if (countIdx == targetIdx) {
                    return nums2[j];
                }
                j++;
            }
            countIdx++;
        }
        return -1;
    }
}
