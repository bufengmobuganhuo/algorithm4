package leetcode.binary_search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex352 {
    public static void main(String[] args) {
        Ex352 ex352 = new Ex352();
        ex352.addNum(1);
        ex352.addNum(3);
        ex352.addNum(7);
        ex352.addNum(2);
        ex352.addNum(6);
        ex352.addNum(9);
        ex352.addNum(4);
        ex352.addNum(10);
        ex352.addNum(5);
        ex352.addNum(5);
    }
    private List<int[]> intervals;

    public Ex352() {
        intervals = new LinkedList<>();
    }

    public void addNum(int val) {
        if (intervals.isEmpty()) {
            intervals.add(new int[]{val, val});
            return;
        }
        // 找到右边界>=val的最小值对应的索引，他一定>=0
        int nextIdx = ceil(val);
        if (nextIdx == 0) {
            situation1(val);
        } else {
            situation2(nextIdx, val);
        }
    }

    // ceil = 0
    private void situation1(int val) {
        int a = intervals.get(0)[0];
        // 可以知道b >= val
        int b = intervals.get(0)[1];
        // [2，6], val = 5的情况，不需要合并或插入
        if (val >= a && val <= b) {
            return;
            // [2, 3], val = 1，需要扩展区间
        } else if (val == a - 1) {
            intervals.get(0)[0] = val;
            // [3, 3], val = 1，在前面插入一个区间
        } else {
            intervals.add(0, new int[]{val, val});
        }
    }

    private void situation2(int nextIdx, int val) {
        // val比最大的右边界还要大(假设为[4,6]) && val = 9，此时需要插入一个新区间
        if (intervals.size() == nextIdx && val - intervals.get(nextIdx - 1)[1] > 2) {
            intervals.add(new int[]{val, val});
            return;
            // val比最大的右边界还要大(假设为[4,6]) && val = 7，此时需要扩展最后一个区间
        } else if (intervals.size() == nextIdx && val - intervals.get(nextIdx - 1)[1] == 1) {
            intervals.get(nextIdx - 1)[1] = val;
            return;
        }
        //[a, b] [c, d](d >= val, b < val)
        int b = intervals.get(nextIdx - 1)[1];
        int c = intervals.get(nextIdx)[0];
        // [4, 7] [10, 10], val = 8的情况，扩展上一个区间
        if (c - b > 2 && val == b + 1){
            intervals.get(nextIdx - 1)[1] = val;
            // [4, 7] [10, 10], val = 9，扩展ceil对应的区间
        } else if (c - b > 2 && val == c - 1) {
            intervals.get(nextIdx)[0] = val;
            // [4, 6] [10, 10], val = 8，插入一个新区间
        } else if (val < c){
            intervals.add(nextIdx, new int[]{val, val});
        }
    }

    private int ceil(int target) {
        int leftPtr = 0, rightPtr = intervals.size();
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (intervals.get(midPtr)[1] <= target) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr;
            }
        }
        if (rightPtr - 1 >= 0 && intervals.get(rightPtr - 1)[1] == target) {
            return rightPtr - 1;
        }
        return rightPtr;
    }

    public int[][] getIntervals() {
        return intervals.toArray(new int[intervals.size()][]);
    }
}
