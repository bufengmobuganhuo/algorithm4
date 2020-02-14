package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangyu
 * 2020/2/11 14:31
 * 练习2.2.17:链表排序
 */
public class EX_2_2_17<T extends Comparable> {
    public static void main(String[] args) {

    }
    public void solution(ListNode head){
        if (head==null||head.next==null){
            return;
        }

        ListNode left=head;
        //第一个有序子链表
        ListNode mid= findBlockSize(left);
        //第二个有序子列表
        ListNode right=findBlockSize(mid.next);
        mid.next=null;
        merge(left,right);
    }

    private ListNode merge(ListNode listNode1,ListNode listNode2){
        ListNode res=new ListNode();
        ListNode current=res;
        while (listNode1!=null||listNode2!=null){
            if (listNode1==null){
                current.next=listNode2;
                listNode2=listNode2.next;
            }else if(listNode2==null){
                current.next=listNode1;
                listNode1=listNode1.next;
            }else if(listNode1.value.compareTo(listNode2.value)<0){
                current.next=listNode1;
                listNode1=listNode1.next;
            }else {
                current=listNode2;
                listNode2=listNode2.next;
            }
            current=current.next;
        }
        return res.next;
    }

    private ListNode findBlockSize(ListNode listNode){
        int size=1;

        return new ListNode();
    }

    private class ListNode{
        T value;
        ListNode next;
    }
}
