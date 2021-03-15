package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/5/13 上午8:43
 * TODO
 */
public class Ex19 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        Ex19 ex19 = new Ex19();
        ex19.removeNthFromEnd2(head, 2);
    }

    /**
     * 使用双指针，一个指向奇数节点，一个指向偶数节点，从而可以一次遍历到所有节点，并知道每个节点的索引，从而可以删除
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        List<ListNode> mark = new ArrayList<>();
        ListNode oddPtr = head;
        ListNode evenPtr = null;
        int len = 1;
        while (true) {
            if (evenPtr != null) {
                mark.add(evenPtr);
            }
            if (oddPtr != null) {
                mark.add(oddPtr);
            }
            if (oddPtr != null && oddPtr.next == null) {
                break;
            }
            if (evenPtr != null && evenPtr.next == null) {
                len--;
                break;
            }
            oddPtr = oddPtr.next.next;
            if (evenPtr == null) {
                evenPtr = head.next;
            } else {
                evenPtr = evenPtr.next.next;
            }
            len += 2;
        }
        ListNode deleteNode = mark.get(len - n);
        if (len - n > 0) {
            mark.get(len - n - 1).next = deleteNode.next;
        } else {
            head = deleteNode.next;
        }
        deleteNode.next = null;
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode firstNode = dummy, secondNode = head;
        for (int i = 0; i < n; i++) {
            secondNode = secondNode.next;
        }
        while (secondNode != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        firstNode.next = firstNode.next.next;
        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
