package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

/**
 * @author yuzhang
 * @date 2020/9/29 8:42 上午
 * TODO
 */
public class LinkedList_2<T> {
    public static void main(String[] args) {
        LinkedList_2<Integer> linkedList = new LinkedList_2<>();
        int[] nums = {1,1,2,1,3,1,4,5,6};
        for (int i = 0; i < nums.length; i++) {
            linkedList.add(nums[i]);
        }
        linkedList.delete(3);
        linkedList.remove(1);
        LinkedNode<Integer> first = linkedList.reverseByIterable();
        while (first!=null){
            System.out.print(first.item+" ");
            first= first.next;
        }
        System.out.println();
        first = linkedList.reverseByRecursive();
        while (first!=null){
            System.out.print(first.item+" ");
            first= first.next;
        }
    }
    private int size;
    private LinkedNode<T> first;
    private LinkedNode<T> last;

    public void add(T item) {
        LinkedNode<T> oldLast = last;
        last = new LinkedNode<T>(item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public T delete(int k) {
        if (k >= size) {
            return null;
        }
        LinkedNode<T> pre = null;
        LinkedNode<T> cur = first;
        for (int i = 0; i < k; i++) {
            pre = cur;
            cur = cur.next;
        }
        if (pre != null) {
            pre.next = cur.next;
        } else {
            first = cur.next;
        }
        cur.next = null;
        size--;
        return cur.item;
    }

    public void remove(T item) {
        if (isEmpty()) {
            return;
        }
        // 从头开始的
        while (first.item.equals(item)) {
            first = first.next;
            size--;
        }
        // 如果全删完了，则结束
        if (isEmpty()) {
            return;
        }
        LinkedNode<T> pre = first;
        LinkedNode<T> cur = first.next;
        while (cur != null) {
            if (cur.item.equals(item)) {
                pre.next = cur.next;
                size--;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public LinkedNode<T> reverseByIterable() {
        if (isEmpty()) {
            return null;
        }
        LinkedNode<T> pre = null;
        LinkedNode<T> cur = first;
        while (cur != null) {
            LinkedNode<T> tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        first = pre;
        return pre;
    }

    public LinkedNode<T> reverseByRecursive() {
        return reverseByRecursive(first);
    }

    private LinkedNode<T> reverseByRecursive(LinkedNode<T> head) {
        if (isEmpty()) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        LinkedNode<T> next = head.next;
        LinkedNode<T> rest = reverseByRecursive(next);
        next.next = head;
        head.next = null;
        return rest;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class LinkedNode<T> {
        T item;
        LinkedNode<T> next;

        public LinkedNode(T item, LinkedNode<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}


