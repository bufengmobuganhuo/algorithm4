package chapter3_Searching.chapter3_2_BinarySearchTrees;

import org.omg.CORBA.INTERNAL;

import java.util.Random;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/2/8 上午9:30
 * TODO
 */
public class BinarySearchTree3 implements BinarySearchTreeTemplate<Integer, Integer> {
    public static void main(String[] args) {
        BinarySearchTree3 binarySearchTree = new BinarySearchTree3();
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
        System.out.println(binarySearchTree.height());
        for (Integer key : binarySearchTree.keys(70, 130)) {
            System.out.println(key);
        }
        System.out.println(binarySearchTree.randomKey());
    }
    private TreeNode<Integer, Integer> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode<Integer, Integer> node) {
        if (node == null) {
            return 0;
        }
        return node.nodeCount;
    }

    @Override
    public TreeNode<Integer, Integer> get(Integer integer) {
        return getIterable(root, integer);
    }

    private TreeNode<Integer, Integer> getRecursive(TreeNode<Integer, Integer> node, Integer key) {
        if (node == null) {
            return null;
        }
        int comRes = key.compareTo(node.value);
        if (comRes == 0) {
            return node;
        } else if (comRes < 0) {
            return getRecursive(node.left, key);
        } else {
            return getRecursive(node.right, key);
        }
    }

    private TreeNode<Integer, Integer> getIterable(TreeNode<Integer, Integer> node, Integer key) {
        while (node != null) {
            int comRes = key.compareTo(node.key);
            if (comRes == 0) {
                return node;
            } else if (comRes > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    @Override
    public void put(Integer integer, Integer integer2) {
        root = putRecursive(root, integer, integer2);
    }

    private void putIterable(Integer key, Integer value) {
        if (root == null) {
            root = new TreeNode<>(key, value, 1);
        }
        Stack<TreeNode<Integer, Integer>> path = new Stack<>();
        TreeNode<Integer, Integer> tmp = root;
        while (tmp != null) {
            path.push(tmp);
            int comRes = key.compareTo(tmp.key);
            if (comRes == 0) {
                tmp.value = value;
                return;
            } else if (comRes < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        tmp = new TreeNode<>(key, value, 1);
        TreeNode<Integer, Integer> father = path.peek();
        if (father.key < value) {
            father.right = tmp;
        } else {
            father.left = tmp;
        }
        while (!path.isEmpty()) {
            TreeNode<Integer, Integer> node = path.pop();
            node.nodeCount++;
        }
    }

    private TreeNode<Integer, Integer> putRecursive(TreeNode<Integer, Integer> node, Integer key, Integer value) {
        if (node == null) {
            return new TreeNode<>(key, value, 1);
        }
        int comRes = key.compareTo(node.key);
        if (comRes == 0) {
            node.value = value;
        } else if (comRes < 0) {
            node.left = putRecursive(node.left, key, value);
        } else {
            node.right = putRecursive(node.right, key, value);
        }
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public TreeNode<Integer, Integer> min() {
        if (root == null) {
            return null;
        }
        return minRecursive(root);
    }

    private TreeNode<Integer, Integer> minIterable() {
        if (root == null) {
            return null;
        }
        TreeNode<Integer, Integer> tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private TreeNode<Integer, Integer> minRecursive(TreeNode<Integer, Integer> node) {
        if (node.left == null) {
            return node;
        }
        return minRecursive(node.left);
    }

    @Override
    public TreeNode<Integer, Integer> max() {
        if (root == null) {
            return null;
        }
        return maxRecursive(root);
    }

    private TreeNode<Integer, Integer> maxIterable() {
        TreeNode<Integer, Integer> tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    private TreeNode<Integer, Integer> maxRecursive(TreeNode<Integer, Integer> node) {
        if (node.right == null) {
            return node;
        }
        return maxRecursive(node.right);
    }

    @Override
    public TreeNode<Integer, Integer> floor(Integer integer) {
        return floorRecursive(root, integer);
    }

    private TreeNode<Integer, Integer> floorIterable(Integer key) {
        TreeNode<Integer, Integer> tmp = root;
        TreeNode<Integer, Integer> floorNode = null;
        while (tmp != null) {
            int comRes = key.compareTo(tmp.key);
            if (comRes == 0) {
                return tmp;
            } else if (comRes > 0) {
                floorNode = tmp;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return floorNode;
    }

    private TreeNode<Integer, Integer> floorRecursive(TreeNode<Integer, Integer> node, Integer key) {
        if (node == null) {
            return null;
        }
        int comRes = key.compareTo(node.key);
        if (comRes == 0) {
            return node;
        } else if (comRes < 0) {
            return floorRecursive(node.left, key);
        } else {
            TreeNode<Integer, Integer> tmp = floorRecursive(node.right, key);
            if (tmp != null) {
                return tmp;
            }
            return node;
        }
    }

    @Override
    public TreeNode<Integer, Integer> ceiling(Integer integer) {
        return ceilingRecursive(root, integer);
    }

    private TreeNode<Integer, Integer> ceilingIterable(Integer key) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer, Integer> tmp = root;
        TreeNode<Integer, Integer> ceilingNode = null;
        while (tmp != null) {
            int comRes = key.compareTo(tmp.key);
            if (comRes == 0) {
                return tmp;
            } else if (comRes < 0) {
                ceilingNode = tmp;
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        return ceilingNode;
    }

    private TreeNode<Integer, Integer> ceilingRecursive(TreeNode<Integer, Integer> node, Integer key) {
        if (node == null) {
            return null;
        }
        int comRes = node.key.compareTo(key);
        if (comRes == 0) {
            return node;
        } else if (comRes < 0) {
            return ceilingRecursive(node.right, key);
        }
        TreeNode<Integer, Integer> tmp = ceilingRecursive(node.left, key);
        return tmp == null ? node : tmp;
    }

    @Override
    public TreeNode<Integer, Integer> select(int rank) {
        return selectRecursive(root,rank);
    }

    private TreeNode<Integer, Integer> selectIterable(int rank) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer, Integer> tmp = root;
        int count = 0;
        while (tmp != null) {
            count = size(tmp.left);
            if (count == rank) {
                break;
            } else if (count < rank) {
                rank = rank - count - 1;
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }

        }
        return tmp;
    }

    private TreeNode<Integer, Integer> selectRecursive(TreeNode<Integer, Integer> node, int rank) {
        if (node == null) {
            return null;
        }
        int count = size(node.left);
        if (count == rank) {
            return node;
        } else if (count > rank) {
            return selectRecursive(node.left, rank);
        } else {
            return selectRecursive(node.right, rank - count - 1);
        }
    }

    @Override
    public int rank(Integer integer) {
        return rankRecursive(root, integer);
    }

    private int rankIterable(Integer key) {
        if (root == null) {
            return -1;
        }
        TreeNode<Integer, Integer> tmp = root;
        int rank = 0;
        while (tmp != null) {
            int comRes = key.compareTo(tmp.key);
            if (comRes == 0) {
                return size(tmp.left) + rank;
            } else if (comRes < 0) {
                tmp = tmp.left;
            } else {
                rank += size(tmp.left) + 1;
                tmp = tmp.right;
            }
        }
        return rank;
    }

    private int rankRecursive(TreeNode<Integer, Integer> node, Integer key) {
        if (node == null) {
            return -1;
        }
        int comRes = key.compareTo(node.key);
        if (comRes == 0) {
            return size(node.left);
        } else if (comRes < 0) {
            return rankRecursive(node.left, key);
        } else {
            return rankRecursive(node.right, key) + size(node.left) + 1;
        }
    }

    @Override
    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMinRecursive(root);
    }

    private void deleteMinIterable() {
        Stack<TreeNode<Integer, Integer>> path = new Stack<>();
        TreeNode<Integer, Integer> tmp = root;
        while (tmp.left != null) {
            path.push(tmp);
            tmp = tmp.left;
        }
        TreeNode<Integer, Integer> father = path.peek();
        father.left = tmp.right;
        while (!path.isEmpty()) {
            path.pop().nodeCount--;
        }
    }

    private TreeNode<Integer, Integer> deleteMinRecursive(TreeNode<Integer, Integer> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMinRecursive(node.left);
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        root = deleteMaxRecursive(root);
    }

    private void deleteMaxIterable() {
        TreeNode<Integer, Integer> tmp = root;
        Stack<TreeNode<Integer, Integer>> path = new Stack<>();
        while (tmp.right != null) {
            path.push(tmp);
            tmp = tmp.right;
        }
        TreeNode<Integer, Integer> father = path.peek();
        father.right = tmp.left;
        while (!path.isEmpty()) {
            path.pop().nodeCount--;
        }
    }

    private TreeNode<Integer, Integer> deleteMaxRecursive(TreeNode<Integer, Integer> node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMaxRecursive(node.right);
        return node;
    }

    @Override
    public void delete(Integer integer) {
        root = deleteRecursive(root, integer);
    }

    private TreeNode<Integer, Integer> deleteRecursive(TreeNode<Integer, Integer> node, Integer key) {
        if (node == null) {
            return null;
        }
        int comRes = key.compareTo(node.key);
        if (comRes < 0) {
            node.left = deleteRecursive(node.left, key);
        } else if (comRes > 0) {
            node.right = deleteRecursive(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            TreeNode<Integer,Integer> susNode = node;
            node = minRecursive(susNode.right);
            node.right = deleteMinRecursive(susNode.right);
            node.left = susNode.left;
        }
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Iterable<Integer> keys(Integer lo, Integer hi) {
        return null;
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
    public Integer randomKey() {
        Random random = new Random();
        int randomRank = random.nextInt(size());
        return select(randomRank).key;
    }
}
