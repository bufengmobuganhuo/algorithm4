package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

/**
 * @author yuzhang
 * @date 2021/2/2 上午8:45
 * TODO
 */
public class Ex_1_3_30 {
    public static void main(String[] args) {
        LinkedListNode<Integer> head = new LinkedListNode<>(1);
        head.next = new LinkedListNode<>(2);
        head.next.next = new LinkedListNode<>(3);
        head.next.next.next = new LinkedListNode<>(4);
        head.next.next.next.next = new LinkedListNode<>(5);
        head = reverseByRecursive(head);
        while (head != null) {
            System.out.println(head.key);
            head = head.next;
        }
    }

    public static LinkedListNode<Integer> reverseByIterate(LinkedListNode<Integer> head) {
        if (head == null) {
            return null;
        }
        LinkedListNode<Integer> lastNode = null;
        LinkedListNode<Integer> curNode = head;
        while (curNode != null) {
            LinkedListNode<Integer> nextNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = nextNode;
        }
        return lastNode;
    }

    public static LinkedListNode<Integer> reverseByRecursive(LinkedListNode<Integer> head){
        if (head==null){
            return null;
        }
        if (head.next==null){
            return head;
        }
        LinkedListNode<Integer> nextNode = head.next;
        // 负责找到最后两个节点，然后开启反转过程，除此之外没有其他作用
        LinkedListNode<Integer> restNode = reverseByRecursive(head.next);
        nextNode.next = head;
        head.next = null;
        return restNode;
    }
}
