package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_1_StringSorts;

import edu.princeton.cs.algs4.Insertion;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/31 17:27
 * 高位优先排序
 */
public class MSD {
    public static void main(String[] args) {
        String[] arr={
                "4PGC838",
                "2IYE230",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1ICK750",
                "1OHV845",
                "1OHV845",
                "4JZY524",
                "4JZY524",
                "1ICK750",
                "1ICK750",
        };
        MSD.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static int R=256;
    private static String[] aux;
    private static final int M=0;

    public static void sort(String[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        aux=new String[arr.length];
        sort(arr,0,arr.length-1,0);
    }

    /**
     * @param arr
     * @param lo
     * @param hi
     * @param d 对第i个字符执行键索引排序
     */
    public static void sort(String[] arr,int lo,int hi,int d){
        //防止栈溢出
        if (hi<=lo+M){
            Insertion.sort(arr);
            return;
        }
        int[] count=new int[R+2];
        //统计频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(arr[i],d)+2]++;
        }
        //将频率转化为索引起始位置
        for (int r = 0; r < R+1; r++) {
            count[r+1]+=count[r];
        }
        //数据分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(arr[i] ,d)+1]++]=arr[i];
        }
        //回写
        for (int i = lo; i <= hi; i++) {
            arr[i]=aux[i-lo];
        }
        //这些字符串的第0-d位是相同的，它们组成了一个子数组，
        // 范围是：lo+count[r] -> lo+count[r+1]-1
        for (int r = 0; r < R; r++) {
            sort(arr,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }

    private static int charAt(String str,int d){
        if (d<str.length()){
            return str.charAt(d);
        }else{
            return -1;
        }
    }

}
