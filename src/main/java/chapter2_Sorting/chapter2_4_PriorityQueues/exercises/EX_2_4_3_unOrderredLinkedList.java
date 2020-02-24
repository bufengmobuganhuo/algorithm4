package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import utils.ArrayUtil;

import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/2/21 19:12
 * 练习2.4.3：使用有无序链表实现优先队列
 */
public class EX_2_4_3_unOrderredLinkedList<T extends Comparable<T>> {
    public static void main(String[] args) {
        EX_2_4_3_unOrderredLinkedList<Integer> ex_2_4_3_unOrderredLinkedList=new EX_2_4_3_unOrderredLinkedList<>();
        Integer[] arr= ArrayUtil.createInt(10,15);

        for (Integer item:arr){
            ex_2_4_3_unOrderredLinkedList.insert(item);
            System.out.print(item+" ");
        }
        System.out.println("");
        for (int i=0;i<arr.length;i++){
            System.out.print(ex_2_4_3_unOrderredLinkedList.delMax()+" ");
        }
    }
    private LinkedList<T> priorityQueue;
    private int size;

    public EX_2_4_3_unOrderredLinkedList() {
        priorityQueue=new LinkedList<>();
    }

    public void insert(T item){
        size++;
        priorityQueue.addFirst(item);
    }

    public T delMax(){
        int maxIdx=0;
        for (int i=0;i<size;i++){
            if (less(priorityQueue.get(maxIdx),priorityQueue.get(i))){
                maxIdx=i;
            }
        }
        size--;
        return priorityQueue.remove(maxIdx);
    }

    private boolean less(Comparable value1, Comparable value2){
        return value1.compareTo(value2)<0;
    }

}
