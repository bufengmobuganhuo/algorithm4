package leetcode.linkedlist;

import org.omg.CORBA.INTERNAL;
import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/6/30 4:44 下午
 * 对链表进行插入排序
 */
public class Ex147 {
    public static void main(String[] args) {
        ListNode node=new ListNode(4);
        node.next=new ListNode(3);
        node.next.next=new ListNode(7);
        node.next.next.next=new ListNode(2);
        node.next.next.next.next=new ListNode(5);
        Ex147 ex147=new Ex147();
        ListNode head= ex147.insertionSortList(node);
    }

    public ListNode insertionSortList(ListNode head) {
        if (head==null){
            return null;
        }
        // 哨兵结点
        ListNode dummyNode=new ListNode(Integer.MIN_VALUE);
        dummyNode.next=head;
        while(head!=null&&head.next!=null){
            // 已经有序
            if (head.val<=head.next.val){
                head=head.next;
                continue;
            }
            ListNode pre=dummyNode;
            //从头开始，找到最后一个小于head.next.val的结点:pre
            while(pre.next.val<=head.next.val){
                pre=pre.next;
            }
            // 将head.next插入在pre前面，因为从dummy一直到head都是有序的，
            // 所以只需要在该位置插入
            ListNode cur=head.next;
            head.next=cur.next;
            cur.next=pre.next;
            pre.next=cur;
        }
        return dummyNode.next;
    }

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 数组的插入排序
     * @param arr
     */
    private static void insertSort(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {
                Integer temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }
}
