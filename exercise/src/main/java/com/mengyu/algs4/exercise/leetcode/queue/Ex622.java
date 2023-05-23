package com.mengyu.algs4.exercise.leetcode.queue;

/**
 * @author yuzhang
 * @date 2020/7/10 9:10 上午
 * leetcode 622
 */
public class Ex622 {
    public static void main(String[] args) {
        Ex622 ex622=new Ex622(8);
        ex622.enQueue(3);
        ex622.enQueue(9);
        ex622.enQueue(5);
        ex622.enQueue(0);
        System.out.println(ex622.deQueue());
        System.out.println(ex622.deQueue());
        System.out.println(ex622.isEmpty());
        System.out.println(ex622.isEmpty());
        System.out.println(ex622.Rear());
        System.out.println(ex622.Rear());
        System.out.println(ex622.deQueue());

    }
    private int[] arr;
    private int frontPtr;
    // 队列长度
    private int queueLen;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public Ex622(int k) {
        frontPtr=0;
        arr=new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()){
            queueLen++;
            int rearPtr=(frontPtr+queueLen-1)%arr.length;
            rearPtr=rearPtr<arr.length?rearPtr:0;
            arr[rearPtr]=value;
            return true;
        }
        return false;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    // 从队首删除
    public boolean deQueue() {
        if (!isEmpty()){
            frontPtr=frontPtr<arr.length-1?frontPtr+1:0;
            queueLen--;
            return true;
        }
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (!isEmpty()){
            return arr[frontPtr];
        }
        return -1;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (!isEmpty()){
            int rearPtr=(frontPtr+queueLen-1)%arr.length;
            return arr[rearPtr];
        }
        return -1;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return queueLen==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return queueLen==arr.length;
    }
}
