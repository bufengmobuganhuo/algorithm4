package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_2_Tries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/6/2 11:15
 * 单词查找树
 */
public class TrieST<Value> implements StTemplate<Value> {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        TrieST<Integer> trieST = new TrieST<>();
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

    /**
     * ASCII码范围
     */
    private static final int R = 256;
    private Node root;
    private int size;

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int len) {
        if (node == null) {
            return null;
        }
        //找到了要被删除键，则将其值设为空
        if (len == key.length()) {
            if (node.val != null) {
                node.val = null;
                size--;
            }
        } else {
            char chr = key.charAt(len);
            node.next[chr] = delete(node.next[chr], key, len + 1);
        }
        //没有找到要删除的键
        if (node.val != null) {
            return node;
        }
        for (char chr = 0; chr < R; chr++) {
            // 如果node（被删除键的尾节点）有非空连接，则在node.val=null后直接返回
            if (node.next[chr] != null) {
                return node;
            }
        }
        //如果没有非空连接，则删除该节点
        return null;
    }

    /**
     * @param str
     * @return 给定一个字符串，从查找树的键中找到这个字符串的最长前缀
     */
    @Override
    public String longestPrefixOf(String str) {
        int length = search(root, str, 0, 0);
        return str.substring(0, length);
    }

    private int search(Node node, String str, int index, int length) {
        if (node == null) {
            return length;
        }
        //如果找到了一个完整的键
        if (node.val != null) {
            length = index;
        }
        char chr = str.charAt(index);
        return search(node.next[chr], str, index + 1, length);
    }

    /**
     * @param pattern
     * @return 查找匹配pattern的所有键（带有通配符）
     */
    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pattern, queue);
        return queue;
    }

    private void collect(Node node, String pre, String pattern, Queue<String> queue) {
        if (node == null) {
            return;
        }
        int preLen = pre.length();
        //找到了匹配的键
        if (preLen == pattern.length() && node.val != null) {
            queue.offer(pre);
        }
        //虽然找到了匹配的字符串，但不是键（val==null）
        if (preLen == pattern.length()) {
            return;
        }
        char nextPat = pattern.charAt(preLen);
        for (char chr = 0; chr < R; chr++) {
            //只遍历匹配的字符
            if (nextPat == '.' || nextPat == chr) {
                collect(node.next[chr], pre + chr, pattern, queue);
            }
        }
    }

    /**
     * @return 获取所有键
     */
    @Override
    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * @param pre
     * @return 所有以pre开头的键
     */
    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    private void collect(Node node, String pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        //如果是一个完整的键，则放入队列
        if (node.val != null) {
            queue.offer(pre);
        }
        for (char chr = 0; chr < R; chr++) {
            collect(node.next[chr], pre + chr, queue);
        }
    }

    @Override
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    private Node put(Node node, String key, Value value, int index) {
        if (node == null) {
            node = new Node();
        }
        //查找命中，则更新值
        if (index == key.length()) {
            //如果val=null，说明之前键不存在，需要+1,否则说明键已经存在
            size = node.val == null ? size + 1 : size;
            node.val = value;
            return node;
        }
        //此处的整数值就已经代表了一个字符
        int chrIndex = key.charAt(index);
        node.next[chrIndex] = put(node.next[chrIndex], key, value, index + 1);
        return node;
    }

    /**
     * @param key
     * @return 根据键查找
     */
    @Override
    public Value get(String key) {
        Node res = get(root, key, 0);
        return res == null ? null : (Value) res.val;
    }

    private Node get(Node node, String key, int index) {
        //遇到空连接，查找未命中
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            return node;
        }
        int chrIndex = key.charAt(index);
        return get(node.next[chrIndex], key, index + 1);
    }

    /**
     * @return 统计符号表的大小（不是单词查找树所有节点个数）
     */
    public int size() {
        return size;
    }

    public static class Node {
        public Object val;
        public Node[] next = new Node[R];
    }
}
