package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_2_Tries.exercises;

import com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_2_Tries.StTemplate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/4 上午10:50
 * TODO
 */
public class TstTry<Value> implements StTemplate<Value> {
    private int size;
    private TstNode<Value> root;

    @Override
    public void delete(String key) {

    }

    @Override
    public String longestPrefixOf(String str) {
        if (root == null) {
            return null;
        }
        TstNode<Value> node = root;
        int len = 0;
        int index = 0;
        while (node != null) {
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
        Queue<String> queue = new LinkedList<>();
        collect(root, pattern, 0, new StringBuilder(), queue);
        return queue;
    }

    private void collect(TstNode<Value> node, String pattern, int index, StringBuilder pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        char chr = pattern.charAt(index);
        if (chr == '.' || chr < node.chr) {
            collect(node, pattern, index, pre, queue);
        }
        if (chr == '.' || chr == node.chr) {
            if (pattern.length() - 1 == index && node.val != null) {
                queue.offer(pre.toString());
            }
            collect(node.mid, pattern, index + 1, pre, queue);
            pre.deleteCharAt(pre.length() - 1);
        }
        if (chr == '.' || chr > node.chr) {
            collect(node.right, pattern, index, pre, queue);
        }

    }

    @Override
    public Queue<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        TstNode<Value> node = get(root, pre, 0);
        if (node == null) {
            return queue;
        }
        if (node.val != null) {
            queue.offer(pre);
        }
        collect(node, new StringBuilder(pre), 0, queue);
        return queue;
    }

    private void collect(TstNode<Value> node, StringBuilder pre, int index, Queue<String> queue) {
        if (node == null) {
            return;
        }
        collect(node.left, pre, index, queue);
        if (node.val != null) {
            queue.offer(pre.toString());
        }
        collect(node.mid, pre.append(node.chr), index + 1, queue);
        collect(node.right, pre.deleteCharAt(pre.length() - 1), index, queue);
    }

    @Override
    public Value get(String key) {
        TstNode<Value> res = get(root, key, 0);
        return res == null ? null : res.val;
    }

    private TstNode<Value> get(TstNode<Value> node, String key, int index) {
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
        }
        return node;
    }

    @Override
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private TstNode<Value> put(TstNode<Value> node, String key, Value value, int index) {
        char chr = key.charAt(index);
        if (node == null) {
            node = new TstNode<>();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = put(node.left, key, value, index);
        } else if (chr > node.chr) {
            node.right = put(node.right, key, value, index);
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, value, index + 1);
        } else {
            if (node.val == null) {
                size++;
            }
            node.val = value;
        }
        return node;
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    static class TstNode<Value> {
        Value val;
        char chr;
        TstNode<Value> left, mid, right;
    }
}
