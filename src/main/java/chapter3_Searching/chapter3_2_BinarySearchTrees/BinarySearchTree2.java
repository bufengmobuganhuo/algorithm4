package chapter3_Searching.chapter3_2_BinarySearchTrees;

import netscape.security.UserTarget;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/12 8:38 上午
 * TODO
 */
public class BinarySearchTree2<Key extends Comparable<Key>, Value> implements BinarySearchTreeTemplate<Key, Value> {
    public static void main(String[] args) {
        BinarySearchTree2<Integer, Integer> binarySearchTree = new BinarySearchTree2<>();
        binarySearchTree.put(100, 100);
        binarySearchTree.put(80, 80);
        binarySearchTree.put(120, 120);
        binarySearchTree.put(90, 90);
        binarySearchTree.put(110, 110);
        binarySearchTree.put(125, 125);
        binarySearchTree.put(95, 95);
        System.out.println(binarySearchTree.min().key);
        System.out.println(binarySearchTree.max().key);
        System.out.println(binarySearchTree.rank(110));
        System.out.println(binarySearchTree.select(4).key);
        System.out.println(binarySearchTree.height());
        System.out.println(binarySearchTree.avgCompares());
        binarySearchTree.delete(100);
        binarySearchTree.deleteMax();
        binarySearchTree.deleteMin();
        System.out.println(binarySearchTree.get(125));
        for (Integer key : binarySearchTree.keys(70, 130)) {
            System.out.println(key);
        }
    }

