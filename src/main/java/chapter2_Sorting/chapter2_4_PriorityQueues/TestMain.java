package chapter2_Sorting.chapter2_4_PriorityQueues;

/**
 * @author zhangyu
 * 2020/2/20 15:08
 *
 */
public class TestMain {
    public static void main(String[] args) {
        Heap<Integer> heap=new Heap<>(10);
        for (int i=0;i<10;i++){
            heap.insert(i);
        }
        System.out.println(heap.max());
        System.out.println(heap.delMax());
        System.out.println(heap.max());
    }
}
