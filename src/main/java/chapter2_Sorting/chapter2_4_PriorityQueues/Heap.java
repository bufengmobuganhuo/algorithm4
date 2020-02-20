package chapter2_Sorting.chapter2_4_PriorityQueues;

import chapter2_Sorting.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/20 11:04
 * TODO
 */
public class Heap<T extends Comparable>{
    private T[] pq;
    //数组的大小
    private int N;
    private boolean less(int i,int j){
        return pq[i].compareTo(j)<0;
    }
    private void exchange(int i,int j){
        T temp=pq[i];
        pq[i]=pq[j];
        pq[j]=temp;
    }

    /**
     * @param k
     * 当子节点“变得”>父节点时，需要将子节点从下层到上层移动（上浮）
     */
    public void swim(int k){
        //一直和父节点交换位置
        while (k>1&&less(k/2,k)){
            exchange(k/2,k);
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
            exchange(j,k);
        }
    }
}
