package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.StackBasedOnLinkedList;

/**
 * 用栈实现队列
 */
public class EX_1_3_49<T> {
    private StackBasedOnLinkedList<T> enStack;
    private StackBasedOnLinkedList<T> deStack;
    private int size;

    public EX_1_3_49() {
        enStack=new StackBasedOnLinkedList<T>();
        deStack=new StackBasedOnLinkedList<T>();
    }

    public void enqueue(T item){
        while (!deStack.isEmpty()){
            enStack.push(deStack.pop());
        }
        enStack.push(item);
        size++;
    }

    public T dequeue(){
        if (size==0){
            throw new IndexOutOfBoundsException();
        }
        while (!enStack.isEmpty()){
            deStack.push(enStack.pop());
        }
        size--;
        return deStack.pop();
    }
}
