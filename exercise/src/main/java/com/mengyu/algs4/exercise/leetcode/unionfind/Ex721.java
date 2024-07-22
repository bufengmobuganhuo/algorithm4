package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex721 {

    private int[] roots;

    private int[] weights;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 给每个邮箱一个id
        Map<String, Integer> email2Idx = new HashMap<>();
        Map<String, String> email2Name = new HashMap<>();
        int idx = 0;
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!email2Idx.containsKey(email)) {
                    email2Idx.put(email, idx++);
                }
                email2Name.put(email, account.get(0));
            }
        }

        roots = new int[idx];
        weights = new int[idx];
        for (int i = 0; i < idx; i++) {
            roots[i] = i;
            weights[i] = 1;
        }

        for (List<String> account : accounts) {
            // 同一个人下的邮箱的id都属于同一个人，把他们连接起来
            int firstIdx = email2Idx.get(account.get(1));
            for (int i = 2; i < account.size(); i++) {
                int nextIdx = email2Idx.get(account.get(i));
                connect(firstIdx, nextIdx);
            }
        }
        // 哪些email属于同一个人
        Map<Integer, List<String>> idx2Email = new HashMap<>();
        for (String email : email2Idx.keySet()) {
            // 找到每个邮箱的并查集根
            int root = find(email2Idx.get(email));
            idx2Email.computeIfAbsent(root, key -> new ArrayList<>()).add(email);
        }

        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : idx2Email.values()) {
            // emails就是同一个人的邮箱
            Collections.sort(emails);
            // 找到这个人的账户名
            String name = email2Name.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    private void connect(int point1, int point2) {
        int root1 = find(point1), root2 = find(point2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            roots[root2] = root1;
        } else {
            weights[root2] += weights[root1];
            roots[root1] = root2;
        }
    }

    private int find(int point) {
        while (roots[point] != point) {
            roots[point] = roots[roots[point]];
            point = roots[point];
        }
        return point;
    }
}
