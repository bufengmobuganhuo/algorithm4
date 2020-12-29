package bytedance.dec23rd;

/**
 * @author yuzhang
 * @date 2020/12/23 上午9:34
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        //head.next = new ListNode(2);
        //head.next.next = new ListNode(3);
        //head.next.next.next = new ListNode(4);
        //head.next.next.next.next = new ListNode(5);
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.removeNthFromEnd(head,1));
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return null;
        }
        ListNode lastLeftPtr = null;
        ListNode leftPtr = head;
        ListNode rightPtr = head;
        int i = 0;
        while (i < n - 1){
            rightPtr = rightPtr.next;
            i++;
        }
        while (rightPtr.next!=null){
            rightPtr = rightPtr.next;
            lastLeftPtr = leftPtr;
            leftPtr = leftPtr.next;
        }
        if (lastLeftPtr!=null){
            lastLeftPtr.next = leftPtr.next;
        } else{
            head = leftPtr.next;
        }
        return head;
    }

      static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
