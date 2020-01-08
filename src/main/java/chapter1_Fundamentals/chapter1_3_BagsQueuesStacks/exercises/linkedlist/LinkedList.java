package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import jdk.nashorn.internal.ir.ReturnNode;
import sun.plugin.cache.OldCacheEntry;

import java.util.Iterator;

/**
 * 链表相关练习
 */
public class LinkedList<T extends Comparable> implements Iterable{
    private Node first;
    private Node last;
    private int size;
    private T maxValue;
    public boolean isEmpty(){
        return size==0;
    }
    public void add(T item){
        Node oldLast=last;
        last=new Node();
        last.item=item;
        if (isEmpty()){
            first=last;
        }else{
            oldLast.next=last;
        }
        size++;
    }

    /**
     * @param item 练习1.3.26 删除指定值
     */
    public void remove(T item){
        if (isEmpty()){
            return;
        }
        Node current=first;
        Node lastCurrent=first;
        //先删除头结点，如果头结点一直都是要删除的元素，则一直删除
        while (first!=null){
            if (!first.item.equals(item)){
                break;
            }
            first=first.next;
            size--;
        }
        while (current!=null){
            if (current.item.equals(item)){
                lastCurrent.next=current.next;
            }else{
                lastCurrent=current;
            }
            current=current.next;
            size--;
        }
    }

    /**
     * @return 练习1.3.28 使用递归查找链表最大值
     */
    public T max(){
        if (first==null){
            return null;
        }
        maxValue=first.item;
        return max(first);
    }
    private T max(Node currentHead){
        if (currentHead==null){
            return maxValue;
        }
        maxValue=currentHead.item.compareTo(maxValue)>0?currentHead.item:maxValue;
        return max(currentHead.next);
    }
    public Iterator iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T>{
        private Node current=first;
        public boolean hasNext() {
            return current!=null;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            T item=current.item;
            current=current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class Node{
        T item;
        Node next;
    }
}
