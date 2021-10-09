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
        ex352.addNum(6);
        System.out.println(Arrays.toString(ex352.getIntervals()));
        ex352.addNum(6);
        System.out.println(Arrays.toString(ex352.getIntervals()));
        ex352.addNum(0);
        System.out.println(Arrays.toString(ex352.getIntervals()));
        ex352.addNum(4);
        System.out.println(Arrays.toString(ex352.getIntervals()));
        ex352.addNum(6);
        System.out.println(Arrays.toString(ex352.getIntervals()));
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
        int b = intervals.get(0)[1];
        if (val >= a && val <= b) {
            return;
        } else if (val == a - 1) {
            intervals.get(0)[0] = val;
        } else {
            intervals.add(0, new int[]{val, val});
        }
    }

    private void situation2(int nextIdx, int val) {
        if (intervals.size() == nextIdx) {
            intervals.add(new int[]{val, val});
            return;
        }
        int a = intervals.get(nextIdx - 1)[0];
        int b = intervals.get(nextIdx - 1)[1];
        int c = intervals.get(nextIdx)[0];
        int d = intervals.get(nextIdx)[1];
        if (val == a - 1) {
            intervals.get(nextIdx - 1)[0] = val;
        } else if (c - b == 2 && val == b + 1) {
            intervals.remove(nextIdx - 1);
            intervals.remove(nextIdx - 1);
            intervals.add(nextIdx - 1, new int[]{a, d});
        } else if (c - b > 2 && val == b + 1){
            intervals.get(nextIdx - 1)[1] = val;
        } else if (c - b > 2 && val == c - 1) {
            intervals.get(nextIdx)[0] = val;
        } else {
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
