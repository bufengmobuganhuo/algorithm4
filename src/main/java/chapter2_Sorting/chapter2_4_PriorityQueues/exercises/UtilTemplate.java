package chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

/**
 * @author zhangyu
 * 2020/2/26 10:52
 * 工具类
 */
public interface UtilTemplate<T extends Comparable<T>> {
    default boolean isEmpty(int size){
        return size==0;
    }
    default boolean less(T value1,T value2){
        return value1.compareTo(value2)<0;
    }
    default void exchange(T[] priorityQue, int i,int j){
        T temp=priorityQue[i];
        priorityQue[i]=priorityQue[j];
        priorityQue[j]=temp;
    }
    default T[] resize(T[] priorityQue,int length,int size){
        T[] temp=(T[]) new Comparable[length];
        System.arraycopy(priorityQue,1,temp,1,size);
        return temp;
    }
}
