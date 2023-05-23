package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class TestMain {
    public static void main(String[] args) {
/*        StackBasedOnArr<Integer> stackBasedOnArr =new StackBasedOnArr<Integer>();
        for (int i=0;i<10;i++){
            stackBasedOnArr.push(i);
        }
        for (Integer item: stackBasedOnArr){
            System.out.println(stackBasedOnArr.pop());
        }*/
        StackBasedOnLinkedList<Integer> stackBasedOnLinkedList=new StackBasedOnLinkedList<Integer>();
        for (int i=0;i<10;i++){
            stackBasedOnLinkedList.push(i);
        }
/*        for (int i=0;i<11;i++){
            System.out.println(stackBasedOnLinkedList.pop());
        }*/
        Iterator<Integer> iterator=stackBasedOnLinkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        QueueBasedOnLinkedList<String> queue=new QueueBasedOnLinkedList<String>();
        while (!StdIn.isEmpty()){
            String item=StdIn.readString();
            if (!"-".equals(item)){
                queue.enqueue(item);
            }else if(!queue.isEmpty()){
                System.out.print(queue.dequeue()+" ");
            }
        }
        System.out.println(queue.length+" left on queue");

        Iterator<String> iteratorQueue=queue.iterator();
        while (iteratorQueue.hasNext()){
            System.out.println(iteratorQueue.next());
        }
    }
}
