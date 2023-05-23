package com.mengyu.algs4.exercise.leetcode.rank.year2022.july10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2022/7/10 11:28
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        System.out.println(new Ex3().canChange("_L__R__R_", "L______RR"));
    }

    public boolean canChange(String start, String target) {
        int len = start.length();
        List<Integer> startL = new ArrayList<>();
        List<Integer> targetL = new ArrayList<>();
        List<Integer> startR = new ArrayList<>();
        List<Integer> targetR = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (start.charAt(i) == 'L') {
                startL.add(i);
            } else if (start.charAt(i) == 'R') {
                startR.add(i);
            }
            if (target.charAt(i) == 'L') {
                targetL.add(i);
            } else if (target.charAt(i) == 'R') {
                targetR.add(i);
            }
        }
        if (startL.size() != targetL.size() || startR.size() != targetR.size()) {
            return false;
        }
        for (int i = 0; i < startL.size(); i++) {
            int startLIdx = startL.get(i);
            int targetLIdx = targetL.get(i);
            if (startLIdx < targetLIdx) {
                return false;
            }
            int rIdx = ceil(targetLIdx, startR);
            if (rIdx < startR.size() && startR.get(rIdx) <= targetLIdx) {
                return false;
            }
        }
        for (int i = 0; i < startR.size(); i++) {
            int startRIdx = startR.get(i);
            int targetRIdx = targetR.get(i);
            if (startRIdx > targetRIdx) {
                return false;
            }
            int lIdx = ceil(startRIdx, startL);
            if (lIdx < startL.size() &&  startL.get(lIdx) <= targetRIdx) {
                return false;
            }
        }
        return true;
    }

    private int ceil(int target, List<Integer> intervals) {
        int leftPtr = 0, rightPtr = intervals.size();
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (intervals.get(midPtr) <= target) {
                leftPtr = midPtr + 1;
            } else {
                rightPtr = midPtr;
            }
        }
        if (rightPtr - 1 >= 0 && intervals.get(rightPtr - 1) == target) {
            return rightPtr - 1;
        }
        return rightPtr;
    }
}
