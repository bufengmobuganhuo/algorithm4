package leetcode.linkedlist;

/**
 * @author yu zhang
 */
public class Ex147_1 {
    public static void main(String[] args) {
        ListNode head = new ListNode(8);
        head.next = new ListNode(5);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next = new ListNode(3);
        Ex147_1 ex147_1 = new Ex147_1();
        System.out.println(ex147_1.insertionSortList(head));
    }
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }
            ListNode next = cur.next;
            pre.next = next;
            cur.next = null;
            head = insert(head, cur);
            cur = next;
        }
        return head;
    }

    private ListNode insert(ListNode head, ListNode target) {
        if (target.val < head.val) {
            target.next = head;
            head = target;
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null && cur.val <= target.val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur == null) {
            pre.next = target;
            return head;
        }
        pre.next = target;
        target.next = cur;
        return head;
    }
}
