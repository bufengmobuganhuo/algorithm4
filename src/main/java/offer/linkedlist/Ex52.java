package offer.linkedlist;

/**
 * @author yuzhang
 * @date 2020/12/16 上午10:06
 * TODO
 */
public class Ex52 {
    /**
     * 假设两个链表长度分别为L1+C、L2+C，C为公共部分的长度，按照如下走法：
     * 1. 第一个人走了L1+C步后，回到第二个人起点走L2步
     * 2. 第2个人走了L2+C步后，回到第一个人起点走L1步
     * 3. 当两个人走的步数都为L1+L2+C时就两个家伙就相爱了
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode ptrA = headA;
        ListNode ptrB = headB;
        while (ptrA != ptrB) {
            ptrA = ptrA == null ? headB : ptrA.next;
            ptrB = ptrB == null ? headA : ptrB.next;
        }
        return ptrA;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
