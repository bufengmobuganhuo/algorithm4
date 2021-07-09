package leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex406 {
    public static void main(String[] args) {
        int[][] people = {
            {7, 0},
            {4, 4},
            {7, 1},
            {5, 0},
            {6, 1},
            {5, 2}
        };
        Ex406 ex406 = new Ex406();
        System.out.println(Arrays.deepToString(ex406.reconstructQueue(people)));
    }

    /**
     * 1. 按照身高降序排序，可以保证：对于nums[i]，nums[0...i-1]都比他的身高高
     * 2. 按照people[i][1]升序排序，可以知道：对于people[i]，为了满足people[i][1]，只会向前调整
     */
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        sort(people, 0, len - 1);
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[len][]);
    }

    private void sort(int[][] people, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIdx = partition(people, start, end);
        sort(people, start, partitionIdx - 1);
        sort(people, partitionIdx + 1, end);
    }

    private int partition(int[][] people, int start, int end) {
        int i = start, j = end + 1;
        int[] partitionEle = people[start];
        while (true) {
            while (less(people, ++i, partitionEle)) {
                if (i == end) {
                    break;
                }
            }
            while (!less(people, --j, partitionEle)) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(people, i, j);
        }
        exch(people, start, j);
        return j;
    }

    private void exch(int[][] people, int i, int j) {
        int[] tmp = people[i];
        people[i] = people[j];
        people[j] = tmp;
    }

    private boolean less(int[][] people, int i, int[] person) {
        if (people[i][0] > person[0]) {
            return true;
        } else if (people[i][0] < person[0]) {
            return false;
        }
        return people[i][1] <= person[1];
    }
}
