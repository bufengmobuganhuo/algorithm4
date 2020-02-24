package chapter2_Sorting.chapter2_4_PriorityQueues;

/**
 * @author zhangyu
 * 2020/2/20 15:08
 *
 */
public class TestMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue =new PriorityQueue<>(20);
        for (int i=0;i<20;i++){
            priorityQueue.insert(i);
        }
        for (int i=0;i<20;i++){
            System.out.println(priorityQueue.delMax());
        }
    }
}
