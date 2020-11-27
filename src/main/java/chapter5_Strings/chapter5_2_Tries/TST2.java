package chapter5_Strings.chapter5_2_Tries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/26 上午9:03
 * TODO
 */
public class TST2 implements StTemplate<Object> {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        TST2 tst = new TST2();
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
    private TstNode root;
    private int size;

    @Override
    public void delete(String key) {

    }

    @Override
    public String longestPrefixOf(String str) {
        if (root == null) {
            return null;
        }
        int idx = 0, length = 0;
        TstNode node = root;
        while (idx < str.length() && node != null) {
            char chr = str.charAt(idx);
            if (chr < node.chr) {
                node = node.left;
            } else if (chr > node.chr) {
                node = node.right;
            } else {
                idx++;
                if (node.value != null) {
                    length = idx;
                }
                node = node.mid;
            }
        }
        return str.substring(0, length);
    }


    @Override
    public Queue<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), pattern, 0, queue);
        return queue;
    }

    private void collect(TstNode node, StringBuilder pre, String pattern, int idx, Queue<String> queue) {
        if (node == null) {
            return;
        }
        char chr = pattern.charAt(idx);
        if (chr == '.' || chr < node.chr) {
            collect(node.left, pre, pattern, idx, queue);
        }
        if (chr == '.' || chr == node.chr) {
            if (idx == pattern.length() - 1 && node.value != null) {
                queue.offer(pre.toString());
            }
            if (idx < pattern.length() - 1) {
                collect(node.mid, pre.append(node.chr), pattern, idx + 1, queue);
                pre.deleteCharAt(pre.length() - 1);
            }
        }
        if (chr == '.' || chr > node.chr) {
            collect(node.right, pre, pattern, idx, queue);
        }
    }

    @Override
    public Queue<String> keys() {
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        TstNode node = get(root, pre, 0);
        if (node == null) {
            return queue;
        } else if (node.value != null) {
            queue.offer(pre);
        }
        collect(root, new StringBuilder(pre), queue);
        return queue;
    }

    private void collect(TstNode node, StringBuilder pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        collect(node.left, pre, queue);
        if (node.value != null) {
            queue.offer(pre.toString());
        }
        collect(node.mid, pre.append(node.chr), queue);
        collect(node.right, pre.deleteCharAt(pre.length() - 1), queue);
    }

    @Override
    public Object get(String key) {
        TstNode node = get(root, key, 0);
        return node == null ? null : node.value;
    }

    public TstNode get(TstNode node, String key, int idx) {
        if (node == null) {
            return null;
        }
        char chr = key.charAt(idx);
        if (node.chr > chr) {
            return get(node.left, key, idx);
        } else if (node.chr < chr) {
            return get(node.right, key, idx);
        } else if (idx < key.length() - 1) {
            return get(node.mid, key, idx + 1);
        }
        return node;
    }

    @Override
    public void put(String key, Object o) {
        if (!containsKey(key)) {
            size++;
        }
        root = put(root, key, o, 0);
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    private TstNode put(TstNode node, String key, Object value, int idx) {
        char chr = key.charAt(idx);
        if (node == null) {
            node = new TstNode();
            node.chr = chr;
        }
        if (chr > node.chr) {
            node.right = put(node.right, key, value, idx);
        } else if (chr < node.chr) {
            node.left = put(node.left, key, value, idx);
        } else if (idx < key.length() - 1) {
            node.mid = put(node.mid, key, value, idx + 1);
        } else {
            node.value = value;
        }
        return node;
    }

    static class TstNode {
        char chr;
        Object value;
        TstNode left;
        TstNode mid;
        TstNode right;
    }
}
