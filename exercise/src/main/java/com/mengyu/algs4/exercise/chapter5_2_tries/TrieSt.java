package com.mengyu.algs4.exercise.chapter5_2_tries;

import com.mengyu.algs4.utils.StTemplate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class TrieSt<Value> implements StTemplate<Value> {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        TrieSt<Integer> trieST = new TrieSt<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            trieST.put(entry.getKey(), entry.getValue());
        }
        System.out.println(trieST.get("sells"));
        Queue<String> queue = trieST.keysWithPrefix("sh");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue = trieST.keys();
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue = trieST.keysThatMatch("se..s");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        System.out.println(trieST.longestPrefixOf("sheIsAGirl"));
        trieST.delete("sea");
        System.out.println("------------");
        System.out.println(trieST.size);
    }

    private static final int R = 256;

    private Node root;

    private int size;

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (key.length() == index) {
            if (node.val != null) {
                node.val = null;
                size--;
            }
        } else {
            char chr = key.charAt(index);
            node.next[chr] = delete(node.next[chr], key, index + 1);
        }
        if (node.val != null) {
            return node;
        }
        for (int i = 0; i < R; i++) {
            if (node.next[i] != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    public String longestPrefixOf(String str) {
        int len = collect(root, str, 0, 0);
        return str.substring(0, len);
    }

    private int collect(Node node, String str, int index, int length) {
        if (node == null || index == str.length()) {
            return length;
        }
        if (node.val != null) {
            length = index;
        }
        return collect(node.next[str.charAt(index)], str, index + 1, length);
    }

    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> que = new LinkedList<>();
        search(root, pattern, "", que);
        return que;
    }

    private void search(Node node, String pattern, String pre, Queue<String> que) {
        if (node == null) {
            return;
        }
        if (pattern.length() == pre.length() && node.val != null) {
            que.offer(pre);
        }
        if (pattern.length() == pre.length()) {
            return;
        }
        char chr = pattern.charAt(pre.length());
        for (char i = 0; i < R; i++) {
            if (i == chr || chr == '.') {
                search(node.next[i], pattern, pre + i, que);
            }
        }
    }

    @Override
    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> que = new LinkedList<>();
        collect(get(root, pre, 0), pre, que);
        return que;
    }

    private void collect(Node node, String pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            queue.offer(pre);
        }
        for (char i = 0; i < R; i++) {
            collect(node.next[i], pre + i, queue);
        }
    }

    @Override
    public Value get(String key) {
        Node node = get(root, key, 0);
        return node != null ? (Value) node.val : null;
    }

    private Node get(Node node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            return node;
        }
        return get(node.next[key.charAt(index)], key, index + 1);
    }

    @Override
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, Value val, int index) {
        if (node == null) {
            node = new Node();
        }
        if (key.length() == index) {
            size = val == null ? size : size + 1;
            node.val = val;
            return node;
        }
        node.next[key.charAt(index)] = put(node.next[key.charAt(index)], key, val, index + 1);
        return node;
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    private static class Node {
        private Object val;

        private Node[] next = new Node[R];
    }
}
