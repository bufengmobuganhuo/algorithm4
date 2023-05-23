package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort.exercises;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/6/9 11:34
 * 练习2.3.15：第一次尝试
 */
public class EX_2_3_15_1 {
    public static void main(String[] args) {
        int[] nuts={3,4,9,2,6,1,8,7,0,5};
        int[] bolts={0,8,6,4,2,3,1,7,5,9};
        EX_2_3_15_1 ex_2_3_15_1=new EX_2_3_15_1();
        ex_2_3_15_1.solution(nuts,bolts);
        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }
    public void solution(int[] nuts,int[] bolts){
        sort(nuts,bolts,0,nuts.length-1);
    }

    private void sort(int[] nuts,int[] bolts,int start,int end){
        if (start>=end){
            return;
        }
        int partitionIdx=partition(nuts, bolts,start,end);
        sort(nuts,bolts,start,partitionIdx-1);
        sort(nuts,bolts,partitionIdx+1,end);
    }


    private int partition(int[] nuts,int[] bolts,int start,int end){
        int left=start,right=end+1;
        int partitionNut=nuts[left];

        //保证bolts中切分元素相同
        for (int i = start; i <= end; i++) {
            if (bolts[i]==partitionNut){
                exch(bolts,i,left);
                break;
            }
        }
        //对nuts进行切分
        while (true){
            while (nuts[++left]<partitionNut){
                if (left==end){
                    break;
                }
            }
            while (partitionNut<nuts[--right]){
                if (right==start){
                    break;
                }
            }
            if (left>=right){
                break;
            }
            exch(nuts,left,right);
        }
        exch(nuts,start,right);

        //对bolts切分
        left=start;
        right=end+1;
        while (true){
            while (partitionNut>bolts[++left]){
                if (left==end){
                    break;
                }
            }
            while (partitionNut<bolts[--right]){
                if (right==start){
                    break;
                }
            }
            if (left>=right){
                break;
            }
            exch(bolts,left,right);
        }
        exch(bolts,start,right);
        return right;
    }
    private void exch(int[] arr,int i,int j){
        int temp= arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
