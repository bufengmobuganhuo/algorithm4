package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;


import utils.ArrayUtil;

import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/2/21 18:54
 * 练习2.4.3：使用有序链表实现优先队列
 */
public class EX_2_4_3_OrderredLinkedList<T extends Comparable<T>> {
    public static void main(String[] args) {
        EX_2_4_3_OrderredLinkedList<Integer> ex_2_4_3_orderredLinkedList=new EX_2_4_3_OrderredLinkedList<>();
        Integer[] arr= ArrayUtil.createInt(10,15);

        for (Integer item:arr){
            ex_2_4_3_orderredLinkedList.insert(item);
            System.out.print(item+" ");
        }
        System.out.println("");
        for (int i=0;i<arr.length;i++){
            System.out.print(ex_2_4_3_orderredLinkedList.delMax()+" ");
        }
    }
    private LinkedList<T> priorityQueue;
    private int size;

    public EX_2_4_3_OrderredLinkedList() {
        priorityQueue=new LinkedList<>();
    }

    public void insert(T item){
        int idx=0;
        for (;idx<priorityQueue.size();idx++){
            if (item.compareTo(priorityQueue.get(idx))>0){
                break;
            }
        }
        priorityQueue.add(idx,item);
        size++;
    }

    private boolean less(Comparable value1,Comparable value2){
        return value1.compareTo(value2)<0;
    }

    public T delMax(){
        size--;
        return priorityQueue.pollFirst();
    }
}
