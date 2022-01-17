package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex873 {
    public static void main(String[] args) {
        Ex873 ex873 = new Ex873();
        System.out.println(ex873.lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
    /**
     * 1.数列实际上可以分成一个元组，例如对于如下数列：2(下标为1)， 3(下标为2)， 5(下标为4)：
     * 可以按照下标分成(1,2) -> (2, 4)，只有当A[i] + A[j] = A[k]时，(i, j)和(j, k)可以组成一个数列
     * 2.令longest(i, j)表示截止到(i, j)元组，数列的长度，则可知
     * longest(j, k) = (A[k] - A[j])存在于数列中 ? longest(i, j) + 1 : 2(A[j],A[k]就已经是两个数了)
     */
    public int lenLongestFibSubseq(int[] arr) {
        int N = arr.length;
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idxMap.put(arr[i], i);
        }
        Map<Integer, Integer> longest = new HashMap<>();
        int ans = 0;
        for (int k = 0; k < arr.length; k++) {
            for (int j = 0; j < k; j++) {
                int i = idxMap.getOrDefault(arr[k] - arr[j], -1);
                // 保证arr[i]存在，并且在arr[j]的前面
                if (i >= 0 && i < j) {
                    // 截止到元组(i,j)的数列长度
                    // i和j需要组成一个唯一的键表示(i, j)元组，那么可以使用进制的思想，i * N + j一定是唯一的
                    int len = longest.getOrDefault(i * N + j, 2) + 1;
                    // 此时已经满足了arr[k] = arr[i] + arr[j]，则最短长度是3
                    longest.put(j * N + k, len);
                    ans = Math.max(ans, len);
                }
            }
        }
        return ans;
    }
}
