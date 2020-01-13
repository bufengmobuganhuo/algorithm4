package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms;

import chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.BinarySerach;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args) {
        int[] arr= StdIn.readAllInts();
        System.out.println(getResult(arr));
    }
    public static int getResult(int[] arr){
        if (arr==null||arr.length==0){
            return 0;
        }
        int count=0;
        Arrays.sort(arr);
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if(BinarySerach.find(-(arr[i]+arr[j]),arr)>j){
                    count++;
                }
            }
        }
        return count;
    }
}
