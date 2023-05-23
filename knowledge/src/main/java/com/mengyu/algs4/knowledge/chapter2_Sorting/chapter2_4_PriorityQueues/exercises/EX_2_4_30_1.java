package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2020/6/24 10:00 上午
 * 练习2.4.30：第一次尝试
 */
public class EX_2_4_30_1 {
    public static void main(String[] args) {
        EX_2_4_30_1 ex_2_4_30_1=new EX_2_4_30_1();
        Integer[] arr= ArrayUtil.createInt(9,15);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            ex_2_4_30_1.insert(arr[i]);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(ex_2_4_30_1.deleteMid());
    }
    private Integer mid;
    private PriorityQueue<Integer> rightPq;
    private PriorityQueue<Integer> leftPq;

    public EX_2_4_30_1() {
        rightPq = new PriorityQueue<>();
        leftPq=new PriorityQueue<>(new maxComparator());
    }

    public int deleteMid() {
        int temp = mid;
        if (leftPq.size() - rightPq.size() == 1) {
            mid = leftPq.poll();
        } else {
            mid = rightPq.poll();
        }
        balance();
        return temp;
    }

    public int getMid() {
        return mid;
    }

    public void insert(Integer item) {
        if (leftPq.size()+rightPq.size()==0){
            mid=item;
        }
        if (mid > item) {
            leftPq.offer(item);
        } else {
            rightPq.offer(item);
        }
        balance();
    }

    /**
     * 调整左右堆，使其size相差不超过1
     */
    private void balance() {
        //如果有奇数个则左右要平衡，如果是偶数，则无所谓了
        int compare=(rightPq.size()+leftPq.size()+1)%2==0?0:1;
        while (rightPq.size() - leftPq.size() > compare) {
            leftPq.offer(mid);
            mid = rightPq.poll();
        }
        while (leftPq.size() - rightPq.size() > compare) {
            rightPq.offer(mid);
            mid = leftPq.poll();
        }
    }

    class maxComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }
}
