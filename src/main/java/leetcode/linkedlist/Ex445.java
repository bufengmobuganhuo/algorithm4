package leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/7/2 9:49 上午
 * 两数相加，解法一
 */
public class Ex445 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        //l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(2);
        //l2.next = new ListNode(6);
        //l2.next.next = new ListNode(4);
        Ex445 ex445 = new Ex445();
        ListNode res = ex445.addTwoNumbers(l1, l2);
    }

    // 上一次的进位
    private int lastCarry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        // 将链表反转，然后进行相加
        ListNode rL1 = reverse(l1);
        ListNode rL2 = reverse(l2);
        ListNode head = null;

        while (rL1 != null && rL2 != null) {
            int sum = rL1.val + rL2.val + lastCarry;
            if (sum >= 10) {
                sum -= 10;
                lastCarry = 1;
                head= insert(head, sum);
            } else {
                lastCarry = 0;
                head = insert(head, sum);
            }
            rL1 = rL1.next;
            rL2 = rL2.next;
        }
        head = addLeftNodes(rL1, head);
        head = addLeftNodes(rL2, head);
        if (lastCarry > 0) {
            head = insert(head, lastCarry);
        }
        return head;
    }

    private ListNode addLeftNodes(ListNode node, ListNode head) {
        while (node != null) {
            int sum = node.val + lastCarry;
            if (sum >= 10) {
                lastCarry = 1;
                sum -= 10;
                head = insert(head, sum);
            } else {
                lastCarry = 0;
                head = insert(head, sum);
            }
            node = node.next;
        }
        return head;
    }

    private ListNode insert(ListNode head, int val) {
        if (head == null) {
            head = new ListNode(val);
            return head;
        }
        ListNode node = new ListNode(val);
        node.next = head;
        return node;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
