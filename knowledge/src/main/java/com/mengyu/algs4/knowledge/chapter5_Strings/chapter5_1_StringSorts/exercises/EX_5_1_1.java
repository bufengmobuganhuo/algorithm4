package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_1_StringSorts.exercises;

import com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_1_StringSorts.KeyIndexedCounting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/6/1 17:09
 *
 */
public class EX_5_1_1 {
    public static void main(String[] args) {
        KeyIndexedCounting.Student[] students={
                new KeyIndexedCounting.Student("Anderson",2),
                new KeyIndexedCounting.Student("Brown",3),
                new KeyIndexedCounting.Student("Davis",3),
                new KeyIndexedCounting.Student("Garcia",4),
                new KeyIndexedCounting.Student("Harris",1),
                new KeyIndexedCounting.Student("Jackson",3),
                new KeyIndexedCounting.Student("Johnson",4),
                new KeyIndexedCounting.Student("Martin",1),
        };
        EX_5_1_1.solution(students,5);
        System.out.println(Arrays.toString(students));
    }
    public static void solution(KeyIndexedCounting.Student[] arr,int R){
        if (arr==null||arr.length==0){
            return;
        }
        Map<Integer,Integer> countMap=new HashMap<>(arr.length);
        int[] count=new int[R+1];
        //统计频率
        for (int i = 0; i < arr.length; i++) {
            count[arr[i].key+1]++;
        }
        //将频率转化为数组中的起始索引
        for (int r = 0; r < R; r++) {
            count[r+1]+=count[r];
            countMap.put(r+1,count[r+1]);
        }
        //数据分类
        KeyIndexedCounting.Student[] aux=new KeyIndexedCounting.Student[arr.length];
        for (int i = 0; i < arr.length; i++) {
            aux[countMap.get(arr[i].key)]=arr[i];
            countMap.put(arr[i].key,countMap.get(arr[i].key)+1);
        }
        System.arraycopy(aux,0,arr,0,aux.length);
    }
}
