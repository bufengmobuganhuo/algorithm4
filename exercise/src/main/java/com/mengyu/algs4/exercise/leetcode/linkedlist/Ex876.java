package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yuzhang
 * @date 2020/7/3 11:24 上午
 * 链表的中间结点
 */
public class Ex876 {
    public ListNode middleNode(ListNode head) {
        if (head==null){
            return null;
        }
        // 快慢指针
        ListNode slower=head;
        ListNode faster=head;
        while(faster!=null&&faster.next!=null){
            slower=slower.next;
            faster=faster.next.next;
        }
        return slower;
    }
}
