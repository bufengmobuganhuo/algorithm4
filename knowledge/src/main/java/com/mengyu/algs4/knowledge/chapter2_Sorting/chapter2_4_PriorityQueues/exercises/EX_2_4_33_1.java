package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/6/24 10:37 上午
 * 练习2.4.33：第一次尝试
 * 索引优先队列：根据索引来访问元素
 */
public class EX_2_4_33_1<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.createInt(8, 15);
        System.out.println(Arrays.toString(arr));
        EX_2_4_33_1<Integer> ex_2_4_33_1 = new EX_2_4_33_1<>(8);
        for (int i = 0; i < arr.length; i++) {
            ex_2_4_33_1.insert(i,arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {

            System.out.println(ex_2_4_33_1.delMin());
        }
    }

    /**
     * 存储索引
     */
    private int[] pq;
    /**
     * 存储键，由索引找到键
     */
    private Key[] keys;
    /**
     * qp[i]:索引i在pq中的位置
     * pq[qp[i]]=i
     */
    private int[] qp;
    /**
     * 实际含有多少元素
     */
    private int size;

    public EX_2_4_33_1(int maxSize) {
        pq = new int[maxSize + 1];
        qp = new int[maxSize + 1];
        keys= (Key[]) new Comparable[maxSize+1];
    }

    /**
     *
     * @param idx 删除索引对应的键
     * @return
     */
    public void delete(int idx){
        exch(idx,size--);
        sink(qp[idx]);
        swim(qp[idx]);
        keys[pq[size+1]]=null;
        qp[pq[size+1]]=-1;
    }

    /**
     *
     * @param k 索引k已经存在，但是要更换键
     * @param key
     */
    public void change(int k,Key key){
        keys[k]=key;
        sink(k);
        swim(k);
    }

    public int minIdx(){
        return pq[1];
    }

    public Key min(){
        return keys[pq[1]];
    }

    /**
     * 索引K是否存在
     * @param k
     * @return
     */
    public boolean contains(int k){
        return qp[k]!=-1;
    }

    public void insert(int index, Key item) {
        qp[index] = ++size;
        pq[size] = index;
        keys[index] = item;
        swim(size);
    }

    public Key delMin() {
        if (size == 0) {
            return null;
        }
        int indexOfMin = pq[1];
        exch(1, size--);
        Key res = keys[indexOfMin];
        //索引对应的位置为-1，说明不存在
        qp[indexOfMin] = -1;
        keys[indexOfMin] = null;
        pq[size + 1] = -1;
        sink(1);
        return res;
    }

    /**
     * 上浮
     *
     * @param k 插入元素的索引在pq中的位置，即pq[k]=index,qp[index]=k
     */
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    /**
     * @param k 索引对应的位置
     */
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j + 1 < size && less(j + 1, j)) {
                j++;
            }
            if (less(k, j)) {
                break;
            }
            exch(j, k);
            k = j;
        }
    }

    /**
     * @param i 元素索引对应的位置
     * @param j 元素索引对应的位置
     * @return
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    /**
     * @param i 索引对应的位置
     * @param j 索引对应的位置
     */
    private void exch(int i, int j) {
        int tempIndex = pq[i];
        pq[i] = pq[j];
        pq[j] = tempIndex;

        qp[pq[i]] = j;
        qp[pq[j]] = i;
    }

}
