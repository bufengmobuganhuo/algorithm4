package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

public class TestMain {
    public static void main(String[] args) {
        int[] arr={5,10,25,1};
        System.out.println(BruteForceSearch.search(arr,1000));
        System.out.println(MemorySearch.search(arr,1000));
        System.out.println(DynamicProgramming.search(arr,1000));
    }
}
