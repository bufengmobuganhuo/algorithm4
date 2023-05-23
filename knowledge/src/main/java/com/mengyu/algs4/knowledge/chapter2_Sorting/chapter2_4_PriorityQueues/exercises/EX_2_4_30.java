package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhangyu
 * 2020/2/27 15:01
 * 练习2.4.30：动态中位数查找
 * 对于一组排好序的数：1，2，3，4，5，6，7
 * 1. 4是他们的中位数，如果将4之前的数做成一个大顶堆，4之后的中位数组成一个小顶堆，这两个堆的大小之差不应当>1
 *    则3，5分别在堆顶
 * 2. 后续如果删除4这个中位数，那么新的中位数在3（大顶堆的堆顶元素）或5（小顶堆的堆顶元素）中产生，这取决于两个堆的大小，
 *    从元素较多的堆中产生
 * 3. 插入元素时，根据插入元素与中位数的大小关系放入大/小顶堆中（从较多元素的堆中删除元素作为新中位数，将原中位数插入到较少
 *    元素的堆中）
 * 4. 删除中位数时，从元素较多的堆中删除堆顶元素作为中位数，再进行上述调整
 */
public class EX_2_4_30 {
    public static void main(String[] args) {
        EX_2_4_30 ex_2_4_30=new EX_2_4_30();
        Integer[] arr= ArrayUtil.createInt(10,15);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            ex_2_4_30.insert(arr[i]);
        }
        System.out.println();
        for (int i=0;i<arr.length;i++){
            System.out.print(ex_2_4_30.delMid()+" ");
        }
    }
    //java 中默认是小顶堆,存储中位数右边元素
    private PriorityQueue<Integer> minPriorityQueue;
    private PriorityQueue<Integer> maxPriorityQueue;

    private Integer midian;
    private int size;


    public EX_2_4_30() {
        minPriorityQueue=new PriorityQueue<>();
        maxPriorityQueue=new PriorityQueue<>(new minComparator());
    }

    public Integer delMid(){
        if (size==1){
            size=0;
            return midian;
        }else{
            Integer res=midian;
            if (minPriorityQueue.size()>maxPriorityQueue.size()){
                midian=minPriorityQueue.poll();
            }else{
                midian=maxPriorityQueue.poll();
            }
            size--;
            updateQueue();
            return res;
        }
    }

    public void insert(Integer item){
        if (size==0){
            size++;
            midian=item;
            return;
        }
        size++;
        if(item>=midian){
            minPriorityQueue.add(item);
        }else{
            maxPriorityQueue.add(item);
        }
        updateQueue();
    }

    private void updateQueue(){
        //从较多元素的堆中删除元素作为新中位数，将原中位数放入元素较少堆中
        while (minPriorityQueue.size()-maxPriorityQueue.size()>1){
            Integer item=minPriorityQueue.poll();
            maxPriorityQueue.add(midian);
            midian=item;
        }
        while (maxPriorityQueue.size()-minPriorityQueue.size()>1){
            Integer item=maxPriorityQueue.poll();
            minPriorityQueue.add(midian);
            midian=item;
        }
    }

    public Integer delMax(){
        return maxPriorityQueue.remove();
    }

    public Integer delMin(){
        return minPriorityQueue.remove();
    }

    class minComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }
}
