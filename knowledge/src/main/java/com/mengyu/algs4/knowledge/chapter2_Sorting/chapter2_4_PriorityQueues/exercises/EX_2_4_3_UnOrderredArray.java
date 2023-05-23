package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/21 18:29
 * T练习2.4.3：使用无序数组实现优先队列
 */
public class EX_2_4_3_UnOrderredArray<T extends Comparable<T>> {
    public static void main(String[] args) {
        EX_2_4_3_UnOrderredArray<Integer> ex_2_4_3_orderredArray=new EX_2_4_3_UnOrderredArray<>();
        Integer[] arr= ArrayUtil.createInt(10,15);
        for (Integer item:arr){
            ex_2_4_3_orderredArray.insert(item);
        }
        for (int i=0;i<arr.length;i++){
            System.out.println(ex_2_4_3_orderredArray.delMax());
        }
    }
    private T[] unOrderredArray;
    private int size;

    public EX_2_4_3_UnOrderredArray() {
        unOrderredArray=(T[]) new Comparable[16];
    }

    public void insert(T item){
        size++;
        if (size==unOrderredArray.length){
            resize(unOrderredArray.length*2);
        }
        unOrderredArray[size-1]=item;
    }

    public T delMax(){
        int maxIdx=0;
        for (int i=1;i<size;i++){
            if (unOrderredArray[maxIdx].compareTo(unOrderredArray[i])<0){
                maxIdx=i;
            }
        }
        T res=unOrderredArray[maxIdx];
        exchange(unOrderredArray,maxIdx,size-1);
        size--;
        if (size>0&&size<unOrderredArray.length/4){
            resize(unOrderredArray.length/2);
        }
        return res;
    }

    private void exchange(T[] unOrderredArray,int i, int j){
        T temp=unOrderredArray[i];
        unOrderredArray[i]=unOrderredArray[j];
        unOrderredArray[j]=temp;
    }

    private void resize(int len){
        T[] temp= (T[]) new Comparable[len];
        System.arraycopy(unOrderredArray,0,temp,0,size);
        unOrderredArray=temp;
    }
}
