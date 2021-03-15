package chapter5_Strings.chapter5_2_Tries;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/6/4 10:16
 * 三向单词查找树
 */
public class TST<Value> implements StTemplate<Value> {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("she", 0);
        map.put("sells", 1);
        map.put("sea", 2);
        map.put("shells", 3);
        TST<Integer> tst = new TST<>();
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

    private TSTNode root;
    private int size;

    @Override
    public void delete(String key) {
    }

    @Override
    public String longestPrefixOf(String str) {
        if (isEmpty()) {
            return null;
        }
        TSTNode node = root;
        int length = 0;
        int i = 0;
        while (node != null && i < str.length()) {
            char chr = str.charAt(i);
            if (chr < node.chr) {
                node = node.left;
            } else if (chr > node.chr) {
                node = node.right;
            } else {
                //如果相等，则是匹配到了，长度+1
                i++;
                //如果是键，则更新length
                if (node.val != null) {
                    length = i;
                }
                node = node.mid;
            }
        }
        return str.substring(0, length);
    }

    /**
     * @param pattern
     * @return 通配符匹配
     */
    @Override
    public Queue<String> keysThatMatch(String pattern) {
        if (isEmpty()) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(TSTNode node, StringBuilder pre, int i, String pattern, Queue<String> queue) {
        if (node == null) {
            return;
        }
        char nextPatChr = pattern.charAt(i);
        //是否和左分支的匹配
        if (nextPatChr == '.' || nextPatChr < node.chr) {
            collect(node.left, pre, i, pattern, queue);
        }
        //如果和当前的节点匹配
        if (nextPatChr == '.' || nextPatChr == node.chr) {
            //长度匹配，并且是一个键
            if (i == pattern.length() - 1 && node.val != null) {
                queue.offer(pre.toString() + node.chr);
            }
            //和当前节点匹配，但是当前节点不是键，向中间找
            if (i < pattern.length() - 1) {
                collect(node.mid, pre.append(node.chr), i + 1, pattern, queue);
                //为了后面查找右分支做准备
                pre.deleteCharAt(pre.length() - 1);
            }
        }
        if (nextPatChr == '.' || nextPatChr > node.chr) {
            collect(node.right, pre, i, pattern, queue);
        }
    }

    /**
     * @return 获取所有键
     */
    @Override
    public Queue<String> keys() {
        if (isEmpty()) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    /**
     * @param pre
     * @return 以pre开头的所有键
     */
    @Override
    public Queue<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        //先找到以pre开头的分支（不一定是键）
        TSTNode node = get(root, pre, 0);
        if (node == null) {
            return queue;
        } else if (node.val != null) {
            queue.offer(pre);
        }
        collect(node.mid, new StringBuilder(pre), queue);
        return queue;
    }

    private void collect(TSTNode node, StringBuilder pre, Queue<String> queue) {
        if (node == null) {
            return;
        }
        collect(node.left, pre, queue);
        if (node.val != null) {
            queue.offer(pre.toString() + node.chr);
        }
        //只有中间的分支才是以node.chr为一部分内容的键
        //才需要append，其他的不需要
        collect(node.mid, pre.append(node.chr), queue);
        collect(node.right, pre.deleteCharAt(pre.length() - 1), queue);
    }

    @Override
    public Value get(String key) {
        TSTNode node = get(root, key, 0);
        return node == null ? null : (Value) node.val;
    }

    private TSTNode get(TSTNode node, String key, int index) {
        if (node == null) {
            return null;
        }
        char chr = key.charAt(index);
        if (chr < node.chr) {
            return get(node.left, key, index);
        } else if (chr > node.chr) {
            return get(node.right, key, index);
            //防止查找未命中，导致的数据越界问题
        } else if (index < key.length() - 1) {
            return get(node.mid, key, index + 1);
        }
        //查找命中
        return node;
    }

    @Override
    public void put(String key, Value val) {
        if (!containsKey(key)) {
            size++;
        }
        root = put(root, key, val, 0);
    }

    private TSTNode put(TSTNode node, String key, Value value, int index) {
        char chr = key.charAt(index);
        if (node == null) {
            node = new TSTNode();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = put(node.left, key, value, index);
        } else if (chr > node.chr) {
            node.right = put(node.right, key, value, index);
            //防止查找未命中，导致的数据越界
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, value, index + 1);
            //index=key.length()-1，查找命中
        } else {
            node.val = value;
        }
        return node;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public static class TSTNode {
        public Object val;
        public TSTNode left, mid, right;
        public char chr;
    }
}
