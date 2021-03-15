package chapter5_Strings.chapter5_2_Tries.exercises;

import chapter5_Strings.chapter5_2_Tries.StTemplate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/4 上午9:24
 * TODO
 */
public class TrieStTry<Value> implements StTemplate<Value> {
    private static final int R = 256;
    private Node<Value> root;
    private int size;

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node<Value> delete(Node<Value> node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            if (node.val != null) {
                size--;
                node.val = null;
            }
        } else {
            int chr = key.charAt(index);
            node.next[chr] = delete(node.next[chr], key, index + 1);
        }
        if (node.val != null) {
            return node;
        }
        for (int i = 0; i < R; i++) {
            if (node.next[i].val != null) {
                return node.next[i];
            }
        }
        return null;
    }

    @Override
    public String longestPrefixOf(String str) {
        int len = collect(root, str, 0, 0);
        return str.substring(0, len);
    }

    private int collect(Node<Value> node, String str, int index, int length) {
        if (node == null) {
            return length;
        }
        if (node.val != null) {
            length = index;
        }
        int chr = str.charAt(index);
        return collect(node.next[chr], str, index + 1, length);
    }

    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        search(root, pattern, 0, "", queue);
        return queue;
    }

    private void search(Node<Value> node, String pattern, int index, String pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (index == pattern.length() && node.val != null) {
            queue.offer(pre);
        }
        if (index == pattern.length()) {
            return;
        }
        int chr = pattern.charAt(index);
        for (int i = 0; i < R; i++) {
            if (chr == '.' || chr == i) {
                search(node.next[i], pattern, index + 1, pre + i, queue);
            }
        }
    }

    @Override
    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    private void collect(Node<Value> node, String pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            queue.offer(pre);
        }
        for (int i = 0; i < R; i++) {
            collect(node.next[i], pre + i, queue);
        }
    }

    @Override
    public Value get(String key) {
        Node<Value> res = get(root, key, 0);
        return res != null ? res.val : null;
    }

    private Node<Value> get(Node<Value> node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            return node;
        }
        int nextChr = key.charAt(index);
        return get(node.next[nextChr], key, index + 1);
    }

    @Override
    public void put(String key, Value value) {
        put(root, key, value, 0);
    }

    private Node<Value> put(Node<Value> node, String key, Value value, int index) {
        if (node == null) {
            node = new Node<>();
        }
        if (index == key.length()) {
            size = node.val != null ? size : size + 1;
            node.val = value;
            return node;
        }
        int nextChr = key.charAt(index);
        return put(node.next[nextChr], key, value, index + 1);
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    static class Node<Value> {
        Node<Value>[] next = new Node[R];
        Value val;
    }
}
