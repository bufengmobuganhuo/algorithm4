package chapter2_Sorting.chapter2_2_MergeSort.exercises;

/**
 * @author zhangyu
 * 2020/2/11 14:31
 * 练习2.2.17:链表排序
 */
public class EX_2_2_17<T extends Comparable> {
    public static void main(String[] args) {

    }

    public void solution(ListNode head) {
        if (head == null) {
            return;
        }

        while (true) {
            ListNode lo = head;
            //第一个有序子链表的结尾
            ListNode mid = findSortedBlockSize(lo);
            // 为null，说明已经有序
            if (mid.next == null) {
                return;
            }
            while (mid != null && mid.next != null) {
                // 第二个有序子链表的结尾
                ListNode hi = findSortedBlockSize(mid.next);
                //如果是第一次归并,归并返回的有序子链表的头结点就是整个链表的头结点
                if (lo == head) {
                    head = merge(lo, mid, hi);
                } else {
                    lo.next = merge(lo.next, mid, hi);
                }

                // 归并完一部分，从有序子链表的后面开始继续归并
                // lo指向有序子链表的最后一个元素
                // 已知lo -> mid有序，mid.next -> hi有序，则最大的只能是mid或hi
                lo = mid.value.compareTo(hi.value) > 0 ? mid : hi;

                mid = findSortedBlockSize(lo.next);
            }
        }
    }

    private ListNode merge(ListNode lo, ListNode mid, ListNode hi) {
        // 左子链表头结点
        ListNode leftPtr = lo;
        // 右子链表头结点
        ListNode rightPtr = mid.next;
        // 归并后的新头结点
        ListNode head = null;
        ListNode current = null;

        // 将完整的链表切割成三部分：左子链表，右子链表，剩余链表
        mid.next = null;
        // 将右子链表的尾结点.next保存下来，以便日后连接
        ListNode last = hi.next;
        hi.next = null;

        // 决定头结点
        if (leftPtr.value.compareTo(rightPtr.value) > 0) {
            current = rightPtr;
            rightPtr = rightPtr.next;
        } else {
            current = leftPtr;
            leftPtr = leftPtr.next;
        }
        head = current;

        // 归并
        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.value.compareTo(rightPtr.value) > 0) {
                current.next = rightPtr;
                rightPtr = rightPtr.next;
            } else {
                current.next = leftPtr;
                leftPtr = leftPtr.next;
            }
            current = current.next;
        }

        // 连接剩余部分
        current.next = leftPtr != null ? leftPtr : rightPtr;

        // 将有序子链表与剩余链表相连
        // 已知lo -> mid有序，mid.next -> hi有序
        // 那么有序链表的最后一个元素，只可能是mid或hi
        if (mid.next == null) {
            mid.next = last;
        } else {
            hi.next = last;
        }
        return head;
    }

    /**
     * 寻找有序的块
     *
     * @param listNode
     * @return
     */
    private ListNode findSortedBlockSize(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        ListNode endNode = listNode;
        while (endNode.next != null) {
            if (endNode.next == null || endNode.value.compareTo(endNode.next.value) > 0) {
                break;
            }
            endNode = endNode.next;
        }
        return endNode;
    }

    private class ListNode {
        T value;
        ListNode next;

        public ListNode(T value) {
            this.value = value;
        }
    }
}
