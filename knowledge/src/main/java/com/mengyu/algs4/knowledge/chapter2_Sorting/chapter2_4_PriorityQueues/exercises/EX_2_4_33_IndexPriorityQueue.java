package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

/**
 * @author zhangyu
 * 2020/3/1 11:00
 * 练习2.4.33：索引优先队列
 */
public class EX_2_4_33_IndexPriorityQueue<Key extends Comparable<Key>> implements UtilTemplate{
    public static void main(String[] args) {
        //Integer[] arr= ArrayUtil.createInt(8,15);
        Integer[] arr={4,4,2,0,12,13,8,3};
        EX_2_4_33_IndexPriorityQueue<Integer> indexPriorityQueue=new EX_2_4_33_IndexPriorityQueue<>(8);
        for (int i=0;i<arr.length;i++){
            indexPriorityQueue.insert(i,arr[i]);
        }
        //System.out.println("删除最大值"+indexPriorityQueue.delMax());
        indexPriorityQueue.change(2,5);
        System.out.println("返回最大值"+indexPriorityQueue.max());
        for (int i=0;i<arr.length;i++){
            System.out.print(indexPriorityQueue.delMax()+" ");
        }
    }
    private int size;
    /**
     * 索引二叉堆，从1开始，在上浮/下沉时，调整的是该数组
     */
    private int[] pq;
    /**
     * 述数组的逆序，qp[i]的值是“索引i”在pq[]中的位置
     * 即qp[pq[i]]=pq[qp[i]]=i
     * 在上浮/下沉时，调整的是该数组
     */
    private int[] qp;
    /**
     * 有优先级之分的元素,由索引找到keys中的元素
     */
    private Key[] keys;

    //创建一个最大容量为maxSize的优先队列，索引取值范围为：0-maxSize-1
    public EX_2_4_33_IndexPriorityQueue(int maxSize) {
        pq=new int[maxSize+1];
        qp=new int[maxSize+1];
        keys= (Key[]) new Comparable[maxSize+1];
        //一开始索引0-maxSize-1均不存在，故qp的元素设置为-1
        for (int i=0;i<qp.length;i++){
            //pq中的索引i在pq中的位置为-1
            qp[i]=-1;
        }
    }

    //插入一个元素key，将其与索引k关联
    public void insert(int k,Key key){
        size++;
        //索引k在pq中的位置为size,因为插入元素时，是向堆的最后一个位置插入
        qp[k]=size;
        pq[size]=k;
        //由索引k找到元素
        keys[k]=key;
        swim(size);
    }
    //将索引为k的元素设置为key
    public void change(int k,Key key){
        if(!contains(k)){
            throw new IllegalArgumentException();
        }
        keys[k]=key;
        //需要传入索引K在pq中的位置
        swim(qp[k]);
        sink(qp[k]);
    }
    //是否存在索引为k的元素
    public boolean contains(int k){
        return qp[k]!=-1;
    }
    //删除索引k及其关联元素
    public void delete(int k){
        int indexOfDel=pq[k];
        //传入索引对应的位置
        exch(qp[k],size--);
        sink(qp[k]);
        swim(qp[k]);
        //由索引找到key
        keys[k]=null;
        qp[k]=-1;
    }
    //返回最大元素
    public Key max(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return keys[pq[1]];
    }
    //返回最大元素的索引
    public int maxIndex(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return pq[1];
    }
    //删除最大元素并返回它的索引
    public int delMax(){
        int indexOfMax=pq[1];
        exch(1,size--);
        sink(1);
        keys[indexOfMax]=null;
        qp[indexOfMax]=-1;
        pq[size+1]=-1;
        return indexOfMax;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    };

    /**
     * @param k 对应的索引在pq中的位置
     */
    private void swim(int k){
        while (k>1&&less(k/2,k)){
            exch(k,k/2);
            k/=2;
        }
    }

    /**
     * @param k 对应的索引在pq中的位置
     */
    private void sink(int k){
        while (2*k<=size){
            int j=2*k;
            //取最大值
            if (j<size&&less(j,j+1)){
                j++;
            }
            //如果子节点的最大值>待下沉元素，停止下沉
            if (less(j,k)){
                break;
            }
            exch(k,j);
            k=j;
        }
    }

    /**
     * @param i 对应的索引在pq中的位置
     * @param j 对应的索引在pq中的位置
     */
    private void exch(int i, int j){
        //因为keys是通过索引来确定的，所以只需交换索引位置即可
        int indexTemp=pq[i];
        pq[i]=pq[j];
        pq[j]=indexTemp;

        //前面pq的值已经做过交换
        qp[pq[i]]=i;
        qp[pq[j]]=j;
    }

    /**
     * @param i 对应的索引在pq中的位置
     * @param j 对应的索引在pq中的位置
     * @return
     */
    private boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]])<0;
    }


}
