package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/3/12 11:17
 * 练习2.5.24：稳定的优先队列
 */
public class EX_2_5_24_StablePriorityQueue<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] arr={1,5,1,3,8,1,5};
        EX_2_5_24_StablePriorityQueue<Integer> ex_2_5_24_stablePriorityQueue=new EX_2_5_24_StablePriorityQueue<>();
        for (int i=0;i<arr.length;i++){
            ex_2_5_24_stablePriorityQueue.insert(arr[i]);
        }
        for (int i=0;i<arr.length;i++){
            ex_2_5_24_stablePriorityQueue.delMin();
        }
    }
    private Node<T>[] minPriorityQueue;
    private int size;
    private int orderCount=0;

    public EX_2_5_24_StablePriorityQueue() {
        minPriorityQueue= new Node[16];
    }

    public Node<T> delMin(){
        if (isEmpty()){
            return null;
        }
        Node min=minPriorityQueue[1];
        exch(1,size);
        minPriorityQueue[size--]=null;
        sink(1);
        System.out.println("值："+min.value+" 索引："+min.index);
        return min;
    }

    public void insert(T item){
        Node node=new Node();
        node.value=item;
        node.index=orderCount++;
        minPriorityQueue[++size]=node;
        swim(size);
    }

    private void sink(int k){
        while (2*k<=size){
            int j=2*k;
            if (j<size&&less(minPriorityQueue[j+1],minPriorityQueue[j])){
                j++;
            }
            if (less(minPriorityQueue[k],minPriorityQueue[j])){
                break;
            }
            exch(k,j);
            k=j;
        }
    }

    private void swim(int k){
        while (k>1&&less(minPriorityQueue[k],minPriorityQueue[k/2])){
            exch(k,k/2);
            k/=2;
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void exch(int i, int j){
        Node temp=minPriorityQueue[i];
        minPriorityQueue[i]=minPriorityQueue[j];
        minPriorityQueue[j]=temp;
    }

    private boolean less(Node node1,Node node2){
        return node1.compareTo(node2)<0;
    }

    class Node<K extends Comparable<K>> implements Comparable<Node<K>>{
        K value;
        int index;
        @Override
        public int compareTo(Node<K> o) {
            if (value.compareTo(o.value)<0){
                return -1;
            }else if(value.compareTo(o.value)>0){
                return 1;
            }else if (index<o.index){
                return -1;
            }else{
                return 1;
            }
        }
    }
}
