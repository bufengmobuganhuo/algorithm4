package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/12/22 上午9:47
 * TODO
 */
public class Ex1588 {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            /*
             * 当遍历第i个元素，其左边可能的取数方式为：
             *  1. 以{1,2,3,4,5}，i=2为例：
             *      （1）取0个：{3}
             *      （2）取1个：{2,3}
             *      （3）取2个：{1,2,3}
             *  2. 可以知道，左边取法共有(i+1)种
             */
            int leftCount = i + 1;
            /**
             * 对于右边，其情况类似:右边元素个数+1（取0个右边的元素）
             */
            int rightCount = len - i;
            /**
             * 要想在包含arr[i]的情况下构成奇数个数的子序列，有两种情况
             * 1. {左边奇数个元素} arr[i] {右边奇数个元素}
             *   可知左边奇数个元素的取法有 leftCount/2 种
             *      右边奇数个元素的取法有 rightCount/2 种
             * 2. {左边偶数个元素} arr[i] {右边偶数个元素}
             *   可知左边偶数个元素的取法有 （leftCount+1）/2 种
             *      右边偶数个元素的取法有 （rightCount+1）/2 种
             */
            int leftOddCount = leftCount / 2;
            int rightOddCount = rightCount / 2;

            int leftEvenCount = (leftCount + 1) / 2;
            int rightEvenCount = (rightCount + 1) / 2;

            // 记录必须包含arr[i]的奇数个数子数组的和
            sum += (leftOddCount * rightOddCount + leftEvenCount * rightEvenCount) * arr[i];
        }
        return sum;
    }
}
