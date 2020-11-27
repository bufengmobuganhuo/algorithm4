package chapter5_Strings.chapter5_2_Tries;

import sun.print.DialogOnTop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/24 上午9:08
 * TODO
 */
public class TrieST2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        TrieST2 trieST = new TrieST2();
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
    private int size;
    private TrieNode root;

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private TrieNode delete(TrieNode node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            if (node.value != null) {
                size--;
                node.value = null;
            }
        } else {
            int chr = key.charAt(index);
            node.next[chr] = delete(node.next[chr], key, index + 1);
        }
        if (node.value != null) {
            return node;
        }
        for (char i = 0; i < R; i++) {
            if (node.next[i] != null) {
                return node;
            }
        }
        return null;
    }

    public String longestPrefixOf(String pre) {
        int length = search(root, 0, 0, pre);
        return pre.substring(0, length);
    }

    private int search(TrieNode node, int length, int index, String pre) {
        if (node == null) {
            return length;
        }
        if (node.value != null) {
            length = index;
        }
        int chr = pre.charAt(index);
        return search(node.next[chr], length, index + 1, pre);
    }

    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> res = new LinkedList<>();
        collect(root, "", pattern, res);
        return res;
    }

    private void collect(TrieNode node, String pre, String pattern, Queue<String> queue) {
        if (node == null) {
            return;
        }
        if (pre.length() == pattern.length() && node.value != null) {
            queue.offer(pre);
            return;
        }
        if (pre.length() == pattern.length()) {
            return;
        }
        int nextChr = pattern.charAt(pre.length());
        for (char chr = 0; chr < R; chr++) {
            if (nextChr == '.' || chr == nextChr) {
                collect(node.next[chr], pre + chr, pattern, queue);
            }
        }
    }

    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> res = new LinkedList<>();
        collect(get(root, pre, 0), res, pre);
        return res;
    }

    private void collect(TrieNode node, Queue<String> queue, String pre) {
        if (node == null) {
            return;
        }
        if (node.value != null) {
            queue.offer(pre);
        }
        for (char i = 0; i < R; i++) {
            collect(node.next[i], queue, pre + i);
        }
    }

    public Object get(String key) {
        TrieNode node = get(root, key, 0);
        return node == null ? null : node.value;
    }

    private TrieNode get(TrieNode node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            return node;
        }
        int chrIdx = key.charAt(index);
        return get(node.next[chrIdx], key, index + 1);
    }

    public void put(String key, Object value) {
        root = put(root, key, value, 0);
    }

    private TrieNode put(TrieNode root, String key, Object value, int index) {
        if (root == null) {
            root = new TrieNode();
        }
        if (index == key.length()) {
            size = root.value == null ? size+1 : size;
            root.value = value;
            return root;
        }
        int chrIdx = key.charAt(index);
        root.next[chrIdx] = put(root.next[chrIdx], key, value, index + 1);
        return root;
    }

    static class TrieNode {
        Object value;
        TrieNode[] next = new TrieNode[R];
    }
}
