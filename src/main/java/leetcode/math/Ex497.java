package leetcode.math;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yu zhang
 * 朴素想法是先随机选取一个矩形，然后从矩阵中随机选取一个点
 * 难点在于如何随机选取一个矩阵：
 * 1. 如果只是简单的给矩阵编号，然后随机选取一个，这种并不能保证每个点被等概率选取，因为矩阵包含的整数点个数不同
 * 2. 因此矩阵包含的整数点个数需要发挥作用，可以使用一个前缀和数组，保存每个矩阵包含的整数点的个数，然后随机出一个数值，定位其属于哪个矩阵
 * （这里实际上也是给每个整数点一个编号，比如prefixSum[0] = 4,则第一个矩形包含的整数点的编号是[1,2, 3, 4]）
 */
public class Ex497 {
    public static void main(String[] args) {
        int[][] rects = {
                {82918473, -57180867, 82918476, -57180863},
                {83793579, 18088559, 83793580, 18088560},
                {66574245, 26243152, 66574246, 26243153},
                {72983930, 11921716, 72983934, 11921720},
        };
        Ex497 ex497 = new Ex497(rects);
        System.out.println(Arrays.toString(ex497.pick()));
        System.out.println(Arrays.toString(ex497.pick()));
        System.out.println(Arrays.toString(ex497.pick()));
        System.out.println(Arrays.toString(ex497.pick()));
    }

    private int[] prefixSum;

    private int[][] rects;

    private Random random = new Random();

    public Ex497(int[][] rects) {
        int len = rects.length;
        prefixSum = new int[len];
        this.rects = rects;
        prefixSum[0] = (rects[0][2] - rects[0][0] + 1) * (rects[0][3] - rects[0][1] + 1);
        for (int i = 1; i < len; i++) {
            int[] rect = rects[i];
            prefixSum[i] = prefixSum[i - 1] + (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
        }
    }

    public int[] pick() {
        // 随机出一个整数点
        int sum = random.nextInt(prefixSum[prefixSum.length - 1] + 1);
        // 找到对应的矩阵编号，即找>= sum的最小值
        int idx = 0;
        int leftPtr = 0, rightPtr = prefixSum.length;
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (prefixSum[midPtr] <= sum) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr;
            }
        }
        if (rightPtr > 0 && prefixSum[rightPtr - 1] == sum) {
            idx = rightPtr - 1;
        } else {
            idx = rightPtr;
        }
        int xStart = rects[idx][0], xEnd = rects[idx][2];
        int yStart = rects[idx][1], yEnd = rects[idx][3];
        int x = xStart + random.nextInt(xEnd - xStart + 1);
        int y = yStart + random.nextInt(yEnd - yStart + 1);
        return new int[]{x, y};
    }
}
