package leetcode.rank.year2021.sep26th;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/12/26 10:57 上午
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 1, 2, 3, 3};
        Ex3 ex3 = new Ex3();
        System.out.println(Arrays.toString(ex3.getDistances(arr)));
    }

    /**
     * https://leetcode-cn.com/problems/intervals-between-identical-elements/solution/qian-zhui-he-ha-xi-by-gnomeshgh_plus-hsfd/
     * 使用前后缀和：
     * 1. leftPreSum：表示前缀和,leftPreSum[i]:表示arr[i]之前所有等于arr[i]元素到arr[i]的距离
     * 计算公式：leftPreSum[i] = 前一个与arr[i]相同的值对应的距离 + (arr[i]到上一个元素的距离) * 上一次为止arr[i]的出现次数
     * 2. rightPreSum: 表示后缀和，rightPreSum[i]:表示arr[i]之后所有等于arr[i]元素到arr[i]的距离
     * 计算公式：rightPreSum[i] = 后一个与arr[i]相同的值对应的距离 + (arr[i]到下一个元素的距离) * 上一次为止arr[i]的出现次数
     * 3. res[i] = leftPreSum[i] + rightPreSum[i]
     */
    public long[] getDistances(int[] arr) {
        // <num, {前一个元素的下标，相同的个数}>，求前缀
        Map<Integer, long[]> numToIdx = new HashMap<>();
        int len = arr.length;
        long[] leftPreSum = new long[len];
        for (int i = 0; i < len; i++) {
            long[] orDefault = numToIdx.getOrDefault(arr[i], new long[2]);
            // 如果有与arr[i]相同的下标
            if (orDefault[1] != 0) {
                leftPreSum[i] += leftPreSum[(int) orDefault[0]] + (i - orDefault[0]) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            numToIdx.put(arr[i], orDefault);
        }
        // 求后缀
        numToIdx = new HashMap<>();
        long[] rightPreSum = new long[len];
        for (int i = len - 1; i >= 0; i--) {
            long[] orDefault = numToIdx.getOrDefault(arr[i], new long[2]);
            if (orDefault[1] != 0) {
                rightPreSum[i] += rightPreSum[(int) orDefault[0]] + (orDefault[0] - i) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            numToIdx.put(arr[i], orDefault);
        }
        long[] res = new long[len];
        for (int i = 0; i < len; i++) {
            res[i] = leftPreSum[i] + rightPreSum[i];
        }
        return res;
    }
}
