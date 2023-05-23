package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/21 10:31
 * 练习2.4.3：使用有序数组实现优先队列
 */
public class EX_2_4_3_OrderredArray<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(20,150);
        EX_2_4_3_OrderredArray<Integer> ex_2_4_3_orderredArray =new EX_2_4_3_OrderredArray<>();
        for (Integer item:arr){
            ex_2_4_3_orderredArray.insert(item);
        }
        for (int i=0;i<arr.length;i++){
            System.out.println(ex_2_4_3_orderredArray.delMax());
        }
    }
    private T[] orderredArr;
    //优先队列大小
    private int size;

    public EX_2_4_3_OrderredArray() {
        orderredArr = (T[]) new Comparable[16];
    }

    public T delMax(){
        if (isEmpty()){
            return null;
        }
        if (size>0&&size< orderredArr.length/4){
            resize(orderredArr.length/2);
        }
        return orderredArr[--size];
    }

    public void insert(T item){
        if (size== orderredArr.length-1){
            resize(orderredArr.length*2);
        }
        int i=size-1;
        while (i>=0&&less(item, orderredArr[i])){
            orderredArr[i+1]= orderredArr[i];
            i--;
        }
        orderredArr[i+1]=item;
        size++;
    }
    private boolean less(Comparable value1,Comparable value2){
        return value1.compareTo(value2)<0;
    }

    private int findIdx(T item){
        for (int i=0;i<=size;i++){
            if (orderredArr[i]==null){
                return i;
            }
            if (item.compareTo(orderredArr[i])>0){
                return i;
            }
        }
        return size;
    }

    private void resize(int len){
        T[] temp=(T[]) new Comparable[len];
        System.arraycopy(orderredArr,0,temp,0,size);
        orderredArr =temp;
    }

    private void moveLeft(int start){
        System.arraycopy(orderredArr,start, orderredArr,start-1,size-start);
    }

    private void moveRight(int start){
        System.arraycopy(orderredArr,start, orderredArr,start+1,size-start);
    }

    public boolean isEmpty(){
        return size==0;
    }

}
