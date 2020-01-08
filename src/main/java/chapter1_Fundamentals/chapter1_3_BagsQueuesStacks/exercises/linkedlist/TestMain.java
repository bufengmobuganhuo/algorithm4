package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class TestMain {
    public static void main(String[] args) {
/*        LinkedList<Integer> list=new LinkedList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(0);
        list.remove(0);
        Iterator<Integer> iterator=list.iterator();
        while (iterator.hasNext()){
            StdOut.println(iterator.next());
        }
        StdOut.println(list.max());*/
        EX_1_3_29_QueueBasedOnRingLinkedList<Integer> queue=new EX_1_3_29_QueueBasedOnRingLinkedList<Integer>();
        for (int i=0;i<10;i++){
            queue.enqueue(i);
        }
        Iterator<Integer> iterator=queue.iterator();
        while (iterator.hasNext()){
            StdOut.println(iterator.next());
        }
        for (int i=0;i<11;i++){
            StdOut.println(queue.dequeue());
        }

    }
}
