package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2383 {
    public static void main(String[] args) {
        int[] energy = {1,4,3,2}, experience = {2,6,3,1};
        System.out.println(new Ex2383().minNumberOfHours(5, 3, energy, experience));
    }
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; i++) {
            if (initialEnergy > energy[i] && initialExperience > experience[i]) {
                initialEnergy -= energy[i];
                initialExperience += experience[i];
            } else if (initialEnergy > energy[i]){
                ans += (experience[i] - initialExperience + 1);
                initialExperience += (experience[i] - initialExperience + 1) + experience[i];
                initialEnergy -= energy[i];
            } else if (initialExperience > experience[i]){
                ans += (energy[i] - initialEnergy + 1);
                initialEnergy = 1;
                initialExperience += experience[i];
            } else {
                ans += (experience[i] - initialExperience + 1);
                initialExperience += (experience[i] - initialExperience + 1) + experience[i];
                ans += (energy[i] - initialEnergy + 1);
                initialEnergy = 1;
            }
        }
        return ans;
    }
}
