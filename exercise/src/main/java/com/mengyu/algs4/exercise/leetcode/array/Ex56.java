package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/7/16 11:39 上午
 * TODO
 */
public class Ex56 {
    public static void main(String[] args) {
        int[][] intervals={{2,3},{1,4}};
        Ex56 ex56=new Ex56();
        ex56.merge(intervals);
    }
    private int[][] tmp;
    public int[][] merge(int[][] intervals) {
        if (intervals==null||intervals.length==0){
            return new int[][]{};
        }
        //tmp=new int[intervals.length][2];
        quickSort(intervals,0,intervals.length-1);
        //记录合并了几个区间
        int count=0;
        for (int i = 1; i < intervals.length; i++) {
            int lastEnd=intervals[i-1][1];
            if (lastEnd>=intervals[i][0]){
                intervals[i][0]= Math.min(intervals[i - 1][0], intervals[i][0]);
                intervals[i][1]=Math.max(intervals[i-1][1],intervals[i][1]);
                Arrays.fill(intervals[i-1],Integer.MAX_VALUE);
                count++;
            }
        }
        int[][] res=new int[intervals.length-count][2];
        int idx=0;
        for (int[] interval : intervals) {
            if (interval[0] == Integer.MAX_VALUE && interval[0] == interval[1]) {
                continue;
            }
            res[idx][0] = interval[0];
            res[idx][1] = interval[1];
            idx++;
        }
        return res;
    }

    private void quickSort(int[][] matrix,int start,int end){
        if (start>=end){
            return;
        }
        int partitionIdx=partition(matrix,start,end);
        quickSort(matrix,start,partitionIdx-1);
        quickSort(matrix,partitionIdx+1,end);
    }

    private int partition(int[][] matrix,int start,int end){
        int partitionKey=matrix[start][0];
        int left=start,right=end+1;
        while (true){
            while(matrix[++left][0]<partitionKey){
                if (left==end){
                    break;
                }
            }
            while(matrix[--right][0]>partitionKey){
                if (right==start){
                    break;
                }
            }
            if (right<=left){
                break;
            }
            exch(matrix,left,right);
        }
        exch(matrix,start,right);
        return right;
    }

    private void exch(int[][] matrix,int i,int j){
        int[] tmp=matrix[i];
        matrix[i]=matrix[j];
        matrix[j]=tmp;
    }

    private void mergeSort(int[][] matrix,int start,int end){
        if (start>=end){
            return;
        }
        int mid=start+(end-start)/2;
        mergeSort(matrix,start,mid);
        mergeSort(matrix,mid+1,end);
        merge(matrix,start,mid,end);
    }

    private void merge(int[][] matrix,int start,int mid,int end){
        int i=start,j=mid+1;
        System.arraycopy(matrix,start,tmp,start,end-start+1);
        for (int k = start; k <= end; k++) {
            if (i>mid){
                matrix[k]=tmp[j++];
            }else if (j>end){
                matrix[k]=tmp[i++];
            }else if (tmp[i][0]<=tmp[j][0]){
                matrix[k]=tmp[i++];
            }else{
                matrix[k]=tmp[j++];
            }
        }
    }
}
