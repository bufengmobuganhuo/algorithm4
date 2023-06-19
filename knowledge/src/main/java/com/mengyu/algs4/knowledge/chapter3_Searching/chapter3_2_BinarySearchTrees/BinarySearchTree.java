package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees;

import com.mengyu.algs4.utils.BinarySearchTreeTemplate;
import com.mengyu.algs4.utils.TreeNode;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/3/16 10:55
 * 二叉查找树
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTreeTemplate<Key, Value> {
    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree<>();
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
            System.out.print(key + " ");
        }
        System.out.println(binarySearchTree.randomKey());
    }

    public TreeNode<Key, Value> root;

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
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getRecursive(root, key);
    }

    /**
     * 递归版本查找
     *
     * @param root
     * @param key
     * @return
     */
    private TreeNode<Key, Value> getRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        //如果根节点的键>被查找键，则向左子树查找
        if (compareRes > 0) {
            return getRecursive(root.left, key);
        } else if (compareRes < 0) {
            //如果根节点的键<被查找键，则向右子树查找
            return getRecursive(root.right, key);
        } else {
            //相等，则查找命中
            return root;
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = putRecursive(root, key, value);
    }

    @Override
    public TreeNode<Key, Value> min() {
        if (root == null) {
            return null;
        }
        return minRecursive(root);
    }

    private TreeNode<Key, Value> minRecursive(TreeNode<Key, Value> root) {
        //如果给定节点的左子树=null，则最小值即为该节点
        if (root.left == null) {
            return root;
        }
        //否则向该节点的左子树查找
        return minRecursive(root.left);
    }

    @Override
    public TreeNode<Key, Value> max() {
        if (root == null) {
            return null;
        }
        return maxRecursive(root);
    }

    @Override
    public TreeNode<Key, Value> floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return floorRecursive(root, key);
    }

    @Override
    public TreeNode<Key, Value> ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return ceilingRecursive(root, key);
    }

    @Override
    public TreeNode<Key, Value> select(int rank) {
        if (rank < 0) {
            return null;
        }
        return selectRecursive(root, rank);
    }

    private TreeNode<Key, Value> selectRecursive(TreeNode<Key, Value> root, int rank) {
        if (root == null) {
            return null;
        }
        int rootRank = size(root.left);
        //如果root的排名=rank（排名从0开始），则root即为所找节点
        if (rootRank == rank) {
            return root;
        } else if (rootRank > rank) {
            //如果root排名>rank，则向root的左子树中查找
            return selectRecursive(root.left, rank);
        } else {
            //如果root排名<rank，则向root的右子树中查找排名为rank-rootRank-1的节点
            return selectRecursive(root.right, rank - rootRank - 1);
        }
    }

    @Override
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rankRecursive(root, key);
    }

    @Override
    public void deleteMin() {
        if (root == null) {
            throw new NullPointerException();
        }
        root = deleteMinRecursive(root);
    }

    private TreeNode<Key, Value> deleteMinRecursive(TreeNode<Key, Value> root) {
        if (root.left == null) {
            //如果一直向左找，直到无法再向左，那么在递归的第N层返回这个节点（被删除结点）的右子节点以替换被删除节点
            return root.right;
        }
        //将下一层递归（第N层返回的root.left.right）返回的值赋值给root.left
        root.left = deleteMinRecursive(root.left);
        //更新
        root.nodeCount = size(root.left) + size(root.right) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    @Override
    public void deleteMax() {
        if (root == null) {
            throw new NullPointerException();
        }
        root = deleteMaxRecursive(root);
    }

    @Override
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = delete(root, key);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) {
            throw new IllegalArgumentException();
        }
        Queue<Key> resQueue = new ArrayDeque<>();
        keys(root, resQueue, lo, hi);
        return resQueue;
    }

    @Override
    public int height() {
        //使用一个变量保存高度
        //return height(root);
        return heightRecursive(root);
    }

    @Override
    public double avgCompares() {
        int depthSum = depthSum(root);
        return depthSum / root.nodeCount + 1;
    }

    @Override
    public Key randomKey() {
        Random random = new Random();
        int randomRank = random.nextInt(size());
        return select(randomRank).key;
    }

    private int depthSum(TreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return depthSum(node.left) + depthSum(node.right) + node.nodeCount - 1;
    }

    //递归版本
    private int heightRecursive(TreeNode<Key, Value> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightRecursive(node.left) + 1;
        int rightHeight = heightRecursive(node.right) + 1;
        return Math.max(leftHeight, rightHeight);
    }


    private int height(TreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private void keys(TreeNode<Key, Value> node, Queue<Key> resQueue, Key lo, Key hi) {
        if (node == null) {
            return;
        }
        int loCompareNode = lo.compareTo(node.key);
        int hiCompareNode = hi.compareTo(node.key);
        //如果lo<node.key，说明node的左子树中也有符合条件的
        if (loCompareNode < 0) {
            keys(node.left, resQueue, lo, hi);
        }
        //如果node符合条件，则加入队列
        if (loCompareNode <= 0 && hiCompareNode >= 0) {
            resQueue.add(node.key);
        }
        //如果hi>node.key，则说明node的右子树中也有符合条件的
        if (hiCompareNode > 0) {
            keys(node.right, resQueue, lo, hi);
        }
    }


    private TreeNode<Key, Value> delete(TreeNode<Key, Value> node, Key key) {
        if (node == null) {
            return null;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes < 0) {
            //查找被删除键，如果root.key<key，则向右删除
            node.right = delete(node.right, key);
        } else if (compareRes > 0) {
            //如果root.key>key,则向左删除
            node.left = delete(node.left, key);
            //找到被删除节点node
        } else {
            //被删除节点只有单个子树的情况
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // 此处发生的替换是为了后面继续引用该node
            TreeNode<Key, Value> deletedNode = node;
            //从被删除节点的右子树中找到后继节点（右子树中的最小节点）,
            node = minRecursive(deletedNode.right);
            //从被删除节点的右子树中删除上述后继节点，会返回node的右子节点，
            // 将它赋值给minNode.right，完成了被删除节点右子树部分的替换
            node.right = deleteMinRecursive(deletedNode.right);
            node.left = deletedNode.left;
        }
        node.nodeCount = size(node.left) + size(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }


    private TreeNode<Key, Value> deleteMaxRecursive(TreeNode<Key, Value> root) {
        if (root.right == null) {
            //一直向右找，直到无法再向右时，在第N层递归返回该节点（被删除结点）的左子节点
            return root.left;
        }
        // 这是第N-1层
        root.right = deleteMaxRecursive(root.right);
        root.nodeCount = size(root.left) + size(root.right) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }


    private int rankRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return -1;
        }
        int compareRes = root.key.compareTo(key);
        //如果root.key=key，则排名=root.left.nodeCount
        if (compareRes == 0) {
            return size(root.left);
        } else if (compareRes > 0) {
            //如果root.key>key，则向root的左子树中查找
            return rankRecursive(root.left, key);
        } else {
            //如果root.key<key，则向root的右子树中查找，最后的排名=root.left.nodeCount+1+右子树中排名
            return size(root.left) + 1 + rankRecursive(root.right, key);
        }
    }

    private TreeNode<Key, Value> ceilingRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        //如果root.key=key，则>=key的最小键，就是root.key
        if (compareRes == 0) {
            return root;
        } else if (compareRes < 0) {
            //如果root.key<key,则>=key的最小键在root的右子树中
            return ceilingRecursive(root.right, key);
        }
        //如果root.key>=key,则除非root的左子树中存在一个键>=key，否则>=key的最小键就是root
        TreeNode<Key, Value> leftRes = ceilingRecursive(root.left, key);
        return leftRes == null ? root : leftRes;
    }

    private TreeNode<Key, Value> floorRecursive(TreeNode<Key, Value> root, Key key) {
        if (root == null) {
            return null;
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            //如果key==root.key，则是<=key的最大键
            return root;
        } else if (compareRes > 0) {
            //如果root.key>key,则<=key的最大键在root节点的左子树中
            return floorRecursive(root.left, key);
        }
        //如果root.key<key,则只有在root节点的右子树中存在键<=key时，才是结果，
        // 否则<=key的最大键就是root.key
        TreeNode<Key, Value> rightRes = floorRecursive(root.right, key);
        return rightRes == null ? root : rightRes;
    }

    private TreeNode<Key, Value> maxRecursive(TreeNode<Key, Value> root) {
        if (root.right == null) {
            return root;
        }
        return maxRecursive(root.right);
    }

    private TreeNode<Key, Value> putRecursive(TreeNode<Key, Value> root, Key key, Value value) {
        //如果“插入起点节点”=null，
        // 会在递归的第N层返回一个新节点
        if (root == null) {
            return new TreeNode<Key, Value>(key, value, 1, 0);
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes > 0) {
            //如果“插入起点节点”的值>被插入键，则向左子树插入
            //此处的赋值会发生在第N-1层递归，则第N层递归返回的新节点会被赋值给root.left
            root.left = putRecursive(root.left, key, value);
        } else if (compareRes < 0) {
            //如果“插入起点节点”<被插入键，则向右子树插入
            root.right = putRecursive(root.right, key, value);
        } else {
            //否则更新该节点的值
            root.value = value;
        }
        //插入结束后，需要更新每个节点中保存的节点数量值
        root.nodeCount = size(root.left) + size(root.right) + 1;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }
}
