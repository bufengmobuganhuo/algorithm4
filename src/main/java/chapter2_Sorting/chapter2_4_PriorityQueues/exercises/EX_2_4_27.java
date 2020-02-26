package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import utils.ArrayUtil;

import java.awt.event.ItemListener;

/**
 * @author zhangyu
 * 2020/2/26 10:48
 * 练习2.4.27：在大顶堆中找到最小值
 */
public class EX_2_4_27<T extends Comparable<T>> implements UtilTemplate<T>{
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        //Integer[]arr ={9, 4, 4, 2, 8, 3, 14, 10 };
        EX_2_4_27<Integer> priorityQueue =new EX_2_4_27<>();

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            priorityQueue.insert(arr[i]);
        }
        System.out.println();
        for (int i=0;i<arr.length;i++){
            System.out.println("最小值："+priorityQueue.getMin());
            System.out.print(priorityQueue.delMax()+" ");
        }
    }
    private T[] priorityQue;
    private int size;
    private T min;

    public EX_2_4_27() {
        priorityQue= (T[]) new Comparable[5];
        min=null;
    }

    public T getMin(){
        return min;
    }

    public T delMax(){
        if (isEmpty(size)){
            min=null;
            throw new ArrayIndexOutOfBoundsException();
        }
        T res=priorityQue[1];
        exchange(priorityQue,1,size--);
        sink(1);
        if (size<=priorityQue.length/4){
            priorityQue=resize(priorityQue,priorityQue.length/2,size);
        }
        return res;
    }

    public void insert(T item){
        if (size==priorityQue.length-1){
            priorityQue= resize(priorityQue,priorityQue.length*2,size);
        }
        if (min==null){
            min= item;
        }else{
            min=min.compareTo(item)<0?min:item;
        }
        priorityQue[++size]=item;
        swim(size);
    }


    private void sink(int k){
        T value=priorityQue[k];
        while (2*k<=size){
            int j=2*k;
            //找到左右子节点的较大值
            if (j<size&&less(priorityQue[j],priorityQue[j+1])){
                j++;
            }
            //如果待下沉元素<较大值，则停止下沉
            if (less(priorityQue[j],value)){
                break;
            }
            //否则将较大元素上浮
            priorityQue[j/2]=priorityQue[j];
            k=j;
        }
        priorityQue[k]=value;
    }

    private void swim(int k){
        T value=priorityQue[k];
        int j=k;
        while (j>1&&less(priorityQue[j/2],value)){
            //将较小的元素下沉
            priorityQue[j]=priorityQue[j/2];
            j/=2;
        }
        priorityQue[j]=value;
    }

}
