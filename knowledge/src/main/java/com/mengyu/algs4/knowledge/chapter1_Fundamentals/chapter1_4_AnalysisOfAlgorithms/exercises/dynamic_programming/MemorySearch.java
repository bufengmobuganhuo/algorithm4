package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

/**
 * 记忆搜索方法
 */
public class MemorySearch {
    public static int search(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        //表示暴力搜索中process1(index,aim)的结果是多少
        int[][] map=new int[arr.length+1][aim+1];
        return process2(arr,0,aim,map);
    }

    public static int process2(int[]arr,int index,int aim,int[][] map){
        int res=0;
        if (index==arr.length){
            res=aim==0?1:0;
        }else{
            int mapValue=0;
            for (int i=0;i*arr[index]<=aim;i++){
                mapValue=map[index+1][aim-i*arr[index]];
                //mapValue!=0:说明process1(index+1,aim-i*arr[index])的情况已经查找过
                if (mapValue!=0){
                    //-1表示按照此种组合，达不到aim
                    res+=mapValue==-1?0:mapValue;
                }else{
                    res+=process2(arr,index+1,aim-i*arr[index],map);
                }
            }
        }
        //如果达不到aim，则赋值为-1
        map[index][aim]=res==0?-1:res;
        return res;
    }
}
