package bytedance.jan5th;

/**
 * @author yuzhang
 * @date 2021/1/5 上午8:51
 * TODO
 */
public class Ex1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode ptr1 = head;
        ListNode ptr2 = head.next;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1 == ptr2) {
                return true;
            }
            ptr1 = ptr1.next;
            if (ptr2.next == null) {
                return false;
            }
            ptr2 = ptr2.next.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
