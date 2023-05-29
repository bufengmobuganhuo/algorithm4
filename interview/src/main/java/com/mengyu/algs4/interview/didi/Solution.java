package com.mengyu.algs4.interview.didi;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuzhang
 * @date 2021/3/31 下午8:47
 * TODO
 */
public class Solution {
    private List<List<Integer>> res;
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("1",1);
        map.put("1",2);
        System.out.println(map.get("1"));
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode lastNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = nextNode;
        }
        return lastNode;
    }


    static class ListNode {
        int val;
        ListNode next;
    }
}
