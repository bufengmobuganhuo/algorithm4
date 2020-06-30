package leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/6/27 10:01 上午
 * TODO
 */
public class Ex92 {
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
//        head.next.next.next=new ListNode(4);
//        head.next.next.next.next=new ListNode(5);
        reverseBetween(head,1,2);
    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int i = 1;
        ListNode root = head;
        ListNode preNode = null;
        while (i < m && root != null) {
            preNode = root;
            root = root.next;
            i++;
        }
        ListNode preNodeForReverse = null;
        ListNode nextNodeForReverse = null;
        ListNode rootNodeForReverse = root;
        i = 0;
        int len = n - m + 1;
        while (i <= len) {
            if (i == len) {
                nextNodeForReverse = rootNodeForReverse;
                break;
            }
            ListNode tmp = rootNodeForReverse.next;
            rootNodeForReverse.next = preNodeForReverse;
            preNodeForReverse = rootNodeForReverse;
            rootNodeForReverse = tmp;
            i++;
        }
        if (preNode!=null){
            preNode.next = preNodeForReverse;
        }else{
            head=preNodeForReverse;
        }
        root.next = nextNodeForReverse;
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}