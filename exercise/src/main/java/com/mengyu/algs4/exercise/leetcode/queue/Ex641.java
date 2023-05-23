package com.mengyu.algs4.exercise.leetcode.queue;

/**
 * @author yuzhang
 * @date 2020/7/10 11:13 上午
 * leetcode641
 */
public class Ex641 {
    public static void main(String[] args) {
        Ex641 ex641=new Ex641(3);
        System.out.println(ex641.insertLast(1));
        System.out.println(ex641.insertLast(2));
        System.out.println(ex641.insertFront(3));
        System.out.println(ex641.insertFront(4));
        System.out.println(ex641.getRear());
        System.out.println(ex641.isFull());
        System.out.println(ex641.deleteLast());
        System.out.println(ex641.insertFront(4));
        System.out.println(ex641.getFront());
        System.out.println();
    }
    private int[] arr;
    private int frontPtr;
    private int rearPtr;
    private int size;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public Ex641(int k) {
        arr=new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (!isFull()){
            arr[frontPtr]=value;
            frontPtr=frontPtr>0?frontPtr-1:arr.length-1;
            size++;
            return true;
        }
        return false;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (!isFull()){
            rearPtr=rearPtr<arr.length-1?rearPtr+1:0;
            arr[rearPtr]=value;
            size++;
            return true;
        }
        return false;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (!isEmpty()){
            frontPtr=frontPtr<arr.length-1?frontPtr+1:0;
            size--;
            return true;
        }
        return false;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (!isEmpty()){
            rearPtr=rearPtr>0?rearPtr-1:arr.length-1;
            size--;
            return true;
        }
        return false;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (!isEmpty()){
            int tmpPtr=frontPtr<arr.length-1?frontPtr+1:0;
            return arr[tmpPtr];
        }
        return -1;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (!isEmpty()){
            return arr[rearPtr];
        }
        return -1;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size==arr.length;
    }
}
