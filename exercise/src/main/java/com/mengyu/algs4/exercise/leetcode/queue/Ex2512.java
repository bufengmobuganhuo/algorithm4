package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2512 {

    public static void main(String[] args) {
        String[] positive_feedback = {"smart","brilliant","studious"}, negative_feedback = {"not"}, report = {"this "
                + "student is studious","the student is smart"};
        int[] student_id = {1,2};
        System.out.println(new Ex2512().topStudents(positive_feedback, negative_feedback, report, student_id, 2));
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>(this::compare);
        Set<String> positiveSet = new HashSet<>(Arrays.asList(positive_feedback));
        Set<String> negativeSet = new HashSet<>(Arrays.asList(negative_feedback));
        for (int i = 0; i < report.length; i++) {
            int score = getScore(positiveSet, negativeSet, report[i]);
            int id = student_id[i];
            int[] stu = new int[]{score, id};
            if (que.isEmpty() || compare(stu, que.peek()) > 0) {
                if (que.size() == k) {
                    que.poll();
                }
                que.offer(new int[]{score, id});
            }
        }
        List<Integer> ans = new LinkedList<>();

        while (!que.isEmpty()) {
            ((LinkedList<Integer>) ans).offerFirst(que.poll()[1]);
        }
        return ans;
    }

    private int compare(int[] o1, int[] o2) {
        if (o1[0] != o2[0]) {
            return o1[0] - o2[0];
        }
        return o2[1] - o1[1];
    }

    private int getScore(Set<String> positiveSet, Set<String> negativeSet, String report) {
        int score = 0;
        for (String word : report.split(" ")) {
            if (positiveSet.contains(word)) {
                score += 3;
            } else if (negativeSet.contains(word)) {
                score -= 1;
            }
        }
        return score;
    }
}
