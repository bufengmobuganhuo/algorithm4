package leetcode.linkedlist;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/2 10:44 上午
 * 两数相加，解法二
 */
public class Ex445_1 {

    private int lastCarry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        Stack<Integer> lStack1 = new Stack<>();
        Stack<Integer> lStack2 = new Stack<>();
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        while (ptr1 != null) {
            lStack1.push(ptr1.val);
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            lStack2.push(ptr2.val);
            ptr2 = ptr2.next;
        }
        ListNode head = null;
        while (!lStack1.isEmpty() && !lStack2.isEmpty()) {
            int num1 = lStack1.pop();
            int num2 = lStack2.pop();
            int sum = num1 + num2 + lastCarry;
            if (sum > 9) {
                lastCarry = 1;
                sum -= 10;
                head = insert(head, sum);
            } else {
                lastCarry = 0;
                head = insert(head, sum);
            }
        }
        head = addLeftNode(lStack1, head);
        head = addLeftNode(lStack2, head);
        if (lastCarry>0){
            head=insert(head,1);
        }
        return head;
    }

    private ListNode addLeftNode(Stack<Integer> stack, ListNode head) {
        while (!stack.isEmpty()) {
            int num = stack.pop() + lastCarry;
            if (num > 9) {
                lastCarry = 1;
                num -= 10;
                head = insert(head, num);
            } else {
                lastCarry = 0;
                head = insert(head, num);
            }
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

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
