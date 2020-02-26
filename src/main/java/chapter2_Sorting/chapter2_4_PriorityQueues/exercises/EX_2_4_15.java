package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import chapter2_Sorting.chapter2_4_PriorityQueues.PriorityQueue;
import utils.ArrayUtil;

import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * @author zhangyu
 * 2020/2/24 10:38
 * 练习2.4.15：判断是否是大顶堆
 */
public class EX_2_4_15<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        EX_2_4_15<Integer> ex_2_4_15=new EX_2_4_15<>();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        for (int i=0;i<arr.length;i++){
            ex_2_4_15.insert(arr[i]);
            priorityQueue.insert(arr[i]);
        }
        System.out.println(ex_2_4_15.isMinQueue());
        System.out.println(ex_2_4_15.isMinQueue(priorityQueue.getPq(),priorityQueue.getN()));
        for (int i=0;i<arr.length;i++){
            System.out.print(ex_2_4_15.delMax()+" ");
        }
    }
    private T[] priorityQueue;
    private int size;

    public EX_2_4_15() {
        priorityQueue=(T[])new Comparable[5];
    }

    private boolean isMinQueue(T[] priorityQueue,int size){
        //堆中元素不可为null
        for (int i=1;i<=size;i++){
            if (priorityQueue[i]==null){
                return false;
            }
        }
        //数组中其他元素不可不为null
        for (int i=size+1;i<priorityQueue.length;i++){
            if (priorityQueue[i]!=null){
                return false;
            }
        }
        if (priorityQueue[0]!=null){
            return false;
        }
        return isMinQueue(priorityQueue,1,size);
    }

    private boolean isMinQueue(T[] priorityQueue,int k,int size){
        if (k>size){
            return true;
        }
        int leftNode=2*k;
        int rightNode=2*k+1;
        if (leftNode<=size&&greater(priorityQueue[k],priorityQueue[leftNode])){
            return false;
        }
        if (rightNode<=size&&greater(priorityQueue[k],priorityQueue[rightNode])){
            return false;
        }
        return isMinQueue(priorityQueue,leftNode,size)&&isMinQueue(priorityQueue,rightNode,size);
    }

    private boolean isMinQueue(){
        //堆中元素不可为null
        for (int i=1;i<=size;i++){
            if (priorityQueue[i]==null){
                return false;
            }
        }
        //数组中其他元素不可不为null
        for (int i=size+1;i<priorityQueue.length;i++){
            if (priorityQueue[i]!=null){
                return false;
            }
        }
        if (priorityQueue[0]!=null){
            return false;
        }
        return isMinQueue(1);
    }

    private boolean isMinQueue(int k){
        if (k>size){
            return true;
        }
        int leftNode=2*k;
        int rightNode=leftNode+1;
        if (leftNode<=size&&greater(k,leftNode)){
            return true;
        }
        if (rightNode<=size&&greater(k,rightNode)){
            return false;
        }
        return isMinQueue(leftNode)&&isMinQueue(rightNode);
    }

    public T delMax(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        exch(size,1);
        T res=priorityQueue[size];
        priorityQueue[size--]=null;
        sink(1);
        if (size<=priorityQueue.length/4){
            resize(priorityQueue.length/2);
        }
        return res;
    }

    public void insert(T item){
        if (size==priorityQueue.length-1){
            resize(priorityQueue.length*2);
        }
        priorityQueue[++size]=item;
        swim(size);
    }

    private void sink(int k){
        while (2*k<=size){
            int j=2*k;
            if (j<size&&greater(j,j+1)){
                j++;
            }
            if (greater(j,k)){
                break;
            }
            exch(k,j);
            k=j;
        }
    }

    private void swim(int k){
        while (k>1&&greater(k/2,k)){
            exch(k,k/2);
            k/=2;
        }
    }

    private void resize(int len){
        T[] temp=(T[]) new Comparable[len];
        System.arraycopy(priorityQueue,1,temp,1,size);
        priorityQueue=temp;
    }

    private boolean greater(int i,int j){
        return priorityQueue[i].compareTo(priorityQueue[j])>0;
    }

    private boolean greater(T value1,T value2){
        return value1.compareTo(value2)>0;
    }

    private void exch(int i, int j){
        T temp=priorityQueue[i];
        priorityQueue[i]=priorityQueue[j];
        priorityQueue[j]=temp;
    }

    public boolean isEmpty(){
        return size==0;
    }
}
