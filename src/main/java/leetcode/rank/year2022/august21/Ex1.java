package leetcode.rank.year2022.august21;

/**
 * @author yuzhang
 * @date 2022/8/21 10:11
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] energy = {1, 1, 1, 1}, experience = {1, 1, 1, 50};
        System.out.println(new Ex1().minNumberOfHours(1, 1, energy, experience));
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int count = 0, n = energy.length;
        for (int i = 0; i < n; i++) {
            if (initialEnergy > energy[i] && initialExperience > experience[i]) {
                initialEnergy -= energy[i];
                initialExperience += experience[i];
            } else if (initialEnergy > energy[i]) {
                initialEnergy -= energy[i];
                count += experience[i] - initialExperience + 1;
                initialExperience += experience[i] + 1;
            } else if (initialExperience > experience[i]) {
                count += energy[i] - initialEnergy + 1;
                initialExperience += experience[i];
                initialEnergy = 1;
            } else {
                count += energy[i] - initialEnergy + 1;
                count += experience[i] - initialExperience + 1;
                initialExperience += experience[i] + 1;
                initialEnergy = 1;
            }
        }
        return count;
    }
}