    private TreeNode<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return node.nodeCount;
    }


    @Override
    public TreeNode<Key, Value> get(Key key) {
        if (root == null) {
            return null;
        }

        return getIterable(key);
    }

    private TreeNode<Key, Value> getRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            return root;
        } else if (compareRes < 0) {
            return getRecursive(root.right, key);
        } else {
            return getRecursive(root.left, key);
        }
    }

    private TreeNode<Key, Value> getIterable(Key key) {
        TreeNode<Key, Value> tmp = root;
        while (tmp != null) {
            int compareRes = tmp.key.compareTo(key);
            if (compareRes == 0) {
                return tmp;
            } else if (compareRes < 0) {
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        // root = putRecursive(root, key, value);
        putIterable(key,value);
    }

    private TreeNode<Key, Value> putRecursive(TreeNode<Key, Value> root, Key key, Value value) {
        if (root == null) {
            return new TreeNode<>(key, value, 1);
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            root.value = value;
            return root;
        } else if (compareRes < 0) {
            root.right = putRecursive(root.right, key, value);
        } else {
            root.left = putRecursive(root.left, key, value);
        }
        root.nodeCount = size(root.left) + size(root.right) + 1;
        return root;
    }

    private void putIterable(Key key, Value value) {
        if (root == null) {
            root = new TreeNode<>(key, value, 1);
            return;
        }
        // 保存遍历路径上的节点
        Stack<TreeNode<Key, Value>> path = new Stack<>();
        TreeNode<Key, Value> tmp = root;
        while (tmp != null) {
            path.push(tmp);
            int compareRes = tmp.key.compareTo(key);
            if (compareRes == 0) {
                tmp.value = value;
                return;
            } else if (compareRes < 0) {
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        // 栈顶元素就是新插入节点的父节点
        TreeNode<Key, Value> father = path.peek();
        TreeNode<Key, Value> child = new TreeNode<>(key, value, 1);
        if (father.key.compareTo(child.key) < 0) {
            father.right = child;
        } else {
            father.left = child;
        }
        while (!path.isEmpty()) {
            TreeNode<Key, Value> node = path.pop();
            node.nodeCount++;
        }
    }

    @Override
    public TreeNode<Key, Value> min() {
        if (root == null) {
            return null;
        }
        return minIterable();
    }

    private TreeNode<Key, Value> minRecursive(TreeNode<Key, Value> root) {
        if (root.left == null) {
            return root;
        }
        return minRecursive(root.left);
    }

    private TreeNode<Key, Value> minIterable() {
        TreeNode<Key, Value> tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    @Override
    public TreeNode<Key, Value> max() {
        if (root == null) {
            return null;
        }
        return maxIterable();
    }

    private TreeNode<Key, Value> maxRecursive(TreeNode<Key, Value> root) {
        if (root.right == null) {
            return root;
        }
        return maxRecursive(root.right);
    }

    private TreeNode<Key, Value> maxIterable() {
        TreeNode<Key, Value> tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    @Override
    public TreeNode<Key, Value> floor(Key key) {
        return floorIterable(key);
    }

    private TreeNode<Key, Value> floorRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            return root;
        } else if (compareRes > 0) {
            return floorRecursive(root.left, key);
        } else {
            TreeNode<Key, Value> node = floorRecursive(root.right, key);
            return node == null ? root : node;
        }
    }

    private TreeNode<Key, Value> floorIterable(Key key) {
        TreeNode<Key, Value> floorNode = null;
        TreeNode<Key, Value> tmp = root;
        while (tmp != null) {
            int compareRes = tmp.key.compareTo(key);
            if (compareRes == 0) {
                return tmp;
            } else if (compareRes < 0) {
                floorNode = tmp;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return floorNode;
    }

    @Override
    public TreeNode<Key, Value> ceiling(Key key) {
        return ceilingIterable(key);
    }

    private TreeNode<Key, Value> ceilingRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            return root;
        } else if (compareRes < 0) {
            return ceilingRecursive(root.right, key);
        } else {
            TreeNode<Key, Value> node = ceilingRecursive(root.left, key);
            return node == null ? root : node;
        }
    }

    private TreeNode<Key, Value> ceilingIterable(Key key) {
        TreeNode<Key, Value> ceilingNode = null;
        TreeNode<Key, Value> tmp = root;
        while (tmp != null) {
            int compareRes = tmp.key.compareTo(key);
            if (compareRes == 0) {
                return tmp;
            } else if (compareRes < 0) {
                tmp = tmp.right;
            } else {
                ceilingNode = tmp;
                tmp = tmp.left;
            }
        }
        return ceilingNode;
    }

    @Override
    public TreeNode<Key, Value> select(int rank) {
        return selectIterable(rank);
    }

    private TreeNode<Key, Value> selectRecursive(TreeNode<Key, Value> root, int rank) {
        if (root == null) {
            return null;
        }
        int count = size(root.left);
        if (count == rank) {
            return root;
        } else if (count < rank) {
            return selectRecursive(root.right, rank - count - 1);
        } else {
            return selectRecursive(root.left, rank);
        }
    }

    private TreeNode<Key, Value> selectIterable(int rank) {
        TreeNode<Key, Value> tmp = root;
        while (tmp != null) {
            int count = size(tmp.left);
            if (count == rank) {
                return tmp;
            } else if (count < rank) {
                rank = rank - count - 1;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return null;
    }

    @Override
    public int rank(Key key) {
        return rankIterable(key);
    }

    private int rankRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return -1;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            return size(root.left);
        } else if (compareRes < 0) {
            return rankRecursive(root.right, key) + size(root.left) + 1;
        } else {
            return rankRecursive(root.left, key);
        }
    }

    private int rankIterable(Key key) {
        TreeNode<Key, Value> tmp = root;
        int rank = 0;
        while (tmp != null) {
            int compareRes = tmp.key.compareTo(key);
            if (compareRes == 0) {
                return rank + size(tmp.left);
            } else if (compareRes < 0) {
                rank += size(tmp.left) + 1;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return -1;
    }

    @Override
    public void deleteMin() {
        if (root == null) {
            return;
        }
        deleteMinIterable();
    }

    private TreeNode<Key, Value> deleteMinRecursive(TreeNode<Key, Value> root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMinRecursive(root.left);
        root.nodeCount = size(root.left) + size(root.right) + 1;
        return root;
    }

    private void deleteMinIterable() {
        TreeNode<Key, Value> tmp = root;
        Stack<TreeNode<Key, Value>> path = new Stack<>();
        while (tmp.left != null) {
            path.push(tmp);
            tmp = tmp.left;
        }
        TreeNode<Key, Value> father = path.peek();
        father.left = tmp.right;
        while (!path.isEmpty()) {
            TreeNode<Key, Value> node = path.pop();
            node.nodeCount--;
        }
    }

    @Override
    public void deleteMax() {
        if (root == null) {
            return;
        }
        deleteMaxIterable();
    }

    private TreeNode<Key, Value> deleteMaxRecursive(TreeNode<Key, Value> root) {
        if (root.right == null) {
            return root.left;
        }
        root.right = deleteMaxRecursive(root.right);
        root.nodeCount = size(root.left) + size(root.right) + 1;
        return root;
    }

    private void deleteMaxIterable() {
        TreeNode<Key, Value> tmp = root;
        Stack<TreeNode<Key, Value>> path = new Stack<>();
        while (tmp.right != null) {
            path.push(tmp);
            tmp = tmp.right;
        }
        TreeNode<Key, Value> father = path.peek();
        father.right = tmp.left;
        while (!path.isEmpty()) {
            path.pop().nodeCount--;
        }
    }

    @Override
    public void delete(Key key) {
        deleteRecursive(root, key);
    }

    private TreeNode<Key, Value> deleteRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes < 0) {
            root.left = deleteRecursive(root.left, key);
        } else if (compareRes > 0) {
            root.right = deleteRecursive(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode<Key, Value> deletedNode = root;
            root = minRecursive(deletedNode.right);
            root.right = deleteMinRecursive(deletedNode.right);
            root.left = deletedNode.left;
        }
        root.nodeCount = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> keys = new LinkedList<>();
        keysRecursive(keys,lo,hi,root);
        return keys;
    }

    private void keysRecursive(Queue<Key> que, Key lo, Key hi, TreeNode<Key, Value> root) {
        if (root == null) {
            return;
        }
        int loCom = lo.compareTo(root.key);
        int hiCom = hi.compareTo(root.key);
        if (loCom < 0) {
            keysRecursive(que, lo, hi, root.left);
        }
        if (hiCom > 0) {
            keysRecursive(que, lo, hi, root.right);
        }
        if (loCom <= 0 && hiCom >= 0) {
            que.offer(root.key);
        }
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public double avgCompares() {
        return 0;
    }

    @Override
    public Key randomKey() {
        return null;
    }
}
