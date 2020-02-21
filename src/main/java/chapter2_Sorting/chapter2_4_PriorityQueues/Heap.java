package chapter2_Sorting.chapter2_4_PriorityQueues;

import chapter2_Sorting.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/20 11:04
 * 大顶堆
 */
public class Heap<T extends Comparable<T>>{
    private T[] pq;
    //优先队列的大小
    private int N;

    public Heap(int size) {
        pq=(T[]) new Comparable[size+1];
    }

    public Heap() {
        pq=(T[]) new Comparable[16];
    }

    public T delMax(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        //将根节点与最后一个元素交换
        exchange(1,N);
        //令交换后的根节点下沉，以在正确位置
        sink(1);
        T max=pq[N];
        pq[N--]=null;
        if (N>=(pq.length+1)/4){
            resize(pq.length/2);
        }
        return max;
    }


    /**
     * @return 返回最大元素
     */
    public T max(){
        if (isEmpty()){
            throw new  IndexOutOfBoundsException();
        }
        return pq[1];
    }

    /**
     * @param value 插入元素
     */
    public void insert(T value){
        if (N==pq.length+1){
            resize(pq.length*2);
        }
        pq[++N]=value;
        swim(N);
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exchange(int i,int j){
        T temp=pq[i];
        pq[i]=pq[j];
        pq[j]=temp;
    }

    private void resize(int length){
        T[] temp=(T[]) new Comparable[length];
        System.arraycopy(pq,1,temp,1,N);
        pq=temp;
    }

    /**
     * @param k
     * 当子节点“变得”>父节点时，需要将子节点从下层到上层移动（上浮）
     */
    public void swim(int k){
        //一直和父节点交换位置
        while (k>1&&less(k/2,k)){
            exchange(k/2,k);
            k/=2;
        }
    }

    /**
     * @param k
     * 当父节点“变得”<子节点时，需要将父节点从上层到下层移动（下沉）
     */
    public void sink(int k){
        while (2*k<=N){
            int j=2*k;
            //比较左右节点，取较大值
            if (j<N&&less(j,j+1)){
                j++;
            }
            //如果子结点中较大值<要移动的父节点，则停止移动父节点
            if (less(j,k)){
                break;
            }
            //左右子节点的大小是不一定的
            exchange(j,k);
            k=j;
        }
    }
}
