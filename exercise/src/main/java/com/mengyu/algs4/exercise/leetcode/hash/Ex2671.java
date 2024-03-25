package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex2671 {

    public static void main(String[] args) {
        Ex2671 ex2671 = new Ex2671();
        ex2671.add(4);
        ex2671.deleteOne(3);
        ex2671.hasFrequency(1);
        ex2671.deleteOne(6);
        ex2671.add(8);
        ex2671.add(6);
        ex2671.deleteOne(6);
        ex2671.deleteOne(4);
        ex2671.hasFrequency(1);
        ex2671.deleteOne(4);
        ex2671.hasFrequency(1);
        ex2671.add(3);
        ex2671.add(8);
    }

    private int[] numbers;

    private int[] frequencies;

    public Ex2671() {
        numbers = new int[100000];
        frequencies = new int[100000];
    }

    public void add(int number) {
        int before = numbers[number];
        frequencies[before] = Math.max(0, frequencies[before] - 1);
        int frequency = ++numbers[number];
        frequencies[frequency]++;
    }

    public void deleteOne(int number) {
        int before = numbers[number];
        frequencies[before] = Math.max(0, frequencies[before] - 1);
        int frequency = Math.max(0, numbers[number] - 1);
        frequencies[frequency]++;
        numbers[number] = frequency;
    }

    public boolean hasFrequency(int frequency) {
        return frequencies[frequency] > 0;
    }
}
