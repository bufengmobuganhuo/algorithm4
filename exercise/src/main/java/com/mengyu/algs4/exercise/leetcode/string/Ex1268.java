package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1268 {

    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        System.out.println(new Ex1268().suggestedProducts3(products, "mouse"));
    }

    public List<List<String>> suggestedProducts3(String[] products, String searchWord) {
        Arrays.sort(products);
        StringBuilder sb = new StringBuilder();
        int lastIdx = 0;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            sb.append(searchWord.charAt(i));
            int idx = ceil(products, lastIdx, products.length, sb.toString());
            List<String> list = new ArrayList<>();
            for (int j = idx; j < Math.min(idx + 3, products.length); j++) {
                if (products[j].startsWith(sb.toString())) {
                    list.add(products[j]);
                }
            }
            if (idx != products.length) {
                lastIdx = idx;
            }
            ans.add(list);
        }
        return ans;
    }

    private int ceil(String[] arr, int startPtr, int endPtr, String target) {
        while (startPtr < endPtr) {
            int midPtr = startPtr + (endPtr - startPtr) / 2;
            if (arr[midPtr].compareTo(target) <= 0) {
                startPtr = midPtr + 1;
            } else {
                endPtr = midPtr;
            }
        }
        if (endPtr - 1 >= 0 && arr[endPtr - 1].equals(target)) {
            return endPtr - 1;
        }
        return endPtr;
    }

    private TstNode tstRoot;

    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        for (String product : products) {
            tstRoot = put(tstRoot, product, 0);
        }
        List<List<String>> ans = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            prefix.append(searchWord.charAt(i));
            List<String> list = new ArrayList<>();
            TstNode node = get(tstRoot, prefix.toString(), 0);
            if (node == null) {
                ans.add(list);
                continue;
            } else if (node.isVal) {
                list.add(prefix.toString());
            }
            keysWithPrefix(node.mid, prefix, list);
            Collections.sort(list);
            ans.add(list.subList(0, list.size()));
        }
        return ans;
    }

    private void keysWithPrefix(TstNode node, StringBuilder pre, List<String> keys) {
        if (node == null) {
            return;
        }
        if (node.isVal) {
            keys.add(pre.toString() + node.chr);
        }
        keysWithPrefix(node.left, pre, keys);
        keysWithPrefix(node.mid, pre.append(node.chr), keys);
        keysWithPrefix(node.right, pre.deleteCharAt(pre.length() - 1), keys);
    }

    private TstNode get(TstNode node, String key, int index) {
        if (node == null) {
            return node;
        }
        char chr = key.charAt(index);
        if (chr < node.chr) {
            return get(node.left, key, index);
        } else if (chr > node.chr) {
            return get(node.right, key, index);
        } else if (index < key.length() - 1){
            return get(node.mid, key, index + 1);
        }
        return node;
    }

    private TstNode put(TstNode node, String key, int index) {
        char chr = key.charAt(index);
        if (node == null) {
            node = new TstNode();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = put(node.left, key, index);
        } else if (chr > node.chr) {
            node.right = put(node.right, key, index);
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, index + 1);
        } else {
            node.isVal = true;
        }
        return node;
    }

    private static class TstNode {

        private boolean isVal;

        private char chr;

        private TstNode left, mid, right;
    }

    private Node root;

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String product : products) {
            root = put(root, product, 0);
        }
        List<List<String>> ans = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            prefix.append(searchWord.charAt(i));
            List<String> list = new ArrayList<>();
            keysWithPrefix(get(root, prefix.toString(), 0), prefix.toString(), list);
            ans.add(list);
        }
        return ans;
    }

    private void keysWithPrefix(Node node, String pre, List<String> keys) {
        if (node == null || keys.size() >= 3) {
            return;
        }
        if (node.isVal) {
            keys.add(pre);
        }
        for (int i = 0; i < 26; i++) {
            char chr = (char) (i + 'a');
            keysWithPrefix(node.next[i], pre + chr, keys);
        }
    }

    private Node get(Node node, String key, int index) {
        if (node == null || index == key.length()) {
            return node;
        }
        char chr = key.charAt(index);
        return get(node.next[chr - 'a'], key, index + 1);
    }

    private Node put(Node node, String key, int index) {
        if (node == null) {
            node = new Node();
        }
        if (index == key.length()) {
            node.isVal = true;
            return node;
        }
        char chr = key.charAt(index);
        node.next[chr - 'a'] = put(node.next[chr - 'a'], key, index + 1);
        return node;
    }

    private static class Node {

        private boolean isVal;

        private Node[] next = new Node[26];
    }
}
