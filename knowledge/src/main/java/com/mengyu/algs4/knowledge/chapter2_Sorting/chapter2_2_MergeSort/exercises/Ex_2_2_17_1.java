package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

import com.mengyu.algs4.utils.ArrayUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/10/29 10:27 上午
 * TODO
 */
public class Ex_2_2_17_1 {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.createInt(10,20);
        System.out.println(Arrays.toString(arr));
        ListNode head = null;
        ListNode tmp = null;
        for (int i = 0; i < arr.length; i++) {
            if (tmp==null){
                tmp=new ListNode(arr[i]);
                head = tmp;
                continue;
            }else{
                tmp.next=new ListNode(arr[i]);
            }
            tmp=tmp.next;
        }
        head = sort(head);
        while (head!=null){
            System.out.print(head.val+" ");
            head=head.next;
        }
    }

    private static ListNode sort(ListNode head) {
        if (head == null) {
            return null;
        }
        while (true) {
            ListNode startNode = head;
            ListNode mid = findNextBlock(startNode);
            if (mid.next == null) {
                return head;
            }
            while (mid != null && mid.next != null) {
                ListNode endNode = findNextBlock(mid.next);
                if (startNode == head) {
                    head = merge(startNode, mid, endNode);
                } else {
                    startNode.next = merge(startNode.next, mid, endNode);
                }

                startNode = mid.val > endNode.val ? mid : endNode;
                mid=findNextBlock(startNode.next);
            }
        }
    }

    private static ListNode merge(ListNode start, ListNode mid, ListNode end) {
        ListNode leftPtr = start;
        ListNode rightPtr = mid.next;
        ListNode head = null;
        ListNode current = null;

        mid.next = null;
        ListNode lastNode = end.next;
        end.next = null;

        if (leftPtr.val > rightPtr.val) {
            head = rightPtr;
            rightPtr = rightPtr.next;
        } else {
            head = leftPtr;
            leftPtr = leftPtr.next;
        }
        current = head;

        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.val > rightPtr.val) {
                current.next = rightPtr;
                rightPtr = rightPtr.next;
            } else {
                current.next = leftPtr;
                leftPtr = leftPtr.next;
            }
            current = current.next;
        }

        current.next = leftPtr == null ? rightPtr : leftPtr;

        if (mid.next == null) {
            mid.next = lastNode;
        } else {
            end.next = lastNode;
        }

        return head;
    }

    private static ListNode findNextBlock(ListNode startNode) {
        if (startNode == null) {
            return null;
        }
        ListNode endNode = startNode;
        while (endNode.next != null) {
            if (endNode.val > endNode.next.val) {
                break;
            }
            endNode = endNode.next;
        }
        return endNode;
    }

    static class ListNode {
        Integer val;
        ListNode next;

        public ListNode(Integer val) {
            this.val = val;
        }
    }
}
