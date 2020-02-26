package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import chapter2_Sorting.chapter2_4_PriorityQueues.PriorityQueue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/25 11:32
 * 练习2.4.26：无需交换的堆,大顶堆
 */
public class EX_2_4_26<T extends Comparable<T>>{
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,15);
        //Integer[]arr ={9, 4, 4, 2, 8, 3, 14, 10 };
        EX_2_4_26<Integer> priorityQueue =new EX_2_4_26<>();

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
            priorityQueue.insert(arr[i]);
        }
        System.out.println();
        for (int i=0;i<arr.length;i++){
            System.out.print(priorityQueue.delMax()+" ");
        }
    }
    private T[] priorityQue;
    private int size;

    public EX_2_4_26() {
        priorityQue= (T[]) new Comparable[5];
    }

    public T delMax(){
        if(isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        T max=priorityQue[1];
        exchange(1,size);
        priorityQue[size--]=null;
        sink(1);
        if (size<=priorityQue.length/4){
            resize(priorityQue.length/2);
        }
        return max;
    }

    public void insert(T item){
        if (size==priorityQue.length-1){
            resize(priorityQue.length*2);
        }
        priorityQue[++size]=item;
        swim(size);
    }

    private void sink(int k){
        T value=priorityQue[k];
        //将较小元素下沉
        while (2*k<=size){
            int j=2*k;
            //找到较大值
            if (j<size&&less(priorityQue[j],priorityQue[j+1])){
                j++;
            }
            if(less(priorityQue[j],value)){
                break;
            }
            //将左右子节点的较大值上浮
            priorityQue[j/2]=priorityQue[j];
            k=j;
        }
        //将指定元素下沉到指定位置
        priorityQue[k]=value;
    }

    private void swim(int k){
        //将要上浮的元素保存下来
        T value=priorityQue[k];
        int j=k;
        //将较大元素上浮
        while (j>1&&less(priorityQue[j/2],value)){
            //如果要上浮的元素>当前元素，则将当前元素下沉
            priorityQue[j]=priorityQue[j/2];
            j/=2;
        }
        //j保存了要上浮元素应该在的位置
        priorityQue[j]=value;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private boolean less(T value1,T value2){
        return value1.compareTo(value2)<0;
    }
    private void exchange(int i,int j){
        T temp=priorityQue[i];
        priorityQue[i]=priorityQue[j];
        priorityQue[j]=temp;
    }
    private void resize(int length){
        T[] temp=(T[]) new Comparable[length];
        System.arraycopy(priorityQue,1,temp,1,size);
        priorityQue=temp;
    }
}
