package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import utils.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * 2020/2/27 10:35
 * 练习2.4.29：同时面向最大和最小元素的优先队列
 */
public class EX_2_4_29<T extends Comparable<T>> implements UtilTemplate<T>{
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        EX_2_4_29<Integer> ex_2_4_29=new EX_2_4_29<>();
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            ex_2_4_29.insert(arr[i]);
        }
        System.out.println();
        for (int i=0;i<arr.length;i++){
            System.out.print("删除最大值："+ex_2_4_29.delMax()+" ");
            System.out.println("删除最小值："+ex_2_4_29.delMin()+" ");
        }
    }
    private minPriorityQueue minPriorityQueue;
    private maxPriorityQueue maxPriorityQueue;
    private int size;

    public EX_2_4_29() {
        minPriorityQueue=new minPriorityQueue();
        maxPriorityQueue=new maxPriorityQueue();
    }

    public T delMin(){
        Ele<T> res=minPriorityQueue.delMin();
        maxPriorityQueue.remove(res.pair.index);
        size--;
        return res.value;
    }

    public T delMax(){
        Ele<T> res=maxPriorityQueue.delMax();
        minPriorityQueue.remove(res.pair.index);
        size--;
        return res.value;
    }

    public void insert(T item){
        List<Ele<T>> nodes=getNodes(item,++size);
        minPriorityQueue.insert(nodes.get(0));
        maxPriorityQueue.insert(nodes.get(1));
    }

    private List<Ele<T>> getNodes(T item,int index){
        Ele<T> minPriorityQueEle=new Ele<>(item,index);
        Ele<T> maxPriorityQueEle=new Ele<>(item,index);
        minPriorityQueEle.pair=maxPriorityQueEle;
        maxPriorityQueEle.pair=minPriorityQueEle;
        return new ArrayList<Ele<T>>(){
            {
                add(minPriorityQueEle);
                add(maxPriorityQueEle);
            }
        };
    }

    class maxPriorityQueue{
        private Ele<T>[] priorityQue;
        private int size;

        public maxPriorityQueue() {
            priorityQue=new Ele[16];
        }

        public Ele<T> delMax(){
            return remove(1);
        }

        public Ele<T> remove(int k){
            exch(k,size);
            Ele<T> res=priorityQue[size];
            priorityQue[size--]=null;
            sink(k);
            return res;
        }

        public void insert(Ele<T> item){
            priorityQue[++size]=item;
            swim(size);
        }

        private void sink(int k){
            while (2*k<=size){
                int j=2*k;
                if (j<size&&less(priorityQue[j].value,priorityQue[j+1].value)){
                    j++;
                }
                if (less(priorityQue[j].value,priorityQue[k].value)){
                    break;
                }
                exch(k,j);
                k*=2;
            }
        }

        private void swim(int k){
            while (k>1&&less(priorityQue[k/2].value,priorityQue[k].value)){
                exch(k/2,k);
                k/=2;
            }
        }

        public void exch(int i, int j){
            //替换另一队列内对于本队列的指针
            priorityQue[i].pair.pair=priorityQue[j];
            priorityQue[j].pair.pair=priorityQue[i];

            Ele<T> tempPair= priorityQue[i].pair;
            T tempValue=priorityQue[i].value;

            //替换本队列内的value值
            priorityQue[i].value=priorityQue[j].value;
            priorityQue[j].value=tempValue;

            //替换本队列内对于另一个队列的指针
            priorityQue[i].pair=priorityQue[j].pair;
            priorityQue[j].pair=tempPair;
        }
    }

    class minPriorityQueue{
        private Ele<T>[] priorityQue;
        private int size;

        public minPriorityQueue() {
            priorityQue=new Ele[16];
        }

        public Ele<T> delMin(){
            return remove(1);
        }

        public Ele<T> remove(int k){
            exch(k,size);
            Ele<T> res=priorityQue[size];
            priorityQue[size--]=null;
            sink(k);
            return res;
        }

        public void insert(Ele<T> item){
            priorityQue[++size]=item;
            swim(size);
        }

        private void sink(int k){
            while (2*k<=size){
                int j=2*k;
                if (j<size&&less(priorityQue[j+1].value,priorityQue[j].value)){
                    j++;
                }
                if (less(priorityQue[k].value,priorityQue[j].value)){
                    break;
                }
                exch(k,j);
                k=j;
            }
        }

        private void swim(int k){
            while (k>1&&less(priorityQue[k].value,priorityQue[k/2].value)){
                exch(k/2,k);
                k/=2;
            }
        }

        public void exch(int i, int j){
            //替换另一队列内对于本队列的指针
            priorityQue[i].pair.pair=priorityQue[j];
            priorityQue[j].pair.pair=priorityQue[i];

            Ele<T> tempPair= priorityQue[i].pair;
            T tempValue=priorityQue[i].value;
            //替换本队列内的value值
            priorityQue[i].value=priorityQue[j].value;
            priorityQue[j].value=tempValue;

            //替换本队列内对于另一个队列的指针
            priorityQue[i].pair=priorityQue[j].pair;
            priorityQue[j].pair=tempPair;

        }
    }

    class Ele<K extends Comparable<K>>{
        K value;
        //该节点在当前队列中的索引
        int index;
        //该节点在另外一个队列中对应的节点
        Ele<K> pair;

        public Ele(K value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
