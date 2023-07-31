package com.mengyu.algs4.exercise.chapter5_2_tries;

import com.mengyu.algs4.utils.StTemplate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Tst<Value> implements StTemplate<Value> {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        Tst<Integer> tst = new Tst<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            tst.put(entry.getKey(), entry.getValue());
        }
        System.out.println(tst.get("sells"));
        Queue<String> queue = tst.keysWithPrefix("sh");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue = tst.keys();
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        queue = tst.keysThatMatch("se..s");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("------------");
        System.out.println(tst.longestPrefixOf("sheIsAGirl"));
    }

    private int size;

    private TstNode root;

    @Override
    public void delete(String key) {

    }

    @Override
    public String longestPrefixOf(String str) {
        int len = 0;
        TstNode node = root;
        int index = 0;
        while (node != null && index < str.length()) {
            char chr = str.charAt(index);
            if (chr < node.chr) {
                node = node.left;
            } else if (chr > node.chr) {
                node = node.right;
            } else {
                index++;
                if (node.val != null) {
                    len = index;
                }
                node = node.mid;
            }
        }

        return str.substring(0, len);
    }

    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> que = new LinkedList<>();
        collect(root, pattern, "", 0 , que);
        return que;
    }

    private void collect(TstNode node, String pattern, String pre, int index, Queue<String> que) {
        if (node == null) {
            return;
        }
        char chr = pattern.charAt(index);
        if (chr == '.' || chr < node.chr) {
            collect(node.left, pattern, pre, index, que);
        }
        if (chr == '.' || chr == node.chr) {
            if (pattern.length() - 1 == index && node.val != null) {
                que.offer(pre + node.chr);
            }
            if (index < pattern.length() - 1) {
                collect(node.mid, pattern, pre + node.chr, index + 1, que);
            }
        }
        if (chr == '.' || chr > node.chr) {
            collect(node.right, pattern, pre, index, que);
        }
    }

    @Override
    public Queue<String> keys() {
        Queue<String> que = new LinkedList<>();
        collect(root, "", que);
        return que;
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> que = new LinkedList<>();
        TstNode node = get(root, pre, 0);
        if (node == null) {
            return que;
        } else if (node.val != null) {
            que.offer(pre);
        }
        collect(node.mid, pre, que);
        return que;
    }

    private void collect(TstNode node, String pre, Queue<String> que) {
        if (node == null) {
            return;
        }
        collect(node.left, pre, que);
        if (node.val != null) {
            que.offer(pre + node.chr);
        }
        collect(node.mid, pre + node.chr, que);
        collect(node.right, pre, que);
    }

    @Override
    public Value get(String key) {
        TstNode node = get(root, key, 0);
        return node == null ? null : (Value) node.val;
    }

    private TstNode get(TstNode node, String key, int index) {
        if (node == null) {
            return null;
        }
        char chr = key.charAt(index);
        if (chr < node.chr) {
            return get(node.left, key, index);
        } else if (chr > node.chr) {
            return get(node.right, key, index);
        } else if (index < key.length() - 1) {
            return get(node.mid, key, index + 1);
        } else {
            return node;
        }
    }

    @Override
    public void put(String key, Value value) {
        if (!containsKey(key)) {
            size++;
        }
        root = put(root, key, value, 0);
    }

    private TstNode put(TstNode node, String key, Value val, int index) {
        char chr = key.charAt(index);
        if (node == null) {
            node = new TstNode();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = put(node.left, key, val, index);
        } else if (chr > node.chr) {
            node.right = put(node.right, key, val, index);
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, val, index + 1);
        } else {
            node.val = val;
        }
        return node;
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    private static class TstNode {
        private Object val;

        private TstNode left, mid, right;

        private char chr;
    }
}
