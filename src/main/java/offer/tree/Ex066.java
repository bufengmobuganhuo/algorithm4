package offer.tree;

/**
 * @author yu zhang
 */
public class Ex066 {
    public static void main(String[] args) {
        Ex066 ex066 = new Ex066();
        ex066.insert("apple", 3);
        System.out.println(ex066.sum("ap"));
        ex066.insert("app", 2);
        System.out.println(ex066.sum("ap"));
    }

    private Node root;

    private int prefixSum;

    public Ex066() {
        prefixSum = 0;
    }

    public void insert(String key, int val) {
        root = insert(root, key, 0, val);
    }

    private Node insert(Node node, String key, int idx, int val) {
        if (node == null) {
            node = new Node();
        }
        if (idx == key.length()) {
            node.val = val;
            return node;
        }
        char chr = key.charAt(idx);
        node.children[chr - 'a'] = insert(node.children[chr - 'a'], key, idx + 1, val);
        return node;
    }

    public int sum(String prefix) {
        prefixSum = 0;
        Node node = get(root, prefix, 0);
        collect(node);
        return prefixSum;
    }

    private void collect(Node node) {
        if (node == null) {
            return;
        }
        prefixSum += node.val;
        for (int i = 0; i < 26; i++) {
            collect(node.children[i]);
        }
    }

    private Node get(Node node, String key, int idx) {
        if (node == null) {
            return null;
        }
        if (idx == key.length()) {
            return node;
        }
        return get(node.children[key.charAt(idx) - 'a'], key, idx + 1);
    }

    private static class Node {
        Node[] children;
        int val;

        public Node() {
            children = new Node[26];
        }
    }
}
