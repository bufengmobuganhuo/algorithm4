package com.mengyu.algs4.exercise.interview.array;

/**
 * @author yu zhang
 */
public class Ex16_10 {
    public int maxAliveYear(int[] birth, int[] death) {
        int[] change = new int[102];
        for (int i = 0; i < birth.length; i++) {
            // 1901年出生的人让change[1901]+1
            change[birth[i] - 1900]++;
            // 1901年死亡的人让change[100]-1
            change[death[i] - 1899]--;
        }
        int curAlive = 0;
        int maxAlive = 0;
        int theYear = 1900;
        for (int i = 0; i < 101; i++) {
            curAlive += change[i];
            if (curAlive > maxAlive) {
                maxAlive = curAlive;
                theYear = 1900 + i;
            }
        }
        return theYear;
    }
}
