package leetcode.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex406_1 {
    public static void main(String[] args) {

    }
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        sort(people, 0, len - 1);
        List<int[]> queue = new LinkedList<>();
        for (int[] person : people) {
            queue.add(person[1], person);
        }
        return queue.toArray(new int[len][]);
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
        int leftPtr = start, rightPtr = end + 1;
        int[] partitionEle = people[start];
        while (true) {
            while (less(people[++leftPtr], partitionEle)) {
                if (leftPtr >= end) {
                    break;
                }
            }
            while (less(partitionEle, people[--rightPtr])) {
                if (rightPtr <= start) {
                    break;
                }
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            exch(people, leftPtr, rightPtr);
        }
        exch(people, start, rightPtr);
        return rightPtr;
    }

    private void exch(int[][] people, int i, int j) {
        int[] temp = people[i];
        people[i] = people[j];
        people[j] = temp;
    }

    private boolean less(int[] person1, int[] person2) {
        if (person1[0] != person2[0]) {
            return person1[0] - person2[0] > 0;
        }
        return person1[1] - person2[1] <= 0;
    }
}
