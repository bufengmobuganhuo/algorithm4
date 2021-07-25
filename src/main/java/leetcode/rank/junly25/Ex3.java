package leetcode.rank.junly25;

import org.omg.CORBA.INTERNAL;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/7/25 上午10:59
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {

    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        backtracking(0, new HashSet<>(), 0, students, mentors);
        return maxSum;
    }

    private void backtracking(int stuIdx, Set<Integer> marked, int sum, int[][] students, int[][] mentors) {
        if (stuIdx == students.length) {
            maxSum = Math.max(maxSum, sum);
        }
        for (int i = 0; i < mentors.length; i++) {
            if (marked.contains(i)){
                continue;
            }
            marked.add(i);
            int count = check(students[stuIdx], mentors[i]);
            backtracking(stuIdx + 1, marked, sum + count, students, mentors);
            marked.remove(i);
        }
    }

    private int check(int[] student, int[] mentor) {
        int sum = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                sum++;
            }
        }
        return sum;
    }

}
