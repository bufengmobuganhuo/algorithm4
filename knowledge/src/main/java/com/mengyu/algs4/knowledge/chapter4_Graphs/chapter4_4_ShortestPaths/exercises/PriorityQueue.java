package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/26 10:39
 * TODO
 */
public class PriorityQueue<T extends Comparable<T>> {
    public static void main(String[] args) {

    }
    private T[] pq;
    private int N;

    public PriorityQueue(int size) {
        pq= (T[]) new Comparable[size+1];
    }

    public void insert(T t){
        if (N==pq.length-1){
            resize(N*2);
        }
        pq[++N]=t;
        swim(N);
    }

    public T delMin(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        T res=pq[1];
        exchange(1,N);
        pq[N--]=null;
        sink(1);
        if (N<pq.length/4){
            resize(pq.length/2);
        }
        return res;
    }

    public boolean isEmpty(){
        return N==0;
    }

    private void resize(int cap){
        T[] temp= (T[]) new Comparable[cap];
        System.arraycopy(pq,1,temp,1,N);
        pq=temp;
    }

    /**
     * @param k 上浮
     */
    private void swim(int k){
        while (k>1&&less(k,k/2)){
            exchange(k,k/2);
            k/=2;
        }
    }

    /**
     * @param k 下沉
     */
    private void sink(int k){
        while (2*k<N){
            int j=2*k;
            //取子节点的较小值
            if (less(j+1,j)){
                j++;
            }
            //如果k比较小值还小，则不下沉
            if (less(k,j)){
                break;
            }
            exchange(k,j);
            k=j;
        }
    }

    private void exchange(int i,int j){
        T temp=pq[i];
        pq[i]=pq[j];
        pq[j]=temp;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
}
