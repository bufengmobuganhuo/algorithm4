package com.mengyu.algs4.interview.hsbc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @date 2025/4/24 22:20
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        int[] errorScore = {6, 4 ,1};
        System.out.println(minProject(errorScore, 4, 1));
    }

    public static int  minProject(int[] errorScore, int compP, int othQ)
    {
        int  answer = 0;
        // Write your code here
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> b - a);
        for (int score : errorScore) {
            que.offer(score);
        }
        while (!que.isEmpty()) {
            int maxScore = que.poll();
            if (maxScore == 0) {
                break;
            }
            maxScore = Math.max(maxScore - compP, 0);
            List<Integer> tmpList = new ArrayList<>();
            while (!que.isEmpty()) {
                int score = que.poll();
                score = Math.max(score - othQ, 0);
                if (score > 0) {
                    tmpList.add(score);
                }
            }
            if (maxScore > 0) {
                que.offer(maxScore);
            }
            que.addAll(tmpList);
            answer++;
        }

        return answer;
    }
}
