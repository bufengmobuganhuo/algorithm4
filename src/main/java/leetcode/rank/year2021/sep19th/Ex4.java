package leetcode.rank.year2021.sep19th;

/**
 * @author yuzhang
 * @date 2021/12/19 11:00 上午
 * TODO
 */
public class Ex4 {
    public int kIncreasing(int[] arr, int k) {
        int count = 0;
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[i - k]) {
                count++;
                arr[i - k] = arr[i];
            }
        }
        return count;
    }
}
