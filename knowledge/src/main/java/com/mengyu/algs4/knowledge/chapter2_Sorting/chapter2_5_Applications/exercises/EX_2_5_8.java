package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.*;

/**
 * @author zhangyu
 * 2020/3/7 15:15
 * 练习2.5.8：词频统计
 */
public class EX_2_5_8 {
    public static void main(String[] args) {
        EX_2_5_8 ex_2_5_8=new EX_2_5_8();
        Scanner scanner=new Scanner(System.in);
        String[] words= scanner.nextLine().split(" ");
        ex_2_5_8.solution2(words);
    }
    public void solution2(String[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        //使用map统计词频
        Map<String,Integer> map = new HashMap<>(arr.length);
        for (String word:arr){
            int frequency=map.getOrDefault(word,0);
            map.put(word,++frequency);
        }
        List<Map.Entry<String,Integer>> list =new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(Arrays.toString(list.toArray()));
    }
    public void solution1(String[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        //将数组排序，则相同元素会放在一起
        mergeSort(arr);
        String word=arr[0];
        //使用数组记录词频
        Record[] records=new Record[arr.length];
        int i=0;
        int frequency=1;
        for (int j=1;j<arr.length;j++){
            if (!word.equals(arr[j])){
                records[i++]=new  Record<String>(word,frequency);
                word=arr[j];
                frequency=0;
            }
            frequency++;
        }
        records[i++]=new Record<String>(word,frequency);
        Comparable[] temp=new Comparable[i];
        mergeSort(records,temp,0,i-1);
        System.out.println(Arrays.toString(records));
    }

    private static void mergeSort(Comparable[] arr){
        Comparable[] temp=new Comparable[arr.length];
        mergeSort(arr,temp,0,arr.length-1);
    }

    private static void mergeSort(Comparable[] arr,Comparable[] temp,int start,int end){
        if (start>=end){
            return;
        }
        int mid=start+(end-start)/2;
        mergeSort(arr,temp,start,mid);
        mergeSort(arr,temp,mid+1,end);
        merge(arr,temp,start,mid,end);
    }

    private static void merge(Comparable[] arr,Comparable[] temp, int start,int mid,int end){
        int i=start,j=mid+1;
        System.arraycopy(arr,start,temp,start,end-start+1);
        for (int k=start;k<=end;k++){
            if(i>mid){
                arr[k]=temp[j++];
            }else if(j>end){
                arr[k]=temp[i++];
            }else if(temp[i].compareTo(temp[j])<0){
                arr[k]=temp[i++];
            }else{
                arr[k]=temp[j++];
            }
        }
    }
    class Record<T extends Comparable<T>> implements Comparable<Record<T>>{
        T value;
        Integer frequency;

        public Record(T value,int frequency) {
            this.value = value;
            this.frequency=frequency;
        }

        @Override
        public int compareTo(Record<T> record) {
            return frequency.compareTo(record.frequency);
        }

        @Override
        public String toString() {
            return value+":"+frequency;
        }
    }
}
