package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/6/30 11:53 上午
 * 复制带随机指针的链表,解法二
 */
public class Ex138_2 {
    public static void main(String[] args) {
        Node node=new Node(1);
        node.next=new Node(2);
        node.next.next=new Node(3);
        Ex138_2 ex138_2=new Ex138_2();
        ex138_2.copyRandomList(node);
    }
    public Node copyRandomList(Node head) {
        if (head==null){
            return null;
        }
        // 1.构造一个扭曲链表：A -> A' -> B -> B' -> C -> C'
        Node pointer=head;
        while(pointer!=null){
            Node copyNode=new Node(pointer.val);
            copyNode.next=pointer.next;
            pointer.next=copyNode;
            pointer=pointer.next.next;
        }
        // 2. 为拷贝结点指定random:A'.next.random=A.random.next（要指向拷贝结点）
        pointer=head;
        while(pointer!=null){
            pointer.next.random=(pointer.random!=null)?pointer.random.next:null;
            pointer=pointer.next.next;
        }
        // 3. 将被扭曲的链表还原：A.next=A'.next,A'.next=A.next.next
        pointer=head;
        Node pointerNewListHead=head.next;
        Node pointerNewListNode=head.next;
        while(pointer!=null){
            pointerNewListNode=pointer.next;
            pointer.next=pointer.next.next;
            pointerNewListNode.next=(pointerNewListNode.next!=null)?pointerNewListNode.next.next:null;
            pointer=pointer.next;
        }
        return pointerNewListHead;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }
}
