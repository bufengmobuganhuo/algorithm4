package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import com.sun.org.apache.bcel.internal.generic.FieldOrMethod;
import edu.princeton.cs.algs4.In;
import org.omg.CORBA.INTERNAL;

/**
 * @author zhangyu
 * 2020/5/12 15:03
 * 练习1.3.20，1.3.26，1.3.30：第一次尝试
 */
public class LinkedList_1<T extends Comparable<T>> {
    public static void main(String[] args) {
        LinkedList_1<Integer> linkedList_1=new LinkedList_1<>();
        linkedList_1.delete(0);
        linkedList_1.println();
        linkedList_1.insert(0);
        linkedList_1.delete(0);
        linkedList_1.println();
        linkedList_1.insert(1);
        linkedList_1.insert(2);
        linkedList_1.println();
        linkedList_1.insert(3);
        linkedList_1.insert(4);
        linkedList_1.insert(3);
        linkedList_1.remove(3);
        linkedList_1.println();
        LinkedListNode node = linkedList_1.reverse( linkedList_1.first);
        node=linkedList_1.reverseByRecursive(node);
        while(node!=null){
            System.out.print(node.key+"-");
            node=node.next;
        }
    }
    private LinkedListNode<T> first;
    private int size;

    public void insert(T key){
        if (first==null){
            first=new LinkedListNode(key,null);
            size++;
            return;
        }
        first= new LinkedListNode(key,first);
        size++;
    }

    public T delete(int index){
        if (index>=size){
            return null;
        }
        if (index==0){
            T key=first.key;
            first=first.next;
            size--;
            return key;
        }
        LinkedListNode<T> tempNode=first;
        LinkedListNode<T> lastNode=null;
        for (int i=1;i<= index;i++){
            lastNode=tempNode;
            tempNode=tempNode.next;
        }
        lastNode.next=tempNode.next;
        size--;
        return tempNode.key;
    }

    public void remove(T key){
        if (size==0){
            return;
        }
        //如果头结点就是要删除的，则一直删除
        while (first!=null){
            if (first.key.compareTo(key)!=0){
                break;
            }
            first=first.next;
            size--;
        }
        LinkedListNode tempNode=first;
        LinkedListNode lastNode=tempNode;
        while (tempNode!=null){
            if (tempNode.key.compareTo(key)==0){
                lastNode.next=tempNode.next;
                size--;
            }else{
                lastNode = tempNode;
            }
            tempNode=tempNode.next;
        }
    }

    public LinkedListNode reverse(LinkedListNode x){
        if (x==null){
            return null;
        }
        LinkedListNode lastNode=null;
        LinkedListNode curNode=x;
        while (curNode!=null){
            LinkedListNode tempNode=curNode.next;
            curNode.next=lastNode;

            lastNode=curNode;
            curNode=tempNode;
        }
        return lastNode;
    }

    public LinkedListNode reverseByRecursive(LinkedListNode first){
        if (first==null){
            return null;
        }
        if (first.next==null){
            return first;
        }
        LinkedListNode second=first.next;
        LinkedListNode restNode=reverseByRecursive(second);
        second.next=first;
        first.next=null;
        return restNode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        LinkedListNode node = first;
        while (node!=null){
            stringBuilder.append(node.key+"-");
            node=node.next;
        }
        return stringBuilder.toString();
    }

    public void println(){
        System.out.println(toString());
    }
}
class LinkedListNode<T extends Comparable<T>>{
    T key;
    LinkedListNode next;

    public LinkedListNode(T key) {
        this.key = key;
    }

    public LinkedListNode(T key, LinkedListNode next) {
        this.key = key;
        this.next = next;
    }
}